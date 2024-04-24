package au.edu.jcu.educationalgame;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TimerTest {
        @Test
        public void testConstructor(){
            Timer timer = new Timer();
            assertEquals("00:00", timer.toString());
        }

        @Test
        public void testTicker(){
            Timer timer = new Timer();
            timer.tick();
            assertEquals("00:01", timer.toString());

            for(int i = 0; i < 59; ++i){
                timer.tick();
            }
            assertEquals("01:00", timer.toString());
        }


        public void addition_isCorrect() {
            assertEquals(4, 2 + 2);
        }
    }