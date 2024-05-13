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

    // returns a list of all cells with foods in the visible radius
    // TODO check for tuples or smth similar in order to be able to priorities cells by direction 
    public ArrayList<Cell> foodInCell() {
        // loops through each cell in the vision radius.
        
        int positionX = super.GetX();
        int positionY = super.GetY();

        int yStartingPoint = Math.min(positionY-vision, 0);
        int yEndPoint = Math.max(positionY+vision, this.GetCell().boardHeight());
        int xStartingPoint = Math.min(positionX-vision, 0);
        int xEndPoint = Math.max(positionX+vision, this.GetCell().boardWidth());

        Board BOARD = this.GetCell().getBoard();
        ArrayList<Cell> returnedCells = new ArrayList<Cell>();
        // need reference to board for this one
        for (int i = yStartingPoint; i <= yEndPoint; i++) {   
            for (int j= xStartingPoint; j <= xEndPoint; j++) {
                Cell cell = BOARD.getCell(i, j);

                if(cell.isEmpty()) {
                    continue;
                } if(Math.floor(Helper.getDistance(i, positionX, j, positionY)) <= vision) {
                    continue;
                } if(cell.containsFood()){
                    returnedCells.add(cell);
                }     
            }
        }
        return returnedCells;
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