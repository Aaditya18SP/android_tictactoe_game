package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public String activeplayer, player1, player2;
    TextView tvTitle, tvPlayer1, tvPlayer2, tvChooseplayer;
    EditText etPlayer1, etPlayer2;
    Button btnStart, btnMainExit;
    RadioGroup rgChooseplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = findViewById(R.id.tvTitle);
        tvPlayer1 = findViewById(R.id.tvPlayer1);
        tvPlayer2 = findViewById(R.id.tvPlayer2);
        etPlayer1 = findViewById(R.id.etPlayer1);
        etPlayer2 = findViewById(R.id.etPlayer2);
        btnStart = findViewById(R.id.btnStart);
        btnMainExit = findViewById(R.id.btnMainExit);
        rgChooseplayer = findViewById(R.id.rgChooseplayer);
        tvChooseplayer = findViewById(R.id.tvChooseplayer);


        //click on start button

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1 = etPlayer1.getText().toString();
                player2 = etPlayer2.getText().toString();

                if (etPlayer1.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter player 1", Toast.LENGTH_SHORT).show();
                } else if (etPlayer2.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter player 2", Toast.LENGTH_SHORT).show();
                } else {
                    if (player1.equals(player2)) {
                        Toast.makeText(MainActivity.this, "Enter different names", Toast.LENGTH_SHORT).show();

                    } else {

                        if (R.id.rbPlayer1 == rgChooseplayer.getCheckedRadioButtonId()) {
                            activeplayer = player1;
                            //Toast.makeText(MainActivity.this, "player1 selected", Toast.LENGTH_SHORT).show();
                        } else {
                            activeplayer = player2;
                            //Toast.makeText(MainActivity.this, "player 2  selected", Toast.LENGTH_SHORT).show();
                        }

                        // Log.i("activeplayer", "active player: " + activeplayer);


                        rgChooseplayer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                                switch (i) {
                                    case R.id.rbPlayer1:
                                        activeplayer = player1;
                                        break;
                                    case R.id.rbPlayer2:
                                        activeplayer = player2;
                                        break;

                                }

                            }
                        });


                        Intent intentTomain = new Intent(MainActivity.this, com.example.tictactoe.Main2Activity.class);

                        intentTomain.putExtra("player1", player1);
                        intentTomain.putExtra("player2", player2);
                        if (activeplayer == null) {
                            Toast.makeText(MainActivity.this, "activeplayer is null", Toast.LENGTH_SHORT).show();
                        } else {
                            intentTomain.putExtra("startingplayer", activeplayer);//activeplayer is not visible in interface onclicklistener
                            startActivity(intentTomain);
                        }

                    }
                }

            }


        });


        //EXIT BUTTON
        btnMainExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
