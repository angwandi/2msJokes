package com.example.liband2msjokes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*Android library Activity */
public class libandActivity extends AppCompatActivity {
    public static final String KEY_JOKES = "jokes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liband_activity);
    }
}
