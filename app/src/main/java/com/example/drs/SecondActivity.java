package com.example.drs;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.drs.modules.MoveView;
import com.example.drs.modules.ScalingView;
import com.example.drs.modules.ModuleApp;
import com.example.drs.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.drs.Tags.TAG;


public class SecondActivity extends FragmentActivity implements View.OnTouchListener, SeekBar.OnSeekBarChangeListener {
    ViewGroup viewGroup;

    static RelativeLayout frgLayout;

    RelativeLayout.LayoutParams RLayoutLast;


    @BindView(R.id.seekBar)
    SeekBar progressBar;

    @BindView(R.id.image_translate)
    ImageView img;

    @BindView(R.id.btnRB)
    Button btnRB;

    @BindView(R.id.btnList)
    Button btnList;

    @BindView(R.id.btnCamera)
    Button btnCamera;

    @BindView(R.id.lineLower)
    LinearLayout lineLower;

    @BindView(R.id.imgBackground)
    ImageView imgBack;

    @BindView(R.id.Background)
    ImageView imgBackground;

    static Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "onCreate: SecondActivity");
        ButterKnife.bind(this);
        init();

    }


    public void init() {

        viewGroup = findViewById(R.id.mainLayout);
        frgLayout = viewGroup.findViewById(R.id.frgLayout);

        RLayoutLast = (RelativeLayout.LayoutParams) frgLayout.getLayoutParams();
        frgLayout.setLayoutParams(RLayoutLast);
        presenter = new Presenter(new ModuleApp(new MoveView(), new ScalingView(1, 1), RLayoutLast, getWindowManager().getDefaultDisplay().getHeight()));
        progressBar.setOnSeekBarChangeListener(this);
        frgLayout.setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        RLayoutLast = presenter.translateImg(view, motionEvent.getAction(), (int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        frgLayout.setLayoutParams(RLayoutLast);
        return true;
    }


    @OnClick(R.id.btnShow)
    void showViews() {
        presenter.showViews(btnRB, btnCamera, btnList, imgBack, lineLower, progressBar);

    }

    @OnClick(R.id.btnList)
    void startListActivity() {
        startActivity(new Intent(SecondActivity.this, ListActivity.class));
    }

    @OnClick(R.id.btnCamera)
    void startCamera() {
        presenter.createDialogWindow(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Log.d(TAG, "onActivityResult: camera");
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
            }
            if (requestCode == 2) {
                Log.d(TAG, "onActivityResult: galarey");
                imgBackground.setImageBitmap(presenter.attachImageToBackground(data, this));

            }
        } else {
            Toast.makeText(this, "Изображение не выбрано", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        img.setRotationY(presenter.adjustRotationView(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}