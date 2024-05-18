package game;

import java.util.Random;

public class Helper {
    private static Random rand = new Random();

    public static double getDistance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1-y2, 2));
    }
    // Move in cols axis if true otherwise row.
    public static boolean  randomizeMovement(int distanceRows, int distanceColumns) {
        if(distanceRows == 0){
            return true;
        }
        else if(distanceColumns == 0){
            return false;
        }
        return rand.nextBoolean();
    }
}
