package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        createRadioButtons();
    }

    private void createRadioButtons() {
        RadioGroup minesGroup = findViewById(R.id.radio_group_num_mines);

        //create buttons

        int[] numMinesOptions = getResources().getIntArray(R.array.num_of_mines);

        for(int i = 0; i < numMinesOptions.length; i++) {
            int numMines = numMinesOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText(numMines + " mines");

            //set on click callback
            //add to radio group
            minesGroup.addView(button);
        }

    }

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Settings.class);
    }

    // transition animation when going back to the previous activity
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}