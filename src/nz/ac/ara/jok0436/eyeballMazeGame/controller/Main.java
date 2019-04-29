package nz.ac.ara.jok0436.eyeballMazeGame.controller;

import nz.ac.ara.jok0436.eyeballMazeGame.model.Maze;
import nz.ac.ara.jok0436.eyeballMazeGame.model.MazeBuilder;

public class Main {

	public static void main(String[] args) {
		Maze maze = new MazeBuilder().getMazeFromFile(
				"C:\\Users\\josia\\Dropbox\\BCPR282 - Best Programming Pactices in Java\\Assignment 1\\Eyeball-Maze-Game\\src\\mazes\\Maze_1.txt");
		System.out.println(maze);
	}
}