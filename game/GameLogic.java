package game;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private ArrayList<Human> playersArrayList = new ArrayList<>();
    private ArrayList<Human> deadPlayerList = new ArrayList<>();
    private ArrayList<Human> reproducePlayerList = new ArrayList<>();

    private final int FOODCOUNT;
    private int currentFood;
    private final Board board;
    private static final Random rand = new Random();
    private static final int MAXSPEED = 6;

    GameLogic(Board board, int initialPlayers, int foodAmount) {
        this.board = board;
        this.FOODCOUNT = foodAmount;
        for (int i = 0; i < initialPlayers; i++) {
            Human player;
            Cell cell = board.getCell(randomPosition(), randomPosition());
            player = new Human(cell, 3, 5, randomTrueFalse());
            playersArrayList.add(player);
            board.AddHuman(player, cell);
        }
        int i = 0;
        while (i < FOODCOUNT) {
            Cell cell = board.getCell(randomPosition(), randomPosition()); // No need to store food if we want to keep
                                                                           // it at a fixed amount, if x food is eaten
                                                                           // at the end of a turn x food will be added
                                                                           // back
            if (!(cell.hasFood())) {
                cell.foodAdded();
                i++;
            }
        }
        currentFood = FOODCOUNT;
    }

    public void nextTurn() { //key turn logic
        // resets speed for all alive players
        reset();
        spawnFood();
        //System.out.println("b" + currentFood);
        for (int i = 0; i < MAXSPEED; i++) { 
            nextSubTurn();
        }
        deathFromHunger();

        // simulating eating food.
        for (int i = 0; i < playersArrayList.size(); i++) {
            
            Cell currCell = playersArrayList.get(i).GetCell();
            if (currCell.hasFood()) {

                ArrayList<Human> players = currCell.getHumans();
                foodConflict(0, 1, players); 
                playersArrayList.get(i).getCell().foodRemoved();
                currentFood--;
            }
        }
        finalizeDeath();
        reproduceAll();
        //System.out.println("e" + currentFood);
    }

    private void nextSubTurn() {
        for (int i = 0; i < playersArrayList.size(); i++) {
            playersArrayList.get(i).pickMove();
        }
        for (int i = 0; i < playersArrayList.size(); i++) {
            playersArrayList.get(i).makeMove();
        }
    }

    private void foodConflict(int indx1, int indx2, ArrayList<Human> conflictList) {
        
        if (indx2 >= conflictList.size()) {
            // kill all except indx1, indx2 (kills all if indx1, indx2 are out of range) 
            deathInConflict(indx1, indx2, conflictList);
            return;
        }
        boolean player1 = conflictList.get(indx1).isSocial();
        boolean player2 = conflictList.get(indx2).isSocial();

        if (player1) {
            if (player2) {
                // kill all except indx1 & indx2
                deathInConflict(indx1, indx2, conflictList);
                return; 
            } else {
                // indx 1 dies
                indx1 = indx2;
                indx2++;
            }
        } else {
            if (player2) {
                //second dies
                indx2++;
            } else {
                // both die
                indx1 = indx2+1;
                indx2 = indx1+1;
            }      
        }
        foodConflict(indx1, indx2, conflictList);
    }

    private void deathInConflict(int indx1, int indx2, ArrayList<Human> conflictList) {
        for (int i = 0; i < conflictList.size(); i++) {
            if (i == indx1 || i == indx2) {
                if (i == indx1) {
                    reproducePlayerList.add((conflictList.get(i)));
                }
                continue;
            }
            deadPlayerList.add(conflictList.get(i));
            }
    }
    
    private void deathFromHunger() {
        // gets all dead players 
        for (int i = 0; i < playersArrayList.size(); i++) {
            if (!playersArrayList.get(i).getCell().hasFood()) {
                deadPlayerList.add(playersArrayList.get(i));
            }
        }
    }

    private void finalizeDeath() { //removes dead players from players list and cells' elements list
        for (int i = 0; i < deadPlayerList.size(); i++) {
            deadPlayerList.get(i).death();
            playersArrayList.remove(deadPlayerList.get(i));
        }
        System.gc();
        deadPlayerList.clear();
    }

    private void reset(){ // resets speed
        for (int i = 0; i < playersArrayList.size(); i++) {
            playersArrayList.get(i).resetSpeed();
        }
    }

    private void spawnFood() { // spawns food 
        while (currentFood < FOODCOUNT) {
            Cell cell = board.getCell(randomPosition(), randomPosition());
            if (!(cell.hasFood())) {
                cell.foodAdded();
                currentFood++;
            }
        }
    }

    private void reproduceAll() {
        //System.out.println("init len" + playersArrayList.size());
        //System.out.println("init rep" + reproducePlayerList.size());
        for (int i = 0; i < reproducePlayerList.size(); i++) {
            reproduce(reproducePlayerList.get(i));
        }
        
        reproducePlayerList.clear();
        //System.out.println("final len" + playersArrayList.size());
    }

    private int randomPosition() {
        return rand.nextInt(board.getRows()); // AAnother function will be needed if the n rows != n columns
    }

    private boolean randomTrueFalse() {
        return rand.nextBoolean();
    }

    public ArrayList<Human> getPlayers() {
        return playersArrayList;
    }

    public void reproduce(Human human) {
        //System.out.println("reproduced");
        Human son = human.reproduce();
        playersArrayList.add(son);
    }
}
