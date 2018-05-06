package br.com.marcos.eitacasei.dominio;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Marcos on 06/05/18.
 */
public class SerialBitmap implements Serializable {
    private final int [] pixels;
    private final int width , height;

    public SerialBitmap(Bitmap bitmap){
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        pixels = new int [width*height];
        bitmap.getPixels(pixels,0,width,0,0,width,height);
    }

    public Bitmap getBitmap(){
        return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888);
    }
}
