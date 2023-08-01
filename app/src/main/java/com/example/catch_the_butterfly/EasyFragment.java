package com.example.catch_the_butterfly;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class EasyFragment extends Fragment {

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


    public EasyFragment(Context applicationContext) {
        context = applicationContext;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_easy,container,false);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_easy, container, false);

        textViewTime = viewGroup.findViewById(R.id.textViewTime);
        textViewScore = viewGroup.findViewById(R.id.textViewScore);
        imageView = viewGroup.findViewById(R.id.score_image);

        //sadece 4 ten buyuk score lari kaydet demek icin if yapisi ekleriz
        if (Integer.parseInt(textViewScore.getText().toString()) > 4)
        {
            try {

                int lastScore = Integer.parseInt(textViewScore.getText().toString());

                SQLiteDatabase database = context.openOrCreateDatabase("ScoreTable", Context.MODE_PRIVATE, null);

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


        imageView1 = viewGroup.findViewById(R.id.imageView1);
        imageView2 = viewGroup.findViewById(R.id.imageView2);
        imageView3 = viewGroup.findViewById(R.id.imageView3);
        imageView4 = viewGroup.findViewById(R.id.imageView4);
        imageView5 = viewGroup.findViewById(R.id.imageView5);
        imageView6 = viewGroup.findViewById(R.id.imageView6);
        imageView7 = viewGroup.findViewById(R.id.imageView7);
        imageView8 = viewGroup.findViewById(R.id.imageView8);
        imageView9 = viewGroup.findViewById(R.id.imageView9);
        imageView10 = viewGroup.findViewById(R.id.imageView10);
        imageView11 = viewGroup.findViewById(R.id.imageView11);
        imageView12 = viewGroup.findViewById(R.id.imageView12);
        imageView13 = viewGroup.findViewById(R.id.imageView13);
        imageView14 = viewGroup.findViewById(R.id.imageView14);
        imageView15 = viewGroup.findViewById(R.id.imageView15);



        imageArray = new ImageView[]{imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7,
                imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15};

        showImagesRandom();



        score = 0;

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long timeUntilFinished) {
                //timeLeftMillis = timeUntilFinished;
                textViewTime.setText("Time : " + timeUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                textViewTime.setText("Time off");
                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(context.getApplicationContext());
                //alert.setTitle("Restart ? ");
                alert.setMessage("Do you want to play again?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                        Intent intent = new Intent(context.getApplicationContext(),EasyFragment.class);
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context.getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context.getApplicationContext(),EasyFragment.class);
                        startActivity(intent);

                    }
                });
                alert.show();


            }
        };
        countDownTimer.start();
        return viewGroup;

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



