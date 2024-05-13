package game;

public class Food extends Entity {
    private int amount;

    public Food(Cell cell, int amount){
        super(cell);
        this.amount = amount;

    }
    @Override
    public void death(){
        amount--;
        if (amount<=0){
            super.death();
        }
    }

    public int getAmount() {
        return amount;
    }
}
