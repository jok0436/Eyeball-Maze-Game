package model;

import java.awt.Point;
import java.util.ArrayList;

/*
 * If you move to the right or left, your eyeballs will then be pointing in a new direction 
 * When you solve one of these mazes, the program outputs a code that looks something like: Code=695
 * The go-back-one-move function takes you back a move, but you can click on it repeatedly to go back several moves.
 */
public class Maze {

	private ArrayList<ArrayList<Tile>> map;
	private ArrayList<Goal> goals = new ArrayList<Goal>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private boolean completed;

	public Maze(ArrayList<ArrayList<Tile>> newMap, ArrayList<Goal> newGoals, ArrayList<Player> newPlayers) {
		this.map = newMap;
		this.goals = newGoals;
		this.players = newPlayers;
		this.completed = false;
	}

	// VALID MOVE LOGIC
	public boolean isMatchingShape(Tile source, Tile destination) {
		return source.getShape() == destination.getShape();
	}

	public boolean isMatchingColor(Tile source, Tile destination) {
		return source.getColor() == destination.getColor();
	}

	public boolean isMatchingLocation(Tile source, Tile destination) {
		return source.getLocation().getX() == destination.getLocation().getX()
				|| source.getLocation().getY() == destination.getLocation().getY();
	}

	public boolean isBlankBetweenTiles(Point source, Point destination) {
		int sourceX = (int) source.getX();
		int sourceY = (int) source.getY();
		while (sourceX != destination.x || sourceY != destination.y) {
			if (sourceX > destination.x) {
				sourceX--;
			} else if (sourceX < destination.x) {
				sourceX++;
			}
			if (sourceY > destination.y) {
				sourceY--;
			} else if (sourceY < destination.y) {
				sourceY++;
			}
			Tile current = this.getTileAtPoint(new Point((int) sourceX, (int) sourceY));
			if (current.getShape() == Shape.BLANK) {
				return true;
			}
		}
		return false;
	}

	public boolean isBehind(Direction initial, Point local, Point remote) {
		switch (initial) {
		case UP:
			return local.getY() < remote.getY();
		case RIGHT:
			return local.getX() > remote.getX();
		case DOWN:
			return local.getY() > remote.getY();
		case LEFT:
			return local.getX() < remote.getX();
		default:
			return false;
		}
	}

	public boolean isValidMoveLogic(Player player, Tile source, Tile destination) {
		return (this.isMatchingShape(source, destination) || this.isMatchingColor(source, destination))
				&& this.isMatchingLocation(source, destination)
				&& !this.isBlankBetweenTiles(source.getLocation(), destination.getLocation())
				&& !this.isBehind(player.getDirection(), player.getLocation(), destination.getLocation())
				&& !source.isBlank() && !destination.isBlank();
	}

	public String getMoveData(Player player, Tile source, Tile destination) {
		String output = "";
		if (this.isValidMoveLogic(player, source, destination)) {
			output = "This is a Valid Move.";
		} else {
			output += "This is an Invalid Move because:\n";
			if (!this.isMatchingShape(source, destination) && !this.isMatchingColor(source, destination)) {
				output += "Shape and Color Don't Match\n";
			}
			if (!this.isMatchingLocation(source, destination)) {
				output += "The Destination is not on the Same X or Y Axis as the Source\n";
			}
			if (this.isBlankBetweenTiles(source.getLocation(), destination.getLocation())) {
				output += "There is a Blank Tile between Source and Destination\n";
			}
			if (this.isBehind(player.getDirection(), player.getLocation(), destination.getLocation())) {
				output += "The Destination Tile is Behind the Source Tile Relative to Player Direction\n";
			}
			if (source.isBlank()) {
				output += "Player is on a Blank Tile!?!?!?\n";
			}
			if (destination.isBlank()) {
				output += "Destination is a Blank Tile\n";
			}
		}
		return output;
	}
	// END VALID MOVE LOGIC

	// GAME STATE LOGIC & HANDLING INPUT
	public boolean movePlayer(Player player, Tile source, Tile destination) {
		if (this.isValidMoveLogic(player, source, destination)) {
			player.addToCount();
			player.addToHistory(player.getLocation(), player.getDirection());
			player.setLocation(destination.getLocation());
			player.setDirection(this.getNewDirection(source.getLocation(), destination.getLocation()));
			this.activateGoalsAtPoint(destination.getLocation());
			if (this.allGoalsCompleted()) {
				this.setCompleted(true);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean movePlayer(Player player, Point source, Point destination) {
		return this.movePlayer(player, this.getTileAtPoint(source), this.getTileAtPoint(destination));
	}

	public void reversePlayerMove(Player player) {
		ArrayList<PointDirectionPair> history = player.getHistory();
		PointDirectionPair latest = history.get(history.size() - 1);
		player.setLocation(latest.getPoint());
		player.setDirection(latest.getDirection());
		player.addToCount();
		history.remove(history.size() - 1);
	}

	public boolean movePlayer(Tile destination) {
		return this.movePlayer(this.getPlayer(), this.getPlayer().getLocation(), destination.getLocation());
	}

	public boolean movePlayer(Point destination) {
		return this.movePlayer(this.getTileAtPoint(destination));
	}

	public boolean activateGoalsAtPoint(Point point) {
		Goal current;
		for (int i = 0; i < this.getGoals().size(); i++) {
			current = this.getGoals().get(i);
			if (current.getLocation().getX() == point.getX() && current.getLocation().getY() == point.getY()) {
				current.setCompleted(true);
				return true;
			}
		}
		return false;
	}

	public Direction getNewDirection(Point local, Point remote) {
		if (local.getX() > remote.getX()) {
			return Direction.LEFT;
		} else if (local.getY() > remote.getY()) {
			return Direction.UP;
		} else if (local.getX() < remote.getX()) {
			return Direction.RIGHT;
		} else if (local.getY() < remote.getY()) {
			return Direction.DOWN;
		} else {
			return null;
		}
	}

	// CHECKERS, GETTERS AND SETTERS
	public boolean allGoalsCompleted() {
		for (Goal goal : this.getGoals()) {
			if (!goal.isCompleted()) {
				return false;
			}
		}
		return true;
	}

	public Tile getTileAtPoint(Point point) {
		return this.getMap().get(point.y).get(point.x);
	}

	public Player getPlayer() {
		return this.getPlayers().get(0);
	}

	public Player getPlayer(int which) {
		return this.getPlayers().get(which);
	}

	public ArrayList<Goal> getGoals() {
		return goals;
	}

	public ArrayList<ArrayList<Tile>> getMap() {
		return map;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getRemainingGoals() {
		int count = 0;
		for (int i = 0; i < this.getGoals().size(); i++) {
			if (this.getGoals().get(i).isCompleted() == false) {
				count++;
			}
		}
		return count;
	}
}
