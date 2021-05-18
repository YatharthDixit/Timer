package com.yatharth.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int totalInSecond, m, s; boolean isTimeSet= false, istimerRunning=false, isPlaying=false; MediaPlayer mp;
    AudioManager audio;
    public void playSound(){
        audio= (AudioManager) getSystemService(AUDIO_SERVICE);
        mp = MediaPlayer.create(this, R.raw.d);
        mp.start();
    }

    public void buttonclicked(View view) {
        if(isTimeSet) {
            setTimer(totalInSecond * 1000);
            istimerRunning = true;
            if(isPlaying){
                mp.stop();
            }
        }
        else{
            Toast.makeText(this, "Please move the slider to set Time!", Toast.LENGTH_SHORT).show();
        }

    }
    public void showTime(int m, int s){
        TextView time = (TextView) findViewById(R.id.textView);
        time.setText(String.format("%02d", m)+":"+String.format("%02d", s));

    }
    public void setTimer(int i) {
        long t = (long) i;
        new CountDownTimer(t, 1000) {
            @Override
            public void onTick(long l) {
                int t= (int)l/1000;
                m=t/60;
                s=t%60;
                showTime(m,s);

            }

            @Override
            public void onFinish() {
                istimerRunning=false;
                Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                playSound();
                isPlaying=true;

            }
        }.start();

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = findViewById(R.id.seekBar2);
        seekBar.setMax(600);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                isTimeSet=true;
                m=i/60;
                s=i%60;

                if(!istimerRunning){
                showTime(m,s);}
                totalInSecond=i;




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}