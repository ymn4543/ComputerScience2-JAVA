package connectfour.gui;

import connectfour.ConnectFourException;
import connectfour.client.ConnectFourBoard;
import connectfour.client.ConnectFourNetworkClient;
import connectfour.client.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.application.Platform;

import java.awt.*;
import java.util.List;
/**
 * A JavaFX GUI for the networked Connect Four game.
 *
 * @author James Heloitis @ RIT CS
 * @author Sean Strout @ RIT CS
 * @author Youssef Naguib
 */
public class ConnectFourGUI extends Application implements Observer<ConnectFourBoard> {


    public ConnectFourNetworkClient connectFourNetworkClient;
    public ConnectFourBoard board;
    public Button[] buttons;
    public BorderPane borderPane;
    public Label bottomright;
    public Label bottomleft;


    /**
     * Initializes non-gui elements of game.
     * Creates the board, adds itself as an observer, creates network client,
     * and makes an array for buttons.
     *
     * @throws ConnectFourException if there is a problem
     */
    @Override
    public void init() throws ConnectFourException {
        try {

            // get the command line args
            List<String> args = getParameters().getRaw();

            // get host info and port from command line
            String host = args.get(0);
            int port = Integer.parseInt(args.get(1));

            this.board = new ConnectFourBoard();
            this.board.addObserver(this);
            this.connectFourNetworkClient = new ConnectFourNetworkClient(host, port, board);
            this.buttons = new Button[45];

        } catch(NumberFormatException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }

    }

    /**
     * Construct the layout for the game.
     *
     * @param stage container (window) in which to render the GUI
     * @throws Exception if there is a problem
     */
    public void start( Stage stage ) throws Exception {
        this.borderPane = new BorderPane();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        javafx.scene.image.Image image = new Image(getClass().getResourceAsStream("empty.png"));
        int buttonid = 0;
        for(int r = 0;r<6;r++){
            for(int c = 0;c<7;c++){
                int col = c;
                Button button = new Button();
                button.setGraphic(new ImageView(image));
                button.setBackground(new Background(new BackgroundFill(Color.YELLOW,null,null)));
                gridPane.add(button,c,r);
                buttons[buttonid] = button;
                buttonid++;
                button.setOnAction(Event -> {
                    if(board.isValidMove(col) && board.isMyTurn()) {
                        connectFourNetworkClient.sendMove(col);

                    }
                    else{
                        System.out.println("Please pick another column");
                    }
                });
            }
        }

        this.bottomleft = new Label("Moves left: "+ board.getMovesLeft());
        Label middle = new Label(" * ");
        middle.setAlignment(Pos.CENTER);
        this.bottomright = new Label(" ");
        bottomright.setAlignment(Pos.BOTTOM_RIGHT);
        HBox hbox = new HBox(bottomleft,middle,bottomright);
        hbox.setSpacing(150);
        borderPane.setBottom(hbox);
        borderPane.setCenter(gridPane);
        Scene scene = new Scene(borderPane);

        stage.setScene(scene);
        stage.setTitle("Youssef's Connect-4 Lab");
        stage.show();



        // TODO: call startListener() in ConnectFourNetworkClient here
        connectFourNetworkClient.startListener();



    }

    /**
     * GUI is closing, so close the network connection. Server will get the message.
     */
    @Override
    public void stop() {
        connectFourNetworkClient.close();

    }

    /**
     * Updates GUI.
     * Every time a player makes a move, this method will check every button,
     * and update the GUI for the user to see the move that was made.
     * Called by the update method.
     */
    private void refresh() {
        //change the button to correct png and display on both boards

        javafx.scene.image.Image player1 = new Image(getClass().getResourceAsStream("p1black.png"));
        javafx.scene.image.Image player2 = new Image(getClass().getResourceAsStream("p2red.png"));
        int b=0;
            for (int r = 0; r < 6; r++) {
                for (int c = 0; c < 7; c++) {
                    Button button = buttons[b];
                    ConnectFourBoard.Move move = board.getContents(c, r);
                    if (move.equals(ConnectFourBoard.Move.PLAYER_ONE)) {
                        button.setGraphic(new ImageView(player1));
                    }
                    if (move.equals(ConnectFourBoard.Move.PLAYER_TWO)) {
                        button.setGraphic(new ImageView(player2));
                    }
                    b++;
                }
            }

        switch (board.getStatus()){
            case I_WON:
                bottomright.setText("YOU WIN! YAY!");
                break;
            case I_LOST:
                bottomright.setText("YOU LOSE! ");
                break;
            case TIE:
                bottomright.setText("ITS A TIE! GAME OVER!");
                break;
            case ERROR:
                bottomright.setText("Error");
                break;
            case NOT_OVER:
                bottomright.setText(board.isMyTurn()?"Pick a Column":"Wait for opponent ");
                break;
        }

        bottomleft.setText("Moves left: " + board.getMovesLeft());
    }



    /**
     * Called by the model, client.ConnectFourBoard, whenever there is a state change
     * that needs to be updated by the GUI.
     *
     * @param connectFourBoard
     */
    @Override
    public void update(ConnectFourBoard connectFourBoard) {
        if ( Platform.isFxApplicationThread() ) {
            this.refresh();
        }
        else {
            Platform.runLater( () -> this.refresh() );
        }
    }

    /**
     * The main method expects the host and port.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ConnectFourGUI host port");
            System.exit(-1);
        } else {
            Application.launch(args);
        }
    }
}
