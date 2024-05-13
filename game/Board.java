package game;

import java.util.ArrayList; //Board Class is made of a 2d arraylist that stores all the cells,
                            // the number of rows and columns is stored
                            //TODO update code once player class is created

public class Board {
    private final int ROWS;
    private final int COLUMNS;
    private ArrayList<ArrayList<Cell>> Cells = new ArrayList<ArrayList<Cell>>();

    Board(int rows, int columns) { // Constructor creates a Rows x Columns Array and stores it as Cells
        this.ROWS = rows;
        this.COLUMNS = columns;
        for (int i = 0; i < rows; i++) {
            ArrayList<Cell> NewColumn = new ArrayList<Cell>();
            for (int j = 0; j < columns; j++) {
                Cell s = new Cell(i, j);
                NewColumn.add(s);
            }
            Cells.add(NewColumn);
        }

    }

    public void AddElement(Entity element, int row, int column) {
        Cells.get(row).get(column).AddElement(element);
    }

    public void RemoveElement(int element_i, int row, int column) {
        Cells.get(row).get(column).Remove(element_i);
    }

    public void MoveElement(Entity element, Cell currentCell, Cell destinationCell) {
        // get coordinates of first cell
        Cells.get(currentCell.getRow()).get(currentCell.getColumn()).Remove(element);
        Cells.get(destinationCell.getRow()).get(destinationCell.getColumn()).AddElement(element);

    }

    public void MoveElement(int element_i, int i_row, int i_col, int f_row, int f_col) {
        Entity element = Cells.get(i_row).get(i_col).GetElement(element_i);
        Cells.get(i_row).get(i_col).Remove(element_i);
        Cells.get(f_row).get(f_col).AddElement(element);
    }

    

    public boolean SquareExist(int row, int column) {
        return ((-1 < row) && (row < ROWS) && (-1 < column) && (column < COLUMNS));
    }

    public ArrayList<Entity> GetElements(Cell cell) {
        return Cells.get(cell.getRow()).get(cell.getColumn()).getElements();
    }

    public ArrayList<Entity> GetElements(int row, int column) {
        return Cells.get(row).get(column).getElements();
    }

    public int getRows() {
        return ROWS;
    }

    public int getColumns() {
        return COLUMNS;
    }
}
