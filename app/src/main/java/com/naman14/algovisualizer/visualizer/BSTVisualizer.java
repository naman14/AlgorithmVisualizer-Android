package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import com.naman14.algovisualizer.DataUtils;
import com.naman14.algovisualizer.algorithm.tree.bst.BinarySearchTree;

/**
 * Created by naman on 03/07/16.
 */
public class BSTVisualizer extends AlgorithmVisualizer {

    private Paint textPaint;
    private Paint circlePaint;
    private Paint linePaint;

    private Rect bounds;

    private BinarySearchTree b;
    private int[] array = DataUtils.bst_array;

    public BSTVisualizer(Context context) {
        super(context);
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
        textPaint.setTextSize(getDimensionInPixelFromSP(12));
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        bounds = new Rect();
        textPaint.getTextBounds("0", 0, 1, bounds);

        circlePaint.setColor(Color.RED);
        circlePaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setStrokeWidth(10);
        linePaint.setColor(Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (b != null) {
            Log.e("lol",String.valueOf(b.getRoot().data));
            b.traverse(b.getRoot());
//            drawBst(canvas);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setData(BinarySearchTree b) {
        this.b = b;
        invalidate();
    }

    private void drawCircleTextNode(Canvas canvas, Point p, int number) {
        String text = String.valueOf(number);

        canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circlePaint);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Rect result = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), result);
        int yOffset = result.height() / 2;

        canvas.drawText(text, p.x, p.y + yOffset, textPaint);
    }

    private void drawLine(Canvas canvas, BinarySearchTree.Node parent, BinarySearchTree.Node child) {
        Point start = calculateNodePositon(parent);
        Point end = calculateNodePositon(child);

        canvas.drawLine(start.x, start.y, end.x, end.y, linePaint);
    }

    private void drawBst(Canvas canvas) {
        for (int i = 0; i < array.length; i++) {
            BinarySearchTree.Node current = b.traverse(b.getRoot(), array[i]);
            drawCircleTextNode(canvas, calculateNodePositon(current), 0);

            if (current.left != null)
                drawLine(canvas, current, current.left);
            if (current.right != null)
                drawLine(canvas, current, current.right);

        }
    }

    private Point calculateNodePositon(BinarySearchTree.Node node) {
        int x = 0;
        int y = 0;
        Point p = new Point(x, y);
        BinarySearchTree.Node root = b.getRoot();

        if (node == root) {
            p.x = getWidth()/2;
            p.y = getDimensionInPixel(20);
            return p;
        }

        return p;
    }


}
