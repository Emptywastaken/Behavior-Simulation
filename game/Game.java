package game;

public class Game {
    public static void main(String[] args) {
        GameUI ui = new GameUI(40, 20, 1000, 0.15);
        ui.setVisible(true);
    }
}