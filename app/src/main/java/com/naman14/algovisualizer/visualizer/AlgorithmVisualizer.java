package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by naman on 02/06/16.
 */
public abstract class AlgorithmVisualizer extends View {

    public AlgorithmVisualizer(Context context) {
        super(context);

    }

    public AlgorithmVisualizer(Context context, AttributeSet atrrs) {
        super(context, atrrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getDimensionInPixel(360));
    }

    public int getDimensionInPixel(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public int getDimensionInPixelFromSP(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public abstract void onCompleted();
}
