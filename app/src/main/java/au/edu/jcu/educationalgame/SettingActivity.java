package au.edu.jcu.educationalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    Button easy, medium, difficulty;
    ImageView backBtn;

    private String degree = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        backBtn = findViewById(R.id.backBtnSetting);
        easy= findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        difficulty = findViewById(R.id.difficulty);
        //Use the sub-function
        backBtn.setOnClickListener(v -> onBackPressed());

        easy.setOnClickListener(v -> easyClicked());
        medium.setOnClickListener(v -> mediumClicked());
        difficulty.setOnClickListener(v -> difficultyClicked());
    }
    //Back to main page
    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingActivity.this, LandingActivity.class));
        Toast toast = Toast.makeText(SettingActivity.this, "Back to Original", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    //Choose the easy quiz
    private void easyClicked() {
        degree = "Easy";
        Intent intent = new Intent(SettingActivity.this, LandingActivity.class);
        intent.putExtra("degree", degree);
        startActivity(intent);
        if (degree == "Easy"){
            Toast toast = Toast.makeText(SettingActivity.this, "Selected Easy", Toast.LENGTH_SHORT);
            toast.show();
        }
        finish();

    }
    //Choose the medium quiz
    private void mediumClicked() {
        degree = "Medium";
        Intent intent = new Intent(SettingActivity.this, LandingActivity.class);
        intent.putExtra("degree", degree);
        startActivity(intent);
        Toast toast = Toast.makeText(SettingActivity.this, "Selected Medium", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    //Choose the difficulty quiz
    private void difficultyClicked() {
        degree = "Difficulty";
        Intent intent = new Intent(SettingActivity.this, LandingActivity.class);
        intent.putExtra("degree", degree);
        startActivity(intent);
        Toast toast = Toast.makeText(SettingActivity.this, "Selected Difficulty", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}