package game;

public abstract class Entity {


	// reference to current sell
	private Cell cell;

	//Constructor
	public Entity(Cell cell)
	{
		this.cell = cell; // x, y are same as that of the cell.
	}
	// removes element from cell
	public void death() {
		this.cell.Remove(this);
	}
	
	public int getX(){
		return this.cell.column;
	}

	public int getY(){
		return this.cell.row;
	}

	

	/*
	public enum Direction {
		UP,
		RIGHT,
		DOWN,
		LEFT;
	}

	public enum State {
		DEAD,
		ACTIVE; 
	}
	*/


}