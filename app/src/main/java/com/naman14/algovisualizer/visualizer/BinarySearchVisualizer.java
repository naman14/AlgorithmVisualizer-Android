package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naman on 14/11/16.
 */

public class BinarySearchVisualizer extends AlgorithmVisualizer {

    Paint paint;
    Paint highlightPaint; //blue paint
    Paint highlightPaintTrace; //red paint
    Paint textPaint;

    int[] array;
    List<Integer> highlightPositions = new ArrayList<>();
    int highlightPositionTrace = -1;

    int lineStrokeWidth = getDimensionInPixel(10);


    public BinarySearchVisualizer(Context context) {
        super(context);
        initialise();
    }

    public BinarySearchVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#009688"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(lineStrokeWidth);

        highlightPaint = new Paint(paint);
        highlightPaint.setColor(Color.BLUE);

        highlightPaintTrace = new Paint(paint);
        highlightPaintTrace.setColor(Color.RED);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(getDimensionInPixelFromSP(12));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (array != null) {
            int numberOfLines = array.length;

            float margin = (getWidth() - (30 * numberOfLines)) / (numberOfLines + 1);

            float xPos = margin + getDimensionInPixel(10);
            for (int i = 0; i < array.length; i++) {

                if (highlightPositions.contains(i)) {
                    if (highlightPositionTrace != -1 && highlightPositionTrace == i) {
                        canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 110.0) * getHeight()), xPos, getHeight(), highlightPaintTrace);
                    } else
                        canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 110.0) * getHeight()), xPos, getHeight(), highlightPaint);
                } else {
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 110.0) * getHeight()), xPos, getHeight(), paint);
                }

                canvas.drawText(String.valueOf(array[i]), xPos - lineStrokeWidth / 1.7f, getHeight() - (float) ((array[i] / 110.0) * getHeight()) - 30, textPaint);

                xPos += margin + 30;
            }
            highlightPositions.clear();
            highlightPositionTrace = -1;
        }


    }

    public void setData(int[] integers) {
        this.array = integers;
        invalidate();
    }

    public void highlight(List<Integer> positions) {
        this.highlightPositions.clear();
        this.highlightPositions.addAll(positions);
        invalidate();
    }

    public void highlightTrace(int pos) {
        this.highlightPositionTrace = pos;
        invalidate();
    }

    @Override
    public void onCompleted() {

    }
}
