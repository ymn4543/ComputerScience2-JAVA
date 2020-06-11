public class car {
    //states
    private String maker;
    private int speed;

    //behaviors
    //1.constructors
    public car(String makerVal, int speedVal){
        maker = makerVal;
        speed = speedVal;
    }
    //2. mutators (setters
    public void setMaker(String makeVal){
        maker = makeVal;
    }
    public void setSpeed(int speedVal){
        speed = speedVal;
    }
    //3. Accessors (getters)
    public String getMaker() {
        return maker;
    }
    public int getSpeed(){
        return speed;
    }
    @Override
    public String toString(){
        return "maker=" + maker +", "+ "speed=" + speed;
    }
    public boolean equals(Object o){
        if(0 instanceof car){
            car other = (car)o;
            // this vs other
            return this.maker.equals(other.maker) && this.speed == other.speed;
        }
        return false;
    }

}
