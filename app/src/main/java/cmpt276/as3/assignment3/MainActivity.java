package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pokeball Adventure!");

        setUpMenuButton();
        AutoAdvanceToMenu();
    }

    private void setUpMenuButton() {
        mainLayout = findViewById(R.id.btnMenu);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goes into menu activity
                Intent i = Menu.makeLaunchIntent(MainActivity.this);
                startActivity(i);

                // transition animation to next activity
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    private void AutoAdvanceToMenu() {
        // use postDalayed to advance to menu activity after 10 seconds.
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this,Menu.class);
                MainActivity.this.startActivity(mainIntent);
            }
        }, 10000); // 10 seconds
    }
}