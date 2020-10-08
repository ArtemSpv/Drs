package com.example.drs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import static com.example.drs.Tags.TAG;

public class FragmentImageTranslate extends Fragment implements View.OnTouchListener {


    @BindView(R.id.btnRB)
    Button btnMoveRB;

    @BindView(R.id.image_translate)
    ImageView img;

    @BindView(R.id.imgBackground)
    ImageView imgBack;

    @BindView(R.id.seekBar)
    SeekBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Fragment");


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Fragment");
        View view = inflater.inflate(R.layout.fragment_image_translate, container, false);
        ButterKnife.bind(this, view);

        img.setImageResource(R.drawable.b_arc_brown);

        btnMoveRB.setOnTouchListener(this);

        return view;

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        SecondActivity.
                frgLayout.setLayoutParams(SecondActivity.presenter.
                adjust(
                        motionEvent.getAction(),
                        (int) motionEvent.getRawX(),
                        (int) motionEvent.getRawY()));

        return true;
    }
}
