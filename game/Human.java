package game;

import java.util.ArrayList;

public class Human extends Entity {
    private final int vision;
    private final int speed;
    private boolean alive = true;
    private Cell nextCell = cell;

    public Human(Cell cell, int vision, int speed) {
        super(cell);
        this.vision = vision;
        this.speed = speed;
    }

    public void death() {
        this.Remove();
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
    public void Remove() {
		cell.RemoveHuman(this);
	}
	public void moveCell(Cell newCell) {
		cell.getBoard().MoveElement(this, cell, newCell);
        this.ChangeCell(newCell);
	}
    private int getDistance(Cell cell){
        int x = Math.abs(cell.getRow() - this.getRow());
        int y = Math.abs(cell.getColumn() - this.getColumn());
        return x+y;
    }
    public void pickMove(){
        ArrayList<Cell> viewFood = cell.getBoard().getFoodVision(this, vision);
        Cell closestFood = cell;
        int closestFoodDistance = 1000;
        for (int i = 0; i < viewFood.size(); i++){
            Cell currentFood = viewFood.get(i);
  
            if(getDistance(currentFood) < closestFoodDistance){
                closestFoodDistance = getDistance(currentFood);
                closestFood = currentFood;
                }
        }
        int row = closestFood.getRow();
        int column = closestFood.getColumn();
        int row_moves = row - this.getRow();
        int column_moves = column - this.getColumn();
        if (column_moves != 0){ //Randomness needs to be added to the moves
            if (column_moves < 0) {
                nextCell = cell.getBoard().getCell(this.getRow(), this.getColumn() - 1);
            }
            else{
                nextCell = cell.getBoard().getCell(this.getRow(), this.getColumn() + 1);
            }
        }
        else if (row_moves != 0){
            if (row_moves < 0) {
                nextCell = cell.getBoard().getCell(this.getRow() - 1, this.getColumn());
            }
            else{
                nextCell = cell.getBoard().getCell(this.getRow() + 1, this.getColumn());
            }
        }
    }

    public void makeMove() {
            moveCell(nextCell);
    }
}