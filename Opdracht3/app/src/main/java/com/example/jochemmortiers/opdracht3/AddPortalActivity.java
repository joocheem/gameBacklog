package com.example.jochemmortiers.opdracht3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPortalActivity extends AppCompatActivity{

    EditText url;
    EditText title;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void init(){
        url = findViewById(R.id.urlinput);
        title = findViewById(R.id.titleinput);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().putExtra("title", title.getText().toString());
                getIntent().putExtra("url", url.getText().toString());
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
