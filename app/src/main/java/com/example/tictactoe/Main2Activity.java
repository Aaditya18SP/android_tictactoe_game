package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView tvdisplaydetails, tvResult, tvtesting;
    TextView tvR1C1, tvR1C2, tvR1C3, tvR2C1, tvR2C2, tvR2C3, tvR3C1, tvR3C2, tvR3C3;
    Button btnPlayagain, btnExit;
    String startingplayer, player1, player2, otherplayer;
    String currentchar, otherchar;
    String startingchar = "X";
    String currentplayer;
    int currentcolor;
    int gameresult = -1;
    int totalgames = 0;
    int score_player1 = 0, score_player2 = 0; // starting player is X and other player is O;
    byte i = 0, j = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0, q = 0;
    int boxesclicked = 0;


    //RESULT LOGIC
    ArrayList<ArrayList<String>> winoutcome = new ArrayList<>();
    ArrayList<String> outcome1 = new ArrayList<>();
    ArrayList<String> outcome2 = new ArrayList<>();
    ArrayList<String> outcome3 = new ArrayList<>();
    ArrayList<String> outcome4 = new ArrayList<>();
    ArrayList<String> outcome5 = new ArrayList<>();
    ArrayList<String> outcome6 = new ArrayList<>();
    ArrayList<String> outcome7 = new ArrayList<>();
    ArrayList<String> outcome8 = new ArrayList<>();

    ArrayList<String> arrayofo = new ArrayList<>();
    ArrayList<String> arrayofx = new ArrayList<>();


    //FUNCTIONS

    //1.decide the result
    public void checkgameresult() {

        // 1 --> X wins
        // 2 --> O wins
        // 0 --> draw

        int index_winoutcome;
        ArrayList<String> outcome;
        for (index_winoutcome = 0; index_winoutcome < winoutcome.size(); index_winoutcome++) { //to access the string arrays in winindex
            outcome = winoutcome.get(index_winoutcome);

            if (arrayofx.containsAll(outcome)) {
                gameresult = 1; //X wins
                if (startingplayer.equals(player1)) {
                    score_player1++;
                } else {
                    score_player2++;
                }

                gameover();
                break;

            } else if (arrayofo.containsAll(outcome)) {
                gameresult = 2; //O wins
                if (otherplayer.equals(player1)) {
                    score_player1++;
                } else {
                    score_player2++;
                }

                gameover();
                break;

            } else if (boxesclicked == 9 & index_winoutcome == winoutcome.size() - 1) { //last iteration of checking all elements of all arrays
                gameresult = 0;
                gameover();
                break;//draw
            }
        }

        //if(arrayofx contains anyone outcome array from winindex then X wins )
        //else if(arrayofo contains anyone outcome array from winindex then O wins)
        //else
        //if (all boxes are clicked then draw
    }


    //2.show who's turn it is
    public void displaycurrentplayer() {
        tvdisplaydetails.setText(currentplayer + "'s turn");
        tvdisplaydetails.append(" " + "(" + currentchar + ")");
    }

    //3.switch the player of the game
    public void switchplayer(TextView tv) {
        //switch the player
        if (currentplayer == player1) {
            currentplayer = player2;
            switchchar();//switch the character
            switchcolor(tv);
            if (boxesclicked < 9 && gameresult == -1) {
                displaycurrentplayer();
            } else {
                //gameover();
            }
        } else {
            currentplayer = player1;
            switchchar(); //switch the character
            switchcolor(tv);
            if (boxesclicked < 9 && gameresult == -1) {
                displaycurrentplayer();
            } else {
                //gameover();
            }

        }

    }

    public void switchchar() {
        if (currentchar.equals(startingchar)) {
            currentchar = "O";
        } else {
            currentchar = "X";
        }
    }


    //4.Game Over
    public void gameover() {
        if (gameresult == 0) { //DRAW
            tvdisplaydetails.setText(getResources().getString(R.string.gameover));
            tvdisplaydetails.append("Draw");
            totalgames++;
            tvResult.setText(player1 + " : " + score_player1 + "\n" + player2 + " : " + score_player2 + "\n" + "draws : " + (totalgames - (score_player1 + score_player2)) + "\n" + "total games played : " + totalgames);

        } else if (gameresult == 1) { // X wins
            tvdisplaydetails.setText(getResources().getString(R.string.gameover));
            if (startingplayer.equals(player1)) {
                tvdisplaydetails.append(" " + startingplayer + " WINS!! \n");
            } else {
                tvdisplaydetails.append(" " + startingplayer + " WINS!! \n");
            }

            totalgames++;
            tvResult.setText(player1 + " : " + score_player1 + "\n" + player2 + " : " + score_player2 + "\n" + "draws : " + (totalgames - (score_player1 + score_player2)) + "\n" + "total games played : " + totalgames);
        } else if (gameresult == 2) { //O wins
            tvdisplaydetails.setText(getResources().getString(R.string.gameover));
            if (startingplayer.equals(player1)) {
                tvdisplaydetails.append(" " + otherplayer + " WINS!!\n");
            } else {
                tvdisplaydetails.append(" " + otherplayer + " WINS!! \n");
            }
            totalgames++;
            tvResult.setText(player1 + " : " + score_player1 + "\n" + player2 + " : " + score_player2 + "\n" + "draws : " + (totalgames - (score_player1 + score_player2)) + "\n" + "total games played : " + totalgames);
        } else {
            tvResult.setText("gameover else part");
        }

    }

    //5.switch the color
    public void switchcolor(TextView currenttv) {
        if (currentcolor == getResources().getColor(R.color.x_color)) {
            currentcolor = getResources().getColor(R.color.o_color);
            currenttv.setTextColor(currentcolor);
        } else {
            currentcolor = getResources().getColor(R.color.x_color);
            currenttv.setTextColor(currentcolor);
        }
    }

    //<--------------------------------------START OF ON CREATE---------------------------------------->
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //MAIN HEADING
        //DECIDING THE STARTING PLAYER AND THE OTHER PLAYER WHEN GAME OPENED FOR THE 1ST TIME

        tvdisplaydetails = findViewById(R.id.tvdisplaydetails);
        player1 = getIntent().getStringExtra("player1");
        player2 = getIntent().getStringExtra("player2");
        startingplayer = getIntent().getStringExtra("startingplayer");
        //tvdisplaydetails.setText("player1 is " + player1 +"\n player2 is "+player2 + "\n starting player is  :"+ startingplayer);
        tvdisplaydetails.setText(startingplayer + " starts");
        if (startingplayer.equals(player1)) {
            currentplayer = player1;
            otherplayer = player2;

            currentchar = "X";
            otherchar = "O";

        } else {

            currentplayer = player2;
            otherplayer = player1;

            currentchar = "X";
            otherchar = "O";

        }
        currentcolor = getResources().getColor(R.color.x_color);


        //TEXT VIEWS DEFINITION
        tvR1C1 = findViewById(R.id.tvR1C1);
        tvR1C2 = findViewById(R.id.tvR1C2);
        tvR1C3 = findViewById(R.id.tvR1C3);
        tvR2C1 = findViewById(R.id.tvR2C1);
        tvR2C2 = findViewById(R.id.tvR2C2);
        tvR2C3 = findViewById(R.id.tvR2C3);
        tvR3C1 = findViewById(R.id.tvR3C1);
        tvR3C2 = findViewById(R.id.tvR3C2);
        tvR3C3 = findViewById(R.id.tvR3C3);


        tvR3C3.setOnClickListener(this);
        tvR1C1.setOnClickListener(this);
        tvR1C2.setOnClickListener(this);
        tvR1C3.setOnClickListener(this);
        tvR2C1.setOnClickListener(this);
        tvR2C2.setOnClickListener(this);
        tvR2C3.setOnClickListener(this);
        tvR3C1.setOnClickListener(this);
        tvR3C2.setOnClickListener(this);
        tvR3C3.setOnClickListener(this);

//RESULT AND PLAY AGAIN AND EXIT
        tvResult = findViewById(R.id.tvResult);
        tvtesting = findViewById(R.id.tvtesting);
        btnPlayagain = findViewById(R.id.btnPlayagain);
        btnExit = findViewById(R.id.btnExit);

//PLAY AGAIN ON CLICK LISTENER
        btnPlayagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameresult == 1 | gameresult == 2 | (gameresult == 0 & boxesclicked == 9)) {
                    tvR1C1.setText("");
                    tvR1C2.setText("");
                    tvR1C3.setText("");
                    tvR2C1.setText("");
                    tvR2C2.setText("");
                    tvR2C3.setText("");
                    tvR3C1.setText("");
                    tvR3C2.setText("");
                    tvR3C3.setText("");
                    i = j = k = l = m = n = o = p = q = 0;
                    boxesclicked = 0;
                    arrayofo.clear();
                    arrayofx.clear();
                    gameresult = -1;
                    currentcolor = getResources().getColor(R.color.x_color);

                    if (currentplayer == player1) {
                        //currentplayer=player2;
                        startingplayer = player1;
                        otherplayer = player2;
                        switchchar();
                        tvdisplaydetails.setText(player1 + " starts" + " (" + startingchar + ")");

                    } else {
                        //currentplayer=player2;
                        startingplayer = player2;
                        otherplayer = player1;
                        switchchar();
                        tvdisplaydetails.setText(player2 + " starts" + " (" + startingchar + ")");
                    }


                } else {
                    tvResult.setText("Please Complete the Game!");

                }
            }

        });

        //EXIT LOGIC

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boxesclicked == 0 | boxesclicked == 9 | gameresult != -1) {
                    finish();
                }
            }
        });


        // RESULT LOGIC

        //winoutcome.add(outcome0); //testing
        winoutcome.add(outcome1);
        winoutcome.add(outcome2);
        winoutcome.add(outcome3);
        winoutcome.add(outcome4);
        winoutcome.add(outcome5);
        winoutcome.add(outcome6);
        winoutcome.add(outcome7);
        winoutcome.add(outcome8);

        outcome1.add("0");
        outcome1.add("1");
        outcome1.add("2");

        outcome2.add("3");
        outcome2.add("4");
        outcome2.add("5");

        outcome3.add("6");
        outcome3.add("7");
        outcome3.add("8");

        outcome4.add("0");
        outcome4.add("3");
        outcome4.add("6");

        outcome5.add("1");
        outcome5.add("4");
        outcome5.add("7");

        outcome6.add("2");
        outcome6.add("5");
        outcome6.add("8");

        outcome7.add("0");
        outcome7.add("4");
        outcome7.add("8");

        outcome8.add("2");
        outcome8.add("4");
        outcome8.add("6");


        // 0 1 2
        // 3 4 5
        // 6 7 8

    }

    //<---------------  END OF ON CREATE  ------------>

    //ONCLICK LISTENERS FOR EACH TEXT VIEW WHERE GAME IS PLAYED
    // X == 1
    // O == 0
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvR1C1:
                if (i == 0) {
                    boxesclicked++;

                    if (startingplayer.equals(currentplayer)) {
                        tvR1C1.setText("X");
                        arrayofx.add("0");
                    } else {
                        tvR1C1.setText("O");
                        arrayofo.add("0");
                    }
                    //check for gameresult

                    checkgameresult();

                    //switch the player
                    switchplayer((TextView) v);
                    i++;
                }
                break;

            case R.id.tvR1C2:

                if (j == 0 && gameresult == -1) {
                    boxesclicked++;

                    if (startingplayer.equals(currentplayer)) {
                        tvR1C2.setText("X");

                        arrayofx.add("1");

                    } else {
                        tvR1C2.setText("O");

                        arrayofo.add("1");
                    }
                    //check for gameresult
                    checkgameresult();
                    //switch the player
                    switchplayer((TextView) v);
                    j++;
                }
                break;

            case R.id.tvR1C3:

                if (k == 0 && gameresult == -1) {
                    boxesclicked++;

                    if (startingplayer.equals(currentplayer)) {
                        tvR1C3.setText("X");
                        //arrayofx+="2";
                        arrayofx.add("2");

                    } else {
                        tvR1C3.setText("O");
                        arrayofo.add("2");
                    }
                    //check for gameresult
                    checkgameresult();

                    //switch the player
                    switchplayer((TextView) v);
                    k++;
                }
                break;

            case R.id.tvR2C1:

                if (l == 0 && gameresult == -1) {
                    boxesclicked++;

                    if (startingplayer.equals(currentplayer)) {
                        tvR2C1.setText("X");

                        arrayofx.add("3");
                    } else {
                        tvR2C1.setText("O");

                        arrayofo.add("3");
                    }

                    //check for gameresult
                    checkgameresult();


                    //switch the player
                    switchplayer((TextView) v);
                    l++;
                }
                break;

            case R.id.tvR2C2:

                if (m == 0 && gameresult == -1) {
                    boxesclicked++;

                    if (startingplayer.equals(currentplayer)) {
                        tvR2C2.setText("X");
                        arrayofx.add("4");
                    } else {
                        tvR2C2.setText("O");
                        arrayofo.add("4");
                    }

                    //check for gameresult
                    checkgameresult();

                    //switch the player

                    switchplayer((TextView) v);
                    m++;
                }
                break;

            case R.id.tvR2C3:

                if (n == 0 && gameresult == -1) {
                    boxesclicked++;

                    if (startingplayer.equals(currentplayer)) {
                        tvR2C3.setText("X");

                        arrayofx.add("5");
                    } else {
                        tvR2C3.setText("O");

                        arrayofo.add("5");
                    }
                    //check for gameresult
                    checkgameresult();

                    //switch the player

                    switchplayer((TextView) v);
                    n++;
                }
                break;

            case R.id.tvR3C1:

                if (o == 0 && gameresult == -1) {
                    boxesclicked++;

                    if (startingplayer.equals(currentplayer)) {
                        tvR3C1.setText("X");
                        arrayofx.add("6");
                    } else {
                        tvR3C1.setText("O");
                        arrayofo.add("6");
                    }
                    //check for gameresult
                    checkgameresult();

                    //switch the player

                    switchplayer((TextView) v);
                    o++;
                }
                break;

            case R.id.tvR3C2:

                if (p == 0 && gameresult == -1) {
                    boxesclicked++;

                    if (startingplayer.equals(currentplayer)) {
                        tvR3C2.setText("X");
                        arrayofx.add("7");
                    } else {
                        tvR3C2.setText("O");
                        arrayofo.add("7");
                    }
                    //check for gameresult
                    checkgameresult();

                    //switch the player

                    switchplayer((TextView) v);
                    p++;
                }
                break;

            case R.id.tvR3C3:
                if (q == 0 && gameresult == -1) {
                    boxesclicked++;

                    if (startingplayer.equals(currentplayer)) {
                        tvR3C3.setText("X");
                        arrayofx.add("8");
                    } else {
                        tvR3C3.setText("O");
                        arrayofo.add("8");
                    }
                    //check for gameresult
                    checkgameresult();


                    //switch the player
                    switchplayer((TextView) v);
                    q++;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }


}