package au.edu.jcu.educationalgame;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Timer {

    public int minutes;
    public int seconds;

    public Timer() {
        minutes = seconds = 0;
    }

    public Timer(String value) {
        String[] data = value.split(":");
        minutes = Integer.parseInt(data[0]);
        seconds = Integer.parseInt(data[1]);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

    }

    public void tick() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }
    }

}
