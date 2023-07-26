package com.example.catch_the_butterfly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;



public class ScoreListAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> scoreList;

    LayoutInflater layoutInflater;

    TextView textViewMyEasyLevel;
    TextView textViewMyEasyLevelMessage;
    ImageView imageViewE;


    public ScoreListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    //kendine ait bir sayfasi olmadigi icin contex kullanmaliyiz.Diger ekranlar icin calisir

    public ScoreListAdapter(Context context1, ArrayList<String> MyScoreTable1) {
        super(context1, 0, MyScoreTable1);

        scoreList = MyScoreTable1;
        context = context1;

        int a = 0;
    }

    //uzerinde oynamak icin,degisiklikler icin
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);

            String itemName = scoreList.get(position);

            TextView textViewMyEasyLevel = convertView.findViewById(R.id.textViewMyEasyLevel);
            TextView textViewMyEasyLevelMessage = convertView.findViewById(R.id.textViewMyEasyLevelMessage);
            ImageView imageViewE = convertView.findViewById(R.id.imageViewE);




            int point = Integer.parseInt(scoreList.get(position));
            textViewMyEasyLevel.setText(String.valueOf(point));
            //buraya birsey ekleyecektin

            if (point < 7){
                textViewMyEasyLevelMessage.setText("iyi gidiyorsun");

            } else if (point > 7 && point <10) {
                textViewMyEasyLevelMessage.setText("Harikasin");

            }else {
                textViewMyEasyLevelMessage.setText("Supersin");

            }

            textViewMyEasyLevel.setText(itemName);

            //resim degistirmak icin

            //contexi olmadigi icin renk vs degisiklikleri yapmak icin bu yapiyi kullanabiliriz.

            /*if (item.equals("Orange")){
                textViewFruitName.setTextColor(context.getResources().getColor(R.color.purple_500));

                //textViewFruitName.setText("Peach");
            } else if (item.equals("Mango")) {
                imageViewFruit.setImageResource(R.drawable.ic_launcher_background);

            }

           */
            // imageViewFruit.setImageResource(R.drawable.kelebk);


        }

        return convertView;
    }
}
