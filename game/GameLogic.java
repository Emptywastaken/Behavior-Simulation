package game;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private ArrayList<Human> playeArrayList = new ArrayList<>();
    private int foodCount;
    private int currentFood;
    private final Board board;

    GameLogic(Board board, int initialPlayers, int foodAmount) {
        this.board = board;
        this.foodCount = foodAmount;
        for (int i = 0; i < initialPlayers; i++) {
            Cell cell = board.getCell(randomPosition(), randomPosition());
            if (randomZeroOrOne() == 0) { // Randomly pick if the player is greedy or social, currently not implemented
                Human player = new Human(cell, 3, 5);
                playeArrayList.add(player);
                board.AddHuman(player, cell);
            } else {
                Human player = new Human(cell, 3, 5);
                playeArrayList.add(player);
                board.AddHuman(player, cell);

            }
        }
        int i = 0;
        while (i < foodCount) {
            Cell cell = board.getCell(randomPosition(), randomPosition()); // No need to store food if we want to keep
                                                                           // it at a fixed amount, if x food is eaten
                                                                           // at the end of a turn x food will be added
                                                                           // back
            if (!(cell.hasFood())) {
                cell.foodAdded();
                i++;
            }
        }
        currentFood = foodCount;
    }

    public void nextTurn() {
        for (int i = 0; i < playeArrayList.size(); i++) {
            playeArrayList.get(i).resetSpeed();
        }
        while (currentFood < foodCount) {
            Cell cell = board.getCell(randomPosition(), randomPosition());
            if (!(cell.hasFood())) {
                cell.foodAdded();
                currentFood++;
            }
        }

        for (int i = 0; i < 5; i++) { // Code should be improved
            nextSubTurn();
        }
        ArrayList<Human> deadPlayer = new ArrayList<>();
        for (int i = 0; i < playeArrayList.size(); i++) {
            if (!playeArrayList.get(i).getCell().hasFood()) {
                deadPlayer.add(playeArrayList.get(i));
            }
        }
        for (int i = 0; i < playeArrayList.size(); i++) {
            if (playeArrayList.get(i).getCell().hasFood()) {
                playeArrayList.get(i).getCell().foodRemoved();
                currentFood--;
            }
        }
        for (int i = 0; i < deadPlayer.size(); i++) {
            deadPlayer.get(i).death();
            playeArrayList.remove(deadPlayer.get(i));
        }
        System.gc();

    }

    private void nextSubTurn() { // TODO add logic for different speeds, every player picks the move at the same
                                 // time then they all make it
        for (int i = 0; i < playeArrayList.size(); i++) {
            playeArrayList.get(i).pickMove();
        }
        for (int i = 0; i < playeArrayList.size(); i++) {
            playeArrayList.get(i).makeMove();
        }
    }

    private int randomPosition() {
        Random rand = new Random();
        return rand.nextInt(board.getRows()); // AAnother function will be needed if the n rows != n columns
    }

    private int randomZeroOrOne() {
        Random rand = new Random();
        return rand.nextInt(2);
    }

    public ArrayList<Human> getPlayers() {
        return playeArrayList;
    }
}
