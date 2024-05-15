package game;

public class Entity {

	// reference to current sell
	private Cell cell;
	private Cell nextCell; //here just for testing

	// Constructor
	public Entity(Cell cell) {
		this.cell = cell;
		cell.getBoard().AddElement(this, cell.getRow(), cell.getColumn());
		// x, y are same as that of the cell.
	}

	// removes element from cell
	public void Remove() {
		this.cell.Remove(this);
	}

	public int getX() {
		return cell.getColumn();
	}

	public int getY() {
		return cell.getRow();
	}

	public Cell GetCell() {
		return cell;
	}

	public void ChangeCell(Cell newCell) {
		cell = newCell;
		cell.getBoard().MoveElement(this, cell, newCell);
	}

	public void pickMove(){
		nextCell = cell; //just for testing, will be implemented correctly in human class
	}
	public void makeMove(){
		this.ChangeCell(nextCell);
	}
}