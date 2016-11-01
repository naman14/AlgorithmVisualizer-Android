package com.naman14.algovisualizer.algorithm.tree.bst;

/**
 * Created by naman on 03/07/16.
 */
public class BinarySearchTree {

    public static Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public boolean search(int val) {
        return search(root, val);
    }

    private boolean search(Node r, int val) {
        boolean found = false;
        while ((r != null) && !found) {
            int rval = r.data;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }


    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {
        if (node == null)
            node = new Node(data);
        else {
            if (data <= node.data)
                node.left = insert(node.left, data);
            else
                node.right = insert(node.right, data);
        }
        return node;
    }


    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 +
                    Math.max(getHeight(node.left),
                            getHeight(node.right));
        }
    }

    public Node traverse(Node root, int data) { // What data are you looking for again?
        if (root.data == data) {
            return root;
        }
        if (root.left != null) {
            return traverse(root.left, data);
        }
        if (root.right != null) {
            return traverse(root.right, data);
        }
        return null;
    }

    public void traverse(Node root) { // Each child of a tree is a root of its subtree.
        if (root.left != null) {
            traverse(root.left);
        }
        System.out.println(root.data);
        if (root.right != null) {
            traverse(root.right);
        }
    }


    public class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

}
