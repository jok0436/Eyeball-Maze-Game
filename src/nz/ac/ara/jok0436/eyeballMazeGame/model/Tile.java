package nz.ac.ara.jok0436.eyeballMazeGame.model;

import java.awt.Point;

public class Tile extends Placeable {
	private Shape shape;
	private ShapeColor color;

	public Tile(Point newLocation, Shape newShape, ShapeColor newColor) {
		super(newLocation);
		this.shape = newShape;
		this.color = newColor;
	}

	public Tile(Point newLocation) {
		this(newLocation, Shape.BLANK, ShapeColor.WHITE);
	}

	public ShapeColor getColor() {
		return color;
	}

	public Shape getShape() {
		return shape;
	}

	public void setColor(ShapeColor color) {
		this.color = color;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public void turnBLANK() {
		this.setShape(Shape.BLANK);
		this.setColor(ShapeColor.WHITE);
	}

	public boolean isBlank() {
		return this.getShape() == Shape.BLANK;
	}

	public String toString() {
		return "Shape: " + this.getShape() + " Color: " + this.getColor() + " X: " + this.getLocation().getX() + " Y: "
				+ this.getLocation().getY();
	}

	@Override
	public String getType() {
		return "Tile";
	}
}
