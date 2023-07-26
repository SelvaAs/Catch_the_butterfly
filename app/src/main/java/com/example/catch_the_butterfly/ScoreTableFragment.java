package com.example.catch_the_butterfly;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;


public class ScoreTableFragment extends Fragment {


    ListView listView;
    ArrayList<String>  MyscoreTable  = new ArrayList<>();
    ArrayList<String> scorePointList = new ArrayList<>();
    Context context;
    ScoreListAdapter listViewAdapter;

    public ScoreTableFragment(Context applicationContext) {
        context = applicationContext;

        onAttach(context);
        // Required empty public constructor
    }

    //contect i alamadigi durumlarda kullanilir.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     ViewGroup viewGroup= (ViewGroup) inflater.inflate(R.layout.fragment_score_table_list_view,container,false);

     listView = viewGroup.findViewById(R.id.listView);
     LinearLayout linearLayout = viewGroup.findViewById(R.id.LinearLayout);

   MyscoreTable.clear();

   try {
       SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("ScoreTable",Context.MODE_PRIVATE,null);
       sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS scoreList(id INTEGER PRIMARY KEY ,name VARCHAR,score INTEGER)" );

       Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  scoreList",null);

       int idIndex = cursor.getColumnIndex("id");
       int nameIndex = cursor.getColumnIndex("name");
       int scoreIndex = cursor.getColumnIndex("score");

      // scorePointList.add(String.valueOf(cursor.getInt(scoreIndex)));

       while(cursor.moveToNext()){
           System.out.println("Id :" + cursor.getInt(idIndex));
           System.out.println("Name : " + cursor.getString(nameIndex));
           System.out.println("Score : " + cursor.getInt(scoreIndex));

           scorePointList.add("Name : " + cursor.getString(nameIndex) + "Point : " + cursor.getInt(scoreIndex) );
       }
       cursor.close();

   }catch (Exception e){
       e.printStackTrace();
   }

        listViewAdapter = new ScoreListAdapter(context,scorePointList);
        listView.setAdapter(listViewAdapter);

        return viewGroup;
    }

}