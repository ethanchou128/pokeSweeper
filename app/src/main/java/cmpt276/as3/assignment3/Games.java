package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

//tip 2 - make model store an array of cells, each cell can answer different questions
public class Games extends AppCompatActivity {

    //private static final int NUM_ROWS = 4;
    //private static final int NUM_COLS = 4;
    private static Games instance;
    private int numRows = 4;
    private int numColumns = 6;
    private int numMines = 0;
    public static Games getInstance() {
        if(instance == null) {
            instance = new Games();
        }
        return instance;
    }

    //Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];
    Button buttons[][] = new Button[numRows][numColumns];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        setTitle("Game");

        populateButtons();
    }

    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForButton);
        for (int row=0; row<numRows; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col=0; col<numColumns; col++) {
                final int FINAL_ROW = row;
                final int FINAL_COL = col;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setText("" + row + "," + col);

                // Make text not clip on small buttons
                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        Toast.makeText(this, "Button Clicked: " + row + "," + col,
                Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        // Lock button sizes
        lockButtonSizes();

        // scale image to button
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pokeball);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));

        // Change text on clicked
        button.setText("" + col);
    }

    private void lockButtonSizes() {
        for (int row=0; row < numRows; row++) {
            for (int col=0; col < numColumns; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinWidth(height);
                button.setMaxWidth(height);
            }
        }
    }

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Games.class);
    }

    public void setNumPanels(int rows, int columns) {
        this.numRows = rows;
        this.numColumns = columns;
    }

    public void setNumMines(int mines) {
        this.numMines = mines;
    }

    // transition animation when going back to the previous activity
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}