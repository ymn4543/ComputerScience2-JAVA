package server;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class acts as a stopwatch/countdown.
 */
public class CountDown {

    /** Timer */
    private Timer timer;
    /** how long the timer will last */
    private double countDown;
    /** How many seconds are left in the timer */
    private double secondsLeft;

    /**
     * Countdown constructor
     * @param seconds duration
     */
    public CountDown(double seconds) {
        timer = new Timer();
        this.countDown = seconds;
    }

    /**
     * This method starts the countdown.
     */
    public void start() {
        secondsLeft = countDown;
        // Decrease seconds left every 1 second.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsLeft--;
                if (secondsLeft == 0) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }


    /**
     * This method returns the # of seconds left in the countdown.
     * @return seconds lef (double)
     */
    public double getSecondsLeft() {
        return secondsLeft;
    }
}

