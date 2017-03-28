package com.daltonicchameleon.portfolio.util.helper;

import android.content.Context;

import com.daltonicchameleon.portfolio.util.constants.Constants;

import java.io.IOException;
import java.io.InputStream;

/**
 * portfolio-app
 * Created in 3/28/17 by the following authors:
 * Pedro Okawa
 */
public class FileHelper {

    private Context context;

    public FileHelper(Context context) {
        this.context = context;
    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, Constants.FILE_HELPER_BYTES_UTF8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}