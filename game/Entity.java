package game;

public abstract class Entity {


	// reference to current sell
	public Cell cell;

	//Constructor
	public Entity(Cell cell)
	{
		this.cell = cell; // x, y are same as that of the cell.
	}
	// removes element from cell
	public void Death() {
		this.cell.Remove(this);
	}
	
	public int GetX(){
		return this.cell.column;
	}

	public int GetY(){
		return this.cell.row;
	}

	public void ChangeCell(Cell newCell){
		this.cell = newCell;
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