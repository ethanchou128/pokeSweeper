package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Helps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helps);
        setTitle("How to Play");
        addTextToHelpMenu();
    }

    private void addTextToHelpMenu() {
        ListView listView = findViewById(R.id.helpScreenTextLines);

        String[] textInfoLines = getResources().getStringArray(R.array.gameHelpAndInstructions);
        ArrayAdapter<String> textAdapter = new ArrayAdapter<String>(
        this, R.layout.activity_helps, R.id.textView, textInfoLines
        );

        listView.setAdapter(textAdapter);
    }

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Helps.class);
    }

    // transition animation when going back to the previous activity
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}