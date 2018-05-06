package br.com.marcos.eitacasei.dominio;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marcos on 06/05/18.
 */

public class Casal implements Serializable{

    private String noivo;

    private String noiva;

    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Casal{" +
                "noivo='" + noivo + '\'' +
                ", noiva='" + noiva + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}