package nyc.c4q.stopwatchapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    // When the "Start" button is clicked, the timer is now running which is true.
    public void onClickStart(View view){
        running = true;     // Stopwatch starts running.
    }

    // When the "Stop" button is clicked, the timer is no longer running so this is false.
    public void onClickStop(View view){
        running = false;    // Stopwatch stops running.
    }
    //  When the "Reset" button is clicked, the timer is no longer running so this is false and the seconds will reset to 0.
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    // Sets the number of seconds on the timer
    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler(); // Create a new Handler
        handler.post(new Runnable() {     // Call the post() method with a new Runnable. This code will be processed ASAP.
           @Override
                public void run(){
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs); // This is the meat of the Runnable which will be displayed in the text view
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000); // The Runnable code will restart after a 1 second (1000 millisecond) delay)
            }
        });
    }

}
