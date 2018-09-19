package com.example.jochemmortiers.geoguesswipe;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    HashMap<Integer, Boolean> images = new HashMap<>();

    Snackbar snackbarCorrect;
    Snackbar snackbarIncorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout imagesHolder = findViewById(R.id.images);

        images.put(R.drawable.img1_yes_denmark,true);
        images.put(R.drawable.img2_no_canada,false);
        images.put(R.drawable.img3_no_bangladesh,false);
        images.put(R.drawable.img4_yes_kazachstan,true);
        images.put(R.drawable.img5_no_colombia,false);
        images.put(R.drawable.img6_yes_poland,true);
        images.put(R.drawable.img7_yes_malta,true);
        images.put(R.drawable.img8_no_thailand,false);

        snackbarCorrect = Snackbar.make(getWindow().getDecorView(),"Goed", Snackbar.LENGTH_SHORT);
        snackbarIncorrect = Snackbar.make(getWindow().getDecorView(),"Fout", Snackbar.LENGTH_SHORT);

        for(final Map.Entry<Integer, Boolean> entry: images.entrySet()){

            ImageView image = new ImageView(this);
            image.setAdjustViewBounds(true);
            image.setPadding(0,5,0,5);
            image.setOnTouchListener(new OnSwipeTouchListener(this){
                @Override
                public void onSwipeLeft() {
                    if(entry.getValue()) snackbarCorrect.show();
                    else snackbarIncorrect.show();
                }

                @Override
                public void onSwipeRight() {
                    if(!entry.getValue()) snackbarCorrect.show();
                    else snackbarIncorrect.show();
                }
            });
            Glide.with(this).load(entry.getKey()).into(image);
            imagesHolder.addView(image);


        }


    }
}
