package game;


public abstract class Entity {


	// reference to current sell
	private  Cell cell;

	//Constructor
	public Entity(Cell cell)
	{
		this.cell = cell;
		 // x, y are same as that of the cell.
	}
	// removes element from cell
	public void death() {
		this.cell.Remove(this);
	}
	
	public int getX(){
		return this.cell.getColumn();
	}

	public int getY(){
		return this.cell.getRow();
	}

	public Cell GetCell() {
		return this.cell;
	}

	public void ChangeCell(Cell newCell){
		this.cell = newCell;
		cell.getBoard().MoveElement(this, cell, newCell);
	}
	
 


}