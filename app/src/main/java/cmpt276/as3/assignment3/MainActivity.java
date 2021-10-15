package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    MediaPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pokeball Adventure!");
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
                // transition animation to next activity
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
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

    //media player stopper
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