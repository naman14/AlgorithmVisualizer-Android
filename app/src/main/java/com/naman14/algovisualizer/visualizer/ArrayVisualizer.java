package com.naman14.algovisualizer.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.naman14.algovisualizer.DataUtils;

/**
 * Created by naman on 02/11/16.
 */
public class ArrayVisualizer extends View {

    private Paint containerPaint;
    private Paint textPaint;
    private Paint highlightPaint;

    private Path containerPath;

    private int[] bst_array = DataUtils.bst_array;

    private int highlightPosition = -1;

    public ArrayVisualizer(Context context) {
        super(context);
        initialise();
    }

    public ArrayVisualizer(Context context, AttributeSet atrrs) {
        super(context, atrrs);
        initialise();
    }


    private void initialise() {

        textPaint = new Paint();
        containerPaint = new Paint();
        highlightPaint = new Paint();

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(getDimensionInPixelFromSP(20));
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        containerPaint.setStyle(Paint.Style.STROKE);
        containerPaint.setColor(Color.BLACK);
        containerPaint.setStrokeWidth(5);

        containerPath = new Path();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getDimensionInPixel(80));
    }

    public int getDimensionInPixel(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public int getDimensionInPixelFromSP(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        containerPath.moveTo(getDimensionInPixel(40), getDimensionInPixel(5));
        containerPath.lineTo(getWidth() - getDimensionInPixel(40), getDimensionInPixel(5));
        containerPath.lineTo(getWidth() - getDimensionInPixel(40), getDimensionInPixel(45));
        containerPath.lineTo(getDimensionInPixel(40), getDimensionInPixel(45));
        containerPath.lineTo(getDimensionInPixel(40), getDimensionInPixel(5));
        containerPath.close();

        canvas.drawPath(containerPath, containerPaint);

        int posX = getDimensionInPixel(40) + getDimensionInPixel(15);
        int textWidth = (getWidth() - getDimensionInPixel(80)) / bst_array.length;

        for (int i = 0; i < bst_array.length; i++) {
            String number = String.valueOf(bst_array[i]);
            if (highlightPosition != -1 && highlightPosition == bst_array[i]) {
                canvas.drawText(number, posX, getDimensionInPixel(30), highlightPaint);
            } else {
                canvas.drawText(number, posX, getDimensionInPixel(30), textPaint);
            }
            posX += textWidth;
        }
    }

    public void highlightValue(int num) {
        this.highlightPosition = num;
    }

}
