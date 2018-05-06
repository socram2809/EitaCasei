package br.com.marcos.eitacasei.dominio;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marcos on 06/05/18.
 */

public class PresenteCasal implements Serializable {

    private Casal casal;

    private List<Presente> presentes;

    public Casal getCasal() {
        return casal;
    }

    public void setCasal(Casal casal) {
        this.casal = casal;
    }

    public List<Presente> getPresentes() {
        return presentes;
    }

    public void setPresentes(List<Presente> presentes) {
        this.presentes = presentes;
    }
}
