package com.android.hayvansesleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    MediaPlayer muzikCalar;
    AudioManager soundManager;
    boolean caliyorMu = true;
    SeekBar generalVol, animalVol;
    ImageButton ibTr, ibEn, ibChi, ibJap, ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        ibTr = (ImageButton)findViewById(R.id.ibTr);
        ibEn = (ImageButton)findViewById(R.id.ibEn);
        ibChi = (ImageButton)findViewById(R.id.ibChi);
        ibJap = (ImageButton)findViewById(R.id.ibJap);
        ibBack = (ImageButton)findViewById(R.id.ibBack);
        generalVol = (SeekBar) findViewById(R.id.generalVol);
        animalVol = (SeekBar) findViewById(R.id.animalVol);

        playMusic();

        soundManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxSound = soundManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentSound = soundManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        generalVol.setMax(maxSound);
        generalVol.setProgress(currentSound);

        animalVol.setMax(maxSound);
        animalVol.setProgress(currentSound);

        //genel ses ayarı için seekbar kullanımını ayarlama kısmı
        generalVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                soundManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // müzik için seekbar kullanımını ayarlama
        animalVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                soundManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ibTr.setOnClickListener(v -> setLocale("tr"));
        ibEn.setOnClickListener(v -> setLocale("en"));
        ibChi.setOnClickListener(v -> setLocale("zh"));
        ibJap.setOnClickListener(v -> setLocale("ja"));
        ibBack.setOnClickListener(v -> geriDon());
    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        config.setLocale(locale);
        resources.updateConfiguration(config, dm);

        // Aktiviteyi yeniden başlat
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void geriDon (){
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void playMusic() {
        muzikCalar = MediaPlayer.create(this, R.raw.mainmusic);
        muzikCalar.setLooping(true); // Müzik bitince tekrar çalması için
        muzikCalar.start();
        caliyorMu = true;
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