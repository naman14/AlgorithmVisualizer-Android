package com.naman14.algovisualizer.visualizer.graph;

import android.content.Context;
import android.util.AttributeSet;

import com.naman14.algovisualizer.visualizer.AlgorithmVisualizer;

/**
 * Created by naman on 16/11/16.
 */

public class WeightedUndirectedGraph extends AlgorithmVisualizer {


    public WeightedUndirectedGraph(Context context) {
        super(context);
        initialise();
    }

    public WeightedUndirectedGraph(Context context, AttributeSet attrs) {
        super(context,attrs);
        initialise();
    }

    private void initialise() {

    }

    @Override
    public void onCompleted() {

    }
}
