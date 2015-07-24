package com.deceax.lese.hotpotato;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer;
    ImageView potato;
    TimerHandler timerHandler;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        potato = (ImageView) findViewById(R.id.potato);
        potato.setImageResource(R.drawable.potato);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.byah);
        timer = new Timer("potatoTime");
        startTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_reset) {
            startTimer();
            potato.setImageResource(R.drawable.potato);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    long low = 10000L;
    long high = 30000L;
    Random r = new Random();

    private void startTimer() {
        TimerHandler timerHandler = new TimerHandler();
        timer = new Timer("potatoTime");
        timer.schedule(timerHandler, low+((long)(r.nextDouble()*(high-low))));
    }

    private class TimerHandler extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    potato.setImageResource(R.drawable.explosion);
                    mediaPlayer.start();
                }
            });
        }
    }
}
