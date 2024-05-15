package game;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(5,5);
        GameLogic gl = new GameLogic(board, 3, 2);
        gl.nextTurn();
    }

}
