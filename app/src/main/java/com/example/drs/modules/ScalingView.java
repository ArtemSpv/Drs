package com.example.drs.modules;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class ScalingView {

    private RelativeLayout.LayoutParams RlayoutLast;
    private int width;
    private int height;
    private int rotation;
    private int mX, mY;


    public ScalingView(int width, int height) {
        this.width = width;
        this.height = height;

    }


    public ScalingView adjust( int action, int X, int Y) {
        if (action == MotionEvent.ACTION_DOWN) {
            RelativeLayout.LayoutParams RlayoutFirst = (RelativeLayout.LayoutParams) RlayoutLast;

            mX = X - RlayoutFirst.width;
            mY = Y - RlayoutFirst.height;

        } else if (action == MotionEvent.ACTION_MOVE) {

            RlayoutLast.width = X - mX;
            RlayoutLast.height = Y - mY;

        }
        return this;
    }


    public int adjustRotationView(int progress) {
        progress = (progress - 50) / 2;
        return progress;
    }


    public RelativeLayout.LayoutParams getRlayoutLast() {
        return RlayoutLast;
    }

    public ScalingView setRlayoutLast(RelativeLayout.LayoutParams RlayoutLast) {
        this.RlayoutLast = RlayoutLast;
        return this;
    }

}
