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
                Intent i = Games.makeLaunchIntent(Menu.this);
                startActivity(i);
            }
        });
    }

    private void setUpSettingButton() {
        Button settingButton = findViewById(R.id.btnSetting);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = Settings.makeLaunchIntent(Menu.this);
                startActivity(i);
            }
        });
    }

    private void setUpHelpButton() {
        Button helpButton = findViewById(R.id.btnHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = Helps.makeLaunchIntent(Menu.this);
                startActivity(i);
            }
        });
    }
}