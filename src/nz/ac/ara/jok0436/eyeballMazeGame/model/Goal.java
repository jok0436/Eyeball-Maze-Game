package nz.ac.ara.jok0436.eyeballMazeGame.model;

import java.awt.Point;

public class Goal extends Placeable {

	private boolean completed;

	public Goal(Point newLocation, boolean newcompleted) {
		super(newLocation);
		this.completed = newcompleted;
	}

	public Goal(Point newLocation) {
		this(newLocation, false);
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String getType() {
		return "Goal";
	}
}
