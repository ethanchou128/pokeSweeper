package cmpt276.as3.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import cmpt276.as3.assignment3.model.GameManager;
import cmpt276.as3.assignment3.model.GameStatus;

//class to modify the game layout/number of pokeballs to be found;
//also displays the games played and best score from current session
//and gives an option to reset if desired
public class Settings extends AppCompatActivity {
    private GameManager gameManager;
    private String panelSize;
    private int numPokeBallsToFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // set the title of the action bar to "Edit Game Settings"
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Game Settings");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //create instance of the object to put back into the games object
        gameManager = GameManager.getInstance();

        createRadioButtonsForNumPokeBalls();
        createRadioButtonsForNumPanels();
        setUpResetButton();
        updateUI();
    }

    private void updateUI() {
        TextView numGames = findViewById(R.id.txtNumGamesSetting);
        TextView bestScores = findViewById(R.id.txtBestScoreSetting);
        if (gameManager.getSize() == 0) {
            numGames.setText("# of Games: 0");
            bestScores.setText("Least # of scans used: 0");
        } else {
            numGames.setText("# of Games: " + gameManager.getSize());
            bestScores.setText("Least # of scans used: " + gameManager.determineBestScores());
        }
    }

    private void setUpResetButton() {
        Button resetButton = findViewById(R.id.btnReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.resetGamesPlayed();
                updateUI();
            }
        });
    }

    private void createRadioButtonsForNumPokeBalls() {
        RadioGroup pokeBallsGroup = findViewById(R.id.radio_group_num_poke_balls);
        int[] numPokeBallOptions = getResources().getIntArray(R.array.num_of_poke_balls);

        for (int numPokeBalls : numPokeBallOptions) {
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.mines, numPokeBalls));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numPokeBallsToFind = numPokeBalls;
                }
            });
            pokeBallsGroup.addView(button);
        }
    }

    private void createRadioButtonsForNumPanels() {
        RadioGroup numPanelsGroup = findViewById(R.id.radio_group_num_panels);
        String[] numArrangementsOptions = getResources().getStringArray(R.array.num_game_panels);

        for (final String panelArrangement : numArrangementsOptions) {
            RadioButton button = new RadioButton(this);
            button.setText(panelArrangement);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    panelSize = panelArrangement;
                }
            });
            numPanelsGroup.addView(button);
        }
    }

    private void setGamePanelLayout(String optionChosen, GameStatus game) {
        if(optionChosen.equals("4 by 6")) {
            game.setNumColumns(6);
            game.setNumRow(4);
        } else if (optionChosen.equals("5 by 10")) {
            game.setNumRow(5);
            game.setNumColumns(10);
        } else {
            game.setNumRow(6);
            game.setNumColumns(15);
        }
    }

    private void setNumOfPokeBalls(int num, GameStatus game) {
        game.setNumPokeBalls(num);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean bothButtonsClicked = false;
        if(item.getItemId() == R.id.saveGameSettings) {
            Log.i("button clicked", "hello bud");
            try {
                //these three variables are initialized with a number of 0.
                //if these variables are not set within the respective functions above,
                //an exception is thrown and we try it again.
                if(panelSize.length() != 0 && numPokeBallsToFind != 0) {
                    GameStatus game = new GameStatus();
                    setGamePanelLayout(panelSize, game);
                    setNumOfPokeBalls(numPokeBallsToFind, game);
                    gameManager.setSavedGame(game);
                    bothButtonsClicked = true;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                Toast.makeText(this,
                        "Please select an option from both lists.",
                        Toast.LENGTH_SHORT).show();
            }

            if(bothButtonsClicked) {
                Toast.makeText(this,
                        "Settings Saved, Returning to Menu.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (item.getItemId() == android.R.id.home) {
            //toast politely reminds the user that they didnt click the save button
            //in the top right corner and the settings are still set to default (4x6, 6 mines)
            Toast.makeText(this,
                    "Game not saved, click button in top right to do so.",
                    Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Settings.class);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    //method that adds the menu bar onto the activity (contains the save button)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_settings, menu);
        return true;
    }
}