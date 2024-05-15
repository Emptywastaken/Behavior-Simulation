package game;

public class Test {
    public static void main(String[] args) {
        Board board = new Board(2,2);
        GameLogic gl = new GameLogic(board, 1, 1);
        System.out.println(board.getFoodVision(gl.getPlayers().get(0), 2).get(0).getColumn());
        System.out.println(board.getFoodVision(gl.getPlayers().get(0), 2).get(0).getRow());
        System.out.println(gl.getPlayers().get(0).getX());
        System.out.println(gl.getPlayers().get(0).getY());
        System.out.println(board.getCell(0, 0).getHumans());
        System.out.println(board.getCell(1, 0).getHumans());
        System.out.println(board.getCell(0, 1).getHumans());
        System.out.println(board.getCell(1, 1).getHumans());
        gl.nextTurn();
        System.out.println(gl.getPlayers().get(0).getX());
        System.out.println(gl.getPlayers().get(0).getY());
        System.out.println(board.getCell(0, 0).getHumans());
        System.out.println(board.getCell(1, 0).getHumans());
        System.out.println(board.getCell(0, 1).getHumans());
        System.out.println(board.getCell(1, 1).getHumans());
        gl.nextTurn();
        System.out.println(gl.getPlayers().get(0).getX());
        System.out.println(gl.getPlayers().get(0).getY());
        System.out.println(board.getCell(0, 0).getHumans());
        System.out.println(board.getCell(1, 0).getHumans());
        System.out.println(board.getCell(0, 1).getHumans());
        System.out.println(board.getCell(1, 1).getHumans());
        gl.nextTurn();
        System.out.println(gl.getPlayers().get(0).getX());
        System.out.println(gl.getPlayers().get(0).getY());
        System.out.println(board.getCell(0, 0).getHumans());
        System.out.println(board.getCell(1, 0).getHumans());
        System.out.println(board.getCell(0, 1).getHumans());
        System.out.println(board.getCell(1, 1).getHumans());

    }

}
