package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvPlayer1Score, tvPlayer2Score, tvWinner, Wait;
    private LinearLayout topLayout, bottomLayout;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private Handler handler = new Handler();
    private boolean gameEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPlayer1Score = findViewById(R.id.tvPlayer1Score);
        tvPlayer2Score = findViewById(R.id.tvPlayer2Score);
        tvWinner = findViewById(R.id.tvWinner);
        Wait = findViewById(R.id.textViewWait);
        topLayout = findViewById(R.id.topLayout);
        bottomLayout = findViewById(R.id.bottomLayout);

        // Incrementar el contador del jugador 1 al oprimir la parte superior de la pantalla
        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameEnded) {
                    scorePlayer1++;
                    updateScore();
                }
            }
        });

        // Incrementar el contador del jugador 2 al oprimir la parte inferior de la pantalla
        bottomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameEnded) {
                    scorePlayer2++;
                    updateScore();
                }
            }
        });
    }

    private void updateScore() {
        tvPlayer1Score.setText(String.valueOf(scorePlayer1));
        tvPlayer2Score.setText(String.valueOf(scorePlayer2));

        if (scorePlayer1 >= 11) {
            endGame("Player 1");
        } else if (scorePlayer2 >= 11) {
            endGame("Player 2");
        }
    }

    private void endGame(final String winner) {
        gameEnded = true;
        tvWinner.setText(winner + " wins!");
        Wait.setText("Wait 5 seconds");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetGame();
            }
        }, 5000); // Reiniciar el juego después de 10 segundos
    }

    private void resetGame() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        tvPlayer1Score.setText("0");
        tvPlayer2Score.setText("0");
        tvWinner.setText("");
        Wait.setText("");
        gameEnded = false;
    }
}
