package game;

import java.util.ArrayList; //Board Class is made of a 2d arraylist that stores all the cells,
                            // the number of rows and columns is stored

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
                Cell s = new Cell(i, j, this);
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

    public boolean cellInRange(int row, int column) {
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

    public Cell getCell(int row, int column) {
        return Cells.get(row).get(column);
    }

    public ArrayList<Entity> getVision(Entity human, int vision) {
        int row = human.getY();
        int column = human.getX();
        // int vision = human.getVision();
        ArrayList<Entity> viewedEntity = new ArrayList<>();
        ArrayList<Entity> Elements = new ArrayList<>();

        for (int i = 0; i < vision + 1; i++) {
            if ((cellInRange(row + vision - i, column)) && (i != vision)) {
                Elements = Cells.get(row + vision - i).get(column).getElements();
                viewedEntity.addAll(Elements);
            }
            if ((cellInRange(row - vision + i, column)) && (i != vision)) {
                Elements = Cells.get(row - vision + i).get(column).getElements();
                viewedEntity.addAll(Elements);
            }
            for (int j = 1; j < i + 1; j++) {
                if (cellInRange(row + vision - i, column + j)) {
                    Elements = Cells.get(row + vision - i).get(column + j).getElements();
                    viewedEntity.addAll(Elements);
                }
                if ((cellInRange(row + vision - i, column - j)) && (i != vision)) {
                    Elements = Cells.get(row + vision - i).get(column - j).getElements();
                    viewedEntity.addAll(Elements);
                }
                if ((cellInRange(row - vision + i, column + j)) && (i != vision)) {
                    Elements = Cells.get(row - vision + i).get(column + j).getElements();
                    viewedEntity.addAll(Elements);
                }
                if (cellInRange(row - vision + i, column - j)) {
                    Elements = Cells.get(row - vision + i).get(column - j).getElements();
                    viewedEntity.addAll(Elements);
                }
            }
        }
        return viewedEntity;
    }
}
