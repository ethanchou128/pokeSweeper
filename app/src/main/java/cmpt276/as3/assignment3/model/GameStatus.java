package cmpt276.as3.assignment3.model;

//class that stores all the game information when modified/updated/used in
//Games and Settings classes.
public class GameStatus {
    private int numMines;
    private int numRows;
    private int numColumns;
    private int numScans;


    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }

    public int getNumRow() {
        return numRows;
    }

    public void setNumRow(int numRows) {
        this.numRows = numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public int getNumScans() {
        return numScans;
    }

    public void setNumScans(int numScans) {
        this.numScans = numScans;
    }
}
