package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by naman on 02/06/16.
 */
public class SortingVisualizer extends View {

    Paint paint;
    Paint highlightPaint;
    int[] array;

    int highlightPosition = -1;
    int highlightColor;

    public SortingVisualizer(Context context) {
        super(context);
        initialise();
    }

    public SortingVisualizer(Context context, AttributeSet atrrs) {
        super(context, atrrs);
        initialise();
    }

    private void initialise() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#009688"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(30);

        highlightPaint = new Paint();
        highlightPaint.setColor(Color.RED);
        highlightPaint.setStyle(Paint.Style.FILL);
        highlightPaint.setStrokeWidth(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (array != null) {

            int numberOfLines = array.length;

            float margin = (getWidth() - (30 * numberOfLines)) / (numberOfLines + 1);

            float xPos = margin;
            for (int i = 0; i < array.length; i++) {

                if (highlightPosition != -1 && highlightPosition == i) {
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 10.0) * getHeight()), xPos, getHeight(), highlightPaint);
                    highlightPosition = -1;
                } else
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 10.0) * getHeight()), xPos, getHeight(), paint);

                xPos += margin + 30;
            }
        }


    }

    public void setData(int[] integers) {
        this.array = integers;
        invalidate();
    }

    public void highlight(int position) {
        highlightPosition = position;
        invalidate();
    }
}
