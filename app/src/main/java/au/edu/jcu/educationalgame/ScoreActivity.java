package au.edu.jcu.educationalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView correctAnswers, incorrectAnswers;
    Button restart;

    int correctAnswerNum;
    int incorrectAnswerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        correctAnswers = findViewById(R.id.correctAnswers);
        incorrectAnswers = findViewById(R.id.incorrectAnswers);
        restart = findViewById(R.id.restart);
        //Display the correct and incorrect data through the data uploaded on the Game page
        correctAnswerNum = getIntent().getIntExtra("correct",0);
        incorrectAnswerNum = getIntent().getIntExtra("incorrect",0);

        correctAnswers.setText(String.valueOf(correctAnswerNum));
        incorrectAnswers.setText(String.valueOf(incorrectAnswerNum));
        //Celebration sound after finally finishing the quiz
        final MediaPlayer mediaPlayer = MediaPlayer.create(ScoreActivity.this, R.raw.congratulation);
        mediaPlayer.start();
        //New round of quiz
        restart.setOnClickListener(v -> {
            startActivity(new Intent(ScoreActivity.this, LandingActivity.class));
            finish();
        });
    }
    //Back to main page
    public void onBackPressed() {
        startActivity(new Intent(ScoreActivity.this, LandingActivity.class));
        finish();
    }
}