package connectfour.server;

import connectfour.ConnectFour;
import connectfour.ConnectFourException;
import connectfour.ConnectFourProtocol;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * The server waits for incoming client connections and pairs them off
 * to play the game.
 *
 * @author Youssef Naguib </ymn4543@rit.edu>
 */
public class ConnectFourServer {
    /**  Server's ServerSocket  */
    private ServerSocket serverSocket;
    /**  Player one (X) socket */
    private Socket socketx;
    /**  Player one (X) scanner */
    private Scanner XnetworkIn;
    /**  Player one (X) input stream */
    private InputStream Xin;
    /** Player one (X) output stream */
    private OutputStream Xout;
    /**  Player one (X) print stream */
    private PrintStream XnetworkOut;
    /** Player two (O) socket */
    private Socket socketo;
    /**  Player two (O) scanner */
    private Scanner OnetworkIn;
    /** Player two (O) input stream */
    private InputStream Oin;
    /**  Player two (O) output stream */
    private OutputStream Oout;
    /**  Player two (O) print stream */
    private PrintStream OnetworkOut;


    /**
     * Server Constructor
     * @param port is the port number the server will use
     */
    public ConnectFourServer(int port){
        try {
            this.serverSocket = new ServerSocket(port);

            this.socketx =serverSocket.accept();
            this.Xin = socketx.getInputStream();
            this.Xout = socketx.getOutputStream();
            this.XnetworkOut= new PrintStream(Xout);
            this.XnetworkIn = new Scanner(Xin);

            this.socketo = serverSocket.accept();
            this.Oin = socketo.getInputStream();
            this.Oout = socketo.getOutputStream();
            this.OnetworkOut= new PrintStream(Oout);
            this.OnetworkIn = new Scanner(Oin);

            XnetworkOut.println(ConnectFourProtocol.CONNECT);
            OnetworkOut.println(ConnectFourProtocol.CONNECT);
        }
        catch(IOException ea){
            System.out.println("error");
        }

    }

    /**
     * This method takes care of the gameplay from server side. Server acts as referee.
     * The server accepts connections from two clients. Once both clients are connected, the server
     * will prompt each client to make moves when it is their turn, and inform both clients of the
     * status of the game (moves made, when the game is won or lost, etc.)
     * @throws ConnectFourException If there is an error starting the server.
     * @throws IOException If there is an io interruption.
     */
    public void playgame() throws ConnectFourException,IOException{
            ConnectFour connectFour = new ConnectFour(6,7);

            boolean t = true;
            while(true) {
                if(!t){
                    OnetworkOut.println(ConnectFourProtocol.MAKE_MOVE);
                    if(OnetworkIn.next().equals(ConnectFourProtocol.MOVE)) {
                        int c = OnetworkIn.nextInt();
                        connectFour.makeMove(c);
                        XnetworkOut.println(ConnectFourProtocol.MOVE_MADE + " " + c);
                        OnetworkOut.println(ConnectFourProtocol.MOVE_MADE + " " + c);
                        if (connectFour.hasWonGame()) {
                            OnetworkOut.println(ConnectFourProtocol.GAME_WON);
                            XnetworkOut.println(ConnectFourProtocol.GAME_LOST);
                            break;
                        }
                        if (connectFour.hasTiedGame()) {
                            XnetworkOut.println(ConnectFourProtocol.GAME_TIED);
                            OnetworkOut.println(ConnectFourProtocol.GAME_TIED);
                            break;
                        }
                    }
                    else {
                        OnetworkOut.println(ConnectFourProtocol.ERROR);
                        XnetworkOut.println(ConnectFourProtocol.ERROR);
                    }
                    t = true;
                    continue;
                }
                if(t){
                    XnetworkOut.println(ConnectFourProtocol.MAKE_MOVE);
                    if(XnetworkIn.next().equals(ConnectFourProtocol.MOVE)) {
                        int c = XnetworkIn.nextInt();
                        connectFour.makeMove(c);
                        XnetworkOut.println(ConnectFourProtocol.MOVE_MADE + " " + c);
                        OnetworkOut.println(ConnectFourProtocol.MOVE_MADE + " " + c);
                        if (connectFour.hasWonGame()) {
                            XnetworkOut.println(ConnectFourProtocol.GAME_WON);
                            OnetworkOut.println(ConnectFourProtocol.GAME_LOST);
                            break;
                        }
                        if (connectFour.hasTiedGame()) {
                            XnetworkOut.println(ConnectFourProtocol.GAME_TIED);
                            OnetworkOut.println(ConnectFourProtocol.GAME_TIED);
                            break;
                        }
                    }
                    else {
                        OnetworkOut.println(ConnectFourProtocol.ERROR);
                        XnetworkOut.println(ConnectFourProtocol.ERROR);
                    }
                    t = false;
                    continue;
                }

                }
            serverSocket.close();
            }



    /**
     * Starts a new server.
     *
     * @param args Used to specify the port on which the server should listen
     *             for incoming client connections.
     *
     * @throws ConnectFourException If there is an error starting the server.
     */
    public static void main(String[] args) throws ConnectFourException,IOException {

        if (args.length != 1) {
            System.out.println("Usage: java ConnectFourServer port");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        ConnectFourServer connectFourServer = new ConnectFourServer(port);
        connectFourServer.playgame();

    }
}