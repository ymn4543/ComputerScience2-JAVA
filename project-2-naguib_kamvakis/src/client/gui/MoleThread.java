package client.gui;

import server.CountDown;
import server.WAMServer;
import java.util.Random;


/**
 * This class acts as a mole. It allows moles to run individually and pop
 * up and down randomly. Each mole on the board is represented as a thread.
 */
public class MoleThread extends Thread {
    /** Mole's unique id number*/
    private int id;
    /** Board that mole is a part of */
    private WAMgame game;
    /** The server running the mole thread */
    private WAMServer server;
    /** The duration of the game (in seconds)
     * This countdown class acts as a timer */
    private CountDown clock;


    /**
     * MoleThread constructor
     * @param id mole's id
     * @param game mole's board
     * @param wamServer mole's server
     */
    public MoleThread(int id, WAMgame game, WAMServer wamServer){
        this.id = id;
        this.game = game;
        this.server = wamServer;
    }

    /**
     * Clock setter
     * @param clock is how long the timer will last in seconds
     */
    public void setclock(CountDown clock)
    {
        this.clock=clock;
    }


    /**
     * This is the Mole Thread's run method. While the timer runs,
     * this mole will pop up and down randomly.
     */
    public void run() {
        while (clock.getSecondsLeft()!=0) {
            int sleeptime = randomNum(1, 5);
            int uptime = randomNum(2, 5);
            game.moleUp(id);
            server.up(id);

            try {
                Thread.sleep(uptime * 1000);


            } catch (InterruptedException ie) {
                System.out.println(ie);
            }

            if(game.getMoleAt(id)) {
                game.moleDown(id);
                server.down(id);
            }
            try {
                Thread.sleep(sleeptime * 1000);
            } catch (InterruptedException i) {
                System.out.println(i);
            }
        }
    }


    /**
     * This method generates a randum number between two bounds
     * @param min minimum bound
     * @param max maximum bound
     * @return a random integer within the bound
     */
    public static int randomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

}
