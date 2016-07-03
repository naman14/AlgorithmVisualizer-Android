package com.naman14.algovisualizer;

/**
 * Created by naman on 03/07/16.
 */
public class AlgorithmCode {

    public static final String CODE_BUBBLE_SORT = " for (int i = 0; i < array.length; i++) {\n" +
            "            addLog(\"Doing iteration - \" + i);\n" +
            "            boolean swapped = false;\n" +
            "            for (int j = 0; j < array.length - 1; j++) {\n" +
            "                highlightTrace(j);\n" +
            "                sleep();\n" +
            "                if (array[j] > array[j + 1]) {\n" +
            "                    highlightSwap(j, j + 1);\n" +
            "                    addLog(\"Swapping \" + array[j] + \" and \" + array[j + 1]);\n" +
            "                    int temp = array[j];\n" +
            "                    array[j] = array[j + 1];\n" +
            "                    array[j + 1] = temp;\n" +
            "                    swapped = true;\n" +
            "                    sleep();\n" +
            "                }\n" +
            "            }\n" +
            "            if (!swapped) {\n" +
            "                break;\n" +
            "            }\n" +
            "            sleep();\n" +
            "        }\n" +
            "        completed();";
}
