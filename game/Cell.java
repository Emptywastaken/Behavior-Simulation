package game;

import java.util.ArrayList; // Class used to define the object the board is made up of, it stores his own position and

// any element that "walks" on it whether that is a resource or a player

public class Cell {
    private Board BOARD;
    private final int ROW;
    private final int COLUMN;
    private ArrayList<Entity> elements = new ArrayList<Entity>();
    private boolean food = false;

    Cell(int x, int y, Board board) { // Constructor that takes the ROW, column coordinates of the Cell and stores
        // them
        this.ROW = x; // Currently the values are not used but could become useful
        this.COLUMN = y;
        this.BOARD = board;
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public int AddElement(Entity Element) {
        elements.add(Element);
        return elements.size() - 1; // Array size is returned to store element location in the array
                                    // A way to keep track of the location after previous elements have been removed
        // Would need to be added if we decide to keep this structure otherwise bugs
        // will occur
    }

    public Entity GetElement(int Element_i) { // Returns the element at position Element_i
        return elements.get(Element_i);
    }

    public ArrayList<Entity> getElements() { // Returns the array containing all the elements in the cell
        return elements;
    }

    public void Remove(Entity entity) {
        elements.remove(entity);
    }

    // removes by object
    public void Remove(int Element_i) {
        if (elements.size() - 1 > Element_i) {
            elements.remove(Element_i);
        }

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
