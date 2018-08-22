package br.com.marcos.eitacasei.dominio;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Marcos on 06/05/18.
 */
@Entity
public class Convidado implements Serializable{

    /**
     * Chave que identifica o convidado
     */
    public static final String CONVIDADO_INFO = "ConvidadoInfo";

    /**
     * Identificador do convidado
     */
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
