package cmpt276.as3.assignment3.model;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static GameManager instance;
    private GameManager() {

    }
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private List<GameStatus> games = new ArrayList<>();

    private GameStatus SavedGame;

    public void setSavedGame(GameStatus savedGame) {
        SavedGame = savedGame;
    }

    public GameStatus getSavedGame() {
        return SavedGame;
    }

    public void add(GameStatus game) {games.add(game); }

    public int getSize() {return games.size(); }

    public GameStatus getGame(int currentGame) {return games.get(currentGame); }

    public int bestScores() {
        int bestScore = games.get(0).getNumScans();
        for (int i=1; i<games.size(); i++) {
            if (bestScore > games.get(i).getNumScans()) {
                bestScore = games.get(i).getNumScans();
            }
        }
        return bestScore;
    }

    public void reset() {
        for (GameStatus g : games) {
            games.remove(g);
        }
    }
}
