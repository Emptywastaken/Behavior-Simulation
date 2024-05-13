package game;

public class Greedy extends Human{

    public Greedy(Cell cell, int vision, int stregth, int turnsLeft, int speed) {
        super(cell, vision, stregth, turnsLeft, speed);
    }
 

    @Override
    public Cell pickDirection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean toEat() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean shareFood() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void replicate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
