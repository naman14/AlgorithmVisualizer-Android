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
                traverse();
                break;
            case ADD:
                add(DataUtils.getRandomInt(9));
                break;
            case ADD_AFTER:
                addAfter(DataUtils.getRandomInt(9), DataUtils.getRandomInt(9));
                break;
            case DELETE_FRONT:
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
}



