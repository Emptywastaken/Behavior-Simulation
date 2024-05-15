package game;

import java.util.ArrayList; // Class used to define the object the board is made up of, it stores his own position and

// any element that "walks" on it whether that is a resource or a player

public class Cell {
    private Board BOARD;
    private final int ROW;
    private final int COLUMN;
    private ArrayList<Human> humans = new ArrayList<Human>();
    private boolean food = false;

    Cell(int x, int y, Board board) { // Constructor that takes the ROW, column coordinates of the Cell and stores
        // them
        this.ROW = x; // Currently the values are not used but could become useful
        this.COLUMN = y;
        this.BOARD = board;
    }

    public boolean isEmpty() {
        return this.humans.isEmpty();
    }

    public void AddHuman(Human human) {
        humans.add(human);
    }

    public ArrayList<Human> getHumans() { // Returns the array containing all the elements in the cell
        return humans;
    }

    public void RemoveHuman(Human entity) {
        humans.remove(entity);
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COLUMN;
    }

    public Board getBoard() {
        return BOARD;
    }

    public boolean hasFood() {
        return food;
    }

    public void foodAdded() {
        food = true;
    }

    public void foodRemoved() {
        food = false;
    }

}
