package com.example.deeyu.androidlabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageDetails extends AppCompatActivity {

    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        //----------------lab7 step7
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });
    }


}
