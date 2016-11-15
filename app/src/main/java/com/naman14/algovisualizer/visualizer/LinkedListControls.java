package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.naman14.algovisualizer.R;
import com.naman14.algovisualizer.algorithm.list.linkedlist.LinkedList;

/**
 * Created by naman on 15/11/16.
 */

public class LinkedListControls extends LinearLayout {

    LinkedList linkedList;

    public LinkedListControls(Context context) {
        super(context);
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

        Button add, addAfter, deleteFront, traverse;

        add = (Button) controls.findViewById(R.id.llc_add);
        addAfter = (Button) controls.findViewById(R.id.llc_add_after);
        deleteFront = (Button) controls.findViewById(R.id.llc_delete_front);
        traverse = (Button) controls.findViewById(R.id.llc_traverse);

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedList.sendMessage(LinkedList.ADD);
            }
        });
        addAfter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedList.sendMessage(LinkedList.ADD_AFTER);

            }
        });
        deleteFront.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedList.sendMessage(LinkedList.DELETE_FRONT);
            }
        });
        traverse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedList.sendMessage(LinkedList.TRAVERSE);
            }
        });

        addView(controls);
    }
}
