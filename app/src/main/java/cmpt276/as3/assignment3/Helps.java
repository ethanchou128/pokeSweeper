package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Helps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helps);
        setTitle("Help");

        Intent intent = getIntent();
    }

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Helps.class);
    }
}