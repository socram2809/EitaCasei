package br.com.marcos.eitacasei.dominio;

import java.io.Serializable;

/**
 * Created by Marcos on 06/05/18.
 */

public class Usuario implements Serializable{

    private String login;

    private String senha;

    private boolean convidado;

    private boolean casal;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isConvidado() {
        return convidado;
    }

    public void setConvidado(boolean convidado) {
        this.convidado = convidado;
    }

    public boolean isCasal() {
        return casal;
    }

    public void setCasal(boolean casal) {
        this.casal = casal;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", convidado=" + convidado +
                ", casal=" + casal +
                '}';
    }
}
