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

package com.naman14.algovisualizer.visualizer.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.naman14.algovisualizer.algorithm.graph.Digraph;
import com.naman14.algovisualizer.visualizer.AlgorithmVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedGraphVisualizer extends AlgorithmVisualizer {

    private Paint circlePaint;
    private Paint textPaint;
    private Paint linePaint;
    private Paint circleHighlightPaint;
    private Paint lineHighlightPaint;
    private Rect bounds;

    private List<Integer> highlightNode = new ArrayList<>();
    private Map<Integer, List<Integer>> highlightLine = new HashMap<>();
    private Map<Integer, Point> pointMap = new HashMap<>();

    private Digraph graph;
    private List<Integer> array = new ArrayList<>();

    public DirectedGraphVisualizer(Context context) {
        super(context);
        initialise();
    }

    public DirectedGraphVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {
        circlePaint = new Paint();
        textPaint = new Paint();

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
        lineHighlightPaint.setColor(Color.BLUE);
        lineHighlightPaint.setStrokeWidth(10);
    }

    public void setData(Digraph graph) {
        this.graph = graph;
        this.array = graph.topSort();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (array != null && array.size() != 0)
            drawGraph(canvas);
    }

    private void drawGraph(Canvas canvas) {

        pointMap.clear();
        double[][] graphArray = graph.directed_array;

        int root = 0;

        for (int i = 0; i < array.size(); i++) {

            double parentnode = graphArray[0][i];
            double numhorizontalnode = graphArray[3][i];
            double numverticalnode = graphArray[4][i];
            double toLeftOfRoot = graphArray[5][i];

            Point p = new Point();
            Point p0 = new Point();

            p0.x = getWidth() / 2 + getDimensionInPixel(25);
            p0.y = getDimensionInPixel(40);

            if (parentnode == root) {

                p.x = getWidth() / 2 + getDimensionInPixel(25);
                p.y = getDimensionInPixel(40);

            } else if (toLeftOfRoot == 1) {
                p.x = (int) (p0.x - numhorizontalnode * getDimensionInPixel(60));
                p.y = (int) (p0.y + numverticalnode * getDimensionInPixel(70));

            } else if (toLeftOfRoot == 0) {
                p.x = (int) (p0.x + numhorizontalnode * getDimensionInPixel(60));
                p.y = (int) (p0.y + numverticalnode * getDimensionInPixel(70));

            }

            addNode(p, (int) parentnode);
        }

        drawNodes(canvas);

    }

    private void addNode(Point point, int i) {
        pointMap.put(i, point);
    }

    private void drawNodes(Canvas canvas) {
        for (Map.Entry<Integer, Point> entry : pointMap.entrySet()) {
            Integer key = entry.getKey();

            if (graph.exists(key)) {
                List<Integer> edges = graph.getNeighbours(key);
                for (Integer i : edges) {
                    if (pointMap.get(i) != null) {
                        drawNodeLine(canvas, key, i);
                    }
                }
            }
        }
        for (Map.Entry<Integer, Point> entry : pointMap.entrySet()) {
            Integer key = entry.getKey();
            Point value = entry.getValue();
            drawCircleTextNode(canvas, value, key);
        }
    }

    private void drawCircleTextNode(Canvas canvas, Point p, int number) {
        String text = String.valueOf(number);

        if (highlightNode.contains(number)) {
            canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circleHighlightPaint);
        } else {
            canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circlePaint);
        }
        int yOffset = bounds.height() / 2;

        canvas.drawText(text, p.x, p.y + yOffset, textPaint);

    }


    private void drawNodeLine(Canvas canvas, int s, int e) {

        Point start = pointMap.get(s);
        Point end = pointMap.get(e);

        int midx = (start.x + end.x) / 2;
        int midy = (start.y + end.y) / 2;

        boolean highlight = (highlightLine.containsKey(s) && highlightLine.get(s).contains(e));
        if (highlight) {
            canvas.drawLine(start.x, start.y, end.x, end.y, lineHighlightPaint);
            canvas.drawLine(midx, midy, midx + getDimensionInPixel(5), midy - getDimensionInPixel(2), lineHighlightPaint);
            canvas.drawLine(midx, midy, midx - getDimensionInPixel(5), midy - getDimensionInPixel(2), lineHighlightPaint);
        } else {
            canvas.drawLine(start.x, start.y, end.x, end.y, linePaint);
            canvas.drawLine(midx, midy, midx + getDimensionInPixel(5), midy - getDimensionInPixel(2), linePaint);
            canvas.drawLine(midx, midy, midx - getDimensionInPixel(5), midy - getDimensionInPixel(2), linePaint);
        }
    }

    public void highlightNode(int node) {
        this.highlightNode.add(node);
        invalidate();
    }

    public void highlightLine(int start, int end) {
        List<Integer> edges = highlightLine.get(start);
        if (edges ==null) {
            edges = new ArrayList<>();
        }
        edges.add(end);
        this.highlightLine.put(start,edges);
        invalidate();
    }


    @Override
    public void onCompleted() {
        highlightLine.clear();
        highlightNode.clear();
    }
}
