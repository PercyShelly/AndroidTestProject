package com.example.qtfnvwang.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class flashActivity extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
          SharedPreferences guide = getSharedPreferences("guide",MODE_PRIVATE);
                String temp_status=guide.getString("third","");
                if(temp_status.equalsIgnoreCase("key") ){
                    Intent intent = new Intent();
                    intent.setClass(flashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent();
                    intent.setClass(flashActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }
}
