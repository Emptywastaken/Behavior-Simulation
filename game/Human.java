package game;

import java.util.ArrayList;

public class Human extends Entity {
    private final int vision;
    private final int speed;
    private boolean alive = true;
    private Cell nextCell = this.getCell();

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
		this.GetCell().RemoveHuman(this);
	}
	public void moveCell(Cell newCell) {
        Cell cell = this.GetCell();
		cell.getBoard().MoveElement(this, cell, newCell);
        this.ChangeCell(newCell);
	}
    private int getDistance(Cell cell){
        int x = Math.abs(cell.getColumn() - this.getX());
        int y = Math.abs(cell.getRow() - this.getY());
        return x+y;
    }
    public void pickMove(){
        ArrayList<Cell> viewFood = this.GetCell().getBoard().getFoodVision(this, vision);
        Cell closestFood = this.getCell();
        int closestFoodDistance = 1000;
        for (int i = 0; i < viewFood.size(); i++){
            Cell currentFood = viewFood.get(i);
  
            if(getDistance(currentFood) < closestFoodDistance){
                closestFoodDistance = getDistance(currentFood);
                closestFood = currentFood;
                }
        }
        int x = closestFood.getRow();
        int y = closestFood.getColumn();
        int x_moves = x - this.getX();
        int y_moves = y - this.getY();
        if (y_moves != 0){ //Randomness needs to be added to the moves
            if (y_moves < 0) {
                nextCell = this.getCell().getBoard().getCell(this.getX(), this.getY() - 1);
            }
            else{
                nextCell = this.getCell().getBoard().getCell(this.getX(), this.getY() + 1);
            }
        }
        else if (x_moves != 0){
            if (x_moves < 0) {
                nextCell = this.getCell().getBoard().getCell(this.getY(), this.getX() - 1);
            }
            else{
                nextCell = this.getCell().getBoard().getCell(this.getY(), this.getX() + 1);
            }
        }
    }

    public void makeMove() {
        moveCell(nextCell);
        nextCell = this.getCell();
    }
}