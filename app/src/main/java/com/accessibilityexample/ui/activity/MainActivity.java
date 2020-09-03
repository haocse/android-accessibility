package com.accessibilityexample.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accessibilityexample.R;

import static com.accessibilityexample.Service.MyAccessibilityService.isAccessibilitySettingsOn;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isAccessibilitySettingsOn(getApplicationContext());
    }
}
