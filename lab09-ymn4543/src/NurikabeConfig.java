import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * A class to represent a single configuration in the Nurikabe puzzle.
 *
 * @author Sean Strout @ RITCS
 * @author Youssef Naguib
 */
public class NurikabeConfig implements Configuration {
    /**
     * the nurikabe board representation
     */
    private char[][] board;
    /**
     * the row being visited
     */
    private int lastrowvisited;
    /**
     * the column being visited
     */
    private int lastcolvisited;
    /**
     * the number of rows in the board
     */
    private int rows;
    /**
     * the number of columns in the board
     */
    private int columns;
    /**
     * a string of numbers for determining if a cell is a number
     */
    private static String numbers ="123456789";
    private int islandsum;
    private int seasum;
    private int islandnum;
    private int seanum;
    private int emptysum;
    private Pair<Integer , Integer> Last;

    /**
     * Construct the initial configuration from an input file whose contents
     * are, for example:<br>
     * <tt><br>
     * 3 3          # rows columns<br>
     * 1 . #        # row 1, .=empty, 1-9=numbered island, #=island, &#64;=sea<br>
     * &#64; . 3    # row 2<br>
     * 1 . .        # row 3<br>
     * </tt><br>
     * @param filename the name of the file to read from
     * @throws FileNotFoundException if the file is not found
     */
    public NurikabeConfig(String filename) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(filename))) {
            this.islandsum = 0;
            this.islandnum = 0;
            this.emptysum = 0;
            this.seanum = 0;
            this.rows = Integer.parseInt(in.next());
            this.columns = Integer.parseInt(in.next());
            this.board = new char[rows][columns];
            int count = 0;
            for(int r = 0;r<rows;r++) {
                for (int c = 0; c < columns; c++) {
                    char n = in.next().charAt(0);
                    this.board[r][c] = (n);
                    if(numbers.contains(String.valueOf(n))){
                        islandsum+=Integer.parseInt(String.valueOf(n));
                        count++;
                    }
                    else{
                        emptysum+=1;
                    }
                }
            }
            this.seasum=(rows*columns)-islandsum;
            this.lastcolvisited = 0;
            this.lastrowvisited = 0;

            islandsum-=count;

        }

    }

    /**
     * The copy constructor takes a config, other, and makes a full "deep" copy
     * of its instance data.
     *
     * @param other the config to copy
     */
    protected NurikabeConfig(NurikabeConfig other, char next) {
        // TODO
        this.rows = other.rows;
        this.columns = other.columns;
        this.lastcolvisited = other.lastcolvisited;
        this.lastrowvisited = other.lastrowvisited;
        this.seanum = other.seanum;
        this.islandnum = other.islandnum;
        this.seasum = other.seasum;
        this.islandsum = other.islandsum;
        this.emptysum = other.emptysum;



        // create copy of board
        this.board = new char[rows][columns];
        for (int row=0; row<this.rows; ++row) {
            System.arraycopy(other.board[row], 0, this.board[row], 0, columns);
        }
        char here = (this.board[lastrowvisited][lastcolvisited]);
        if(!numbers.contains(String.valueOf(here))){
            board[lastrowvisited][lastcolvisited] = next;
            this.Last = new Pair<>(lastrowvisited,lastcolvisited);
            if(next=='#'){
                this.islandnum+=1;
            }
            if(next=='@'){
                this.seanum+=1;
            }

        }

    parse();


    }

    /**
     * This function moves to the next empty cell after a cell is filled
     */
    public void parse(){
        if(lastrowvisited >0 && lastcolvisited >0 && this.board[rows-1][columns-1]!='.'&&
                !numbers.contains(String.valueOf(this.board[rows-1][columns-1]))){
            this.lastcolvisited = columns-1;
            this.lastrowvisited = rows-1;
        }
        while(this.board[lastrowvisited][lastcolvisited]!='.'){
            lastcolvisited+=1;
            if(lastcolvisited==columns){
                this.lastcolvisited=0;
                this.lastrowvisited+=1;

            }
                if (this.lastrowvisited == rows) {
                    this.lastrowvisited = rows - 1;
                    break;
                }

        }
    }


    /**
     * This method generates two successors of a board, one with an added
     * sea cell and one with an added island cell.
     * @return successors
     */
    @Override
    public Collection<Configuration> getSuccessors() {
        char here = (this.board[lastrowvisited][lastcolvisited]);
        if(numbers.contains(String.valueOf(here))){
            parse();
        }
        List<Configuration> successors = new LinkedList<>();




        NurikabeConfig nurikabeConfig = new NurikabeConfig(this,'@');
        successors.add(nurikabeConfig);

        NurikabeConfig nurikabeConfig2 = new NurikabeConfig(this,'#');
        successors.add(nurikabeConfig2);


        return successors;
    }


    /**
     * This method determines whether a successor is a valid version of the board or not
     * @return boolean
     */
    @Override
    public boolean isValid() {
       //neither the island cell count, or sea cell count exceeds what the maximum should be for either.
        if(seanum>seasum){
            return false;
        }
        if(islandnum>islandsum){
            return false;
        }
        if(lastrowvisited==0&&lastcolvisited==0){
            return true;
        }
            Pair pair = this.Last;
            int r = (Integer) pair.getKey();
            int c = (Integer) pair.getValue();
            char here = this.board[r][c];


        if(islandnum+seanum==emptysum){
            if(!allSeaConnected()) {
                return false;
            }
        }
        if(here=='@'&&r>0&&c>0){
           return nopoolexists(pair);
        }
        if(here=='#'){
            //check island isn't connected to another
            if (findIslandPath(pair)){
                //check the island is less or equal to island number
                return(correctIslandSize(pair));

            }
            else{
                return false;
            }
        }


        return true;
    }


    /**
     * This method determines if any pools (2x2 sea cell areas) exist.
     * @param pair the coordinates of sea cell being checked
     * @return boolean
     */
    public boolean nopoolexists(Pair pair){
        char[]compass = new char[4];
        int r = (Integer) pair.getKey();
        int c = (Integer) pair.getValue();
        char N = this.board[r-1][c];
        compass[0]=N;
        char W = this.board[r][c-1];
        compass[1]=W;
        char NW = this.board[r-1][c-1];


        return !(N=='@'&& W=='@'&& NW=='@');

    }


    /**
     * This method finds the first sea cell on the board.
     * @return Pair of coordinates
     */
    private Pair<Integer, Integer> findFirstSeaCell() {
        int row=0;
        int col=0;

        while (true) {
            if (this.board[row][col] == '@') {
                break;
            }
            col += 1;
            if (col == columns) {
                row += 1;
                col = 0;
            }
        }

        return new Pair<>(row, col);
    }


    /**
     * This method determines if all the sea cells are connected somehow.
     * @return boolean
     */
        private boolean allSeaConnected() {
            Set<Pair<Integer, Integer>> visited = new HashSet<>();
            visited.clear();


            Pair<Integer, Integer> firstNumberedCell = findFirstSeaCell();
            visited.add(firstNumberedCell);

            int total = countSeaCellsDFS(firstNumberedCell, visited) ;
            return visited.size() == this.seasum;
        }


    /**
     * This is a DFS method for determining if sea cells are connected.
     * @param cell starting cell
     * @param visited set of visited cells
     * @return # of connected cells to first sea cell
     */
    private int countSeaCellsDFS(Pair<Integer, Integer> cell, Set<Pair<Integer, Integer>> visited) {
        int count = 0;
        int row = cell.getKey();
        int col = cell.getValue();

        // check N
        Pair<Integer, Integer> north = new Pair<>(row-1, col);
        if (row-1 >= 0  &&
                this.board[row-1][col] == '@' &&
                !north.isInside(visited)) {
            visited.add(north);


            count= count +1+countSeaCellsDFS(north,visited);
        }

        // check S
        Pair<Integer, Integer> south = new Pair<>(row+1, col);
        if (row+1 < rows  &&
                this.board[row+1][col] == '@' &&
                !south.isInside(visited)) {
            visited.add(south);


            count= count +1+countSeaCellsDFS(south,visited);
        }

        // check E
        Pair<Integer, Integer> east = new Pair<>(row, col+1);
        if (col+1 < columns  &&
                this.board[row][col+1] == '@' &&
                !east.isInside(visited)) {
            visited.add(east);


            count= count +1+countSeaCellsDFS(east,visited);
        }

        // check W
        Pair<Integer, Integer> west = new Pair<>(row, col-1);
        if (col-1 >= 0  &&
                this.board[row][col-1] == '@' &&
                !west.isInside(visited)) {
            visited.add(west);

            count = count + 1 + countSeaCellsDFS(west, visited);
        }

        return count;
    }


    /**
     * This method determines if a island is connected to more than 2 numbered cells.
     * @param pair island cell
     * @return boolean
     */
    private boolean findIslandPath(Pair pair) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        visited.clear();


        visited.add(pair);


        int t = countIslandCellsDFS(pair, visited);
        return  t <= 1;
    }

    /**
     * This method determines if a island is comprised of less than or exactly
     * the number of island cells it should have.
     * @param pair
     * @return
     */
    private boolean correctIslandSize(Pair pair){
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        visited.clear();

        visited.add(pair);

        int total  =  countCorrectIslandCellsDFS(pair, visited);

        int c = visited.size();
        return total>=c;
    }


    /**
     * This method counts the amount of numbered cells in an island using DFS
     * @param cell starting cell
     * @param visited set of visited cells
     * @return int
     */
    private int countIslandCellsDFS(Pair<Integer, Integer> cell, Set<Pair<Integer, Integer>> visited) {
        int count = 0;
        int row = cell.getKey();
        int col = cell.getValue();

        // check N
        Pair<Integer, Integer> north = new Pair<>(row-1, col);
        if (row-1 >= 0  &&
                (numbers.contains(String.valueOf(this.board[row-1][col])) || this.board[row-1][col]=='#') &&
                !north.isInside(visited)) {
            visited.add(north);

            if(numbers.contains(String.valueOf(this.board[row-1][col]))){
                count= count +1+countIslandCellsDFS(north,visited);
            }
            else{
                count = count + countIslandCellsDFS(north, visited);
            }
        }

        // check S
        Pair<Integer, Integer> south = new Pair<>(row+1, col);
        if (row+1 < rows  &&
                (numbers.contains(String.valueOf(this.board[row+1][col])) || this.board[row+1][col]=='#') &&
                !south.isInside(visited)) {
            visited.add(south);

            if(numbers.contains(String.valueOf(this.board[row+1][col]))){
                count= count +1+countIslandCellsDFS(south,visited);
            }
            else{
                count = count + countIslandCellsDFS(south, visited);
            }
        }

        // check E
        Pair<Integer, Integer> east = new Pair<>(row, col+1);
        if (col+1 < columns  &&
                (numbers.contains(String.valueOf(this.board[row][col+1])) || this.board[row][col+1]=='#') &&
                !east.isInside(visited)) {
            visited.add(east);

            if(numbers.contains(String.valueOf(this.board[row][col+1]))){
                count= count +1+countIslandCellsDFS(east,visited);
            }
            else{
                count = count + countIslandCellsDFS(east, visited);
            }
        }

        // check W
        Pair<Integer, Integer> west = new Pair<>(row, col-1);
        if (col-1 >= 0  &&
                (numbers.contains(String.valueOf(this.board[row][col-1])) || this.board[row][col-1]=='#') &&
                !west.isInside(visited)) {
            visited.add(west);

            if(numbers.contains(String.valueOf(this.board[row][col-1]))){
                count= count + 1 + countIslandCellsDFS(west,visited);
            }
            else{
                count = count + countIslandCellsDFS(west, visited);
            }
        }


        return count;
    }


    /**
     * This method counts how many island cells are connected in an island.
     * @param cell starting cell
     * @param visited set of visited cells
     * @return int
     */
    private int countCorrectIslandCellsDFS(Pair<Integer, Integer> cell, Set<Pair<Integer, Integer>> visited) {
        int count = 1;
        int row = cell.getKey();
        int col = cell.getValue();
        int total = 1;

        // check N
        Pair<Integer, Integer> north = new Pair<>(row-1, col);
        if (row-1 >= 0  &&
                (numbers.contains(String.valueOf(this.board[row-1][col])) || this.board[row-1][col]=='#') &&
                !north.isInside(visited)) {
            visited.add(north);


            if(numbers.contains(String.valueOf(this.board[row-1][col]))){
                total = Integer.parseInt(String.valueOf(this.board[row-1][col]));

            }
                count = count + countCorrectIslandCellsDFS(north, visited);

        }

        // check S
        Pair<Integer, Integer> south = new Pair<>(row+1, col);
        if (row+1 < rows  &&
                (numbers.contains(String.valueOf(this.board[row+1][col])) || this.board[row+1][col]=='#') &&
                !south.isInside(visited)) {
            visited.add(south);


            if(numbers.contains(String.valueOf(this.board[row+1][col]))){
                    total = Integer.parseInt(String.valueOf(this.board[row+1][col]));

                }
                count = count + countCorrectIslandCellsDFS(south, visited);

            }


        // check E
        Pair<Integer, Integer> east = new Pair<>(row, col+1);
        if (col+1 < columns  &&
                (numbers.contains(String.valueOf(this.board[row][col+1])) || this.board[row][col+1]=='#') &&
                !east.isInside(visited)) {
            visited.add(east);


            if(numbers.contains(String.valueOf(this.board[row][col+1]))){
                total = Integer.parseInt(String.valueOf(this.board[row][col+1]));

            }
            count = count + countCorrectIslandCellsDFS(east, visited);

        }



        // check W
        Pair<Integer, Integer> west = new Pair<>(row, col-1);
        if (col-1 >= 0  &&
                (numbers.contains(String.valueOf(this.board[row][col-1])) || this.board[row][col-1]=='#') &&
                !west.isInside(visited)) {
            visited.add(west);

            if(numbers.contains(String.valueOf(this.board[row][col-1]))){
                    total = Integer.parseInt(String.valueOf(this.board[row][col-1]));

                }
                count = count + countCorrectIslandCellsDFS(west, visited);

            }

        Pair<Integer,Integer> p = new Pair<>(total,count);
        return total;
    }


    /**
     * This method is identical to correctIslandSize but only returns
     * true if island size is exactly correct, not less than.
     * @param pair coordinates
     * @return boolean
     */
    private boolean correctIslandSize2(Pair pair){
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        visited.clear();

        visited.add(pair);
        int ro = (Integer)pair.getKey();
        int co = (Integer)pair.getValue();
        char to = this.board[ro][co];
        int s = Integer.parseInt(String.valueOf(to));
        int total  = countCorrectIslandCellsDFS(pair, visited);

        int c = visited.size();
        return s==c;
    }



    /**
     * This method ensures no islands have invalid cells
     * @return boolean
     */
    private boolean allIslandsAreCorrect(){
        for(int r = 0; r< rows;r++){
            for(int c = 0; c<columns;c++){
                if(numbers.contains(String.valueOf(this.board[r][c]))){
                    Pair p = new Pair(r,c);
                    if(correctIslandSize2(p)==false){
                        return false;
                    }
                }
            }
        }
        return true;
    }



    @Override
    /**
     * This method determines if a version of the board is the correct (goal) version.
     */
    public boolean isGoal() {
        return (this.board[rows-1][columns-1]!='.' || !numbers.contains(String.valueOf(this.board[rows-1][columns-1])))&& this.islandnum==this.islandsum
                && this.seanum==this.seasum && allSeaConnected() && allIslandsAreCorrect();

    }

    /**
     * Returns a string representation of the puzzle, e.g.: <br>
     * <tt><br>
     * 1 . #<br>
     * &#64; . 3<br>
     * 1 . .<br>
     * </tt><br>
     */
    @Override
    public String toString() {
        String board ="";
        for(int r = 0;r<rows;r++){
            for(int c = 0; c<columns; c++){
                board = board + "["+ this.board[r][c] + "]";
            }
            board += "\n";
        }
        return board;
    }



}