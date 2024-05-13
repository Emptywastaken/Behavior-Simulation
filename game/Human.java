package game;

import java.util.ArrayList;

public abstract class Human extends Entity {

     
    private boolean readyToReplicate = false;
    private boolean hasFood = false;
    private int turnsLeft;
    private final int stregth;
    private final int vision;
    private final int speed;


    public Human(Cell cell, int vision, int stregth, int turnsLeft, int speed){
        super(cell);
        this.vision = vision;
        this.stregth = stregth;
        this.turnsLeft = turnsLeft;
        this.speed = speed;
    }
    /* 
    public Human(Cell cell, Human parent) {
        super(cell);
    } */

    // Pick direction function depends on the character of the human
    // greedy, generous etc...
    public abstract Cell PickDirection();
    public abstract boolean ToAttack();

    // TODO: consider case when human needs to choose who gets the food.
    public abstract boolean ShareFood(); 
    
    // added in case we consider more factors when checking for replication
    public abstract void Replicate();
    
    public void Move(Cell new_cell) throws OutOfRangeException {
        int x = super.GetX();
        int y = super.GetX();
        double dist = Helper.getDistance(x, y, new_cell.getColumn(), new_cell.getRow());
        
        if(dist > speed) {
            throw new OutOfRangeException();    
        }
        super.ChangeCell(new_cell);
    }   

    public ArrayList<Cell> targetInCell(Entity neededEntity) {
        // loops through each cell in the vision radius.
        // TODO implement a way to loop through cells using board
        // Overload move element so cell objects can be passed directly
        Class cls = neededEntity.getClass();
        int positionX = super.GetX()
        for (int i = 0; i < rows; i++) {
            ArrayList<Cell> NewColumn = new ArrayList<Cell>();
            for (int j = 0; j < columns; j++) {
                Cell s = new Cell(i, j);
                NewColumn.add(s);
            }
            Cells.add(NewColumn);
        }
        return
    } 


    @Override
    public void Death() {
        if(turnsLeft <= 0) {
            super.Death();
        }
	}
    // TODO: randomness eg.(lambda*stregthDiffernce) chance to alternate the outcome.
    public void Attack(Human target){
        if (this.stregth == target.stregth) {
            this.DecreaseHealth();
            target.DecreaseHealth();
        } else if (this.stregth > target.stregth) {
            target.DecreaseHealth();
        } else {
            this.DecreaseHealth();
        }
    }
    
    public void DecreaseHealth() {
        this.turnsLeft--;
    }

    public void DecreaseHealth(int amount) {
        this.turnsLeft = this.turnsLeft - amount;
    }
}