package nz.ac.ara.jok0436.eyeballMazeGame.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nz.ac.ara.jok0436.eyeballMazeGame.model.Direction;
import nz.ac.ara.jok0436.eyeballMazeGame.model.Maze;
import nz.ac.ara.jok0436.eyeballMazeGame.model.MazeBuilder;
import nz.ac.ara.jok0436.eyeballMazeGame.model.Tile;

class modelTest {
	private Maze maze;
	private MazeBuilder builder;

	@BeforeEach
	void setup() {
		builder = new MazeBuilder();
		maze = builder.getMazeFromFile(
				"C:\\Users\\josia\\Dropbox\\BCPR282 - Best Programming Pactices in Java\\Assignment 1\\Eyeball-Maze-Game\\src\\mazes\\Maze_1.txt");
	}

//BASIC TESTS 
	@Test
	void mazeGoalsTest1() {
		assertEquals(1, maze.getGoals().size(), "Goal Count Should be 1");
	}

	@Test
	void mazePlayersTest1() {
		assertEquals(1, maze.getPlayers().size(), "Player Count Should be 1");
	}

	@Test
	void mazeMapTests1() {
		assertEquals(6, maze.getMap().size(), "Map Height Should be 6");
	}

	@Test
	void mazeMapTests2() {
		assertEquals(4, maze.getMap().get(0).size(), "Map Width for Y:0 Should be 4");
	}

	@Test
	void mazeTileTest1() {
		assertEquals(0, maze.getMap().get(1).get(0).getLocation().x, "Tile at X:0 Y:1 Should Have an X of 0");
	}

	@Test
	void mazeTileTest2() {
		assertEquals(1, maze.getMap().get(1).get(0).getLocation().y, "Tile at X:0 Y:1 Should Have an Y of 1");
	}

	@Test
	void mazeTileTest3() {
		assertEquals(0, maze.getMap().get(3).get(0).getLocation().x, "Tile at X:0 Y:3 Should Have an X of 0");
	}

	@Test
	void mazeTileTest4() {
		assertEquals(3, maze.getMap().get(3).get(0).getLocation().y, "Tile at X:0 Y:3 Should Have an Y of 3");
	}

	// Testing Correct Move Logic

	@Test
	void mazeLogicTest1() {
		Tile t1 = maze.getMap().get(1).get(0);
		Tile t2 = maze.getMap().get(3).get(0);
		assertEquals(false, maze.isMatchingColor(t1, t2), "Tile X:0 Y:1 Has Different Color as Tile X:0 Y:3");
	}

	@Test
	void mazeLogicTest2() {
		Tile t1 = maze.getMap().get(1).get(1);
		Tile t2 = maze.getMap().get(1).get(2);
		assertEquals(true, maze.isMatchingColor(t1, t2), "Tile X:1 Y:1 Has Same Color as Tile X:2 Y:1");

	}

	@Test
	void mazeLogicTest3() {
		Tile t1 = maze.getMap().get(1).get(0);
		Tile t2 = maze.getMap().get(3).get(0);
		assertEquals(false, maze.isMatchingShape(t1, t2), "Tile X:0 Y:1 Has Different Shape as Tile X:0 Y:3");
	}

	@Test
	void mazeLogicTest4() {
		Tile t1 = maze.getMap().get(1).get(0);
		Tile t2 = maze.getMap().get(1).get(3);
		assertEquals(true, maze.isMatchingShape(t1, t2), "Tile X:0 Y:1 Has Same Shape as Tile X:3 Y:1");
	}

	@Test
	void mazeLogicTest5() {
		Tile t1 = maze.getMap().get(1).get(0);
		Tile t2 = maze.getMap().get(3).get(0);
		assertEquals(true, maze.isMatchingLocation(t1, t2), "Tile X:0 Y:1 Shares a X or Y with Tile X:0 Y:3");
	}

	@Test
	void mazeLogicTest6() {
		Tile t1 = maze.getMap().get(1).get(2);
		Tile t2 = maze.getMap().get(3).get(3);
		assertEquals(false, maze.isMatchingLocation(t1, t2), "Tile X:2 Y:1 Doesnt Share a X or Y with Tile X:3 Y:3");
	}

	@Test
	void mazeLogicTest7() {
		Tile t1 = maze.getMap().get(0).get(0);
		Tile t2 = maze.getMap().get(0).get(3);
		assertEquals(true, maze.isBlankBetweenTiles(t1.getLocation(), t2.getLocation()),
				"Tile X:0 Y:0 Blank Between Tile X:3 Y:0");
	}

	@Test
	void mazeLogicTest8() {
		Tile t1 = maze.getMap().get(2).get(2);
		Tile t2 = maze.getMap().get(2).get(3);
		assertEquals(false, maze.isBlankBetweenTiles(t1.getLocation(), t2.getLocation()),
				"Tile X:2 Y:2 No Blank Between Tile X:3 Y:2");
	}

	@Test
	void mazeLogicTest9() {
		Tile t1 = maze.getMap().get(2).get(1);
		Tile t2 = maze.getMap().get(1).get(1);
		assertEquals(true, maze.isBehind(Direction.DOWN, t1.getLocation(), t2.getLocation()),
				"Tile X:1 Y:1 is Behind Tile X:1 Y:2 With Direction DOWN");
	}

	@Test
	void mazeLogicTest10() {
		Tile t1 = maze.getMap().get(2).get(1);
		Tile t2 = maze.getMap().get(1).get(1);
		assertEquals(false, maze.isBehind(Direction.UP, t1.getLocation(), t2.getLocation()),
				"Tile X:1 Y:1 isnt Behind Tile X:1 Y:2 With Direction UP");
	}

	@Test
	void mazeLogicTest11() {
		Tile t1 = maze.getMap().get(2).get(1);
		Tile t2 = maze.getMap().get(1).get(1);
		assertEquals(false, maze.isBehind(Direction.LEFT, t1.getLocation(), t2.getLocation()),
				"Tile X:1 Y:1 isnt Behind Tile X:1 Y:2 With Direction LEFT");
	}

// Testing the player class
	@Test
	void mazePlayerTest1() {
		maze.getPlayer().setLocation(new Point(1, 2));
		assertEquals(new Point(1, 2), maze.getPlayer().getLocation(),
				"Hard Setting a New Location for the Player Should Work");
	}

	@Test
	void mazePlayerTest2() {
		maze.getPlayer().setDirection(Direction.UP);
		assertEquals(false,
				maze.isBehind(maze.getPlayer().getDirection(), maze.getPlayer().getLocation(), new Point(1, 3)),
				"Player X:1 Y:5 is not behind X:1 Y:3 With Direction UP");
	}

	@Test
	void mazePlayerTest3() {
		maze.getPlayer().setDirection(Direction.LEFT);
		assertEquals(true,
				maze.isBehind(maze.getPlayer().getDirection(), maze.getPlayer().getLocation(), new Point(2, 2)),
				"Player X:1 Y:5 is behind X:2 Y:2 With Direction LEFT");
	}

	@Test
	void mazePlayerTest4() {
		maze.getPlayer().setDirection(Direction.RIGHT);
		assertEquals(true,
				maze.isBehind(maze.getPlayer().getDirection(), maze.getPlayer().getLocation(), new Point(0, 2)),
				"Player X:1 Y:5 is behind X:0 Y:2 With Direction RIGHT");
	}

	@Test
	void mazePlayerTest5() {
		maze.getPlayer().setDirection(Direction.DOWN);
		assertEquals(true,
				maze.isBehind(maze.getPlayer().getDirection(), maze.getPlayer().getLocation(), new Point(1, 1)),
				"Player X:1 Y:5 is behind X:1 Y:1 With Direction DOWN");
	}

	@Test
	void mazePlayerTest6() {
		assertEquals(true, maze.isBlankBetweenTiles(new Point(2, 0), new Point(0, 0)),
				"Blanks Between X:2 Y:0 and X:0 Y:0");
	}

	@Test
	void mazePlayerTest7() {
		assertEquals(false, maze.isBlankBetweenTiles(new Point(0, 3), new Point(3, 3)),
				"No Blanks Between X:0 Y:3 and X:3 Y:3");
	}

	@Test
	void mazePlayerTest8() {
		assertEquals(false, maze.isBlankBetweenTiles(new Point(1, 5), new Point(1, 1)),
				"No Blanks Between X:1 Y:5 and X:1 Y:1");
	}

	@Test
	void gameStateTest() {
		assertEquals(true, maze.movePlayer(new Point(1, 3)), "Moving to X:1 Y:3 Game State 1");
		assertEquals(true, maze.movePlayer(new Point(3, 3)), "Moving to X:3 Y:3 Game State 2");
		assertEquals(true, maze.movePlayer(new Point(3, 1)), "Moving to X:3 Y:1 Game State 3");
		assertEquals(true, maze.movePlayer(new Point(0, 1)), "Moving to X:0 Y:1 Game State 4");
		assertEquals(true, maze.movePlayer(new Point(0, 4)), "Moving to X:0 Y:4 Game State 5");
		assertEquals(true, maze.movePlayer(new Point(2, 4)), "Moving to X:2 Y:4 Game State 6");
		assertEquals(true, maze.movePlayer(new Point(2, 0)), "Moving to X:2 Y:0 Game State 7 END");
		assertEquals(false, maze.movePlayer(new Point(1, 0)), "Moving to X:1 Y:0 Game State 8");
		assertEquals(false, maze.movePlayer(new Point(0, 1)), "Moving to X:0 Y:1 Game State 9");
		assertEquals(false, maze.movePlayer(new Point(0, 4)), "Moving to X:0 Y:4 Game State 10");
		assertEquals(false, maze.movePlayer(new Point(2, 4)), "Moving to X:2 Y:4 Game State 11");
		assertEquals(false, maze.movePlayer(new Point(3, 3)), "Moving to X:3 Y:3 Game State 12");
		assertEquals(true, maze.isCompleted(), "Maze Should be Completed Now Game State 13");
		maze.reversePlayerMove(maze.getPlayer());
		assertEquals(new Point(2, 4), maze.getPlayer().getLocation(),
				"Should be Able to Reverse Player Move back to 2,4");
		maze.reversePlayerMove(maze.getPlayer());
		assertEquals(new Point(0, 4), maze.getPlayer().getLocation(),
				"Should be Able to Reverse Player Move back to 0,4");
		maze.reversePlayerMove(maze.getPlayer());
		assertEquals(new Point(0, 1), maze.getPlayer().getLocation(),
				"Should be Able to Reverse Player Move back to 0,1");
		maze.reversePlayerMove(maze.getPlayer());
		assertEquals(new Point(3, 1), maze.getPlayer().getLocation(),
				"Should be Able to Reverse Player Move back to 3,1");
		maze.reversePlayerMove(maze.getPlayer());
		assertEquals(new Point(3, 3), maze.getPlayer().getLocation(),
				"Should be Able to Reverse Player Move back to 3,3");
		maze.reversePlayerMove(maze.getPlayer());
		assertEquals(new Point(1, 3), maze.getPlayer().getLocation(),
				"Should be Able to Reverse Player Move back to 1,3");
		assertEquals(new Point(1, 3), maze.getPlayer().getLocation(), "Player Should be Back at Starting Position");
		assertEquals(13, maze.getPlayer().getCount(), "Count Should be 13 After All Moves");
	}

	@Test
	void randomMazeGenerationTest1() {
		MazeBuilder builder = new MazeBuilder();
		Maze maze = builder.generateRandomMaze(10, 10);
		assertEquals(11, maze.getMap().size(), "Maze Should have a Height of 11 After Generating");
	}

	@Test
	void randomMazeGenerationTest2() {
		MazeBuilder builder = new MazeBuilder();
		Maze maze = builder.generateRandomMaze(20, 20);
		assertEquals(21, maze.getMap().size(), "Maze Should have a Height of 21 After Generating");
	}

	@Test
	void randomMazeGenerationTest3() {
		MazeBuilder builder = new MazeBuilder();
		Maze maze = builder.generateRandomMaze(50, 50);
		assertEquals(51, maze.getMap().size(), "Maze Should have a Height of 51 After Generating");
	}

	@Test
	void randomMazeGenerationTest4() {
		MazeBuilder builder = new MazeBuilder();
		Maze maze = builder.generateRandomMaze(1000, 1000);
		assertEquals(1001, maze.getMap().size(), "Maze Should have a Height of 1001 After Generating");
	}
}
