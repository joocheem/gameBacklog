package com.example.jochemmortiers.diceroll;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] mDiceRes = new int[]{
            R.drawable.d1,
            R.drawable.d2,
            R.drawable.d3,
            R.drawable.d4,
            R.drawable.d5,
            R.drawable.d6
    };

    int mCurrentNumber;
    int mScore = 0;
    int mHighScore = 0;

    List<String> mRecentList = new LinkedList<>();
    TextView mScoreText;
    TextView mHighScoreText;
    ListView mRecentScores;
    ImageView mUpButton;
    ImageView mDice;
    ImageView mDownButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        afterViews();
    }

    private void initViews() {
        mScoreText = findViewById(R.id.score);
        mHighScoreText = findViewById(R.id.score2);
        mRecentScores = findViewById(R.id.recentList);
        mUpButton = findViewById(R.id.buttonUp);
        mDice = findViewById(R.id.dice);
        mDownButton = findViewById(R.id.buttonDown);

    }

    private void afterViews() {
        mCurrentNumber = rollDice();

        mUpButton.setOnClickListener(this);
        mDownButton.setOnClickListener(this);
    }

    private int rollDice() {
        Random r = new Random();
        return r.nextInt(7 - 1) + 1;
    }

    @Override
    public void onClick(View v) {
        boolean higher = false;
        switch (v.getId()){
            case R.id.buttonUp:
                higher = true;
                break;
            case R.id.buttonDown:
                higher = false;
                break;
        }

        int num = rollDice();

        if ((higher && num>mCurrentNumber) || (!higher && num<mCurrentNumber)){
            mScore +=1;
            if (mScore > mHighScore){
                mHighScore = mScore;
            }
            Snackbar.make(getWindow().getDecorView(), "You win :)", Snackbar.LENGTH_SHORT).show();
        }else {
            mScore = 0;
            Snackbar.make(getWindow().getDecorView(), "You lose :(", Snackbar.LENGTH_SHORT).show();
        }
        mCurrentNumber = num;
        mRecentList.add("Throw is: " + num);


        mScoreText.setText("Score: " + mScore);
        mHighScoreText.setText("Highscore: " +mHighScore);

        mDice.setImageResource(mDiceRes[num-1]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1, mRecentList);
        mRecentScores.setAdapter(adapter);
    }
}