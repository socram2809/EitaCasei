package br.com.marcos.eitacasei.dominio;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marcos on 06/05/18.
 */
@Entity
public class Casal implements Serializable{

    /**
     * Chave que identifica o casal
     */
    public static final String CASAL_INFO = "CasalInfo";

    /**
     * Chave que identifica a edição de casais
     */
    public static final String CASAL_EDIT = "CasalEdicao";

    /**
     * Identificador do casal
     */
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String noivo;

    private String noiva;

    private String login;

    private String senha;

    public String getNoivo() {
        return noivo;
    }

    public void setNoivo(String noivo) {
        this.noivo = noivo;
    }

    public String getNoiva() {
        return noiva;
    }

    public void setNoiva(String noiva) {
        this.noiva = noiva;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
        return "noivo=" + noivo +
                ", noiva=" + noiva +
                ", login=" + login +
                ", senha=" + senha + "\n";
    }
}
