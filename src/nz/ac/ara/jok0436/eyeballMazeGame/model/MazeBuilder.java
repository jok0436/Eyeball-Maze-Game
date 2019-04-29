package nz.ac.ara.jok0436.eyeballMazeGame.model;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import nz.ac.ara.jok0436.eyeballMazeGame.model.Direction;
import nz.ac.ara.jok0436.eyeballMazeGame.model.Tile;

public class MazeBuilder {

	public Maze getMazeFromFile(String fileName) {
		ArrayList<String> data = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while (bufferedReader.ready()) {
				data.add(bufferedReader.readLine());
			}
			bufferedReader.close();
			return this.parseMazeData(data);
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
		return null;
	}

	public Maze parseMazeData(ArrayList<String> data) {
		String[] splitFirstLine = data.get(0).split("-");
		int[] first = Arrays.stream(splitFirstLine[0].split("\\|")).mapToInt(Integer::parseInt).toArray();
		int[] second = Arrays.stream(splitFirstLine[1].split("\\|")).mapToInt(Integer::parseInt).toArray();
		ArrayList<Player> players = this.parsePlayerData(first);
		ArrayList<Goal> goals = this.parseGoalData(second);
		data.remove(0);
		ArrayList<ArrayList<Tile>> map = this.parseMapData(data);
		return new Maze(map, goals, players);
	}

	public ArrayList<Goal> parseGoalData(int[] data) {
		ArrayList<Goal> goals = new ArrayList<Goal>();
		if (data.length % 2 == 0) {
			Goal goal;
			for (int i = 0; i < data.length; i += 2) {
				goal = new Goal(new Point(data[i], data[i + 1]));
				goals.add(goal);
			}
			return goals;
		} else {
			return null;
		}
	}

	public ArrayList<Player> parsePlayerData(int[] data) {
		ArrayList<Player> players = new ArrayList<Player>();
		Player player;
		if (data.length % 3 == 0) {
			for (int i = 0; i < data.length; i += 3) {
				player = new Player(new Point(data[i], data[i + 1]), Direction.getDirectionFromInt(data[i + 2]));
				players.add(player);
			}
			return players;
		} else {
			return null;
		}
	}

	public ArrayList<ArrayList<Tile>> parseMapData(ArrayList<String> data) {
		Tile tile;
		ArrayList<Tile> tiles;
		ArrayList<ArrayList<Tile>> map = new ArrayList<ArrayList<Tile>>();
		int xCount;
		for (int i = 0; i < data.size(); i++) {
			tiles = new ArrayList<Tile>();
			xCount = 0;
			for (int i2 = 0; i2 < data.get(i).length(); i2++) {
				Point newLocation = new Point(xCount, i);
				if (data.get(i).charAt(i2) == '_') {
					tile = new Tile(newLocation);
				} else {
					int currentChar = Character.getNumericValue(data.get(i).charAt(i2));
					int currentCharAddOne = Character.getNumericValue(data.get(i).charAt(i2 + 1));
					Shape newShape = Shape.getShapeFromInt(currentChar);
					ShapeColor newShapeColor = ShapeColor.getShapeColorFromInt(currentCharAddOne);
					tile = new Tile(newLocation, newShape, newShapeColor);
					i2++;
				}
				xCount++;
				tiles.add(tile);
			}
			map.add(tiles);
		}
		return map;
	}

	public boolean writeMazeToFile(Maze maze, String fileName) {
		String data = "";
		data += this.mazePlayersToString(maze);
		data += "-";
		data += this.mazeGoalsToString(maze);
		data += "\n";
		data += this.mazeMapToString(maze);
		try {
			File newTextFile = new File(fileName);
			FileWriter fw = new FileWriter(newTextFile);
			fw.write(data);
			fw.close();
			return true;
		} catch (IOException iox) {
			iox.printStackTrace();
			return false;
		}
	}

	private String mazeMapToString(Maze maze) {
		String output = "";
		Tile current;
		for (int i = 0; i < maze.getMap().size(); i++) {
			for (int i2 = 0; i2 < maze.getMap().get(i).size(); i2++) {
				current = maze.getMap().get(i).get(i2);
				if (current.isBlank()) {
					output += "_";
				} else {
					output += Shape.getIntFromShape(current.getShape());
					output += ShapeColor.getIntFromShapeColor(current.getColor());
				}
			}
			output += "\n";
		}
		return output;
	}

	private String mazeGoalsToString(Maze maze) {
		String output = "";
		for (int i = 0; i < maze.getGoals().size(); i++) {
			output += maze.getGoals().get(i).getLocation().x + "|" + maze.getGoals().get(i).getLocation().y + "|";
		}
		return output;
	}

	private String mazePlayersToString(Maze maze) {
		String output = "";
		for (int i = 0; i < maze.getPlayers().size(); i++) {
			output += maze.getPlayers().get(i).getLocation().x + "|" + maze.getPlayers().get(i).getLocation().y + "|"
					+ Direction.getIntFromDirection(maze.getPlayers().get(i).getDirection()) + "|";
		}
		return output;
	}

	public Maze generateRandomMaze(int width, int height) {
		ArrayList<ArrayList<Tile>> map = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> row;
		Tile current;
		for (int i = 0; i <= height; i++) {
			row = new ArrayList<Tile>();
			for (int i2 = 0; i2 <= width; i2++) {
				current = new Tile(new Point(i2, i), Shape.getShapeFromInt(this.randomTo(5)),
						ShapeColor.getShapeColorFromInt(this.randomTo(5)));
				row.add(current);
			}
			map.add(row);
		}
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(this.randomPoint(width, height)));
		ArrayList<Goal> goals = new ArrayList<Goal>();
		goals.add(new Goal(this.randomPoint(width, height)));
		return new Maze(map, goals, players);
	}

	private Point randomPoint(int xMax, int yMax) {
		return new Point(ThreadLocalRandom.current().nextInt(0, xMax + 1),
				ThreadLocalRandom.current().nextInt(0, yMax + 1));
	}

	private int randomTo(int max) {
		return ThreadLocalRandom.current().nextInt(0, max + 1);
	}
}
