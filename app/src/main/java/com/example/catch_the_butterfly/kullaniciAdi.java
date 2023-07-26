package com.example.catch_the_butterfly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class kullaniciAdi extends AppCompatActivity{

    EditText kullaniciAdi;
    Button button;
    LinearLayout linear;


    private SharedPreferences mySharedPreferences;
    private SharedPreferences.Editor editor;
    String userName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2kullanici_adi);

        kullaniciAdi = findViewById(R.id.editTextKullaniciAdi);
        button = findViewById(R.id.buttonStart);




        mySharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        editor = mySharedPreferences.edit();


        if((mySharedPreferences.getString("userName","")).equals(""))
        {
            Toast.makeText(getApplicationContext(),"Welcome :) ",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(com.example.catch_the_butterfly.kullaniciAdi.this, MainPage.class);
            startActivity(intent);
        }





        //ekranin herhangi bir yerinetiklayip klavyeyi indirmak icin
        linear = findViewById(R.id.kullanicilinearLayout);
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(linear.getWindowToken(),0);

            }
        });


        kullaniciAdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (kullaniciAdi.getText().toString().equals("")) {
                    Toast.makeText(com.example.catch_the_butterfly.kullaniciAdi.this, "Please Enter Your Username", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("userName", kullaniciAdi.getText().toString());
                    editor.apply();

                    Intent intent = new Intent(com.example.catch_the_butterfly.kullaniciAdi.this, MainPage.class);
                    startActivity(intent);
                }
            }
        });





    }
}