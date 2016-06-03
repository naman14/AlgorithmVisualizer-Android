package com.naman14.algovisualizer;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.naman14.algovisualizer.algorithm.sorting.BubbleSort;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    BottomBar bottomBar;

    LogFragment logFragment;
    CodeFragment codeFragment;
    AlgoDescriptionFragment algoFragment;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("");

        bottomBar = BottomBar.attachShy((CoordinatorLayout) findViewById(R.id.coordinator), savedInstanceState);
        bottomBar.noNavBarGoodness();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        logFragment = new LogFragment();
        codeFragment = new CodeFragment();
        algoFragment = new AlgoDescriptionFragment();

        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

        bottomBar.setItems(
                new BottomBarTab(R.drawable.ic_wb_incandescent_white_24dp, "Details"),
                new BottomBarTab(R.drawable.ic_code_white_24dp, "Code"),
                new BottomBarTab(R.drawable.ic_short_text_white_24dp, "Execution")
        );

        bottomBar.setOnTabClickListener(new OnTabClickListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReSelected(int position) {

            }
        });

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

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(algoFragment, "Algo");
        adapter.addFragment(codeFragment, "Code");
        adapter.addFragment(logFragment, "Log");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
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
