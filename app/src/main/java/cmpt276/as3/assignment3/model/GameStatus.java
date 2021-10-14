package cmpt276.as3.assignment3.model;

public class GameStatus {
    private int numMines;
    private int numRows;
    private int numColumns;
    private int numScans;

    //Singleton Support
    private static GameStatus instance;
    private GameStatus() {}
    public static GameStatus getInstance() {
        if (instance == null) {
            instance = new GameStatus();
        }
        return instance;
    }

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

    public int getNumScans() { return numScans; }

    public void setNumScans(int numScans) { this.numScans = numScans;  }
}
