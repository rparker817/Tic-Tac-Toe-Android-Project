package com.example.tictactoerparkerassignmet1;
//Robert Parker using a pixel 3
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int current_player = 0;
    int turn_count = 0;
    int[] pressed_buttons = {0,0,0,0,0,0,0,0,0};
    int[] game_progress = {2,2,2,2,2,2,2,2,2};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    boolean has_won = false;
    boolean turn_finished = false;

    //when the game is created, set the game board backgrounds to blank disable the next player button and make it grey
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.imageButton1).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton2).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton3).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton4).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton5).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton6).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton7).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton8).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton9).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.btnNextPlayer).setEnabled(false);
        findViewById(R.id.btnNextPlayer).setBackgroundColor(Color.GRAY);
    }


    //When a board button is pressed
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void game_grid_pressed(View view) {
        if(!turn_finished) { //turn finished prevents the player pressing more than one area before the next player gets to play
            turn_finished = true;
            View img = (View) view;
            int button_id = Integer.parseInt(img.getTag().toString()); //store the id of the button which was pressed

            if (!check_press(button_id)) { //pass the button id to check if the button has already been pressed

               //increment the turn count, then add the button id to pressed_buttons array so it cannot be pressed again
               //add the current players code to the game_progress array in the correct position
                turn_count += 1;
                pressed_buttons[turn_count - 1] = button_id;
                game_progress[button_id - 1] = current_player;

               //change the button to an X or O and animate
                if (current_player == 0) {

                    img.setBackgroundResource(R.drawable.tictactoecircle3);
                    img.setScaleX(0.0f);
                    img.setScaleY(0.0f);
                    img.setAlpha(0f);
                    img.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .alpha(1.0f);


                } else {

                    img.setBackgroundResource(R.drawable.tictactoecross3);
                    img.setScaleX(0.0f);
                    img.setScaleY(0.0f);
                    img.setAlpha(0f);
                    img.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .alpha(1.0f)
                            .setDuration(500);

                }
                //check for a win state
                if (turn_count > 4) {
                    if(check_win_state())
                    {
                        declare_winner();
                    };
                }
                // if the game has not finished, allow for the next player button to be pressed
                if(turn_count <9 && has_won == false){
                    findViewById(R.id.btnNextPlayer).setEnabled(true);
                    findViewById(R.id.btnNextPlayer).setBackgroundColor(Color.GREEN);
                }


            }

        }
    }

    // check the pressed_buttons array for a match
    public boolean check_press(int id){
        for (int element : pressed_buttons) {
            if (element == id) {
                return true;
            }
        }
        return false;
    }


    // if the game progress matches a win state, return true
    public boolean check_win_state(){
        for (int[] winPosition : winPositions) {
            if (game_progress[winPosition[0]] == game_progress[winPosition[1]] &&
                    game_progress[winPosition[1]] == game_progress[winPosition[2]] &&
                    game_progress[winPosition[0]] != 2) {
                has_won = true;
                return true;

            }
        }
        //if nobody has won at the end of the game, call a draw function
        if (turn_count == 9 && has_won == false) {
            set_draw();
        }
        return false;
    }

    public void declare_winner(){
        TextView label = findViewById(R.id.gameComments);
        if(current_player == 0){
            label.setText("Player 1 you win!");
        }

        else{
            label.setText("Player 2 you win!");
        }
        findViewById(R.id.btnNextPlayer).setEnabled(false);
        findViewById(R.id.btnNextPlayer).setBackgroundColor(Color.GRAY);
    }

    public void set_draw(){
        TextView label = findViewById(R.id.gameComments);
        label.setText("It's a draw!!");
        findViewById(R.id.btnNextPlayer).setEnabled(false);
        findViewById(R.id.btnNextPlayer).setBackgroundColor(Color.GRAY);
    }

    //Reset all the variables and game board when the reset button is pressed
    public void reset_game(View view) {
        for(int i = 0;i < 9;i++)
        {
            pressed_buttons[i] = 0;
        }
        for(int i = 0;i < 9;i++)
        {
            game_progress[i] = 2;
        }
        findViewById(R.id.imageButton1).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton2).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton3).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton4).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton5).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton6).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton7).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton8).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.imageButton9).setBackgroundResource(R.drawable.tictactoebg3);
        findViewById(R.id.btnNextPlayer).setEnabled(false);
        findViewById(R.id.btnNextPlayer).setBackgroundColor(Color.GRAY);
        TextView label = findViewById(R.id.gameComments);
        label.setText("Player 1 Go");
        turn_finished = false;
        has_won = false;
        current_player = 0;
        turn_count = 0;
    }


    // move the game forward to allow the next player a turn
    public void next_player(View view)
    {
        findViewById(R.id.btnNextPlayer).setEnabled(false);
        findViewById(R.id.btnNextPlayer).setBackgroundColor(Color.GRAY);
        if(current_player ==0)
        {
            current_player = 1;
            TextView label = findViewById(R.id.gameComments);
            label.setText("Now Player 2 Go");
        }
        else {
            current_player = 0;
            TextView label = findViewById(R.id.gameComments);
            label.setText("Player 1 Go");
        }
        turn_finished = false;
    }

}