package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.naman14.algovisualizer.DataUtils;
import com.naman14.algovisualizer.algorithm.tree.bst.BinarySearchTree;

/**
 * Created by naman on 03/07/16.
 */
public class BSTVisualizer extends AlgorithmVisualizer {

    private Paint paint;
    private Paint circlePaint;
    private Rect bounds;

    private BinarySearchTree b;

    public BSTVisualizer(Context context) {
        super(context);
        initialise();
    }

    public BSTVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    private void initialise() {

        b = DataUtils.createBinaryTree();

        paint = new Paint();
        circlePaint = new Paint();

        paint.setColor(Color.WHITE);
        paint.setTextSize(getDimensionInPixelFromSP(12));
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);

        bounds = new Rect();
        paint.getTextBounds("0", 0, 1, bounds);

        circlePaint.setColor(Color.RED);
        circlePaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (b != null) {
            drawCircleTextNode(canvas, getWidth() / 2, 15 - (bounds.height() / 2), 0);

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

    private void drawCircleTextNode(Canvas canvas, int x, int y, int number) {
        canvas.drawCircle(x, y, bounds.width() + 5, circlePaint);
        canvas.drawText(String.valueOf(number), -3, 15, paint);
    }


}
