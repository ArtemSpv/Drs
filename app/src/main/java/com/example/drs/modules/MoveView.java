package com.example.drs.modules;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MoveView {
    private RelativeLayout.LayoutParams RLayoutLast;
    int mX, mY;



    public MoveView translateImg(View v, int action, int X, int Y) {
        if (action == MotionEvent.ACTION_DOWN) {
            RelativeLayout.LayoutParams RlayoutFirst = (RelativeLayout.LayoutParams) v.getLayoutParams();
            mX = X - RlayoutFirst.leftMargin;
            mY = Y - RlayoutFirst.topMargin;
        } else if (action == MotionEvent.ACTION_MOVE) {
            RLayoutLast.leftMargin = X - mX;
            RLayoutLast.topMargin = Y - mY;
            RLayoutLast.rightMargin = -250;
            RLayoutLast.bottomMargin = -250;
        }
        return this;
    }


    public MoveView setRLayoutLast(RelativeLayout.LayoutParams RLayoutLast) {
        this.RLayoutLast = RLayoutLast;
        return this;
    }

    public RelativeLayout.LayoutParams getRLayoutLast() {
        return RLayoutLast;
    }
}
