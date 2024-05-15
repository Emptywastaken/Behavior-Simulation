package game;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private ArrayList<Human> playeArrayList = new ArrayList<>();
    private int foodCount;
    private Board board;

    GameLogic(Board board, int initialPlayers, int foodAmount) {
        this.board = board;
        this.foodCount = foodAmount;
        for (int i = 0; i < initialPlayers; i++) {
            Cell cell = board.getCell(randomPosition(), randomPosition());
            if (randomZeroOrOne() == 0) { // Randomly pick if the player is greedy or social, currently not implemented
                Human player = new Human(cell, 2, i);
                playeArrayList.add(player);
                board.AddHuman(player, cell);
            } else {
                Human player = new Human(cell, 2, i);
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

    }
    
    public void nextTurn(){ //TODO add logic for different speeds, every player picks the move at the same time then they all make it
        for (int i = 0; i < playeArrayList.size(); i++){
            playeArrayList.get(i).pickMove();
        }
        for (int i = 0; i < playeArrayList.size(); i++){
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
