package server;

import client.gui.WAMgame;
import common.WAMProtocol;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class represents a client handler to assist server in handling multiple
 * clients.
 */
public class ClientHandler implements Runnable{
    /** The server using this handler */
    private WAMServer server;
    /** Client socket */
    private final Socket socket;
    /** Scanner takes in messages from client */
    private final Scanner scanner;
    /** Printstream sends messages to client */
    private final PrintStream printer;
    /** # of rows */
    private int rows;
    /** # of columns */
    private int cols;
    /** # of players */
    private int player;
    /** handler ID */
    private int ID;
    /** the board/game */
    private WAMgame board;
    /** number of points this player is hard */
    private int point;

    /**
     * ClientHandler constructor
     * @param socket handler's socket
     * @param server handler's server
     * @param rows # of rows
     * @param cols # of columns
     * @param player # of players
     * @param id ID number of client
     * @param board game/board
     * @throws IOException
     */
    public ClientHandler(Socket socket, WAMServer server, int rows, int cols, int player, int id, WAMgame board)  throws IOException {
        this.socket = socket;
        //allows us to call methods from the server, which is how we can share data between threads
        this.server = server;
        scanner = new Scanner(socket.getInputStream());
        printer = new PrintStream(socket.getOutputStream());
        this.rows = rows;
        this.cols = cols;
        this.player = player;
        this.ID = id;
        this.point = 0;
        this.board = board;

    }


    /**
     * This method sends a welcome message to the client including the # of
     * rows, columns, players, and it's ID
     */
    public void welcome() {
        printer.println(WAMProtocol.WELCOME + " " + rows + " " + cols + " " + player + " " + ID);
        }


    /**
     * This method runs the handler, communicating with the client until
     * the game ends.
     */
    public  void run(){
        while(true){
            if(scanner.hasNext()){
                String request = scanner.next();
                if(request.equals(WAMProtocol.WHACK)){
                    int m = scanner.nextInt();
                    int p = scanner.nextInt();
                    if(board.getMoleStatus(m)){
                        this.server.down(m);
                        addPoint();

                    }
                    else{
                        subtractPoint();
                        server.down(m);
                    }
                    server.updatescores();
                }

            }
        }
        }


    /**
     * Sends a message to the client to send a mole up
     * @param mole mole number
     */
    public void MoleUp(int mole) {
         printer.println(WAMProtocol.MOLE_UP + " " + mole);
     }

    /**
     * Sends a message to the client to send a mole down
     * @param mole mole number
     */
    public void MoleDown(int mole){
        this.board.moleDown(mole);
        printer.println(WAMProtocol.MOLE_DOWN + " " + mole);
    }

    /**
     * Takes away a point from point total
     */
    public void subtractPoint(){
        this.point-=1;
    }

    /**
     * adds two points to point total
     */
    public void addPoint(){
        this.point+=2;
    }


    /**
     * Sends score message to client with updated score[]
     * @param scores int[] of scores
     */
    public void score(int[] scores){
        String sc = "";
        for(int s=0;s< scores.length;s++){
            sc += scores[s] + " ";
        }
        printer.println(WAMProtocol.SCORE + " " + sc);
    }


    /**
     * returns number of points this client has.
     * @return
     */
    public int getPoint(){
        return this.point;
    }

    /**
     * returns this client handler's ID
     * @return
     */
    public int getID(){
        return this.ID;
}

    /**
     * Sends a game tied message to client
     */
    public void GameTied(){
    printer.println(WAMProtocol.GAME_TIED);
    close();
}

    /**
     * Sends a game won message to client
     */
    public void GameWon(){
        printer.println(WAMProtocol.GAME_WON);
        close();
    }
    /**
     * Sends a game lost message to client
     */
    public void GameLost(){
        printer.println(WAMProtocol.GAME_LOST);
        close();
    }

    /**
     * Closes connection
     */
    public void close() {
        try {
            socket.close();
        }
        catch(IOException ie){
            System.out.println(ie);
        }
    }
}

