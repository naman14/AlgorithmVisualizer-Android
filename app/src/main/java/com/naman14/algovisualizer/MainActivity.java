package com.naman14.algovisualizer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.naman14.algovisualizer.algorithm.sorting.BubbleSort;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    LogFragment logFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        logFragment = new LogFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.log_container, logFragment).commit();

        final SortingVisualizer visualizer = (SortingVisualizer) findViewById(R.id.visualizer);

        final BubbleSort bubbleSort = new BubbleSort(visualizer, this, logFragment);
        bubbleSort.setData(DataUtils.createRandomArray(15));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bubbleSort.isStarted()) {
                    bubbleSort.startExecution();
                    fab.setImageResource(R.drawable.ic_pause_white_24dp);
                } else {
                    if (bubbleSort.isPaused()) {
                        bubbleSort.setPaused(false);
                        fab.setImageResource(R.drawable.ic_pause_white_24dp);
                    } else {
                        bubbleSort.setPaused(true);
                        fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
