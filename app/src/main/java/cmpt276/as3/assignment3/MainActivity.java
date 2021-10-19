package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
    MediaPlayer musicPlayer;
    CountDownTimer countdownTimer;
    private long remainingMilliseconds = 10000;
    private boolean tenSecondsPassed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Poke Ball Adventure!");
        playMusic();
        setUpMenuButton();
    }

    private void setUpMenuButton() {
        RelativeLayout mainLayout = findViewById(R.id.btnMenu);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goes into menu activity; music stops accordingly.
                Intent i = Menu.makeLaunchIntent(MainActivity.this);
                startActivity(i);
                stopMusicPlayer();
                setTenSecondsPassed(false);
                // transition animation to next activity
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        //countdown timer to determine whether to advance to main menu or not.
        //above, if the menu button is pressed, the boolean tenSecondsPassed is set to false,
        //which does not move the program back to the main menu screen even though the menu button
        //is pressed.
        //video inspiration is from here: https://www.youtube.com/watch?v=zmjfAcnosS0
        countdownTimer = new CountDownTimer(remainingMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                remainingMilliseconds = l;
            }
            @Override
            public void onFinish() {
                //if ten seconds does pass without an entry, it forces the activity through to the main menu.
                if(getTenSecondsPassed()) {
                    Intent i = Menu.makeLaunchIntent(MainActivity.this);
                    startActivity(i);
                    stopMusicPlayer();
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        }.start();
    }

    private void setTenSecondsPassed(boolean isPassed) {
        this.tenSecondsPassed = isPassed;
    }

    private boolean getTenSecondsPassed() {
        return tenSecondsPassed;
    }

    //the code below was adapted from the Youtube video linked:
    //https://www.youtube.com/watch?v=C_Ka7cKwXW0
    //all the code below was taken and adapted from the demo linked.
    private void stopMusicPlayer() {
        if(musicPlayer != null) {
            musicPlayer.release();
            musicPlayer = null;
        }
    }

    public void playMusic() {
        if(musicPlayer == null) {
            musicPlayer = MediaPlayer.create(this, R.raw.titlescreen);
            musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopMusicPlayer();
                }
            });
        }
        musicPlayer.start();
    }

    //media player stopper/resumer
    @Override
    protected void onStop() {
        super.onStop();
        stopMusicPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playMusic();
    }
}