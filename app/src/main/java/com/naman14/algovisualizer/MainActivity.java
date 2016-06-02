package com.naman14.algovisualizer;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.naman14.algovisualizer.visualizer.SortingVisualizer;

public class MainActivity extends AppCompatActivity {

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        final SortingVisualizer visualizer = (SortingVisualizer) findViewById(R.id.visualizer);
        visualizer.setData(DataUtils.createRandomArray(15));
        visualizer.start();

        final Handler handler = new Handler();
        i = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                visualizer.highlight(i);
                if (i < 15)
                    i++;
                else {
                    if (i > 0)
                        i--;
                    else i++;
                }
                handler.postDelayed(this, 250);
            }
        }, 250);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
