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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

import cmpt276.as3.assignment3.model.GameStatus;

public class Settings extends AppCompatActivity {
    private GameStatus game;

    private String panelSize;
    private int numMinesToFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // set the title of the action bar to "Edit Game Settings"
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Game Settings");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //create instance of the object to put back into the games object
        game = GameStatus.getInstance();

        createRadioButtonsForNumMines();
        createRadioButtonsForNumPanels();
    }

    private void createRadioButtonsForNumMines() {
        RadioGroup minesGroup = findViewById(R.id.radio_group_num_mines);

        //create buttons
        int[] numMinesOptions = getResources().getIntArray(R.array.num_of_mines);

        for (int numMines : numMinesOptions) {
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.mines, numMines));

            //set on click callback
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Settings.this, "You clicked " + numMines, Toast.LENGTH_SHORT)
                            .show();
                    numMinesToFind = numMines;
                }
            });
            //add to radio group
            minesGroup.addView(button);

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
                    Toast.makeText(Settings.this, "You clicked " + panelArrangement, Toast.LENGTH_SHORT)
                            .show();
                }
            });
            //add to radio group
            numPanelsGroup.addView(button);

        }
    }

    private void setMessageContents(String optionChosen) {
        if(optionChosen.equals("4 by 6")) {
            game.setNumColumns(6);
            game.setNumRow(4);
        } else if (optionChosen.equals("5 by 10")) {
            game.setNumRow(5);
            game.setNumColumns(10);
        } else {
            game.setNumRow(5);
            game.setNumColumns(15);
        }
    }

    private void setNumOfMines(int num) {
        game.setNumMines(num);
    }

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Settings.class);
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

                if(panelSize.length() != 0 && numMinesToFind != 0) {
                    setMessageContents(panelSize);
                    setNumOfMines(numMinesToFind);
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
            Toast.makeText(this,
                    "Game not saved, click button in top right to do so.",
                    Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // transition animation when going back to the previous activity
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_settings, menu);
        return true;
    }
}