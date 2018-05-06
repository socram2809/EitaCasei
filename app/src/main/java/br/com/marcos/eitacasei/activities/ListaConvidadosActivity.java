package br.com.marcos.eitacasei.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.adapters.ConvidadoAdapter;
import br.com.marcos.eitacasei.dominio.Convidado;

/**
 * Created by Marcos on 06/05/18.
 */

public class ListaConvidadosActivity extends Activity {

    /**
     * Lista de Convidados
     */
    private ArrayList<Convidado> convidados;

    /**
     * Adapter de Convidados
     */
    private ConvidadoAdapter convidadoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_convidados);

        if(savedInstanceState == null) {
            convidados = new ArrayList<Convidado>();
        }else{
            convidados = (ArrayList<Convidado>) savedInstanceState.getSerializable("convidados");
        }
        ListView listaConvidados = findViewById(R.id.listaConvidados);
        convidadoAdapter = new ConvidadoAdapter(this, convidados);
        listaConvidados.setAdapter(convidadoAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("convidados",convidados);
    }

    /**
     * Cadastra o convidado
     * @param view
     */
    public void cadastrarConvidado(View view){
        EditText textConvidado = findViewById(R.id.nomeConvidado);
        Convidado convidado = new Convidado();
        convidado.setNome(textConvidado.getText().toString());
        convidados.add(convidado);
        convidadoAdapter.notifyDataSetChanged();
    }
}
