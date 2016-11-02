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
import java.util.List;

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
    private int[][] bst = DataUtils.bst;

    List<Integer> nodesdrawn = new ArrayList<>();

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

            p0.x = getWidth() / 2 + getDimensionInPixel(20);
            p0.y = getDimensionInPixel(40);

            if (parentnode == root) {

                p.x = getWidth() / 2 + getDimensionInPixel(20);
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


            if (leftnode != -1 && rightnode != -1 && leftnode == rightnode) {
                p1.x = p.x;
                p1.y = p.y + getDimensionInPixel(70);
                drawNodeLine(canvas, p, p1);
                drawCircleTextNode(canvas, p1, leftnode);

            } else {
                if (leftnode != -1) {
                    p1.x = p.x - getDimensionInPixel(60);
                    p1.y = p.y + getDimensionInPixel(70);
                    drawNodeLine(canvas, p, p1);
                    drawCircleTextNode(canvas, p1, leftnode);

                }
                if (rightnode != -1) {
                    p2.x = p.x + getDimensionInPixel(60);
                    p2.y = p.y + getDimensionInPixel(70);
                    drawNodeLine(canvas, p, p2);
                    drawCircleTextNode(canvas, p2, rightnode);

                }
            }

            drawCircleTextNode(canvas, p, parentnode);

        }
    }

    private void drawCircleTextNode(Canvas canvas, Point p, int number) {
        if (!nodesdrawn.contains(number)) {
            String text = String.valueOf(number);

            canvas.drawCircle(p.x, p.y, getDimensionInPixel(15), circlePaint);
            int yOffset = bounds.height() / 2;

            canvas.drawText(text, p.x, p.y + yOffset, textPaint);
            nodesdrawn.add(number);
        }

    }


    private void drawNodeLine(Canvas canvas, Point start, Point end) {
        canvas.drawLine(start.x, start.y,
                end.x, end.y, linePaint);
    }


}
