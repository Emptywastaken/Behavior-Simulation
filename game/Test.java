package game;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(20,20);
        GameLogic gl = new GameLogic(board, 5, 5);
        System.out.println(board.getFoodVision(gl.getPlayers().get(0), 50).get(0).getRow());
        System.out.println(board.getFoodVision(gl.getPlayers().get(0), 50).get(0).getColumn());
        System.out.println(gl.getPlayers().get(0).getRow());
        System.out.println(gl.getPlayers().get(0).getColumn());
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        gl.nextTurn();
        System.out.println(gl.getPlayers().get(0).getRow());
        System.out.println(gl.getPlayers().get(0).getColumn());   
    }
}
