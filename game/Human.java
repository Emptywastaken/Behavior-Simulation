package game;

public abstract class Human extends Entity {

     
    public boolean readyToReplicate = false;
    public boolean hasFood = false;
    private int turnsLeft;
    public final int stregth;
    private final int vision;
    private final int speed;


    public Human(Cell cell, int vision, int stregth, int turnsLeft, int speed){
        super(cell);
        this.vision = vision;
        this.stregth = stregth;
        this.turnsLeft = turnsLeft;
        this.speed = speed;
    }
    /* 
    public Human(Cell cell, Human parent) {
        super(cell);
    } */

    // Pick direction function depends on the character of the human
    // greedy, generous etc...
    public abstract Cell PickDirection();
    public abstract boolean ToAttack();

    // TODO: consider case when human needs to choose who gets the food.
    public abstract boolean shareFood(); 
    
    // added in case we consider more factors when checking for replication
    public abstract void Replicate();
    
    public void Move(Cell new_cell) throws OutOfRangeException {
        int x = super.GetX();
        int y = super.GetX();
        double dist = Math.sqrt(x*x + y*y);

        if(dist > speed) {
            throw new OutOfRangeException();    
        }
        super.ChangeCell(new_cell);
    }   

    @Override
    public void Death() {
        if(turnsLeft <= 0) {
            super.Death();
        }
	}
    // TODO: randomness eg.(lambda*stregthDiffernce) chance to alternate the outcome.
    public void Attack(Human target){
        if (this.stregth == target.stregth) {
            this.turnsLeft--;
            target.turnsLeft--;
        } else if (this.stregth > target.stregth) {
            target.turnsLeft--;
        } else {
            this.turnsLeft--;
        }
    }

    
    



}