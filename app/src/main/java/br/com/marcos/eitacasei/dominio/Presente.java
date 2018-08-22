package br.com.marcos.eitacasei.dominio;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

import java.io.Serializable;
import java.sql.Blob;

/**
 * Created by Marcos on 06/05/18.
 */
@Entity
public class Presente implements Serializable {

    /**
     * Chave que identifica o presente
     */
    @Deprecated
    public static final String PRESENTE_INFO = "PresenteInfo";

    /**
     * Chave que identifica a edição de presentes
     */
    public static final String PRESENTE_EDIT = "PresenteEdicao";

    /**
     * Identificador do presente
     */
    @PrimaryKey(autoGenerate = true)
    private long id;

    /**
     * Nome do produto
     */
    private String produto;

    /**
     * Foto do produto
     */
    private String foto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
}
