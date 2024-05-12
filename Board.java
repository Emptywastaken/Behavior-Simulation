import java.util.ArrayList; //Board Class is made of a 2d arraylist that stores all the cells,
                            // the number of rows and columns is stored
//TODO update code once player class is created
public class Board {
 private final int rows;
 private final int columns;
 private ArrayList<ArrayList<Cell>> Cells = new ArrayList<ArrayList<Cell>>();

 Board(int rows, int columns) { //Constructor creates a Rows x Columns Array and stores it as Cells
     this.rows = rows;
     this.columns = columns;
     for (int i = 0; i < rows; i++ ){
         ArrayList<Cell> NewColumn = new ArrayList<Cell>();
         for (int j = 0; j < columns; j++){
             Cell s = new Cell(i, j);
             NewColumn.add(s);
         }
         Cells.add(NewColumn);
     }

 }
 public void AddElement(int element, int row, int column){
     Cells.get(row).get(column).AddElement(element);
 }
 public void RemoveElement(int element_i, int row, int column){
     Cells.get(row).get(column).Remove(element_i);
 }
 public void MoveElement(int element_i, int i_row, int i_col, int f_row, int f_col){
     int element = Cells.get(i_row).get(i_col).GetElement(element_i);
     Cells.get(i_row).get(i_col).Remove(element_i);
     Cells.get(f_row).get(f_col).AddElement(element);
 }
 public boolean SquareExist(int row, int column){
     return ((-1 < row) && (row < rows) && (-1 < column) && (column < columns));
 }
 public ArrayList<Integer> GetElements(int row, int column){
     return Cells.get(row).get(column).InCell();
    }
}
