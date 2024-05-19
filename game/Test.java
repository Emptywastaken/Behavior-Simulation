package game;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(100, 100);
        GameLogic gl = new GameLogic(board, 20, 7000);
        /*
         * gl.nextTurn();
         * System.out.println(gl.getPlayers().size());
         * gl.nextTurn();
         * System.out.println(gl.getPlayers().size());
         * gl.nextTurn();
         * System.out.println(gl.getPlayers().size());
         * gl.nextTurn();
         * System.out.println(gl.getPlayers().size());
         * gl.nextTurn();
         * System.out.println(gl.getPlayers().size());
         */
        for (int i = 0; i < 10000; i++) {
            gl.nextTurn();
            System.out.println(gl.getCounters());

        }
    }
}
