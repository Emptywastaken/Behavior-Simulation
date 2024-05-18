package game;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(10, 10);
        GameLogic gl = new GameLogic(board, 100, 100);
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
        gl.reproduce(gl.getPlayers().get(0));
        System.out.println(gl.getPlayers().size());
    }
}
