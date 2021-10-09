package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Menu");

        Intent intent = getIntent();

        setUpGameButton();
        setUpSettingButton();
        setUpHelpButton();
    }

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Menu.class);
    }

    private void setUpGameButton() {
        Button gameButton = findViewById(R.id.btnGame);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goes into game activity
                Intent i = Games.makeLaunchIntent(Menu.this);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    private void setUpSettingButton() {
        Button settingButton = findViewById(R.id.btnSetting);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goes into setting activity
                Intent i = Settings.makeLaunchIntent(Menu.this);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    private void setUpHelpButton() {
        Button helpButton = findViewById(R.id.btnHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goes into help activity
                Intent i = Helps.makeLaunchIntent(Menu.this);
                startActivity(i);

                // transition animation to the next activity
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    // transition animation when going back to the previous activity
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}

