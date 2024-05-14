package game;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(10, 10);
        Entity entity1 = new Entity(board.getCell(1, 1));
        Entity entity2 = new Entity(board.getCell(1, 2));
        Entity entity3 = new Entity(board.getCell(2, 2));
        Entity entity4 = new Entity(board.getCell(2, 2));
        System.out.println(board.getVision(entity1, 2));

    }

}
