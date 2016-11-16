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

package com.naman14.algovisualizer.algorithm.list;

import android.app.Activity;

import com.naman14.algovisualizer.DataUtils;
import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.StackVisualizer;

public class Stack extends Algorithm implements DataHandler {

    public static final String PUSH = "push";
    public static final String POP = "pop";
    public static final String PEEK = "peek";

    private StackVisualizer visualizer;
    private Stack stack;

    public int maxSize;
    private int[] stackArray;
    private int top;

    public Stack(int s) {
        maxSize = s;
        stackArray = new int[maxSize];
        top = -1;
    }

    public Stack(int s, StackVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this(s);
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }


    public void push(int j) {
        stackArray[++top] = j;
    }

    public int pop() {
        return stackArray[top--];
    }

    public int peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public int[] getStackArray() {
        return stackArray;
    }


    private void visualizePush(int data) {
        addLog("Pushing data:" + data + " into the stack");
        if (stack.top == stack.maxSize - 1) {
            addLog("Stack is full, unable to push");
            addLog("max sixe of stack is " + stack.maxSize);
            return;
        }
        stack.stackArray[++stack.top] = data;
        addLog("Pushed:" + data + " into the stack, the new head is: " + data);
        updateData(stack);
        highlightNode(data);
        sleep();
        highlightNode(-1);
    }

    private void visualizePeek() {
        if (stack.isEmpty()) {
            addLog("Stack is empty, unable to peek");
            return;
        }
        addLog("Peeking into the stack");
        int top = stack.stackArray[stack.top];
        addLog("The head is: " + top);
        updateData(stack);
        highlightNode(top);
        sleep();
        highlightNode(-1);
    }

    private void visualizePop() {
        if (stack.isEmpty()) {
            addLog("Stack is empty, unable to pop");
            return;
        }
        addLog("Popping the stack");
        int pop = stack.pop();
        addLog("Popped the stack, the data popped is " + pop);
        int top = stack.top;
        stack.stackArray[top + 1] = 0;
        highlightNode(pop);
        sleep();
        updateData(stack);
        highlightNode(-1);
    }

    private void highlightNode(final int node) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightNode(node);
            }
        });
    }

    @Override
    public void onMessageReceived(String message) {
        switch (message) {
            case PEEK:
                clearLog();
                visualizePeek();
                break;
            case PUSH:
                clearLog();
                visualizePush(DataUtils.getRandomInt(50) + 15);
                break;
            case POP:
                clearLog();
                visualizePop();
                break;
        }
    }

    @Override
    public void onDataRecieved(Object data) {
        this.stack = (Stack) data;
    }

    public void setData(final Stack stack) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(stack);
            }
        });
        start();
        prepareHandler(this);
        sendData(stack);
    }

    private void updateData(final Stack stack) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(stack);
            }
        });

    }
}