package nyc.c4q.stopwatchapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //  Activity Launched:  onCreate()
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  This allows the app to restore the values saved in the Bundle before destroying (changing phone's orientation from potrait to landscape)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    // If activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    //  If activity is restarted
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    //  Activity Running: onSaveInstanceState()

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    // When the "Start" button is clicked, the timer is now running which is true.
    public void onClickStart(View view) {

        running = true;     // Stopwatch starts running.
    }

    // When the "Stop" button is clicked, the timer is no longer running so this is false.
    public void onClickStop(View view) {
        running = false;    // Stopwatch stops running.
    }

    //  When the "Reset" button is clicked, the timer is no longer running so this is false and the seconds will reset to 0.
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    // Sets the number of seconds on the timer
    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler(); // Create a new Handler
        handler.post(new Runnable() {     // Call the post() method with a new Runnable. This code will be processed ASAP.
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
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

/* onStop to stop activity when user is outside of the app using another app. (not visible). Records whether the stopwatch was running.
   No longer needed since added onPause() method above which replaced onStop() method. */

//    @Override
//    protected void onStop(){
//        super.onStop();
//        wasRunning = running;
//        running = false;
//    }

/*  onStart to restart activity once user restarts the app's activity. Restarts the stopwatch if it were formally running.
        No longer needed since added onResume() method above which replaced onStart() method. */

//    @Override
//    protected void onStart(){
//        super.onStart();
//        if(wasRunning){
//            running = true;
//        }
//    }


