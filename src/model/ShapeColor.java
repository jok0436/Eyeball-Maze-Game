package model;

public enum ShapeColor {
	WHITE, BLUE, RED, YELLOW, GREEN, PURPLE;

	public static ShapeColor getShapeColorFromInt(int which) {
		switch (which) {
		case 0:
			return ShapeColor.WHITE;
		case 1:
			return ShapeColor.BLUE;
		case 2:
			return ShapeColor.RED;
		case 3:
			return ShapeColor.YELLOW;
		case 4:
			return ShapeColor.GREEN;
		case 5:
			return ShapeColor.PURPLE;
		default:
			return ShapeColor.WHITE;
		}
	}

	public static int getIntFromShapeColor(ShapeColor which) {
		switch (which) {
		case WHITE:
			return 0;
		case BLUE:
			return 1;
		case RED:
			return 2;
		case YELLOW:
			return 3;
		case GREEN:
			return 4;
		case PURPLE:
			return 5;
		default:
			return -1;
		}
	}
}
