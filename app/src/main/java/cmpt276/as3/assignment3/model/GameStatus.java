package cmpt276.as3.assignment3.model;

public class GameStatus {
    private int numMine;
    private int numRow;
    private int numCol;

    //Singleton Support
    private static GameStatus instance;
    private GameStatus() {}
    public static GameStatus getInstance() {
        if (instance == null) {
            instance = new GameStatus();
        }
        return instance;
    }

    public int getNumMine() {
        return numMine;
    }

    public void setNumMine(int numMine) {
        this.numMine = numMine;
    }

    public int getNumRow() {
        return numRow;
    }

    public void setNumRow(int numRow) {
        this.numRow = numRow;
    }

    public int getNumCol() {
        return numCol;
    }

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }
}
