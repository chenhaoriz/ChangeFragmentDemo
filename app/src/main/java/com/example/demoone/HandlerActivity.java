package com.example.demoone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerActivity extends AppCompatActivity {
    public static final String TAG = "HandlerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,new ParentFragment()).commitNowAllowingStateLoss();
    }


}