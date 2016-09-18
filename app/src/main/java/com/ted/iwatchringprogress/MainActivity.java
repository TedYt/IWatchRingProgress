package com.ted.iwatchringprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ted.demo.PathPractise;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PathPractise pp= new PathPractise(this);
        setContentView(pp);
        pp.startAnim(1000);// 1 sec
    }
}
