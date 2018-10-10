package com.example.jochemmortiers.gamebacklog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddGameActivity extends AppCompatActivity {

    EditText title;
    EditText platform;
    EditText notes;
    Spinner status;
    FloatingActionButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void init() {
        title = findViewById(R.id.add_title);
        platform = findViewById(R.id.add_platform);
        notes = findViewById(R.id.add_notes);
        status = findViewById(R.id.add_spinner);
        save = findViewById(R.id.add_fab);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Game.getStatusStrings(this));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);

        if (getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            title.setText(bundle.getString("title"));
            platform.setText(bundle.getString("platform"));
            notes.setText(bundle.getString("notes"));
            status.setSelection(bundle.getInt("status"));

        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().putExtra("title", title.getText().toString());
                getIntent().putExtra("platform", platform.getText().toString());
                getIntent().putExtra("notes", notes.getText().toString());
                getIntent().putExtra("status", status.getSelectedItemPosition());
                setResult(RESULT_OK, getIntent());
                finish();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
