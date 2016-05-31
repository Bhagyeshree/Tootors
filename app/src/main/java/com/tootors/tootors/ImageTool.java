package com.tootors.tootors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;


public class ImageTool {

    public static String imageToBase64(String path) {
        String filePath = Environment.getExternalStorageDirectory() + path;
        Bitmap selectedImage =  BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 93, stream);
        byte[] byteArray = stream.toByteArray();
        String strBase64 = Base64.encodeToString(byteArray, Base64.URL_SAFE);

        return strBase64;
    }

    public static Bitmap base64ToImage(String base) {
        try {
            byte[] bytes = Base64.decode(base, Base64.URL_SAFE);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return decodedByte;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return null;
    }


}
