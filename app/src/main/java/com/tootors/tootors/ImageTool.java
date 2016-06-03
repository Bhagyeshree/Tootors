package com.tootors.tootors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ImageTool {

    public static String imageToBase64(String path) {
        String filePath = Environment.getExternalStorageDirectory() + path;
        Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 93, stream);
        byte[] byteArray = stream.toByteArray();
        String strBase64 = Base64.encodeToString(byteArray, Base64.URL_SAFE);

        return strBase64;
    }

    public static Bitmap base64ToImage(String base) {
        try {
            String noHeader = base.substring(base.indexOf(",") + 1); // remove data:image/png;base64,
            InputStream stream = new ByteArrayInputStream(Base64.decode(noHeader.getBytes(),
                    Base64.DEFAULT));
            return BitmapFactory.decodeStream(stream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
