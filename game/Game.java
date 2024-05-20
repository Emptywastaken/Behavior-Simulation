package game;

public class Game {
    public static void main(String[] args) {
        GameUI ui = new GameUI(100, 1000, 7000, 0.4);
        ui.setVisible(true);
    }
}