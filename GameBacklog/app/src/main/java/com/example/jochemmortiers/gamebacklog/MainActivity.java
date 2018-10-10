package com.example.jochemmortiers.gamebacklog;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GameAdapter.ItemClickListener {
    private static final int NEW_GAME = 1;
    private static final String DATABASE_NAME = "game_db";
    private static final int EDIT_GAME = 2;

    GameAdapter mAdapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    List<Game> game;
    private Game mEditingGame;

    private GameDatabase mGameDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initializeDatabase();
        getAllGames();

        recyclerView = findViewById(R.id.recyclerview);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddActivity();
            }
        });

    }

    private void initializeDatabase() {
        mGameDatabase = Room.databaseBuilder(getApplicationContext(),
                GameDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public void getAllGames(){
        doAsync(new Runnable() {
            @Override
            public void run() {
                game = mGameDatabase.dao().getAllGames();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setUpRecyclerView(game);
                    }
                });
            }
        });
    }



    void setUpRecyclerView(List<Game> game){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GameAdapter(this,game);
        mAdapter.setClickListener(this);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Remove item from backing list here
                final int position = viewHolder.getAdapterPosition();
                doAsync(new Runnable() {
                    @Override
                    public void run() {
                        mGameDatabase.dao().deleteGame(mAdapter.getItem(position));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.remove(position);
                            }
                        });
                    }
                });
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

     void init(){
        recyclerView = findViewById(R.id.recyclerview);
        fab = findViewById(R.id.fab);
        game = new ArrayList<>();
         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 goToAddActivity();
             }
         });
     }

     void goToAddActivity() {
        Intent intent = new Intent(this, AddGameActivity.class);
        startActivityForResult(intent, NEW_GAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if (data == null || data.getExtras() == null){
                return;
            }
            Bundle bundle = data.getExtras();
            switch (requestCode){
                case NEW_GAME:
                    final String title = bundle.getString("title");
                    final String platform = bundle.getString("platform");
                    final String notes = bundle.getString("notes");
                    final int status = bundle.getInt("status");
                    doAsync(new Runnable() {
                        @Override
                        public void run() {
                            mGameDatabase.dao().addGame(new Game(title, platform, notes, status,System.currentTimeMillis()));
                            getAllGames();
                        }
                    });
                    break;
                case EDIT_GAME:
                    String editedTitle = bundle.getString("title");
                    String editedPlatform = bundle.getString("platform");
                    String editedNotes = bundle.getString("notes");
                    int editedStatus = bundle.getInt("status");

                    mEditingGame.setTitle(editedTitle);
                    mEditingGame.setPlatform(editedPlatform);
                    mEditingGame.setNotes(editedNotes);
                    mEditingGame.setStatus(editedStatus);
                    mEditingGame.setDate(System.currentTimeMillis());

                    doAsync(new Runnable() {
                        @Override
                        public void run() {
                            mGameDatabase.dao().updateGame(mEditingGame);
                            getAllGames();
                        }
                    });
                    break;
            }

        }

    }

    void doAsync(Runnable pRunnable) {
        new Thread(pRunnable).start();
    }

    @Override
    public void onItemClick(View view, int position) {
        Game game = mAdapter.getItem(position);
        Intent intent = new Intent(this, AddGameActivity.class);
        intent.putExtra("title",game.getTitle());
        intent.putExtra("platform", game.getPlatform());
        intent.putExtra("notes", game.getNotes());
        intent.putExtra("status", game.getStatus());
        startActivityForResult(intent, EDIT_GAME);
        mEditingGame = game;
    }
}

