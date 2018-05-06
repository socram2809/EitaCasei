package br.com.marcos.eitacasei.dominio;

import java.io.Serializable;

/**
 * Created by Marcos on 06/05/18.
 */

public class Convidado implements Serializable{

    private String nome;

    private Usuario usuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
