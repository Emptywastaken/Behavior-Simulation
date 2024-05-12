package game;

public abstract class Human extends Entity {

     
    private boolean replicate = false;
    private int turns_left;
    private int stregth;
    private int vision;
    private int speed;

    public Human(Cell cell, int vision, int stregth, int turns_left, int speed){
        super(cell);
        this.vision = vision;
        this.stregth = stregth;
        this.turns_left = turns_left;
        this.speed = speed;
    }

    // Pick direction function depends on the character of the human
    // greedy, generous etc...
    public abstract Cell pickDirection();

    public abstract boolean attack();
    // TODO: consider case when human needs to choose who gets the food.
    public abstract boolean shareFood(); 

    @Override
    public void move (Cell new_cell) throws OutOfRangeException {
        int x = super.getX();
        int y = super.getY();
        double dist = Math.sqrt(x*x + y*y);

        if (dist > speed) {
            throw new OutOfRangeException();    
        }
        super.move(new_cell); 
}

}