package com.example.catch_the_butterfly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class easyLevelPage extends AppCompatActivity{

    TextView textViewTime;
    TextView textViewScore;

    ImageView imageView;

    int score;

    CountDownTimer countDownTimer;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;

    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;

    SQLiteDatabase database;

    Cursor cursor;
    Context context;
    int point = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_level_page);

        textViewScore = findViewById(R.id.textViewScore);

        //sadece 4 ten buyuk score lari kaydet demek icin if yapisi ekleriz
            if(Integer.parseInt(textViewScore.getText().toString()) > 4)
        {
            try {

                int lastScore = Integer.parseInt(textViewScore.getText().toString());

                SQLiteDatabase database = this.openOrCreateDatabase("ScoreTable", MODE_PRIVATE, null);
                //eger fragment ile calissaydik this yerine contex kullanmaliydik.
                //fragment te context biz olusturuyoruz.
                database.execSQL("CREATE TABLE IF NOT EXISTS scoreList(id INTEGER PRIMARY KEY ,name VARCHAR,score INT)");

                //database.execSQL("INSERT INTO scoreList (name , score) VALUES('Esra' , lastScore)");

                String sqlQuery = "INSERT INTO scoreList (name,score ) VALUES(?,?)";
                SQLiteStatement statement = database.compileStatement(sqlQuery);
                statement.bindString(1, "EasyLevelScore");
                statement.bindLong(2, lastScore);
                statement.executeInsert();

                Cursor cursor = database.rawQuery("SELECT * FROM scoreList ", null);

                int idIndex = cursor.getColumnIndex("id");
                int nameIndex = cursor.getColumnIndex("name");
                int scoreIndex = cursor.getColumnIndex("score");

                while (cursor.moveToNext()) {
                    System.out.println("Id :" + cursor.getInt(idIndex));
                    System.out.println("Name : " + cursor.getString(nameIndex));
                    System.out.println("Score : " + cursor.getInt(scoreIndex));
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);
        imageView11=findViewById(R.id.imageView11);
        imageView12=findViewById(R.id.imageView12);
        imageView13=findViewById(R.id.imageView13);
        imageView14=findViewById(R.id.imageView14);
        imageView15=findViewById(R.id.imageView15);



        imageArray=new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,
                imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15};

        showImagesRandom();


        textViewTime=findViewById(R.id.textViewTime);
        textViewScore=findViewById(R.id.textViewScore);
        imageView=findViewById(R.id.score_image);

        score=0;

        countDownTimer=new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long timeUntilFinished) {
                //timeLeftMillis = timeUntilFinished;
                textViewTime.setText("Time : "+timeUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                textViewTime.setText("Time off");
                handler.removeCallbacks(runnable);

                for (ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(easyLevelPage.this);
                //alert.setTitle("Restart ? ");
                alert.setMessage("Do you want to play again?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(easyLevelPage.this, "Game Over", Toast.LENGTH_SHORT).show();
                            finish();
                    }
                });
                alert.show();



            }
        };
        countDownTimer.start();



    }


    public void increaseScore(View view){
        score++;
        textViewScore.setText(String.valueOf(score));



    }


    public void showImagesRandom(){
        System.out.println("showImages calisti");

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run()
            {
                for (ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random=new Random();
                int i=random.nextInt(12);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,1500);


            }
        };
        handler.post(runnable);

    }


}
