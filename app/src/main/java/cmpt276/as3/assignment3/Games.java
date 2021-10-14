package cmpt276.as3.assignment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import cmpt276.as3.assignment3.model.GameStatus;

// Populate tables of button and hidden mine for user to tap on, the task for the player
// is to find all the mines
public class Games extends AppCompatActivity {
    private GameStatus game;

    Button[][] buttons; // buttons for table layout
    /*
    To record if a button has a value of:
    0 - means the button is untapped
    1 - means the button is a mine
    2 - means the button is revealed but not a mine
    3 - means the button is a revealed mine
     */
    int[][] IntButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        setTitle("Game");

        game = GameStatus.getInstance();

        populateButtons();
        updateUI();
    }

    private void populateButtons() {
        Random randomMine = new Random();
        int numRows = game.getNumRow();
        int numColumns = game.getNumColumns();
        final int[] numMines = {game.getNumMines()};
        final int[] numScans = {0};
        game.setNumScans(numScans[0]);

        // default table size and number of mines
        if (numRows == 0 && numColumns == 0 && numMines[0] == 0) {
            numRows = 4;
            game.setNumRow(numRows);
            numColumns = 6;
            game.setNumColumns(numColumns);
            numMines[0] = 6;
            game.setNumMines(numMines[0]);
        }

        IntButtons = new int[numRows][numColumns];
        buttons = new Button[numRows][numColumns];

        // previous is to prevent from repetition since random might give us the same position again
        int[][] previous = new int[numRows][numColumns];
        for (int i = 0; i < numMines[0]; i++) {
            // get random row and col
            int MineIndex_Row = randomMine.nextInt(numRows);
            int MineIndex_Col = randomMine.nextInt(numColumns);

            // while the position is a mine, increment the col by 1, if it is til the end
            // wrap back to the beginning and increment the row by 1, and so for
            while (previous[MineIndex_Row][MineIndex_Col] == 1) {
                MineIndex_Col++;
                if (MineIndex_Col >= numColumns) {
                    MineIndex_Col = 0;
                    MineIndex_Row++;
                    if (MineIndex_Row >= numRows) {
                        MineIndex_Row = 0;
                        MineIndex_Col = 0;
                    }
                }
            }

            // Mark the button as a mine by giving it a value of 1
            IntButtons[MineIndex_Row][MineIndex_Col] = 1;
            previous[MineIndex_Row][MineIndex_Col] = 1;
        }

        // populating table layout
        TableLayout table = findViewById(R.id.tableForButton);
        for (int row = 0; row< numRows; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col< numColumns; col++) {
                final int FINAL_ROW = row;
                final int FINAL_COL = col;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                // Make text not clip on small buttons
                button.setPadding(0,0,0,0);
                int finalNumRows = numRows;
                int finalNumColumns = numColumns;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // if clicked on a mine
                        if (IntButtons[FINAL_ROW][FINAL_COL] == 1) {
                            gridButtonClicked(FINAL_ROW, FINAL_COL);

                            numMines[0]--;
                            game.setNumMines(numMines[0]);
                            IntButtons[FINAL_ROW][FINAL_COL] = 3;

                            // Rescan the scanned button in the same row,
                            // so that it updates the number of mines in the row and col
                            for (int a=0; a<game.getNumColumns(); a++) {
                                int numMineScanned = 0;
                                Button button = buttons[FINAL_ROW][a];
                                if (IntButtons[FINAL_ROW][a] == 2){
                                    for (int i = 0; i< finalNumRows; i++) {
                                        if (IntButtons[i][FINAL_COL] == 1) {
                                            numMineScanned++;
                                        }
                                    }

                                    for (int i = 0; i< finalNumColumns; i++) {
                                        if (IntButtons[FINAL_ROW][i] == 1) {
                                            numMineScanned++;
                                        }
                                    }
                                    button.setText("" + numMineScanned);
                                }
                            }

                            // Rescan the scanned button in the same col,
                            // so that it updates the number of mines in the row and col
                            for (int a=0; a<game.getNumRow(); a++) {
                                int numMineScanned = 0;
                                Button button = buttons[a][FINAL_COL];
                                if (IntButtons[a][FINAL_COL] == 2) {
                                    for (int i = 0; i< finalNumRows; i++) {
                                        if (IntButtons[i][FINAL_COL] == 1) {
                                            numMineScanned++;
                                        }
                                    }

                                    for (int i = 0; i< finalNumColumns; i++) {
                                        if (IntButtons[FINAL_ROW][i] == 1) {
                                            numMineScanned++;
                                        }
                                    }
                                    button.setText("" + numMineScanned);
                                }
                            }
                        }

                        // if not tapped on a mine
                        else if (IntButtons[FINAL_ROW][FINAL_COL] == 0){
                            int numMineScanned = 0;
                            numScans[0]++;
                            game.setNumScans(numScans[0]);
                            IntButtons[FINAL_ROW][FINAL_COL] = 2;

                            // scan for mines in the same row and col
                            for (int i = 0; i< finalNumRows; i++) {
                                if (IntButtons[i][FINAL_COL] == 1) {
                                    numMineScanned++;
                                }
                            }

                            for (int i = 0; i< finalNumColumns; i++) {
                                if (IntButtons[FINAL_ROW][i] == 1) {
                                    numMineScanned++;
                                }
                            }

                            Button button = buttons[FINAL_ROW][FINAL_COL];
                            button.setText("" + numMineScanned);
                        }

                        // if the player found all the mine, display up popup dialog to congrats the winner
                        if (numMines[0] == 0) {
                                AlertDialog dialog;
                                AlertDialog.Builder builder = new AlertDialog.Builder(Games.this);

                                builder.setTitle("Congratulation, Adventurer, You have found all the PokeBalls!");

                                builder.setPositiveButton("OK", ((dialogInterface, i) -> {
                                    game.setNumRow(0);
                                    game.setNumColumns(0);
                                    finish();
                                }));

                                dialog = builder.create();
                                dialog.show();
                        }
                        updateUI();
                    }
                });
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    // to scale the image of pokeball
    private void gridButtonClicked(int row, int col) {
        Toast.makeText(this, "You found a PokeBall!",
                Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        lockButtonSizes();

        // scale image to button
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pokeball);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    private void lockButtonSizes() {
        for(int row = 0; row < game.getNumRow(); row++) {
            for(int col = 0; col < game.getNumColumns(); col++) {
                Button button = buttons[row][col];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    // update the number of mine left and the scan used for the player to keep in mind
    @SuppressLint("SetTextI18n")
    private void updateUI() {
        TextView numMineLeft = findViewById(R.id.txtNumMineLeft);
        TextView numScan = findViewById(R.id.txtNumScans);

        numMineLeft.setText("# of PokeBall left: " + game.getNumMines());
        numScan.setText("# of Scans used: " + game.getNumScans());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static Intent makeLaunchIntent(Context c) {
        return new Intent(c, Games.class);
    }

    // transition animation when going back to the previous activity
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
