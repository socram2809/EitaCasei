package br.com.marcos.eitacasei.dominio;

import java.io.Serializable;

/**
 * Created by Marcos on 06/05/18.
 */

public class Convidado implements Serializable{

    /**
     * Chave que identifica o convidado
     */
    public static final String CONVIDADO_INFO = "ConvidadoInfo";

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
