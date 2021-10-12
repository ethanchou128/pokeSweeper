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

    public int getNumRow() {
        return numRow;
    }

    public int getNumCol() {
        return numCol;
    }

    // Testing merge

}
