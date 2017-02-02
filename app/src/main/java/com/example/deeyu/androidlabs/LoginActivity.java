package com.example.deeyu.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "StartActivity";
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    Button button2;
    EditText ed1;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME,"In onCreate");

        ed1=(EditText)findViewById(R.id.editText1);
        button2 = (Button) findViewById(R.id.button2);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString(Name, "email@domain.com");
            ed1.setText(name);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                String name  = ed1.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, name);
                editor.commit();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume");

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart");


    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause");

    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy");

    }

}
