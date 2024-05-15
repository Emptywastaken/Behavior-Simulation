package game;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private ArrayList<Entity> playeArrayList = new ArrayList<>();
    private int foodCount;
    private Board board;

    GameLogic(Board board, int initialPlayers, int foodAmount) {
        this.board = board;
        this.foodCount = foodAmount;
        for (int i = 0; i < initialPlayers; i++) {
            if (randomZeroOrOne() == 0) { // Randomly pick if the player is greedy or social, currently not implemented
                Cell cell = board.getCell(randomPosition(), randomPosition());
                Entity player = new Entity(cell);
                playeArrayList.add(player);
            } else {
                Cell cell = board.getCell(randomPosition(), randomPosition());
                Entity player = new Entity(cell);
                playeArrayList.add(player);
            }
        }
        int i = 0;
        while (i < foodCount) {
            Cell cell = board.getCell(randomPosition(), randomPosition()); // No need to store food if we want to keep
                                                                           // it at a fixed amount, if x food is eaten
                                                                           // at the end of a turn x food will be added
                                                                           // back
            if (!(cell.hasFood())) {
            }
            Food food = new Food(cell);
            cell.foodAdded();
            i++;
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

    public ArrayList<Entity> getPlayers() {
        return playeArrayList;
    }
}
