package game;

public class Game {
    public static void main(String[] args) {
        GameUI ui = new GameUI(40, 100, 600, 0.1, 999);
        ui.setVisible(true);
    }
}