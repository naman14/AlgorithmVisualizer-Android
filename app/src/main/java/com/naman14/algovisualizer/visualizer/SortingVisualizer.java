/*
 * Copyright (C) 2016 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

public class SortingVisualizer extends AlgorithmVisualizer {

    Paint paint;
    Paint highlightPaintSwap;
    Paint highlightPaintTrace;
    Paint textPaint;
    int[] array;

    int highlightPositionOne = -1, highlightPositionTwo = -1;
    int highlightPosition = -1;
    int lineStrokeWidth = getDimensionInPixel(10);

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
        paint.setStrokeWidth(lineStrokeWidth);

        highlightPaintSwap = new Paint(paint);
        highlightPaintSwap.setColor(Color.RED);

        highlightPaintTrace = new Paint(paint);
        highlightPaintTrace.setColor(Color.BLUE);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(getDimensionInPixelFromSP(15));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (array != null) {
            int numberOfLines = array.length;

            float margin = (getWidth() - (30 * numberOfLines)) / (numberOfLines + 1);

            float xPos = margin + getDimensionInPixel(10);
            for (int i = 0; i < array.length; i++) {

                if (i == highlightPositionOne || i == highlightPositionTwo) {
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 10.0) * getHeight()), xPos, getHeight(), highlightPaintSwap);
                } else if (i == highlightPosition)
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 10.0) * getHeight()), xPos, getHeight(), highlightPaintTrace);
                else {
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 10.0) * getHeight()), xPos, getHeight(), paint);
                }

                canvas.drawText(String.valueOf(array[i]), xPos - lineStrokeWidth / 3, getHeight() - (float) ((array[i] / 10.0) * getHeight()) - 30, textPaint);

                xPos += margin + 30;
            }
            highlightPositionOne = -1;
            highlightPositionTwo = -1;
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setData(int[] integers) {
        this.array = integers;
        invalidate();
    }

    public void highlightSwap(int one, int two) {
        this.highlightPositionOne = one;
        this.highlightPositionTwo = two;
        invalidate();
    }

    public void highlightTrace(int position) {
        this.highlightPosition = position;
        invalidate();
    }

    @Override
    public void onCompleted() {
        this.highlightPosition = -1;
        this.highlightPositionTwo = -1;
        this.highlightPositionOne = -1;
        invalidate();
    }
}
