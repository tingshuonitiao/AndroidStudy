package com.example.tsnt.bitmap.ImageLoader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static android.R.attr.id;

/**
 * Created by ting说你跳 on 2017/4/16.
 */

public class ImageResizer {
    public static Bitmap compressImage(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //calculate inSampleSize
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, options);
        //set inSampleSize
        int size1 = options.outWidth / reqWidth;
        int size2 = options.outHeight / reqHeight;
        options.inSampleSize = size1 > size2 ? size1 : size2;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, id, options);
    }


}
