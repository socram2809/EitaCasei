package br.com.marcos.eitacasei.dominio;

import java.io.Serializable;

/**
 * Created by Marcos on 06/05/18.
 */
@Deprecated
public class Usuario implements Serializable{

    private String login;

    private String senha;

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

    @Override
    public String toString() {
        return "login=" + login +
                ", senha=" + senha;
    }
}
