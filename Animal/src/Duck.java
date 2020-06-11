public class Duck implements Flyer,Swimmer extends Animal {
    public final static double FLY_SPEED_MS = 22.352;
    public final static double RUN_SPEED_MS = 13.94765;
    public final static double SWIM_SPEED_MS = 26.8224;
    private double wingspan;
    private int happiness;

    @Override
    public String toString() {
        return "Duck{" +
                "wingspan=" + wingspan +
                ", happiness=" + happiness +
                '}';
    }

    public int getHappiness() {
        return happiness;
    }

    public Duck(double wingspan, String name) {
        super();
        this.wingspan = wingspan;
        happiness = 0;
    }



    public void pet(int seconds){
        happiness +=seconds*wingspan;
    }

    @Override
    public void run(int seconds) {
        seconds * RUN_SPEED_MS;
    }

    @Override
    public String speak() {
        return toString() + " says, \"quack! quack!\"";
    }

    @Override
    public void fly(int seconds) {

    }

    @Override
    public double dive(int minutes) {
        return minutes*SWIM_SPEED_MS;
    }


}
