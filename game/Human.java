package game;

import java.util.ArrayList;

public abstract class Human extends Entity {

    private boolean readyToReplicate = false;
    private int turnsLeft;
    private final int stregth;
    private final int vision;
    private final int speed;
    private boolean alive = true;
    private Cell nextCell;

    public Human(Cell cell, int vision, int stregth, int turnsLeft, int speed) {
        super(cell);
        this.vision = vision;
        this.stregth = stregth;
        this.turnsLeft = turnsLeft;
        this.speed = speed;
    }

    public void death() {
        this.Remove();
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    // Pick direction function depends on the character of the human
    // greedy, generous etc...
    public abstract Cell pickDirection();

    public abstract boolean toEat();

    // TODO: consider case when human needs to choose who gets the food.
    public abstract boolean shareFood();

    // added in case we consider more factors when checking for replication
    public abstract void replicate();

    public void pickMove(){
        nextCell =  this.GetCell(); // must add logic to pick next cell
    }

    public void Move() throws OutOfRangeException {
        super.ChangeCell(nextCell);
    }

    // returns a list of all cells with foods in the visible radius
    // TODO check for tuples or smth similar in order to be able to priorities cells
    // by direction
    public ArrayList<Cell> foodInVision() {
        // loops through each cell in the vision radius.

        int positionX = super.getX();
        int positionY = super.getY();

        // in order to consider out of range cases.
        int yStartingPoint = Math.min(positionY - vision, 0);
        int yEndPoint = Math.max(positionY + vision, this.GetCell().getRow());
        int xStartingPoint = Math.min(positionX - vision, 0);
        int xEndPoint = Math.max(positionX + vision, this.GetCell().getColumn());

        Board BOARD = this.GetCell().getBoard();
        ArrayList<Cell> returnedCells = new ArrayList<Cell>();
        // need reference to board for this one
        for (int i = yStartingPoint; i <= yEndPoint; i++) {
            for (int j = xStartingPoint; j <= xEndPoint; j++) {
                Cell cell = BOARD.getCell(i, j);

                if (cell.isEmpty()) {
                    continue;
                }
                if (Helper.getDistance(i, positionX, j, positionY) > vision) {
                    continue;
                }
                if (cell.containsFood() != -1) {
                    returnedCells.add(cell);
                }
            }
        }
        return returnedCells;
    }

    // returns all neighbouring players
    public ArrayList<Entity> getNeighboors() {

        int x = super.getX();
        int y = super.getY();

        Board BOARD = this.GetCell().getBoard();
        ArrayList<Entity> neighbours = new ArrayList<Entity>();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j < x; j++) {

                if (i == x & j == y) {
                    continue;
                }
                if (!BOARD.cellInRange(i, j)) {
                    continue;
                }

                Cell cell = BOARD.getCell(i, j);
                if (!cell.isEmpty()) {
                    for (Entity entitiy : cell.getElements()) {
                        if (entitiy instanceof Human) {
                            neighbours.add(entitiy);
                        }
                    }

                }
            }
        }
        return neighbours;
    }

    // TODO: randomness eg.(lambda*stregthDiffernce) chance to alternate the
    // outcome.
    public void Attack(Human target) {
        if (this.stregth == target.stregth) {
            this.decreaseHealth();
            target.decreaseHealth();
        } else if (this.stregth > target.stregth) {
            target.decreaseHealth();
        } else {
            this.decreaseHealth();
        }
    }

    public void decreaseHealth() {
        this.turnsLeft--;
        if (turnsLeft <= 0) {
            this.death();
        }
    }

    public void decreaseHealth(int amount) {
        this.turnsLeft = this.turnsLeft - amount;
        if (turnsLeft <= 0) {
            this.death();
        }
    }
}