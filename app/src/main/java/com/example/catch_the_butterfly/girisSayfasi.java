package com.example.catch_the_butterfly;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class girisSayfasi extends AppCompatActivity {

    ImageView kelebekTikla;
    ImageView tauch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1giris_sayfasi);

        kelebekTikla = findViewById(R.id.kelebekTikla);
        tauch = findViewById(R.id.tauch);


        Animation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(1000); // Bir devir süresi (ms)
        alphaAnimation.setRepeatMode(Animation.REVERSE); // Tekrar modu
        alphaAnimation.setRepeatCount(Animation.INFINITE); // Tekrar sayısı

        // ImageView'a animasyonu ata
        kelebekTikla.startAnimation(alphaAnimation);


        Animation shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation);
        tauch.startAnimation(shakeAnimation);


        kelebekTikla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentGoToMainPage = new Intent(girisSayfasi.this, kullaniciAdi.class);
                startActivity(intentGoToMainPage);
            }
        });


    }
}