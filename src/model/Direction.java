package model;

public enum Direction {
	UP, DOWN, LEFT, RIGHT;

	public static Direction getDirectionFromInt(int which) {
		switch (which) {
		case 0:
			return Direction.UP;
		case 1:
			return Direction.RIGHT;
		case 2:
			return Direction.DOWN;
		case 3:
			return Direction.LEFT;
		default:
			return Direction.UP;
		}
	}

	public static int getIntFromDirection(Direction which) {
		switch (which) {
		case UP:
			return 0;
		case RIGHT:
			return 1;
		case DOWN:
			return 2;
		case LEFT:
			return 3;
		default:
			return -1;
		}
	}
}
