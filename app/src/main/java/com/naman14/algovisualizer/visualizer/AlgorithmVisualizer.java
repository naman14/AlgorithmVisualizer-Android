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
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public abstract class AlgorithmVisualizer extends View {

    public AlgorithmVisualizer(Context context) {
        super(context);

    }

    public AlgorithmVisualizer(Context context, AttributeSet atrrs) {
        super(context, atrrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getDimensionInPixel(330));
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

    public abstract void onCompleted();
}
