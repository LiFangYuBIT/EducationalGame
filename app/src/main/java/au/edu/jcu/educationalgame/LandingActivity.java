package au.edu.jcu.educationalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LandingActivity extends AppCompatActivity {

    private LinearLayout englishQuiz;
    private LinearLayout mathQuiz;

    String degreeLevel;
    String level;

    private String topicName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Button startQuiz = findViewById(R.id.startQuiz);
        englishQuiz = findViewById(R.id.englishQuiz);
        mathQuiz = findViewById(R.id.mathQuiz);

        //Make difficulty selections, display return values and record. Record it for easy retransmission to the game page.
        TextView degree = findViewById(R.id.degree);
        ImageView settingBtn = findViewById(R.id.settingBtn);
        degreeLevel = getIntent().getStringExtra("degree");

        if (Objects.equals(degreeLevel, "Easy") || Objects.equals(degreeLevel, "Medium") || Objects.equals(degreeLevel, "Difficulty")) {
            degree.setText(degreeLevel);
            level = degreeLevel;
        }
        //animation try
        ImageView englishIcon = findViewById(R.id.englishIcon);
        //Key testing, but not limited to buttons.
        englishQuiz.setOnClickListener(v -> englishClicked());
        mathQuiz.setOnClickListener(v -> mathClicked());

        startQuiz.setOnClickListener(v -> startClicked());

        settingBtn.setOnClickListener(v -> settingClicked());
    }

    //Click English quiz, there is feedback on the page
    public void englishClicked() {
        if (topicName.equals("English Quiz")) {
            topicName = "";
            englishQuiz.setBackgroundResource(R.drawable.cl_white_text_box);
            mathQuiz.setBackgroundResource(R.drawable.cl_white_text_box);
            Toast toast = Toast.makeText(LandingActivity.this, "Deselect English", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            topicName = "English Quiz";
            englishQuiz.setBackgroundResource(R.drawable.cl_white_shadow);
            mathQuiz.setBackgroundResource(R.drawable.cl_white_text_box);
            Toast toast = Toast.makeText(LandingActivity.this, "Select English", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //Click the math quiz, there is feedback on the page
    public void mathClicked() {
        if (topicName.equals("Math Quiz")) {
            topicName = "";
            englishQuiz.setBackgroundResource(R.drawable.cl_white_text_box);
            mathQuiz.setBackgroundResource(R.drawable.cl_white_text_box);
            Toast toast = Toast.makeText(LandingActivity.this, "Deselect Mathematics", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            topicName = "Math Quiz";
            mathQuiz.setBackgroundResource(R.drawable.cl_white_shadow);
            englishQuiz.setBackgroundResource(R.drawable.cl_white_text_box);
            Toast toast = Toast.makeText(LandingActivity.this, "Select Mathematics", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //Click to start the currently selected test, the page has feedback
    public void startClicked() {
        if (topicName.isEmpty()) {
            Toast toast = Toast.makeText(LandingActivity.this, "Please Select A Topic", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Intent intent = new Intent(LandingActivity.this, GameActivity.class);
            intent.putExtra("topicName", topicName);
            intent.putExtra("level", level);

//            Pair[] pairs = new Pair[1];
//            pairs[0] = new Pair<View, String>(englishIcon, "englishIcon");

            // 动画尝试
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LandingActivity.this, pairs);

//            startActivity(intent, options.toBundle());
            startActivity(intent);
        }
    }

    //Click to enter the setting page, there is feedback on the page
    public void settingClicked() {
        Intent intent = new Intent(LandingActivity.this, SettingActivity.class);
        startActivity(intent);
    }
}