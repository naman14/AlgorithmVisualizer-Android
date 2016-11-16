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

package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.naman14.algovisualizer.R;
import com.naman14.algovisualizer.algorithm.list.LinkedList;
import com.roughike.bottombar.BottomBar;

public class LinkedListControls extends LinearLayout {

    LinkedList linkedList;
    BottomBar bottomBar;
    FloatingActionButton fab;

    public LinkedListControls(Context context, BottomBar bottomBar, FloatingActionButton fab) {
        super(context);
        this.bottomBar = bottomBar;
        this.fab = fab;
        initialise();
    }

    public LinkedListControls(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public void setLinkedList(LinkedList algorithm) {
        this.linkedList = algorithm;
    }


    private void initialise() {
        setOrientation(VERTICAL);

        View controls = LayoutInflater.from(getContext()).inflate(R.layout.linked_list_controls, this, false);

        final Button add, deleteFront, traverse;

        add = (Button) controls.findViewById(R.id.llc_add);
        deleteFront = (Button) controls.findViewById(R.id.llc_delete_front);
        traverse = (Button) controls.findViewById(R.id.llc_traverse);

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomBar.selectTabAtPosition(1,true);
                linkedList.sendMessage(LinkedList.ADD);
            }
        });
        deleteFront.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomBar.selectTabAtPosition(1,true);
                linkedList.sendMessage(LinkedList.DELETE_FRONT);
            }
        });
        traverse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.performClick();
            }
        });


        addView(controls);
    }
}
