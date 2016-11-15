package com.naman14.algovisualizer;

/**
 * Created by naman on 03/07/16.
 */
public class AlgorithmCode {

    public static final String CODE_BUBBLE_SORT = "  logArray(\"Original array - \", array);\n" +
            "\n" +
            "  for (int i = 0; i < array.length; i++) {\n" +
            "   addLog(\"Doing iteration - \" + i);\n" +
            "   boolean swapped = false;\n" +
            "   for (int j = 0; j < array.length - 1; j++) {\n" +
            "    highlightTrace(j);\n" +
            "    sleep();\n" +
            "    if (array[j] > array[j + 1]) {\n" +
            "     highlightSwap(j, j + 1);\n" +
            "     addLog(\"Swapping \" + array[j] + \" and \" + array[j + 1]);\n" +
            "     int temp = array[j];\n" +
            "     array[j] = array[j + 1];\n" +
            "     array[j + 1] = temp;\n" +
            "     swapped = true;\n" +
            "     sleep();\n" +
            "    }\n" +
            "   }\n" +
            "   if (!swapped) {\n" +
            "    break;\n" +
            "   }\n" +
            "   sleep();\n" +
            "  }\n" +
            "  addLog(\"Array has been sorted\");\n" +
            "  completed();";

    public static final String CODE_INSERTION_SORT = " logArray(\"Original array - \", array);\n" +
            "\n" +
            " int n = array.length;\n" +
            " for (int j = 1; j < n; j++) {\n" +
            "  int key = array[j];\n" +
            "  int i = j - 1;\n" +
            "  while ((i > -1) && (array[i] > key)) {\n" +
            "   array[i + 1] = array[i];\n" +
            "   highlightSwap(i, i + 1);\n" +
            "   addLog(\"Swapping \" + array[i] + \" and \" + array[i + 1]);\n" +
            "   i--;\n" +
            "  }\n" +
            "  sleep();\n" +
            "  array[i + 1] = key;\n" +
            " }\n" +
            " addLog(\"Array has been sorted\");\n" +
            " completed();";

    public static final String CODE_BST_SEARCH = " int id = DataUtils.getRandomKeyFromBST();\n" +
            " addLog(\"Searching for \" + String.valueOf(id));\n" +
            " BinarySearchTree.Node current = b.getRoot();\n" +
            " addLog(\"Starting from root: \" + current.data);\n" +
            " highlightNode(current.data);\n" +
            " sleep();\n" +
            " while (current != null) {\n" +
            "  if (current.data == id) {\n" +
            "   addLog(\"Key \" + String.valueOf(id) + \" found in binary search tree\");\n" +
            "   completed();\n" +
            "   break;\n" +
            "  } else if (current.data > id) {\n" +
            "   addLog(\"Going from \" + current.data + \" to \" + current.left.data);\n" +
            "   highlightLine(current.data, current.left.data);\n" +
            "   current = current.left;\n" +
            "   highlightNode(current.data);\n" +
            "   sleep();\n" +
            "  } else {\n" +
            "   addLog(\"Going from \" + current.data + \" to \" + current.right.data);\n" +
            "   highlightLine(current.data, current.right.data);\n" +
            "   current = current.right;\n" +
            "   highlightNode(current.data);\n" +
            "   sleep();\n" +
            "  }\n" +
            " }";

    public static final String CODE_BST_INSERT = " int[] array = DataUtils.bst_array;\n" +
            " BinarySearchTree tree = new BinarySearchTree();\n" +
            " logArray(\"Items to be inserted - \", array);\n" +
            " removeAllNodes();\n" +
            " sleepFor(800);\n" +
            "\n" +
            " for (int i = 0; i < array.length; i++) {\n" +
            "  addLog(\"Inserting \" + array[i] + \" in the binary tree\");\n" +
            "  tree.insert(array[i]);\n" +
            "  addNode(array[i]);\n" +
            "  highlightNode(array[i]);\n" +
            "  sleepFor(800);\n" +
            " }\n" +
            "\n" +
            " highlightNode(-1);\n" +
            " completed();";
    public static final String CODE_BINARY_SEARCH = " logArray(\"Array - \", array);\n" +
            "\n" +
            " int rnd = new Random().nextInt(array.length);\n" +
            " int data = array[rnd];\n" +
            " addLog(\"Searching for \" + data);\n" +
            "\n" +
            " int low = 0;\n" +
            " int high = array.length - 1;\n" +
            "\n" +
            " highlight((low + high) / 2, false, false);\n" +
            " sleepFor(1000);\n" +
            "\n" +
            " while (high >= low) {\n" +
            "\n" +
            "  int middle = (low + high) / 2;\n" +
            "  addLog(\"Searching at index: \" + middle);\n" +
            "  highlightTrace(middle);\n" +
            "\n" +
            "  if (array[middle] == data) {\n" +
            "   addLog(data + \" is found at position \" + middle);\n" +
            "   break;\n" +
            "  }\n" +
            "  if (array[middle] < data) {\n" +
            "   low = middle + 1;\n" +
            "   addLog(\"Going right\");\n" +
            "   highlight(middle, false, true);\n" +
            "   sleepFor(1000);\n" +
            "  }\n" +
            "  if (array[middle] > data) {\n" +
            "   high = middle - 1;\n" +
            "   addLog(\"Going left\");\n" +
            "   highlight(middle, true, false);\n" +
            "   sleepFor(1000);\n" +
            "  }\n" +
            " }\n" +
            " completed();";

    public static final String CODE_LINKED_LIST_INSERT = "  Node nd = new Node();\n" +
            "  nd.setValue(element);\n" +
            "  addLog(\"Adding: \" + element + \" to the list\");\n" +
            "  sleep();\n" +
            "  if (linkedList.head == null) {\n" +
            "   addLog(\"List is empty, setting both head and tail to the same element\");\n" +
            "   linkedList.head = nd;\n" +
            "   linkedList.tail = nd;\n" +
            "  } else {\n" +
            "   addLog(\"Setting current tail next link to new node\");\n" +
            "   linkedList.tail.setNextRef(nd);\n" +
            "   addLog(\"Set tail as newly created node\");\n" +
            "   linkedList.tail = nd;\n" +
            "  }\n" +
            "  sleep();\n" +
            "  updateData(linkedList);\n" +
            "  highlightNode(element);";

    public static final String CODE_LINKED_LIST_DELETE = "if (linkedList.head == null) {\n" +
            " addLog(\"Linked list is empty\");\n" +
            " return;\n" +
            "}\n" +
            "Node tmp = linkedList.head;\n" +
            "addLog(\"Current head is :\" + tmp.getValue());\n" +
            "sleep();\n" +
            "addLog(\"Deleting head: \" + tmp.getValue());\n" +
            "highlightNode(tmp.getValue());\n" +
            "sleep();\n" +
            "linkedList.head = tmp.getNextRef();\n" +
            "if (linkedList.head == null) {\n" +
            " addLog(\"The next node is null, setting tail as null\");\n" +
            " linkedList.tail = null;\n" +
            "} else addLog(\"Setting head to the next node:\" + linkedList.head.getValue());\n" +
            "sleep();\n" +
            "addLog(\"Deleted: \" + tmp.getValue() + \" from the list\");\n" +
            "updateData(linkedList);";

    public static final String CODE_STACK_PUSH = "addLog(\"Pushing data:\" + data + \" into the stack\");\n" +
            "if (stack.top == stack.maxSize - 1) {\n" +
            " addLog(\"Stack is full, unable to push\");\n" +
            " addLog(\"max sixe of stack is \" + stack.maxSize);\n" +
            " return;\n" +
            "}\n" +
            "stack.stackArray[++stack.top] = data;\n" +
            "addLog(\"Pushed:\" + data + \" into the stack, the new head is: \" + data);\n" +
            "updateData(stack);\n" +
            "highlightNode(data);\n" +
            "sleep();\n" +
            "highlightNode(-1);";

    public static final String CODE_STACK_PEEK = "if (stack.isEmpty()) {\n" +
            " addLog(\"Stack is empty, unable to peek\");\n" +
            " return;\n" +
            "}\n" +
            "addLog(\"Peeking into the stack\");\n" +
            "int top = stack.stackArray[stack.top];\n" +
            "addLog(\"The head is: \" + top);\n" +
            "updateData(stack);\n" +
            "highlightNode(top);\n" +
            "sleep();\n" +
            "highlightNode(-1);";

    public static final String CODE_STACK_POP = " if (stack.isEmpty()) {\n" +
            "  addLog(\"Stack is empty, unable to pop\");\n" +
            "  return;\n" +
            " }\n" +
            " addLog(\"Popping the stack\");\n" +
            " int pop = stack.pop();\n" +
            " addLog(\"Popped the stack, the data popped is \" + pop);\n" +
            " int top = stack.top;\n" +
            " stack.stackArray[top + 1] = 0;\n" +
            " highlightNode(pop);\n" +
            " sleep();\n" +
            " updateData(stack);\n" +
            " highlightNode(-1);";

}
