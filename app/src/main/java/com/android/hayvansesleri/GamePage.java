package com.android.hayvansesleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamePage extends AppCompatActivity {
    ImageButton gorsel1, gorsel2, gorsel3, ibReturn;
    TextView txtGorsel1, txtGorsel2, txtGorsel3;
    private List<Animals> animals;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        gorsel1 = findViewById(R.id.gorsel1);
        gorsel2 = findViewById(R.id.gorsel2);
        gorsel3 = findViewById(R.id.gorsel3);

        txtGorsel1 = findViewById(R.id.txtGorsel1);
        txtGorsel2 = findViewById(R.id.txtGorsel2);
        txtGorsel3 = findViewById(R.id.txtGorsel3);

        ibReturn =(ImageButton) findViewById(R.id.btnReturn);

        initializeAnimals();
        displayRandomAnimals();
        ibReturn.setOnClickListener(v -> geriDon());

        gorsel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAnimalSound((Animals) gorsel1.getTag(), gorsel1, txtGorsel1);
            }
        });
        gorsel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAnimalSound((Animals) gorsel2.getTag(), gorsel2, txtGorsel2);
            }
        });
        gorsel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAnimalSound((Animals) gorsel3.getTag(), gorsel3, txtGorsel3);
            }
        });
    }
    public void geriDon (){
        Intent intent = new Intent(GamePage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private Bitmap getScaledBitmap(int resId, int reqWidth, int reqHeight) {
        // İlk olarak resmin boyutlarını almak için sadece boyut bilgisi yüklüyoruz
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resId, options);

        // Gerekli ölçek faktörünü hesaplıyoruz
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Resmi ölçeklendirilmiş olarak yüklüyoruz
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), resId, options);
    }
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
    private void initializeAnimals() {
        animals = new ArrayList<>();
        animals.add(new Animals(getScaledBitmap(R.drawable.bear, 100, 100), R.raw.bear_sound, getString(R.string.bear)));
        animals.add(new Animals(getScaledBitmap(R.drawable.bee, 100, 100), R.raw.bee_sound, getString(R.string.bee)));
        animals.add(new Animals(getScaledBitmap(R.drawable.bird, 100, 100), R.raw.bird_sound, getString(R.string.bird)));
        animals.add(new Animals(getScaledBitmap(R.drawable.cat, 100, 100), R.raw.cat_sound, getString(R.string.cat)));
        animals.add(new Animals(getScaledBitmap(R.drawable.chicken, 100, 100), R.raw.chicken_sound, getString(R.string.chicken)));
        animals.add(new Animals(getScaledBitmap(R.drawable.cockerel, 100, 100), R.raw.cockerel_sound, getString(R.string.cockerel)));
        animals.add(new Animals(getScaledBitmap(R.drawable.cow, 100, 100), R.raw.cow_sound, getString(R.string.cow)));
        animals.add(new Animals(getScaledBitmap(R.drawable.crow, 100, 100), R.raw.crow_sound, getString(R.string.crow)));
        animals.add(new Animals(getScaledBitmap(R.drawable.dog, 100, 100), R.raw.dog_sound, getString(R.string.dog)));
        animals.add(new Animals(getScaledBitmap(R.drawable.donkey, 100, 100), R.raw.donkey_sound, getString(R.string.donkey)));
        animals.add(new Animals(getScaledBitmap(R.drawable.duck, 100, 100), R.raw.duck_sound, getString(R.string.duck)));
        animals.add(new Animals(getScaledBitmap(R.drawable.elephant, 100, 100), R.raw.elephant_sound, getString(R.string.elephant)));
        animals.add(new Animals(getScaledBitmap(R.drawable.frog, 100, 100), R.raw.frog_sound, getString(R.string.frog)));
        animals.add(new Animals(getScaledBitmap(R.drawable.goat, 100, 100), R.raw.goat_sound, getString(R.string.goat)));
        animals.add(new Animals(getScaledBitmap(R.drawable.horse, 100, 100), R.raw.horse_sound, getString(R.string.horse)));
        animals.add(new Animals(getScaledBitmap(R.drawable.lion, 100, 100), R.raw.lion_sound, getString(R.string.lion)));
        animals.add(new Animals(getScaledBitmap(R.drawable.monkey, 100, 100), R.raw.monkey_sound, getString(R.string.monkey)));
        animals.add(new Animals(getScaledBitmap(R.drawable.penguin, 100, 100), R.raw.penguin_sound, getString(R.string.penguin)));
        animals.add(new Animals(getScaledBitmap(R.drawable.pig, 100, 100), R.raw.pig_sound, getString(R.string.pig)));
        animals.add(new Animals(getScaledBitmap(R.drawable.snake, 100, 100), R.raw.snake_sound, getString(R.string.snake)));
        animals.add(new Animals(getScaledBitmap(R.drawable.sheep, 100, 100), R.raw.sheep_sound, getString(R.string.sheep)));
        animals.add(new Animals(getScaledBitmap(R.drawable.wolf, 100, 100), R.raw.wolf_sound, getString(R.string.wolf)));
    }
    private void displayRandomAnimals() {
        List<Animals> randomAnimals = new ArrayList<>(animals);
        Collections.shuffle(randomAnimals);

        setAnimalToImageButton(gorsel1, txtGorsel1, randomAnimals.get(0));
        setAnimalToImageButton(gorsel2, txtGorsel2, randomAnimals.get(1));
        setAnimalToImageButton(gorsel3, txtGorsel3, randomAnimals.get(2));
    }
    private void setAnimalToImageButton(ImageButton imageButton, TextView textView, Animals animal) {
        imageButton.setImageBitmap(animal.getImage());
        imageButton.setTag(animal);
        textView.setText(animal.getName());
    }
    private void updateRandomAnimal(ImageButton imageButton, TextView textView) {
        List<Animals> randomAnimals = new ArrayList<>(animals);
        Collections.shuffle(randomAnimals);
        Animals randomAnimal = randomAnimals.get(0);

        imageButton.setImageBitmap(randomAnimal.getImage());
        imageButton.setTag(randomAnimal);
        textView.setText(randomAnimal.getName());
    }
    private void playAnimalSound(Animals animal, ImageButton imageButton, TextView textView) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, animal.getSoundResid());
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                updateRandomAnimal(imageButton, textView);
            }
        });
        mediaPlayer.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}