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

    public boolean find(int id) {
        Node current = root;
        while (current != null) {
            if (current.data == id) {
                return true;
            } else if (current.data > id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public void insert(int id) {
        Node newNode = new Node(id);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (id < current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
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

    public void traverse (Node root){ // Each child of a tree is a root of its subtree.
        if (root.left != null){
            traverse (root.left);
        }
        System.out.println(root.data);
        if (root.right != null){
            traverse (root.right);
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
