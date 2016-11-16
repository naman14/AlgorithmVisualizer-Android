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
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.naman14.algovisualizer.algorithm.list.Stack;

public class StackVisualizer extends AlgorithmVisualizer {

    int[] array;
    Stack stack;

    private Paint textPaint;
    private Paint circlePaint;
    private Paint linePaint;
    private Paint circleHighlightPaint;
    private Paint lineHighlightPaint;
    private Rect bounds;

    private int highlightNode = -1;

    public StackVisualizer(Context context) {
        super(context);
        initialise();
    }

    public StackVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public void setData(Stack stack) {
        this.stack = stack;
        this.array = stack.getStackArray();
        invalidate();
    }


    private void initialise() {

        textPaint = new Paint();
        circlePaint = new Paint();

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(getDimensionInPixelFromSP(15));
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        bounds = new Rect();
        textPaint.getTextBounds("0", 0, 1, bounds);

        circlePaint.setColor(Color.RED);
        circlePaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setStrokeWidth(5);
        linePaint.setColor(Color.BLACK);

        circleHighlightPaint = new Paint(circlePaint);
        circleHighlightPaint.setColor(Color.BLUE);

        lineHighlightPaint = new Paint(linePaint);
        lineHighlightPaint.setColor(Color.RED);
        lineHighlightPaint.setStrokeWidth(getDimensionInPixel(2));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getDimensionInPixel(170));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (array != null && array.length != 0) {
            drawStack(canvas);
        }
    }

    private void drawStack(Canvas canvas) {
        int actualLength = 0;
        for (int i : array) {
            if (i != 0)
                actualLength++;
        }
        if (actualLength == 0)
            return;
        int blockWidth = getWidth() / (actualLength);

        Point p = new Point();
        p.x = (array.length > 4) ? getDimensionInPixel(30) : getDimensionInPixel(40);
        p.y = getHeight() / 2;

        for (int i = 0; i < array.length; i++) {
            Point end = new Point(p);
            end.x = p.x + blockWidth;
            if (!(i == array.length - 1) && array[i] != 0 && array[i + 1] != 0)
                drawNodeLine(canvas, p, end, false, true);
            drawCircleTextNode(canvas, p, array[i]);
            p.x += blockWidth;
        }
    }


    private void drawNodeLine(Canvas canvas, Point start, Point end, boolean highlight, boolean draw) {
        if (draw) {
            int midx = (start.x + end.x) / 2;
            int midy = (start.y + end.y) / 2;

            if (highlight) {
                canvas.drawLine(start.x, start.y, end.x, end.y, lineHighlightPaint);
                canvas.drawLine(midx, midy, midx - getDimensionInPixel(6), midy + getDimensionInPixel(5), lineHighlightPaint);
                canvas.drawLine(midx, midy, midx - getDimensionInPixel(6), midy - getDimensionInPixel(5), lineHighlightPaint);

            } else {
                canvas.drawLine(start.x, start.y, end.x, end.y, linePaint);
                canvas.drawLine(midx, midy, midx - getDimensionInPixel(6), midy - getDimensionInPixel(5), linePaint);
                canvas.drawLine(midx, midy, midx - getDimensionInPixel(6), midy + getDimensionInPixel(5), linePaint);
            }
        }
    }

    private void drawCircleTextNode(Canvas canvas, Point p, int number) {
        if (number == 0) {
            return;
        }
        String text = String.valueOf(number);

        if (number == highlightNode) {
            canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circleHighlightPaint);
        } else {
            canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circlePaint);
        }
        int yOffset = bounds.height() / 2;

        canvas.drawText(text, p.x, p.y + yOffset, textPaint);

    }

    public void highlightNode(int pos) {
        this.highlightNode = pos;
        invalidate();
    }

    @Override
    public void onCompleted() {
        this.highlightNode = -1;
        invalidate();
    }
}
