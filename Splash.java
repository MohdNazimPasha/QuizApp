package com.example.nazim.quizapp1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.example.nazim.quizapp1.R.id.question;

public class Splash extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it=new Intent(Splash.this , MainActivity.class);
                startActivity(it);
                finish();
            }


        },900);
    }
}


