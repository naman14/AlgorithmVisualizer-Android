package com.naman14.algovisualizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.naman14.algovisualizer.algorithm.sorting.BubbleSort;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class VisualAlgoFragment extends Fragment {

    FloatingActionButton fab;
    BottomBar bottomBar;

    LogFragment logFragment;
    CodeFragment codeFragment;
    AlgoDescriptionFragment algoFragment;

    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_visual_algo, container, false);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        bottomBar = BottomBar.attachShy((CoordinatorLayout) rootView.findViewById(R.id.coordinator), savedInstanceState);
        bottomBar.noNavBarGoodness();

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);

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

        final SortingVisualizer visualizer = (SortingVisualizer) rootView.findViewById(R.id.visualizer);
        final BubbleSort bubbleSort = new BubbleSort(visualizer, getActivity(), logFragment);
        bubbleSort.setData(DataUtils.createRandomArray(15));


        bubbleSort.setCompletionListener(new AlgoCompletionListener() {
            @Override
            public void onAlgoCompleted() {
                fab.setImageResource(R.drawable.ic_settings_backup_restore_white_24dp);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bubbleSort.isStarted()) {
                    bubbleSort.sendMessage("start");
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
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
