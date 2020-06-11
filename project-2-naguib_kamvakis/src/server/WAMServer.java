package server;

import client.gui.MoleThread;
import client.gui.WAMgame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * This  is the server class of the game.
 */
public class WAMServer extends Thread  {
    /** The server socket */
    private ServerSocket serverSocket;
    /** list of threads */
    private ArrayList<Thread> threads;
    /** Array of handlers */
    private ClientHandler[] handlers;
    /** The game/board */
    private WAMgame board;
    /** The duration in seconds of the game */
    double clock;
    /** # of rows in game */
    int rows;
    /** # of columns in game */
    int columns;
    /** array of moles */
    private MoleThread[] moles;
    /** # of players */
    int players;


    /**
     * WAMServer constructor
     * @param port port number
     * @param rows # of rows
     * @param columns # of columns
     * @param players # of players
     * @param runtime # of seconds game will run
     * @throws IOException
     */
    public WAMServer(int port, int rows, int columns, int players, double runtime) throws IOException {
        this.serverSocket = new ServerSocket(port);
        threads = new ArrayList<>();
        this.clock = runtime;
        this.rows = rows;
        this.columns = columns;
        this.handlers = new ClientHandler[players];
        this.moles = new MoleThread[rows*columns];
        this.players = players;
        board = new WAMgame(rows, columns,players);
        int hold = 0;
        for(int i = 0; i < rows*columns; i++){
            MoleThread mole = new MoleThread(i,board,this);
            moles[i]=mole;
        }

        while(hold !=players)
        {
            Socket player = serverSocket.accept();
            ClientHandler handler = new ClientHandler(player,this, rows, columns, players, hold,this.board );
            handlers[hold] = handler;
            Thread client = new Thread(handler);
            threads.add(client);
            hold++;
        }
    }

    /**
     * Welcomes all players and starts game.
     */
    public void run(){
        for(ClientHandler ch: handlers){
            ch.welcome();
        }
        playGame();
    }


    /**
     * This method starts the game and the mole threads.
     * Once the game ends it sends win/loss/tie messages
     * to the correct players.
     */
    public void playGame() {
        CountDown countDown = new CountDown(clock);
        countDown.start();

        for(MoleThread mole: this.moles) {
            mole.setclock(countDown);
            mole.start();
        }
        for(Thread t : threads){
            t.start();
        }

        while(countDown.getSecondsLeft()!=0) {
            try {
                sleep(10);
            }
            catch (InterruptedException i){
                System.out.println(i);
            }
        }
        for(int m =0; m<rows*columns;m++){
            down(m);
        }
            int winner = handlers[0].getPoint();
            int WinnerID = 0;
            int ties = 0;
            for (ClientHandler cl : handlers) {
                int p = cl.getPoint();
                if (p == winner) {
                    ties += 1;
                    winner = cl.getPoint();
                }
                if (p > winner) {
                    winner = cl.getPoint();
                    WinnerID = cl.getID();
                }
            }
            if (ties == players) {
                for (ClientHandler cl : handlers) {
                    cl.GameTied();
                }
            } else {
                for (ClientHandler cl : handlers) {
                    if (cl.getID() == WinnerID) {
                        cl.GameWon();
                    } else {
                        cl.GameLost();
                    }
                }
            }
            try {
                serverSocket.close();
                System.exit(0);
            }
            catch (IOException io) {
                System.out.println(io);
            }
        }


    /**
     * this method updates the player scores.
     */
    public void updatescores(){
        int[] scores = new int[players];
        for(ClientHandler cl: handlers ){
            scores[cl.getID()]= cl.getPoint();
        }
        for (ClientHandler c:handlers){
            c.score(scores);
        }
    }


    /**
     * This method sends a mole up
     * @param mole mole number
     */
    public void up(int mole){
        for (ClientHandler cl : handlers) {
            cl.MoleUp(mole);
        }
    }


    /**
     * This method sends a mole down
     * @param mole mole number
     */
    public void down(int mole){
        for (ClientHandler cl : handlers) {
            cl.MoleDown(mole);
        }

    }


    /**
     * This is the main method for the server.
     * It takes in command line arguments as follows:
     * Port Rows Columns Players Clock
     * @param args array of command lines arguments
     */
    public static void main(String[] args){
        if (args.length != 5) {
            System.out.println(
                    "error");
            System.exit(1);
        }
        if(Integer.parseInt(args[3])<1){
            System.out.println(
                    "not enough players");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        int rows = Integer.parseInt(args[1]);
        int columns = Integer.parseInt(args[2]);
        int players = Integer.parseInt(args[3]);
        double runtime = Double.parseDouble(args[4]);
        try {

            WAMServer wamServer = new WAMServer(port,rows,columns,players,runtime);

            wamServer.run();
        }
        catch(IOException ea){
            System.out.println("fail");
        }
    }

}
