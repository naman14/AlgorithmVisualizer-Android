package com.naman14.algovisualizer.visualizer.graph;

import android.content.Context;
import android.util.AttributeSet;

import com.naman14.algovisualizer.visualizer.AlgorithmVisualizer;

/**
 * Created by naman on 16/11/16.
 */

public class UndirectedGraph extends AlgorithmVisualizer {


    public UndirectedGraph(Context context) {
        super(context);
        initialise();
    }

    public UndirectedGraph(Context context, AttributeSet attrs) {
        super(context,attrs);
        initialise();
    }

    private void initialise() {

    }

    @Override
    public void onCompleted() {

    }
}
