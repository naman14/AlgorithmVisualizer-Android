package com.naman14.algovisualizer.visualizer.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import com.naman14.algovisualizer.algorithm.graph.Edge;
import com.naman14.algovisualizer.algorithm.graph.WeightedGraph;
import com.naman14.algovisualizer.visualizer.AlgorithmVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by naman on 16/11/16.
 */

public class WeightedGraphVisualizer extends AlgorithmVisualizer {

    private Paint circlePaint;
    private Paint textPaint;
    private Paint weightPaint;
    private Paint linePaint;
    private Paint circleHighlightPaint;
    private Paint lineHighlightPaint;
    private Rect bounds;

    private List<Integer> highlightNode = new ArrayList<>();
    private Map<Integer, List<Integer>> highlightLine = new HashMap<>();
    private Map<Integer, Point> pointMap = new HashMap<>();

    private List<Integer> array = new ArrayList<>();

    private WeightedGraph graph;

    public WeightedGraphVisualizer(Context context) {
        super(context);
        initialise();
    }

    public WeightedGraphVisualizer(Context context, AttributeSet attrs) {
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

        weightPaint = new Paint(textPaint);
        weightPaint.setColor(Color.BLUE);
        weightPaint.setTextSize(getDimensionInPixelFromSP(14));

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


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getDimensionInPixel(280));
    }

    public void setData(WeightedGraph graph) {
        this.graph = graph;
        invalidate();
        Log.e("lol", graph.toString());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (graph != null)
            drawGraph(canvas);
    }

    private void drawGraph(Canvas canvas) {

        int[][] vertices = {
                {0, 1, 2, 3, 4},
                {getWidth() - getDimensionInPixel(50), getWidth() / 2 + getDimensionInPixel(50), getWidth() / 2 - getDimensionInPixel(50), getDimensionInPixel(40), getWidth() / 2},
                {getHeight() / 2 + getDimensionInPixel(20), getDimensionInPixel(20), getDimensionInPixel(20), getHeight() / 2 + getDimensionInPixel(20), getHeight() - getDimensionInPixel(30)}
        };

        for (int i = 0; i < graph.size(); i++) {
            int node = vertices[0][i];
            Point p = new Point(vertices[1][i], vertices[2][i]);
            addNode(p, node);
        }

        drawNodes(canvas);
    }

    private void addNode(Point point, int i) {
        pointMap.put(i, point);
    }

    private void drawNodes(Canvas canvas) {
        for (Map.Entry<Integer, Point> entry : pointMap.entrySet()) {
            Integer key = entry.getKey();

            Iterator<Edge> it = graph.getIncidentEdges(key);
            while (it.hasNext()) {
                Edge edge = it.next();
                drawNodeLine(canvas, key, edge.vertex2(), (int) edge.weight());

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


    private void drawNodeLine(Canvas canvas, int s, int e, int weight) {

        Point start = pointMap.get(s);
        Point end = pointMap.get(e);

        int midx = (start.x + end.x) / 2 + 20;
        int midy = (start.y + end.y) / 2 + 20;

        boolean highlight = (highlightLine.containsKey(s) && highlightLine.get(s).contains(e));
        if (highlight) {
            canvas.drawLine(start.x, start.y, end.x, end.y, lineHighlightPaint);
        } else {
            canvas.drawLine(start.x, start.y, end.x, end.y, linePaint);
        }
        canvas.drawText(String.valueOf(weight), midx, midy, weightPaint);

    }

    public void highlightNode(int node) {
        this.highlightNode.add(node);
        invalidate();
    }

    public void highlightLine(int start, int end) {
        List<Integer> edges = highlightLine.get(start);
        if (edges == null) {
            edges = new ArrayList<>();
        }
        edges.add(end);
        this.highlightLine.put(start, edges);
        invalidate();
    }


    @Override
    public void onCompleted() {
        highlightLine.clear();
        highlightNode.clear();
    }

}
