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

import com.naman14.algovisualizer.DataUtils;
import com.naman14.algovisualizer.algorithm.tree.bst.BinarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BSTVisualizer extends AlgorithmVisualizer {

    private Paint textPaint;
    private Paint circlePaint;
    private Paint linePaint;
    private Paint circleHighlightPaint;
    private Paint lineHighlightPaint;

    private Rect bounds;
    private int height = 0;

    private BinarySearchTree b;
    private int[] array = DataUtils.bst_array;
    private int[][] bst = DataUtils.bst;

    private HashMap<Integer, Point> nodes = new HashMap<>();
    private List<Integer> visibleNodes = new ArrayList<>();

    private int highlightNode = -1;
    private int highlighLineStart = -1, highlightLineEnd = -1;

    public BSTVisualizer(Context context) {
        super(context);
        initialise();
    }

    public BSTVisualizer(Context context, int height) {
        super(context);
        this.height = height;
        initialise();
    }

    public BSTVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
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
        lineHighlightPaint.setStrokeWidth(10);

        for (int i : array) {
            visibleNodes.add(i);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (b != null) {
            drawBst(canvas);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (height != 0)
            setMeasuredDimension(getMeasuredWidth(), getDimensionInPixel(height));
    }

    public void setData(BinarySearchTree b) {
        this.b = b;
        invalidate();
    }

    private void drawBst(Canvas canvas) {


        int root = bst[0][0];

        int numLeftNodes = 0, numRightNodes = 0;

        for (int i = 0; i < array.length; i++) {

            int parentnode = bst[0][i];
            int leftnode = bst[1][i];
            int rightnode = bst[2][i];


            Point p = new Point();
            Point p0 = new Point();
            Point p1 = new Point();
            Point p2 = new Point();

            p0.x = getWidth() / 2 + getDimensionInPixel(25);
            p0.y = getDimensionInPixel(40);

            if (parentnode == root) {

                p.x = getWidth() / 2 + getDimensionInPixel(25);
                p.y = getDimensionInPixel(40);

            } else if (parentnode < root) {
                numLeftNodes++;
                p.x = p0.x - numLeftNodes * getDimensionInPixel(60);
                p.y = p0.y + numLeftNodes * getDimensionInPixel(70);

            } else if (parentnode > root) {
                numRightNodes++;
                if (parentnode == 6) {
                    p.x = p0.x;
                    p.y = p0.y + 2 * getDimensionInPixel(70);
                } else {
                    p.x = p0.x + numRightNodes * getDimensionInPixel(60);
                    p.y = p0.y + numRightNodes * getDimensionInPixel(70);
                }
            }

            boolean shouldDraw;

            if (leftnode != -1 && rightnode != -1 && leftnode == rightnode) {
                boolean highlight = false;
                p1.x = p.x;
                p1.y = p.y + getDimensionInPixel(70);
                if (parentnode == highlighLineStart && leftnode == highlightLineEnd && rightnode == highlightLineEnd) {
                    highlight = true;
                }
                shouldDraw = visibleNodes.contains(leftnode);
                drawNodeLine(canvas, p, p1, highlight, shouldDraw);
                addNode(p1, leftnode);

            } else {
                if (leftnode != -1) {
                    boolean highlight = false;
                    p1.x = p.x - getDimensionInPixel(60);
                    p1.y = p.y + getDimensionInPixel(70);
                    if (parentnode == highlighLineStart && leftnode == highlightLineEnd) {
                        highlight = true;
                    }
                    shouldDraw = visibleNodes.contains(leftnode);
                    drawNodeLine(canvas, p, p1, highlight, shouldDraw);
                    addNode(p1, leftnode);

                }
                if (rightnode != -1) {
                    boolean highlight = false;
                    p2.x = p.x + getDimensionInPixel(60);
                    p2.y = p.y + getDimensionInPixel(70);
                    if (parentnode == highlighLineStart && rightnode == highlightLineEnd) {
                        highlight = true;
                    }
                    shouldDraw = visibleNodes.contains(rightnode);
                    drawNodeLine(canvas, p, p2, highlight, shouldDraw);
                    addNode(p2, rightnode);

                }
            }
            addNode(p, parentnode);

        }

        drawNodes(canvas);
    }

    private void addNode(Point point, int number) {
        if (!nodes.containsKey(number)) {
            nodes.put(number, point);
        }
    }

    private void drawNodes(Canvas canvas) {
        for (HashMap.Entry<Integer, Point> entry : nodes.entrySet()) {
            Integer i = entry.getKey();
            Point p = entry.getValue();
            if (visibleNodes.contains(i))
                drawCircleTextNode(canvas, p, i);

        }
    }

    private void drawCircleTextNode(Canvas canvas, Point p, int number) {
        String text = String.valueOf(number);

        if (number == highlightNode) {
            canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circleHighlightPaint);
        } else {
            canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circlePaint);
        }
        int yOffset = bounds.height() / 2;

        canvas.drawText(text, p.x, p.y + yOffset, textPaint);

    }


    private void drawNodeLine(Canvas canvas, Point start, Point end, boolean highlight, boolean draw) {
        if (draw) {
            if (highlight) {
                canvas.drawLine(start.x, start.y, end.x, end.y, lineHighlightPaint);
            } else {
                canvas.drawLine(start.x, start.y, end.x, end.y, linePaint);
            }
        }
    }

    public void highlightNode(int node) {
        this.highlightNode = node;
        invalidate();
    }

    public void highlightLine(int start, int end) {
        this.highlighLineStart = start;
        this.highlightLineEnd = end;
        invalidate();
    }

    //methods for bst insert
    public void removeAllNodes() {
        visibleNodes.clear();
        invalidate();
    }

    public void addNode(int n) {
        visibleNodes.add(n);
        invalidate();
    }

    @Override
    public void onCompleted() {
        this.highlighLineStart = -1;
        this.highlightLineEnd = -1;
        invalidate();
    }
}
