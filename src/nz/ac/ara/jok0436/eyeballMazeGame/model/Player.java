package nz.ac.ara.jok0436.eyeballMazeGame.model;

import java.awt.Point;
import java.util.ArrayList;

public class Player extends Placeable {
	private Direction direction;
	private ArrayList<PointDirectionPair> history = new ArrayList<PointDirectionPair>();
	private int count = 0;

	public Player(Point newLocation, Direction newDirection) {
		super(newLocation);
		this.direction = newDirection;
	}

	public Player(Point newLocation) {
		this(newLocation, Direction.UP);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void addToCount(int add) {
		this.count += add;
	}

	public void addToCount() {
		this.addToCount(1);
	}

	public Direction getDirection() {
		return direction;
	}

	public ArrayList<PointDirectionPair> getHistory() {
		return history;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void addToHistory(Point newPoint, Direction newDirection) {
		this.history.add(new PointDirectionPair(newPoint, newDirection));
	}

	@Override
	public String getType() {
		return "Player";
	}
}
