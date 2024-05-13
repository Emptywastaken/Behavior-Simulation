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
	public void Death() {
		this.cell.Remove(this);
	}
	
	public int GetX(){
		return this.cell.getColumn();
	}

	public int GetY(){
		return this.cell.getRow();
	}

	public Cell GetCell() {
		return this.cell;
	}

	public void ChangeCell(Cell newCell){
		this.cell = newCell;
	}
	
 


}