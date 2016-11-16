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
import com.naman14.algovisualizer.visualizer.LinkedListVisualizer;

public class LinkedList extends Algorithm implements DataHandler {

    public static final String ADD = "add";
    public static final String ADD_AFTER = "add_after";
    public static final String DELETE_FRONT = "delete_front";
    public static final String DELETE_AFTER = "delete_after";
    public static final String TRAVERSE = "traverse";

    private LinkedListVisualizer visualizer;
    private LinkedList linkedList;

    private Node head;
    private Node tail;

    public LinkedList() {

    }

    public LinkedList(LinkedListVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    //LinkedList implementation
    private class Node {

        private int value;
        private Node nextRef;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNextRef() {
            return nextRef;
        }

        public void setNextRef(Node ref) {
            this.nextRef = ref;
        }
    }


    public int size() {
        int size = 0;
        Node tmp = head;
        while (true) {
            if (tmp == null) {
                break;
            }
            size++;
            tmp = tmp.getNextRef();
        }
        return size;
    }

    public int[] getArray() {
        int[] array = new int[size()];
        int i = 0;
        Node tmp = head;
        while (true) {
            if (tmp == null) {
                break;
            }
            array[i] = tmp.getValue();
            tmp = tmp.getNextRef();
            i++;
        }
        return array;
    }

    public void add(int element) {

        Node nd = new Node();
        nd.setValue(element);
        if (head == null) {
            head = nd;
            tail = nd;
        } else {
            tail.setNextRef(nd);
            tail = nd;
        }
        ;
    }

    public void addAfter(int element, int after) {

        Node tmp = head;
        Node refNode = null;
        System.out.println("Traversing to all nodes..");

        while (true) {
            if (tmp == null) {
                break;
            }
            if (tmp.getValue() == after) {
                refNode = tmp;
                break;
            }
            tmp = tmp.getNextRef();
        }
        if (refNode != null) {
            Node nd = new Node();
            nd.setValue(element);
            nd.setNextRef(tmp.getNextRef());
            if (tmp == tail) {
                tail = nd;
            }
            tmp.setNextRef(nd);

        } else {
            System.out.println("Unable to find the given element...");
        }
    }

    public void deleteAfter(int after) {

        Node tmp = head;
        Node refNode = null;
        System.out.println("Traversing to all nodes..");

        while (true) {
            if (tmp == null) {
                break;
            }
            if (tmp.getValue() == after) {
                refNode = tmp;
                break;
            }
            tmp = tmp.getNextRef();
        }
        if (refNode != null) {
            tmp = refNode.getNextRef();
            refNode.setNextRef(tmp.getNextRef());
            if (refNode.getNextRef() == null) {
                tail = refNode;
            }
            System.out.println("Deleted: " + tmp.getValue());
        } else {
            System.out.println("Unable to find the given element...");
        }
    }


    //LinkedList visuzlizations
    public void visualizeAdd(int element) {

        Node nd = new Node();
        nd.setValue(element);
        addLog("Adding: " + element + " to the list");
        sleep();
        if (linkedList.head == null) {
            addLog("List is empty, setting both head and tail to the same element");
            linkedList.head = nd;
            linkedList.tail = nd;
        } else {
            addLog("Setting current tail next link to new node");
            linkedList.tail.setNextRef(nd);
            addLog("Set tail as newly created node");
            linkedList.tail = nd;
        }
        sleep();
        updateData(linkedList);
        highlightNode(element);
    }

    public void deleteFront() {

        if (linkedList.head == null) {
            addLog("Linked list is empty");
            return;
        }
        Node tmp = linkedList.head;
        addLog("Current head is :" + tmp.getValue());
        sleep();
        addLog("Deleting head: " + tmp.getValue());
        highlightNode(tmp.getValue());
        sleep();
        linkedList.head = tmp.getNextRef();
        if (linkedList.head == null) {
            addLog("The next node is null, setting tail as null");
            linkedList.tail = null;
        } else addLog("Setting head to the next node:" + linkedList.head.getValue());
        sleep();
        addLog("Deleted: " + tmp.getValue() + " from the list");
        updateData(linkedList);
    }


    public void traverse() {
        addLog("Traversing the Linked List");
        sleep();
        Node tmp = linkedList.head;
        addLog("Starting from head :" + tmp.getValue());
        highlightNode(tmp.getValue());
        while (true) {
            if (tmp == null) {
                break;
            }
            addLog("Current value: " + tmp.getValue());
            highlightNode(tmp.getValue());
            sleep();
            addLog("Moving forward");
            sleep();
            tmp = tmp.getNextRef();
        }
        addLog("Traversing completed");
        completed();
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
            case Algorithm.COMMAND_START_ALGORITHM:
                //traverse command
                startExecution();
                clearLog();
                traverse();
                break;
            case ADD:
                clearLog();
                visualizeAdd(DataUtils.getRandomInt(40)+5);
                break;
            case DELETE_FRONT:
                clearLog();
                deleteFront();
                break;
        }
    }

    @Override
    public void onDataRecieved(Object data) {
        this.linkedList = (LinkedList) data;
    }

    public void setData(final LinkedList ll) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(ll);
            }
        });
        start();
        prepareHandler(this);
        sendData(ll);
    }

    private void updateData(final LinkedList ll) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(ll);
            }
        });

    }
}



