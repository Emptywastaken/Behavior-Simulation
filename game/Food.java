package game;

public class Food extends Entity {
    private int amount;

    public Food(Cell cell, int amount){
        super(cell);
        this.amount = amount;

    }

    public void decreaseAmount(){
        amount--;
    }

    public int getAmount() {
        return amount;
    }
}
