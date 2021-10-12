package cmpt276.as3.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

public class Settings extends AppCompatActivity {
    private Games games;
    private int numRows = 0;
    private int numColumns = 0;
    private int numMinesToFind = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // set the title of the action bar to "Edit Game Settings"
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Game Settings");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //create instance of the object to put back into the games object
        games = Games.getInstance();

        createRadioButtonsForNumMines();
        createRadioButtonsForNumPanels();
        setupPrintSelectedButton();
    }

    private void setMessageContents(String optionChosen) {
        if(optionChosen.equals("4 by 6")) {
            this.numRows = 4;
            this.numColumns = 6;
        } else if (optionChosen.equals("5 by 10")) {
            this.numRows = 5;
            this.numColumns = 10;
        } else {
            this.numRows = 6;
            this.numColumns = 15;
        }
        Log.i("layout: ", numRows + " rows" + numColumns + " columns");
    }

    private void setupPrintSelectedButton() {
        Button numSelectBtn = findViewById(R.id.findSelected);
        numSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RadioGroup minesGroup = findViewById(R.id.radio_group_num_mines);
                    int idOfSelected = minesGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(idOfSelected);
                    String message = radioButton.getText().toString();

                    Toast.makeText(Settings.this, "Selected button's text is " + message, Toast.LENGTH_SHORT)
                            .show();
                } catch (Exception e) {
                    Toast.makeText(Settings.this, "No number of mines is selected.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createRadioButtonsForNumPanels() {
        RadioGroup numPanelsGroup = findViewById(R.id.radio_group_num_panels);

        String[] numArrangementsOptions = getResources().getStringArray(R.array.num_game_panels);

        for (int i = 0; i < numArrangementsOptions.length; i++) {
            final String panelArrangement = numArrangementsOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText(panelArrangement);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Settings.this, "You clicked " + panelArrangement, Toast.LENGTH_SHORT)
                            .show();
                    //setMessageContents(panelArrangement);
                }
            });
            //add to radio group
            numPanelsGroup.addView(button);
        }
    }

    private void createRadioButtonsForNumMines() {
        RadioGroup minesGroup = findViewById(R.id.radio_group_num_mines);

        //create buttons

        int[] numMinesOptions = getResources().getIntArray(R.array.num_of_mines);

        for(int i = 0; i < numMinesOptions.length; i++) {
            final int numMines = numMinesOptions[i];

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

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Settings.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean bothButtonsClicked = false;
        if(item.getItemId() == R.id.saveGameSettings) {
            Log.i("button clicked", "hello bud");
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