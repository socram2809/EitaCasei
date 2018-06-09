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

    /**
     * Identificador do presente
     */
    private Long id;

    /**
     * Nome do produto
     */
    private String produto;

    /**
     * Foto do produto
     */
    private byte[] foto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
}
