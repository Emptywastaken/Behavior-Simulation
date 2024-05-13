package game;


import java.lang.reflect.Array;
import java.util.ArrayList; // Class used to define the object the board is made up of, it stores his own position and


                            // any element that "walks" on it whether that is a resource or a player
                            //TODO update code once player class is created

public class Cell {
    private Board BOARD;
    private final int ROW;
    private final int COLUMN;
    private ArrayList<Entity> elements = new ArrayList<Entity>(); // TODO change the type to object once class is
                                                                    // created

    Cell(int x, int y) { // Constructor that takes the ROW, column coordinates of the Cell and stores
                         // them
        this.ROW = x; // Currently the values are not used but could become useful
        this.COLUMN = y;
    }
    
    

    public boolean isEmpty(){
        return this.elements.isEmpty();
    }

    public int AddElement(Entity Element) { // TODO change the type to object once class is created
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
    public int containsFood(){ //checks whether cell contains food.
        for (int i = 0; i < elements.size(); i ++) {
            if(elements.get(i) instanceof Food) {
                return i;
            }
        }
        return -1;
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COLUMN;
    }

    public int boardWidth() {
        return BOARD.getColumns();
    }

    public int boardHeight() {
        return BOARD.getRows();
    }

    public Board getBoard() {
        return BOARD;
    }

}
