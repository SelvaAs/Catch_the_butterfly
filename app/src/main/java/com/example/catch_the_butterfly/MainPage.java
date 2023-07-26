package com.example.catch_the_butterfly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends Fragment {


    Button buttonEasy;
    Button buttonMedium;
    Button buttonHard;
    TextView Welcome;

    private SharedPreferences mySharedPreferences;
    private SharedPreferences.Editor editor;
    RelativeLayout relativeLayout;


    BottomNavigationView bottomNavigationView;
    Cursor cursor;
    Fragment fragment;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3main);

        Welcome = findViewById(R.id.textViewWelcome);


        mySharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        editor = mySharedPreferences.edit();
        editor.apply();

        String userName = mySharedPreferences.getString("userName", "");
        Welcome.setText(Welcome.getText().toString() + " " + userName);


        buttonEasy = findViewById(R.id.buttonEasy);
        buttonMedium = findViewById(R.id.buttonMedium);
        buttonHard = findViewById(R.id.buttonHard);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                fragment=new ScoreTableFragment(getApplicationContext());
//                getSupportFragmentManager().beginTransaction().replace(R.id.anaResimlinearLayout,fragment).commit();

                Intent intentGoToEasyLevel = new Intent(MainPage.this, easyLevelPage.class);
                startActivity(intentGoToEasyLevel);

            }
        });

        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentGoToMediumLevel=new Intent(MainPage.this, mediumLevelPage.class);
                startActivity(intentGoToMediumLevel);


            }

        });
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentGoToHardLevel=new Intent(MainPage.this,hardLevelPage.class);
                startActivity(intentGoToHardLevel);

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationListener);

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

             Fragment fragment= null;

            switch (item.getItemId())
            {
                case (R.id.menu_home):

                    Intent intent = new Intent(MainPage.this, girisSayfasi.class);
                    startActivity(intent);
                    break;

                case (R.id.menu_scoreTable):

                    fragment=new ScoreTableFragment(getApplicationContext());
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();


                    break;

                case (R.id.menu_cikis) :

                    finishAffinity();

                    break;


                default:

            }
            return false;
        }
    };
}