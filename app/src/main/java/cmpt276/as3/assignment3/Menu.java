package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

//menu activity that contains the buttons to either the help, settings,
//or go to game screen.
public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Main Menu");

        setUpGameButton();
        setUpSettingButton();
        setUpHelpButton();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUpGameButton() {
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Button gameButton = findViewById(R.id.btnGame);
        gameButton.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                gameButton.startAnimation(scaleUp);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                gameButton.startAnimation(scaleDown);
                Intent i = Games.makeLaunchIntent(Menu.this);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
            return true;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUpSettingButton() {
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Button settingButton = findViewById(R.id.btnSetting);
        settingButton.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                settingButton.startAnimation(scaleUp);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                settingButton.startAnimation(scaleDown);
                Intent i = Settings.makeLaunchIntent(Menu.this);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
            return true;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUpHelpButton() {
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Button helpButton = findViewById(R.id.btnHelp);
        helpButton.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                helpButton.startAnimation(scaleUp);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                helpButton.startAnimation(scaleDown);
                Intent i = Helps.makeLaunchIntent(Menu.this);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
            return true;
        });
    }

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Menu.class);
    }
}

