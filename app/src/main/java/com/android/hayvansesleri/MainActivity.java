package com.android.hayvansesleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton ibPlay, ibSettings, ibExit, ibSound;
    MediaPlayer muzikCalar;
    boolean caliyorMu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        ibPlay = findViewById(R.id.ibStartGame);
        ibSettings = findViewById(R.id.ibSettings);
        ibExit = findViewById(R.id.ibExit);
        ibSound = findViewById(R.id.ibSound);

        playMusic();

        ibSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!caliyorMu) {
                    startMusic();
                    ibSound.setBackgroundResource(R.drawable.speakeron);
                } else {
                    stopMusic();
                    ibSound.setBackgroundResource(R.drawable.speakeroff);
                }
            }
        });
    }
    public void ibOyunaBasla(View v) {
        Intent intent = new Intent(MainActivity.this, GamePage.class);
        startActivity(intent);
    }
    public void ibAyarlar(View v) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
    public void ibCikis(View v) {
        if (muzikCalar != null) {
            muzikCalar.stop();
            muzikCalar.release();
            muzikCalar = null;
        }
        moveTaskToBack(true);
        finish();
        System.exit(0);
    }
    public void playMusic() {
        muzikCalar = MediaPlayer.create(this, R.raw.mainmusic);
        muzikCalar.setLooping(true);
        muzikCalar.start();
        caliyorMu = true;
    }
    private void stopMusic() {
        if (muzikCalar != null && muzikCalar.isPlaying()) {
            muzikCalar.pause();
            caliyorMu = false;
        }
    }
    private void startMusic() {
        if (muzikCalar != null && !muzikCalar.isPlaying()) {
            muzikCalar.start();
            caliyorMu = true;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (muzikCalar != null) {
            muzikCalar.release(); // MediaPlayer serbest bırakma
            muzikCalar = null;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (muzikCalar != null && muzikCalar.isPlaying()) {
            muzikCalar.pause(); // Müzik durdurulsun
            caliyorMu = false;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (muzikCalar != null && !muzikCalar.isPlaying()) {
            muzikCalar.start(); // Müzik tekrar başlatılsın
            caliyorMu = true;
        }
    }
}