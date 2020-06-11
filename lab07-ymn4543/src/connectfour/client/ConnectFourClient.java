package connectfour.client;

import connectfour.ConnectFour;
import connectfour.ConnectFourException;
import connectfour.ConnectFourProtocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents the client side of a Connect Four game. Establishes a connection
 * with the server and then responds to requests from the server (often by
 * prompting the real user).
 *
 * @author Youssef Naguib </ymn4543@rit.edu>
 */
public class ConnectFourClient {
    /** Client socket */
    private Socket socket;
    /** Client scanner */
    private Scanner scanner;
    /** Client inout stream */
    private InputStream inputStream;
    /** Client output stream */
    private OutputStream outputStream;
    /** Client print stream */
    private PrintStream printStream;

    /**
     * Client Constructor
     * @param hostname is the hostname or ip address of server
     * @param port is the port number of the server the client will connect to
     */
    public ConnectFourClient(String hostname, int port) throws IOException {
        try{
            this.socket = new Socket(hostname, port);
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
            this.scanner = new Scanner(inputStream);
            this.printStream = new PrintStream(outputStream);

        }
        catch(UnknownHostException uh){
            System.out.println("error");
        }

    }

    /**
     * A client is a player that will connect to the server and play the game.
     * The client will prompt the user when it is their turn to make a move,
     * and inform the player of the game's progress. The client will only make moves
     * when instructed to do so by the server.
     * @throws NoSuchElementException element doesn't exist.
     * @throws ConnectFourException If there is a problem creating the client
     *      *                              or connecting to the server.
     * @throws IOException  If there is an io interruption.
     */
    public void play() throws NoSuchElementException,ConnectFourException,IOException {
        ConnectFour connectFour = new ConnectFour(6,7);
        while(true){
            String x = scanner.next();
            if(x.equals(ConnectFourProtocol.CONNECT)){
                System.out.println("connected!");
                continue;
            }
            if(x.equals(ConnectFourProtocol.MAKE_MOVE)){
                System.out.println("Your turn! Enter a column: ");
                Scanner n = new Scanner(System.in);
                int c = n.nextInt();
                printStream.println(ConnectFourProtocol.MOVE + " " + c);
                continue;
            }
            if(x.equals(ConnectFourProtocol.MOVE_MADE)){
                int col = scanner.nextInt();
                connectFour.makeMove(col);
                System.out.println("A move has been made in column " + col);
                System.out.println(connectFour.toString());
                continue;
            }
            if(x.equals(ConnectFourProtocol.GAME_WON)){
                System.out.println("You win! Yay! ");
                break;
            }
            if(x.equals(ConnectFourProtocol.GAME_LOST)){
                System.out.println("Sorry, You lose!  ");
                break;
            }
            if(x.equals(ConnectFourProtocol.GAME_TIED)){
                System.out.println("You have tied! Game over! ");
                break;
            }
            if(x.equals(ConnectFourProtocol.ERROR)){
                System.out.println(scanner.nextLine());
                System.out.println("error");
                socket.close();
                break;
            }
            else{
                System.out.println("ok");
            }

        }
    }

    /**
     * Starts a new {@link ConnectFourClient}.
     *
     * @param args Used to specify the hostname and port of the Connect Four
     *             server through which the client will play.
     * @throws ConnectFourException If there is a problem creating the client
     *                              or connecting to the server.
     */
    public static void main(String[] args) throws ConnectFourException {
        if (args.length != 2) {
            System.out.println(
                    "Usage: java ConnectFourClient hostname port");
            System.exit(1);
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            ConnectFourClient connectFourClient = new ConnectFourClient(hostname, port);
            connectFourClient.play();
        }
        catch(IOException ea){
            System.out.println("fail");
        }
    }
}
