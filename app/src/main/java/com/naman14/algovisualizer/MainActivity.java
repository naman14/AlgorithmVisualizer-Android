package com.naman14.algovisualizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by naman on 03/06/16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VisualAlgoFragment algoFragment = new VisualAlgoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, algoFragment).commit();
    }
}
