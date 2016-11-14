package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by naman on 14/11/16.
 */

public class BinarySearchVisualizer extends AlgorithmVisualizer {

    int[] array;

    public BinarySearchVisualizer(Context context) {
        super(context);
        initialise();
    }

    public BinarySearchVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {

    }

    public void setData(int[] integers) {
        this.array = integers;
        invalidate();
    }

    @Override
    public void onCompleted() {

    }
}
