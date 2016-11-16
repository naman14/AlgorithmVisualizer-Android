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
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class ArrayVisualizer extends View {

    private Paint containerPaint;
    private Paint textPaint;
    private Paint highlightPaint;

    private Path containerPath;

    private int[] array;

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

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(getDimensionInPixelFromSP(20));
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        highlightPaint = new Paint(textPaint);
        highlightPaint.setColor(Color.BLUE);
        highlightPaint.setTextSize(getDimensionInPixelFromSP(25));

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

        int density = getResources().getDisplayMetrics().densityDpi;

        int modifieddp = dp;
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                modifieddp = dp - dp / 2;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                modifieddp = dp - dp / 3;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                modifieddp = dp - dp / 4;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
            case DisplayMetrics.DENSITY_XXHIGH:
            case DisplayMetrics.DENSITY_XXXHIGH:
                modifieddp = dp;
                break;
            default:
                break;
        }

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, modifieddp, getResources().getDisplayMetrics());

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
        int textWidth = (getWidth() - getDimensionInPixel(80)) / array.length;

        for (int i = 0; i < array.length; i++) {
            String number = String.valueOf(array[i]);
            if (highlightPosition != -1 && highlightPosition == array[i]) {
                canvas.drawText(number, posX, getDimensionInPixel(30), highlightPaint);
            } else {
                canvas.drawText(number, posX, getDimensionInPixel(30), textPaint);
            }
            posX += textWidth;
        }
    }

    public void setData(int[] array) {
        this.array = array;
    }

    public void highlightValue(int num) {
        this.highlightPosition = num;
        invalidate();
    }

}
