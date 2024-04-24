package au.edu.jcu.educationalgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    private TextView displayTime;

    private TextView questionNum, questionDetail;

    private Button option_1, option_2, option_3, option_4;
    private Button nextBtn;

    private Handler handler;
    private Timer timer;
    private boolean isRunning;
    //Use a stopwatch to record time, fixed value.
    private final int SPEED = 1000;
    private final int STOP_TIME = 1;
    private final int MAX_TIME = 16;
    private List<QuestionList> questionLists;
    private int currentQuestionPosition = 0;
    private String userSelectTopic = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Two values transmitted from the Landing page.
        String selectedTopic = getIntent().getStringExtra("topicName");
        String degree = getIntent().getStringExtra("level");

        ImageView backBtn = findViewById(R.id.backBtn);
        TextView selectedName = findViewById(R.id.selectedName);
        displayTime = findViewById(R.id.displayTime);

        selectedName.setText(selectedTopic);
        //Draw up the sequence number of the button.
        option_1 = findViewById(R.id.option_1);
        option_2 = findViewById(R.id.option_2);
        option_3 = findViewById(R.id.option_3);
        option_4 = findViewById(R.id.option_4);
        nextBtn = findViewById(R.id.nextBtn);
        //Distribution of exam papers by different degrees selected
        if (Objects.equals(degree, "Medium")) {
            questionLists = QuestionMediumBank.getQuestions(selectedTopic);
        } else if (Objects.equals(degree, "Difficulty")) {
            questionLists = QuestionDifficultyBank.getQuestions(selectedTopic);
        } else {
            questionLists = QuestionEasyBank.getQuestions(selectedTopic);
        }
        //timing
        isRunning = false;
        enableTimer();
        //Save data while rotating, and release.
        if (savedInstanceState == null) {
            timer = new Timer();
        } else {
            timer = new Timer(savedInstanceState.getString("value"));
            boolean running = savedInstanceState.getBoolean("running");
            if (running) {
                enableTimer();
            }
        }
        displayTime.setText(timer.toString());

        backBtn.setOnClickListener(v -> onBackPressed());

        questionNum = findViewById(R.id.questionNum);
        questionDetail = findViewById(R.id.questionDetail);
        //The questions are sorted by serial number, and the answers follow the questions, so there will be no mistakes.
        questionNum.setText((currentQuestionPosition + 1) + "/" + questionLists.size());
        questionDetail.setText(questionLists.get(0).getQuestion());
        option_1.setText(questionLists.get(0).getOption_1());
        option_2.setText(questionLists.get(0).getOption_2());
        option_3.setText(questionLists.get(0).getOption_3());
        option_4.setText(questionLists.get(0).getOption_4());
        //Answering the question. . .
        option_1.setOnClickListener(v -> {
            if (userSelectTopic.isEmpty()) {
                userSelectTopic = option_1.getText().toString();

                option_1.setBackgroundResource(R.drawable.cl_wrong);
                option_1.setTextColor(Color.WHITE);

                showAnswer();

                questionLists.get(currentQuestionPosition).setSelectedAnswer(userSelectTopic);
            }
        });
        option_2.setOnClickListener(v -> {
            if (userSelectTopic.isEmpty()) {
                userSelectTopic = option_2.getText().toString();

                option_2.setBackgroundResource(R.drawable.cl_wrong);
                option_2.setTextColor(Color.WHITE);

                showAnswer();

                questionLists.get(currentQuestionPosition).setSelectedAnswer(userSelectTopic);
            }
        });
        option_3.setOnClickListener(v -> {
            if (userSelectTopic.isEmpty()) {
                userSelectTopic = option_3.getText().toString();

                option_3.setBackgroundResource(R.drawable.cl_wrong);
                option_3.setTextColor(Color.WHITE);

                showAnswer();

                questionLists.get(currentQuestionPosition).setSelectedAnswer(userSelectTopic);
            }
        });
        option_4.setOnClickListener(v -> {
            if (userSelectTopic.isEmpty()) {
                userSelectTopic = option_4.getText().toString();

                option_4.setBackgroundResource(R.drawable.cl_wrong);
                option_4.setTextColor(Color.WHITE);

                showAnswer();

                questionLists.get(currentQuestionPosition).setSelectedAnswer(userSelectTopic);
            }
        });
        //After answering the question, switch to the next question.
        nextBtn.setOnClickListener(v -> {
            if (userSelectTopic.isEmpty()) {
                Toast toast = Toast.makeText(GameActivity.this, "Please Select An Option", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                toNextQuestion();
            }
        });
    }
    //Save data while rotating.
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", timer.toString());
        outState.putBoolean("running", isRunning);
        outState.putInt("speed", SPEED);
    }
    //Stopwatch
    private void enableTimer() {
        isRunning = true;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                timer.tick();
                displayTime.setText(timer.toString());
                handler.postDelayed(this, SPEED);
                //When the time is over, hand in the paper directly, and there will be a "failed" prompt.
                if (timer.minutes == STOP_TIME) {
                    timer.minutes = MAX_TIME;
                    final MediaPlayer mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.failed);
                    mediaPlayer.start();
                    Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
                    intent.putExtra("correct", getCorrectAnswers());
                    intent.putExtra("incorrect", getInCorrectAnswers());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    //Get the number of correct answers for each question by comparison.
    private int getCorrectAnswers() {
        int correctAnswers = 0;

        for (int i = 0; i < questionLists.size(); i++) {
            final String SelectedAnswer = questionLists.get(i).getSelectedAnswer();
            final String Answer = questionLists.get(i).getAnswer();

            if (SelectedAnswer.equals(Answer)) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }
    //The number of errors in each answer is obtained by comparison.
    private int getInCorrectAnswers() {
        int incorrectAnswers = 0;

        for (int i = 0; i < questionLists.size(); i++) {
            final String SelectedAnswer = questionLists.get(i).getSelectedAnswer();
            final String Answer = questionLists.get(i).getAnswer();

            if (!SelectedAnswer.equals(Answer)) {
                incorrectAnswers++;
            }
        }
        return incorrectAnswers;
    }
    //Back to main page (Landing)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(GameActivity.this, LandingActivity.class));
        finish();
    }

    private void showAnswer() {
        final String getAnswer = questionLists.get(currentQuestionPosition).getAnswer();
        //Determine whether the user's click is correct and display the correct answer
        if (getAnswer.equals(option_1.getText().toString())) {
            option_1.setBackgroundResource(R.drawable.cl_right);
            option_1.setTextColor(Color.WHITE);
        } else if (getAnswer.equals(option_2.getText().toString())) {
            option_2.setBackgroundResource(R.drawable.cl_right);
            option_2.setTextColor(Color.WHITE);
        } else if (getAnswer.equals(option_3.getText().toString())) {
            option_3.setBackgroundResource(R.drawable.cl_right);
            option_3.setTextColor(Color.WHITE);
        } else if (getAnswer.equals(option_4.getText().toString())) {
            option_4.setBackgroundResource(R.drawable.cl_right);
            option_4.setTextColor(Color.WHITE);
        }
        //Different sound output for correct and incorrect answers
        final MediaPlayer mediaPlayer;
        if (userSelectTopic.equals(getAnswer)) {
            mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.success);
        } else {
            mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.failed);
        }
        mediaPlayer.start();
    }
    //Function for the next question
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    private void toNextQuestion() {
        currentQuestionPosition++;
        //Counting statistics, if it is found that the number will exceed, it will be submitted.
        if ((currentQuestionPosition + 1) == questionLists.size()) {
            nextBtn.setText("Submit Quiz");
        }
        //Reset the color of options and count.
        if (currentQuestionPosition < questionLists.size()) {
            userSelectTopic = "";
            option_1.setTextColor(Color.BLACK);
            option_1.setBackgroundResource(R.drawable.cl_white_text_box);

            option_2.setTextColor(Color.BLACK);
            option_2.setBackgroundResource(R.drawable.cl_white_text_box);

            option_3.setTextColor(Color.BLACK);
            option_3.setBackgroundResource(R.drawable.cl_white_text_box);

            option_4.setTextColor(Color.BLACK);
            option_4.setBackgroundResource(R.drawable.cl_white_text_box);

            questionNum.setText((currentQuestionPosition + 1) + "/" + questionLists.size());
            questionDetail.setText(questionLists.get(currentQuestionPosition).getQuestion());
            option_1.setText(questionLists.get(currentQuestionPosition).getOption_1());
            option_2.setText(questionLists.get(currentQuestionPosition).getOption_2());
            option_3.setText(questionLists.get(currentQuestionPosition).getOption_3());
            option_4.setText(questionLists.get(currentQuestionPosition).getOption_4());
        } else {
            timer.minutes = 0;
            Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getInCorrectAnswers());
            startActivity(intent);
            finish();
        }
    }
}