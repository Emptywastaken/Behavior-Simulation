package game;


public abstract class Entity {

	// reference to current sell
	private Cell cell;

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
		return cell.getColumn();
	}

	public int getY(){
		return cell.getRow();
	}

	public Cell GetCell() {
		return cell;
	}

	public void ChangeCell(Cell newCell){
		cell = newCell;
		cell.getBoard().MoveElement(this, cell, newCell);
	}
	
 


}