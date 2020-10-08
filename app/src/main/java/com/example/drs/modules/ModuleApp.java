package com.example.drs.modules;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import static com.example.drs.Tags.TAG;

public class ModuleApp {
    private MoveView moveView;
    private ScalingView scalingView;
    private RelativeLayout.LayoutParams RlayoutLast;
    private int heightDisplay;
    private boolean SHOW = true;


    public ModuleApp(MoveView moveView, ScalingView scalingView, RelativeLayout.LayoutParams RlayoutLast, int displayHeight) {

        this.moveView = moveView;
        this.scalingView = scalingView;
        this.RlayoutLast = RlayoutLast;
        heightDisplay = displayHeight / 4;
    }


    public RelativeLayout.LayoutParams translateImg(View v, int action, int X, int Y) {
        return moveView
                .setRLayoutLast(RlayoutLast)
                .translateImg(v, action, X, Y)
                .getRLayoutLast();
    }


    public RelativeLayout.LayoutParams adjust(int action, int X, int Y) {
        return scalingView
                .setRlayoutLast(RlayoutLast)
                .adjust(action, X, Y)
                .getRlayoutLast();
    }


    public int adjustRotationView(int progress) {
        return scalingView.adjustRotationView(progress);
    }


    public void showViews(Button btnRB, Button btnCamera, Button btnList, ImageView imgBackground, LinearLayout lineLower, SeekBar progressBar) {
        Log.d(TAG, "showViews: ");
        Animation a = null;

        if (SHOW) {
            a = new TranslateAnimation(0, 0, 0, heightDisplay);
            btnCamera.setEnabled(false);
            btnList.setEnabled(false);
            imgBackground.setVisibility(View.INVISIBLE);
            SHOW = false;
        } else {
            a = new TranslateAnimation(0, 0, heightDisplay, 0);
            btnCamera.setEnabled(true);
            btnList.setEnabled(true);
            imgBackground.setVisibility(View.VISIBLE);
            SHOW = true;
        }

        a.setDuration(1000);
        a.setFillAfter(true);

        lineLower.startAnimation(a);
        progressBar.startAnimation(a);
        btnRB.startAnimation(a);
    }


}