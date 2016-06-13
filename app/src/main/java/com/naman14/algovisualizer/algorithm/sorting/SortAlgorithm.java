package com.naman14.algovisualizer.algorithm.sorting;

import android.os.Handler;

import com.naman14.algovisualizer.algorithm.Algorithm;
import com.naman14.algovisualizer.algorithm.DataHandler;
import com.naman14.algovisualizer.visualizer.SortingVisualizer;

/**
 * Created by naman on 02/06/16.
 */
public class SortAlgorithm extends Algorithm implements DataHandler {

    public SortingVisualizer visualizer;

    public SortAlgorithm(Handler.Callback callback) {
        super(callback);
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

    public void highlight(final int pos) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlight(pos);
            }
        });
    }

    public void logArray(final int[] array) {
        String arrayString = "";
        for (int i : array) {
            arrayString.concat(" " + String.valueOf(i) + " ");
        }
        addLog(arrayString);
    }

    @Override
    public void onDataRecieved(Object data) {

    }

    @Override
    public void onMessageReceived(String message) {

    }
}
