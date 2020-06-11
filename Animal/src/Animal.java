/**
 * All animals can run and speak.
 *
 * @author RIT CS
 */
public abstract class Animal {
    /**
     * Animal runs for a certain number of seconds and returns the distance
     * travelled.
     *
     * @param seconds number of seconds to run
     * @return distance travelled
     */

    protected abstract void run(int seconds);

    /**
     * What the animal says when they speak.
     *
     * @return their message
     */
    public abstract String speak();

    public String toString(){
        return "Animal{name = "
    }

    protected String name;
    protected double distance;

    public Animal(String name){
        this.name = name;
    }


    public void runAndSpeak(int seconds){

    }

    public double getDistance(){
        return distance;
    }


    public void travel(double distance){

    }



}
