package br.com.marcos.eitacasei.dominio;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Marcos on 06/05/18.
 */

public class Presente implements Serializable {

    private String produto;

    private Bitmap foto;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}
