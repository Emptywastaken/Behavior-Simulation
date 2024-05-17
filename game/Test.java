package game;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(1000, 1000);
        GameLogic gl = new GameLogic(board, 5000, 100000);
        gl.nextTurn();
        System.out.println(gl.getPlayers().size());
        gl.nextTurn();
        System.out.println(gl.getPlayers().size());
        gl.nextTurn();
        System.out.println(gl.getPlayers().size());
        gl.nextTurn();
        System.out.println(gl.getPlayers().size());
        gl.nextTurn();
        System.out.println(gl.getPlayers().size());
        gl.nextTurn();
        System.out.println(gl.getPlayers().size());
        gl.nextTurn();
        System.out.println(gl.getPlayers().size());
        gl.nextTurn();
        System.out.println(gl.getPlayers().size());
        gl.nextTurn();
        System.out.println(gl.getPlayers().size());
    }
}
