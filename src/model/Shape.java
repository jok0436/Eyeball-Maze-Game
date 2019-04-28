package model;

public enum Shape {
	BLANK, STAR, CORAL, CROSS, DIAMOND, LIGHTNING;

	public static Shape getShapeFromInt(int which) {
		switch (which) {
		case 0:
			return Shape.BLANK;
		case 1:
			return Shape.STAR;
		case 2:
			return Shape.CORAL;
		case 3:
			return Shape.CROSS;
		case 4:
			return Shape.DIAMOND;
		case 5:
			return Shape.LIGHTNING;
		default:
			return Shape.BLANK;
		}
	}

	public static int getIntFromShape(Shape which) {
		switch (which) {
		case BLANK:
			return 0;
		case STAR:
			return 1;
		case CORAL:
			return 2;
		case CROSS:
			return 3;
		case DIAMOND:
			return 4;
		case LIGHTNING:
			return 5;
		default:
			return -1;
		}
	}
}
