package com.naman14.algovisualizer.algorithm.list.linkedlist;

import android.app.Activity;

import com.naman14.algovisualizer.DataUtils;
import com.naman14.algovisualizer.LogFragment;
import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.LinkedListVisualizer;

/**
 * Created by naman on 15/11/16.
 */

public class LinkedList extends Algorithm implements DataHandler {

    public static final String ADD = "add";
    public static final String ADD_AFTER = "add_after";
    public static final String DELETE_FRONT = "delete_front";
    public static final String DELETE_AFTER = "delete_after";
    public static final String TRAVERSE = "traverse";

    private LinkedListVisualizer visualizer;
    private int[] array;

    private Node head;
    private Node tail;

    public LinkedList() {

    }

    public LinkedList(LinkedListVisualizer visualizer, Activity activity, LogFragment logFragment) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.logFragment = logFragment;
    }

    public void add(int element) {

        Node nd = new Node();
        nd.setValue(element);
        System.out.println("Adding: " + element);

        if (head == null) {
            head = nd;
            tail = nd;
        } else {
            tail.setNextRef(nd);
            tail = nd;
        }
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

    public void deleteFront() {

        if (head == null) {
            System.out.println("Underflow...");
        }
        Node tmp = head;
        head = tmp.getNextRef();
        if (head == null) {
            tail = null;
        }
        System.out.println("Deleted: " + tmp.getValue());
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

    public void traverse() {

        Node tmp = head;
        while (true) {
            if (tmp == null) {
                break;
            }
            System.out.println(tmp.getValue());
            tmp = tmp.getNextRef();
        }
    }

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

    @Override
    public void onMessageReceived(String message) {
        switch (message) {
            case ADD:
                startExecution();
                add(DataUtils.getRandomInt(10));
                break;
        }
    }

    @Override
    public void onDataRecieved(Object data) {
        this.array = (int[]) data;
    }

    public void setData(final int[] array) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(array);
            }
        });
        start();
        prepareHandler(this);
        sendData(array);
    }
}



