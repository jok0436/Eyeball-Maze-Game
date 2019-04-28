package model;

import java.awt.Point;

public abstract class Placeable {
	private Point location;

	public Placeable(Point newLocation) {
		this.location = newLocation;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public String toString() {
		return "Type: " + this.getClass() + " X: " + this.getLocation().getX() + " Y: " + this.getLocation().getY();
	}

	public abstract String getType();
}
