package model;

import java.awt.Point;

public class PointDirectionPair {
	private Point point;
	private Direction direction;

	public PointDirectionPair(Point newPoint, Direction newDirection) {
		this.point = newPoint;
		this.direction = newDirection;
	}

	public Direction getDirection() {
		return direction;
	}

	public Point getPoint() {
		return point;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}
