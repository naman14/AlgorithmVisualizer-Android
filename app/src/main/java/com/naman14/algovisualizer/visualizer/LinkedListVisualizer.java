package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Created by naman on 15/11/16.
 */

public class LinkedListVisualizer extends AlgorithmVisualizer {

    int[] array;

    private Paint textPaint;
    private Paint circlePaint;
    private Paint linePaint;
    private Paint circleHighlightPaint;
    private Paint lineHighlightPaint;
    private Rect bounds;

    private int highlightNode = -1;

    public LinkedListVisualizer(Context context) {
        super(context);
        initialise();
    }

    public LinkedListVisualizer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public void setData(int[] integers) {
        this.array = integers;
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
            drawLinkedList(canvas);
        }
    }

    private void drawLinkedList(Canvas canvas) {

        int blockWidth = getWidth() / (array.length);

        Point p = new Point();
        p.x = (array.length > 5) ? getDimensionInPixel(30) : getDimensionInPixel(40);
        p.y = getHeight() / 2;

        for (int i = 0; i < array.length; i++) {
            Point end = new Point(p);
            end.x = p.x + blockWidth;
            if (!(i == array.length - 1))
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
                canvas.drawLine(end.x, end.y, end.x - 15, end.y - 15, lineHighlightPaint);
                canvas.drawLine(midx, midy, midx - getDimensionInPixel(6), midy - getDimensionInPixel(5), lineHighlightPaint);
                canvas.drawLine(midx, midy, midx - getDimensionInPixel(6), midy + getDimensionInPixel(5), lineHighlightPaint);

            } else {
                canvas.drawLine(start.x, start.y, end.x, end.y, linePaint);
                canvas.drawLine(midx, midy, midx - getDimensionInPixel(6), midy - getDimensionInPixel(5), linePaint);
                canvas.drawLine(midx, midy, midx - getDimensionInPixel(6), midy + getDimensionInPixel(5), linePaint);
            }
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

    @Override
    public void onCompleted() {

    }
}
