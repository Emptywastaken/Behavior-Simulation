package game;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(20, 20);
        GameLogic gl = new GameLogic(board, 5000, 120);
        /* 
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
        */
        for (int i = 0; i < 1000; i++) {
            gl.nextTurn();
        System.out.println(gl.getPlayers().size());
        }
    }
}
