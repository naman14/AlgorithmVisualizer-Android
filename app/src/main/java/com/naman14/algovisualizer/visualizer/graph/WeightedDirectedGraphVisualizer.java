package com.naman14.algovisualizer.visualizer.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.naman14.algovisualizer.algorithm.graph.WeightedDigraph;
import com.naman14.algovisualizer.visualizer.AlgorithmVisualizer;

/**
 * Created by naman on 16/11/16.
 */

public class WeightedDirectedGraphVisualizer extends AlgorithmVisualizer {

    private WeightedDigraph graph;

    public WeightedDirectedGraphVisualizer(Context context) {
        super(context);
        initialise();
    }

    public WeightedDirectedGraphVisualizer(Context context, AttributeSet attrs) {
        super(context,attrs);
        initialise();
    }

    private void initialise() {

    }

    public void setData(WeightedDigraph graph) {
        this.graph = graph;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGraph(canvas);
    }

    private void drawGraph(Canvas canvas) {

    }

    @Override
    public void onCompleted() {

    }
}
