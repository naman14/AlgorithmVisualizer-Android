package com.naman14.algovisualizer;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class WebViewPager extends ViewPager {

    Rect outRect = new Rect();
    int[] location = new int[2];

    public WebViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v.getId() == R.id.codeViewRoot) {
            return inViewInBounds(v.findViewById(R.id.code_view),x,y);
        } else {
            return super.canScroll(v, checkV, dx, x, y);
        }
    }

    private boolean inViewInBounds(View view, int x, int y) {
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }
}