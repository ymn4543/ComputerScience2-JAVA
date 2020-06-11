package client.gui;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static common.WAMProtocol.*;

/**
 * This class acts as the mediator between the server and the player.
 * (Controller)
 */
public class WAMNetworkClient implements Runnable {
    /** Client socket */
    private Socket socket;
    /** Client scanner that takes in messages from server */
    private Scanner netIn;
    /** Printstream that sends messages out to server */
    private PrintStream netOut;
    /** Client input stream */
    private InputStream in;
    /** Client output stream */
    private OutputStream out;
    /** # of rows */
    private int rows;
    /** # of columns */
    private int columns;
    /** # of players */
    private int player;
    /** Client ID */
    private int id;
    /** whether game is finished or not */
    private boolean go;
    /** Whether client is ready to receive more messages
     * (If it has received welcome or not) */
    private boolean ready;
    /** This client's game/board */
    private WAMgame board;
    /** Integer used for tracking game status
     * 0 for game in progress
     * 1 for game won
     * 2 for game lose
     * 3 for game tied */
    private int gamestatus;

    /**
     * WAMNetworkClient constructor
     * @param host host name/address
     * @param port port number
     * @throws IOException
     */
    public WAMNetworkClient(String host, int port) throws IOException {
        //Connects to the server and opens necessary I/OStreams
        this.socket = new Socket(host, port);
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        this.netIn = new Scanner(in);
        this.netOut = new PrintStream(out);
        this.go = true;
        this.ready = false;
        System.out.println("Connected to server " + this.socket);
        this.gamestatus = 0;

    }

    /**
     * Starts the listener thread.
     */
    public void startListener() {
        new Thread(() -> this.run()).start();
    }

    /**
     * This method takes in messages from the server while the game is running.
     */
    public void run(){
        while (go) {
            try {
                while (board.getStatus())//while the game is running
                {
                    if (netIn.hasNext()) {
                        String request = this.netIn.next();
                        String arguments = this.netIn.nextLine().trim();
                        String[] arglist = arguments.split(" ");
                        switch (request) {

                            /*Same as makeParams, in case server sends WELCOME again. Might become an error later,
                            but should be fine for now
                             */
                            case WELCOME:
                                this.rows = Integer.parseInt(arglist[0]);
                                this.columns = Integer.parseInt(arglist[1]);
                                this.player = Integer.parseInt(arglist[2]);
                                this.id = Integer.parseInt(arglist[3]);
                                this.board = new WAMgame(rows, columns,player);
                                this.ready = true;
                                break;

                            //Server provides mole to be raised, tells board to raise that mole
                            case MOLE_UP:
                                int moleup = Integer.parseInt(arguments);
                                board.moleUp(moleup);
                                break;

                            //Server provides mole to be lowered, tells board to lower that mole
                            case MOLE_DOWN:
                                int moledown = Integer.parseInt(arguments);
                                board.moleDown(moledown);
                                break;

                            case SCORE:
                                int [] scores = new int[player];
                                String[] str = arguments.split(" ");
                                for(int p =0;p<player;p++){
                                    scores[p] = Integer.parseInt(str[p]);

                                }
                                board.updateScores(scores);
                                break;

                            //The 3 different ways the game can end, currently all functionally the same
                            case GAME_WON:
                                this.gamestatus = 1;
                                board.endGame();
                                this.stop();
                                close();
                                break;

                            case GAME_LOST:
                                this.gamestatus = 2;
                                board.endGame();
                                this.stop();
                                close();
                                break;

                            case GAME_TIED:
                                this.gamestatus = 3;
                                board.endGame();
                                this.stop();
                                close();
                                break;

                            //in case something happens with the server
                            case ERROR:
                                board.endGame();
                                this.stop();
                                close();
                                break;

                            default:
                                System.err.println("Unrecognized request: " + request);
                        }
                    }
                }
            }
            catch(NoSuchElementException nse ){
                    // Looks like the connection shut down.
                    System.out.println(nse);
                    break;
                }
            catch(Exception e ){
                    System.out.println(e);
                    this.close();
                }
            }
        }



    /**
     * Tells server when a mole has been clicked on so it can relay that data to the players
     * @param moleNumber the ID of the mole that was clicked on
     */
    public void whack(int moleNumber){
        netOut.println(WHACK + " " + moleNumber + " " + getId());
        this.board.moleDown(moleNumber);
    }


    /**
     * returns Gamestatus int
     * @return integer
     * 0 for game in progress
     * 1 for game won
     * 2 for game lose
     * 3 for game tied
     */
    public int getGamestatus(){
        return this.gamestatus;
    }

    /**
     * This method closes the connection.
     */
    public void close(){
        try {
            this.board.close();
            this.socket.close();
            this.go = false;
            this.stop();
        }
        catch (IOException io){
            System.out.println("Done");
        }
    }

    /**
     * Use this method in the GUI class to receive the first message from the server, which contains the
     * information needed to create the board and buttons. Called before the start method happens.
     */
    public void makeParams() throws IOException {
        if(go){
            String request = this.netIn.next();
            String arguments = this.netIn.nextLine().trim();
            String[] arglist = arguments.split(" ");
            System.out.println(Thread.currentThread());

            //Checking to make sure the request is the welcome, otherwise we have none of the numbers we need
            if (request.equals(WELCOME)) {
                this.rows = Integer.parseInt(arglist[0]);
                this.columns = Integer.parseInt(arglist[1]);
                this.player = Integer.parseInt(arglist[2]);
                this.id = Integer.parseInt(arglist[3]);
                this.ready = true;
                this.board = new WAMgame(rows,columns,player);

            }

        }
        else{
            System.out.println("Error in communication with server: First request was not WELCOME");
        }
    }


    /**
     * @return the Wack-a-Mole board object being used
     */
    public WAMgame getBoard(){
        return this.board;
    }

    /**
     * @return boolean indicating whether or not client has been welcomed by the server and received necessary info
     */
    public boolean getReady(){
        return ready;
    }

    /**
     * Ends game
     */
    public void stop(){
        this.go = false;

    }

    /**
     * @return number of rows in the game board
     */
    public int getRows(){
            return this.rows;
    }

    /**
     * @return number of columns in the game board
     */
    public int getColumns(){
        return this.columns;
    }

    /**
     * @return the player number assigned by the server
     */
    public int getPlayer(){
        return this.player;
    }

    /**
     * @return client ID
     */
    public int getId(){
        return this.id;
    }


}

