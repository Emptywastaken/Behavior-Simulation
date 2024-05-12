import java.util.ArrayList; // Class used to define the object the board is made up of, it stores his own position and
                            // any element that "walks" on it whether that is a resource or a player
//TODO update code once player class is created
public class Cell {
    private int row;

    private int column;
    private ArrayList<Integer> elements = new ArrayList<Integer>(); //TODO change the type to object once class is created
    Cell(int x, int y) {  //Constructor that takes the row, column coordinates of the Cell and stores them
        this.row = x;     //Currently the values are not used but could become useful
        this.column = y;
    }

    public int AddElement(int Element){ //TODO change the type to object once class is created
        elements.add(Element);
        return elements.size() - 1;     //Array size is returned to store element location in the array
                                        //A way to keep track of the location after previous elements have been removed
        //Would need to be added if we decide to keep this structure otherwise bugs will occur
    }

    public int GetElement(int Element_i){ //Returns the element at position Element_i
        return elements.get(Element_i);
    }
    public ArrayList<Integer> InCell() { //Returns the array containing all the elements in the cell
        return elements;
    }
    public void Remove(int Element_i) {
        if (elements.size() - 1 > Element_i) {
            elements.remove(Element_i);
        }

}
}

