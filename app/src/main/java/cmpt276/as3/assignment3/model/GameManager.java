package cmpt276.as3.assignment3.model;

import java.util.ArrayList;
import java.util.List;

//class that stores the games already played, as well as the "high scores".
public class GameManager {
    //singleton support, grouped accordingly
    private static GameManager instance;
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private GameManager() {
        //to make sure it does not get initialized
    }

    private List<GameStatus> games = new ArrayList<>();

    private GameStatus SavedGame;

    public void setSavedGame(GameStatus savedGame) {
        SavedGame = savedGame;
    }

    public GameStatus getSavedGame() {
        return SavedGame;
    }

    public void add(GameStatus game) {
        games.add(game);
    }

    public int getSize() {
        return games.size();
    }

    public int determineBestScores() {
        int bestScore = games.get(0).getNumScans();
        for (int i=1; i<games.size(); i++) {
            if (bestScore > games.get(i).getNumScans()) {
                bestScore = games.get(i).getNumScans();
            }
        }
        return bestScore;
    }

    public void resetGamesPlayed() {
        for (GameStatus g : games) {
            games.remove(g);
        }
    }
}
