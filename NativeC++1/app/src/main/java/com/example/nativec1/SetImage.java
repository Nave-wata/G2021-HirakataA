package com.example.nativec1;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class SetImage {
    AssetManager assetManager;

    public SetImage(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void setImageViewBitmapFromAsset(ImageView imageView, String strName) {
        InputStream istr = null;

        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        imageView.setImageBitmap(bitmap);
    }

    public void setImageButtonBitmapFromAsset(ImageButton imageButton, String strName) {
        InputStream istr = null;

        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        imageButton.setImageBitmap(bitmap);
    }
}
