/*
 * Copyright (C) 2016 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.naman14.algovisualizer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.graph.GraphTraversalAlgorithm;
import com.naman14.algovisualizer.algorithm.tree.bst.BSTAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by naman on 03/06/16.
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    ExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<String>> listDataChild;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        expandableList = (ExpandableListView) findViewById(R.id.navigationmenu);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        final VisualAlgoFragment algoFragment = VisualAlgoFragment.newInstance(Algorithm.BUBBLE_SORT);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, algoFragment).commit();

        prepareListData();
        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, expandableList);

        expandableList.setAdapter(mMenuAdapter);

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                algoFragment.setupFragment(Algorithm.BINARY_SEARCH);
                                break;
                            case 1:
                                algoFragment.setupFragment(Algorithm.LINEAR_SEARCH);
                                break;
                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                algoFragment.setupFragment(Algorithm.BUBBLE_SORT);
                                break;
                            case 1:
                                algoFragment.setupFragment(Algorithm.INSERTION_SORT);
                                break;
                            case 2:
                                algoFragment.setupFragment(Algorithm.SELECTION_SORT);
                                break;
                            case 3:
                                algoFragment.setupFragment(Algorithm.QUICKSORT);
                                break;
                        }
                        break;
                    case 2:
                        switch (childPosition) {
                            case 0:
                                algoFragment.setStartCommand(BSTAlgorithm.START_BST_SEARCH);
                                algoFragment.setupFragment(Algorithm.BST_SEARCH);
                                break;
                            case 1:
                                algoFragment.setStartCommand(BSTAlgorithm.START_BST_INSERT);
                                algoFragment.setupFragment(Algorithm.BST_INSERT);
                                break;
                        }
                        break;
                    case 3:
                        switch (childPosition) {
                            case 0:
                                algoFragment.setupFragment(Algorithm.LINKED_LIST);
                                break;
                            case 1:
                                algoFragment.setupFragment(Algorithm.STACK);
                                break;
                        }
                        break;
                    case 4:
                        switch (childPosition) {
                            case 0:
                                algoFragment.setStartCommand(GraphTraversalAlgorithm.TRAVERSE_BFS);
                                algoFragment.setupFragment(Algorithm.BFS);
                                break;
                            case 1:
                                algoFragment.setStartCommand(GraphTraversalAlgorithm.TRAVERSE_DFS);
                                algoFragment.setupFragment(Algorithm.DFS);
                                break;
                            case 2:
                                algoFragment.setupFragment(Algorithm.DIJKSTRA);
                                break;
                            case 3:
                                algoFragment.setupFragment(Algorithm.BELLMAN_FORD);
                                break;
                        }
                        break;
                    case 5:
                        switch (childPosition) {
                            case 0:
                                algoFragment.setupFragment(Algorithm.N_QUEENS);
                                break;
                        }
                        break;
                    case 6:
                        switch (childPosition) {
                            case 0:
                                mDrawerLayout.closeDrawers();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Helpers.showAbout(MainActivity.this);
                                    }
                                }, 350);
                                break;
                            case 1:
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                Uri data = Uri.parse("https://github.com/naman14/AlgorithmVisualizer-Android");
                                intent.setData(data);
                                startActivity(intent);
                                break;
                            case 2:
                                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                                break;
                            case 3:
                                startActivity(new Intent(MainActivity.this, DonateActivity.class));
                                break;
                        }
                        break;

                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return false;
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<ExpandedMenuModel>();
        listDataChild = new HashMap<ExpandedMenuModel, List<String>>();

        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setName("Search");
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setName("Sorting");
        listDataHeader.add(item2);

        ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setName("Tree");
        listDataHeader.add(item3);

        ExpandedMenuModel item4 = new ExpandedMenuModel();
        item4.setName("List");
        listDataHeader.add(item4);

        ExpandedMenuModel item5 = new ExpandedMenuModel();
        item5.setName("Graph");
        listDataHeader.add(item5);

        ExpandedMenuModel item6 = new ExpandedMenuModel();
        item6.setName("Backtracking");
        listDataHeader.add(item6);

        ExpandedMenuModel item10 = new ExpandedMenuModel();
        item10.setName("About");
        listDataHeader.add(item10);

        List<String> heading1 = new ArrayList<>();
        heading1.add("Binary search");
        heading1.add("Linear Search");

        List<String> heading2 = new ArrayList<String>();
        heading2.add("Bubble Sort");
        heading2.add("Insertion Sort");
        heading2.add("Selection Sort");
        heading2.add("Quicksort");
        List<String> heading3 = new ArrayList<String>();
        heading3.add("BST Search");
        heading3.add("BST Insert");

        List<String> heading4 = new ArrayList<String>();
        heading4.add("Linked List");
        heading4.add("Stack");

        List<String> heading5 = new ArrayList<String>();
        heading5.add("BFS Traversal");
        heading5.add("DFS Travsersal");
        heading5.add("Dijkstra");
        heading5.add("Bellman Ford");

        List<String> heading6 = new ArrayList<String>();
        heading6.add("N Queens Problem");

        List<String> heading10 = new ArrayList<String>();
        heading10.add("About");
        heading10.add("Fork on Github");
        heading10.add("Settings");

        try {
            if (BillingProcessor.isIabServiceAvailable(this)) {
                heading10.add("Support development");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        listDataChild.put(listDataHeader.get(0), heading1);
        listDataChild.put(listDataHeader.get(1), heading2);
        listDataChild.put(listDataHeader.get(2), heading3);
        listDataChild.put(listDataHeader.get(3), heading4);
        listDataChild.put(listDataHeader.get(4), heading5);
        listDataChild.put(listDataHeader.get(5), heading6);
        listDataChild.put(listDataHeader.get(6), heading10);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}