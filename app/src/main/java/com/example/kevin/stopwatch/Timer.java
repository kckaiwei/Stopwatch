package com.example.kevin.stopwatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Timer extends AppCompatActivity {
    private boolean countingDown;
    private int seconds = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        runCountdown();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickStart (View view) {
        countingDown = true;

    }

    public void onClickPause (View view){
        countingDown = false;
    }

    public void toStopwatch (View view){
        Intent intent = new Intent(this, StopwatchActivtiy.class);
        startActivity(intent);

    }

    private void runCountdown() {
        final EditText timeText = (EditText) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            public void run () {
                try {
                    seconds = Integer.parseInt(timeText.getText().toString());
                } catch (NumberFormatException nfe){
                    nfe.printStackTrace();
                }
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeText.setText(time);
                if (countingDown) {
                    seconds--;
                }
                handler.postDelayed(this,1000);
            }
        });
    }


}
