package com.example.marmm.demolevel2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity implements Parcelable {

    private EditText mReminderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init local variables
        mReminderView = findViewById(R.id.editText_update);

        //Obtain the parameters provided by MainActivity
        final Reminder reminderUpdate = getIntent().getParcelableExtra(MainActivity.EXTRA_REMINDER);
        mReminderView.setText(reminderUpdate.getmReminderText());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                String text = mReminderView.getText().toString();

                //(reminderUpdate.setmReminderText(updatedReminderText)));
                if (!TextUtils.isEmpty(text)) {
                    reminderUpdate.setmReminderText(text);

                    //Prepare the return parameter and return
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.EXTRA_REMINDER, reminderUpdate.toString());
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                } else {
                    Snackbar.make(view, "Enter some data", Snackbar.LENGTH_LONG);
                }


            }
        });



    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.mReminderView, flags);
    }

    public UpdateActivity() {
    }

    protected UpdateActivity(Parcel in) {
        this.mReminderView = in.readParcelable(EditText.class.getClassLoader());
    }

    public static final Parcelable.Creator<UpdateActivity> CREATOR = new Parcelable.Creator<UpdateActivity>() {
        @Override
        public UpdateActivity createFromParcel(Parcel source) {
            return new UpdateActivity(source);
        }

        @Override
        public UpdateActivity[] newArray(int size) {
            return new UpdateActivity[size];
        }
    };
}
