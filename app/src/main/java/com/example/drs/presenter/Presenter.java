package com.example.drs.presenter;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.drs.R;
import com.example.drs.SecondActivity;
import com.example.drs.modules.ModuleApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.BlockingDeque;

import static com.example.drs.Tags.TAG;

public class Presenter {

    ModuleApp moduleApp;
    SecondActivity activity;
    Uri mImageUri;


    public Presenter(ModuleApp moduleApp) {
        this.moduleApp = moduleApp;
        this.activity = activity;
    }

    public RelativeLayout.LayoutParams translateImg(View v, int action, int X, int Y) {
        return moduleApp.translateImg(v, action, X, Y);
    }


    public RelativeLayout.LayoutParams adjust(int action, int X, int Y) {
        return moduleApp.adjust(action, X, Y);
    }


    public int adjustRotationView(int progress) {
        return moduleApp.adjustRotationView(progress);
    }

    public void showViews(Button btnRB, Button btnCamera, Button btnList, ImageView imgBackground, LinearLayout lineLower, SeekBar progressBar) {
        moduleApp.showViews(btnRB, btnCamera, btnList, imgBackground, lineLower, progressBar);
    }

    public void createDialogWindow(Context context) {

        Intent intent = new Intent();
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);

        builder.setPositiveButton("Галерея", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent.setAction(Intent.ACTION_PICK);
                ((SecondActivity) context).startActivityForResult(intent, 2);
            }
        });
        builder.setNegativeButton("Камера", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                ((SecondActivity) context).startActivityForResult(intent, 1);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setTitle("Выбор");
        dialog.show();

    }

    public Bitmap attachImageToBackground(Intent data, Context context) {
        Uri imageUri = data.getData();
        InputStream imageStream = null;
        try {
            imageStream = context.getContentResolver().openInputStream(imageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        return selectedImage;
    }


}
