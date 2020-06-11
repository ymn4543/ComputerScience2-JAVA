package client.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

/**
 * This class represents the GUI (Graphical User Interface).
 * It allows the player to see the game and interact with it.
 */
public class WAMGUI extends Application implements Observer<WAMgame> {
    /** The GUI's network client. How it communicates with server */
    public WAMNetworkClient wamNetworkClient;
    /** An array of buttons (holes on the board) */
    public Button[] buttons;
    /** A hashtable of Buttons and their ID numbers */
    public Hashtable<Button, Integer> buttonMap;
    /** The window's borderpane layout*/
    public BorderPane borderPane;
    /** The game/board this player will play on */
    public WAMgame board;
    /** The # of rows on the board */
    private int rows;
    /** The # of columns on the board */
    private int columns;
    /** The # of players who will play on the board */
    private int players;
    /** An array of player scores */
    private int[] playerscores;

    @Override
    /**
     * This method initializes all the parameters of the GUI.
     * It creates a network client and connects to a server to
     * receive all necessary information to construct the board.
     */
    public  void init() {
        //Creating connection to server
        try {
            List<String> args = getParameters().getRaw();
            String hostname = args.get(0);
            int port = Integer.parseInt(args.get(1));
            this.wamNetworkClient = new WAMNetworkClient(hostname, port);
        } catch (IOException ea) {
            System.out.println("fail");
        }
        //Preliminary setup that allows the creation of button array. Also initializes the game
        try {
            wamNetworkClient.makeParams();
        }
        catch(IOException wam){
            System.out.println("Connection Failed");

        }

        //Creation of above-mentioned button array. Also stores the # of rows/columns for the scene setup
        this.rows = wamNetworkClient.getRows();
        this.columns = wamNetworkClient.getColumns();
        this.players = wamNetworkClient.getPlayer();
        this.playerscores = new int[players];
        for(int e=0;e<players;e++){
            playerscores[e] = 0;
        }
        this.buttons = new Button[this.rows*this.columns];
        this.buttonMap = new Hashtable<>();

    }


    /**
     * This method creates the GUI using the information from server
     * and displays it to the user.
     * It also creates the buttons and tells them what to do when clicked
     * @param stage the stage that will be shown.
     */
    public void start(Stage stage) {
            this.borderPane = new BorderPane();
            GridPane gridPane = new GridPane();
            gridPane.setGridLinesVisible(true);
            javafx.scene.image.Image image = new Image(getClass().getResourceAsStream("empty.png"));
            //places each button in the button array onto the gridPane, as well as storing it and its id in a hash table
            int buttonid = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    int col = c;
                    Button button = new Button();
                    button.setGraphic(new ImageView(image));
                    gridPane.add(button, c, r);
                    buttons[buttonid] = button;
                    buttonMap.put(button, buttonid);
                    buttonid++;

                    //applies behavior for when button is pushed
                    button.setOnAction(Event -> {
                                int x = buttonMap.get(button);
                                wamNetworkClient.whack(x);
                            }
                    );
                }
            }
            String score = "";
            int index = 0;
            for(int s: playerscores){
                score += " Player " + index + " score: " + playerscores[s] + "  ";
                index++;
            }
            Text scores = new Text(score);
            Text gamestatus = new Text(" GAME IN PROGRESS ");
            borderPane.setCenter(gridPane);
            borderPane.setTop(scores);
            borderPane.setBottom(gamestatus);
            scores.setTextAlignment(TextAlignment.LEFT);
            gridPane.setAlignment(Pos.CENTER);
            Scene scene = new Scene(borderPane);
            int real = this.wamNetworkClient.getId()+1;
            stage.setTitle("WHACK-A-MOLE " + "      PLAYER: " + real);
            this.board = wamNetworkClient.getBoard();
            this.board.addObserver(this);
            stage.setScene(scene);
            stage.show();
            wamNetworkClient.startListener();
    }


    /**
     * This method updates the board so the game runs smoothly
     * @param waMgame the game to update
     */
    public void update(WAMgame waMgame) {
        if ( Platform.isFxApplicationThread() ) {
            this.refresh();
        }
        else {
            Platform.runLater( () -> this.refresh() );
        }
    }

    /**
     * This method refreshes the board so the player can see what is happening.
     * Allows user to see changes happening in order for game to function.
     * Allows moles to pop and scores to be constantly updated.
     */
    private void refresh() {
        javafx.scene.image.Image down = new Image(getClass().getResourceAsStream("empty.png"));
        javafx.scene.image.Image up = new Image(getClass().getResourceAsStream("mole.png"));
        //iterates over the buttons and changes the image to match the status of the buttons
        int b = 0;
        for (int m = 0; m < rows * columns; m++) {
            Button button = buttons[b];
            boolean moleStatus = board.getMoleAt(m);
            if (moleStatus) {
                button.setGraphic(new ImageView(up));
            }
            if (!moleStatus) {
                button.setGraphic(new ImageView(down));
            }
            b++;
        }
        int[] scores = board.getScores();
        String score = " ";
        for (int pl = 0; pl < players; pl++) {
            int real = pl + 1;
            score += "Player " + real + " score: " + scores[pl] + "     ";
        }
        Text text = new Text(score);
        this.borderPane.setTop(text);

        int s = wamNetworkClient.getGamestatus();
        if (s != 0) {
            if (s == 1) {
                Text status = new Text(" GAME WON!!! ");
                borderPane.setBottom(status);
            }
            if (s == 2) {
                Text status = new Text(" GAME LOST!!! ");
                borderPane.setBottom(status);
            }
            if (s == 3) {
                Text status = new Text(" GAME TIED!!! ");
                borderPane.setBottom(status);
            }
        }

    }


    /**
     * this method ends client/server connection
     * @throws IOException
     */
    public void stop() throws IOException {
        wamNetworkClient.stop();
        wamNetworkClient.close();

    }

    /**
     * This main method launches the GUI application.
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "error");
            System.exit(1);
        }
        Application.launch(args);
    }
}
