package com.example.jochemmortiers.opdracht3;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_PORTAL = 1;

    RecyclerView recycler;
    FloatingActionButton fab;
    List<Portal> portals;
    PortalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init(){
        recycler = findViewById(R.id.recycler);
        fab = findViewById(R.id.fab);
        portals = new ArrayList<>();
        setUpRecycler();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddActivity();
            }
        });
    }

    private void goToAddActivity() {
        Intent intent = new Intent(this, AddPortalActivity.class);
        startActivityForResult(intent, NEW_PORTAL);
    }

    void setUpRecycler(){
        recycler.setLayoutManager(new GridLayoutManager(this,3));
        adapter = new PortalAdapter(this, portals);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == NEW_PORTAL){
            Bundle bundle = data.getExtras();
            String url = bundle.getString("url");
            String title = bundle.getString("title");
            portals.add(new Portal(url,title));
            adapter.setData(portals);

        }
    }
}
