package com.example.tsnt.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-03-03 16:47
 * @Description: 通用的工具类
 */

public class CommonUtil {

    /**
     * 检测sdcard是否可用
     *
     * @return true为可用，否则为不可用
     */
    public static boolean sdCardIsAvailable() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    public static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getPicRootPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/AndroidStudy";
    }

    public static File makeFilePath(File file) throws IOException {
        File lFile = file;
        if (!lFile.exists()) {
            // make parent dirs if necessary
            File dir = new File(lFile.getParent());
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                }
            }
            // make file
            try {
                if (lFile.createNewFile()) {
                } else {
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return lFile;
    }

    /**
     * 扫描、刷新相册
     */
    public static void scanPhotos(String filePath, Context context) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    public static String saveImage(Context context, String name, Bitmap bitmap) {
        String path;
        if (sdCardIsAvailable()) {
            path = getPicRootPath();
        } else {
            path = StorageUtils.getCacheDirectory(context) + "/AndroidStudy";
        }
        File file = new File(path, name + ".png");
        if (file.exists()) {
            file.delete();
        }
        try {
            file = makeFilePath(file);
            saveImage2File(file, bitmap, true);
            scanPhotos(file.getAbsolutePath(), context);
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static boolean saveImage2File(File file, Bitmap bitmap, boolean isPng) {
        if (bitmap == null) {
            return false;
        }
        File coverFile = file;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(coverFile);
            if (isPng) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } else {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStream(out);
        }
        return true;
    }
}
