package br.com.marcos.eitacasei.dominio;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Marcos on 06/05/18.
 */

public class Presente implements Serializable {

    /**
     * Chave que identifica o presente
     */
    public static final String PRESENTE_INFO = "PresenteInfo";

    private String produto;

    private SerialBitmap foto;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public SerialBitmap getFoto() {
        return foto;
    }

    public void setFoto(SerialBitmap foto) {
        this.foto = foto;
    }
}
