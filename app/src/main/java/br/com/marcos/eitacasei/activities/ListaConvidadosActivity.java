package br.com.marcos.eitacasei.activities;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.adapters.ConvidadoAdapter;
import br.com.marcos.eitacasei.adapters.PresenteAdapter;
import br.com.marcos.eitacasei.dominio.Convidado;
import br.com.marcos.eitacasei.dominio.Presente;
import br.com.marcos.eitacasei.view.ConvidadoViewModel;
import br.com.marcos.eitacasei.view.PresenteViewModel;

/**
 * Created by Marcos on 06/05/18.
 */

public class ListaConvidadosActivity extends AppCompatActivity {

    /**
     * Lista de Convidados
     */
    private ArrayList<Convidado> convidados;

    /**
     * Adapter de Convidados
     */
    private ConvidadoAdapter convidadoAdapter;

    /**
     * Gerencia os dados relativos a UI em relação aos convidados
     */
    private ConvidadoViewModel convidadoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_convidados);

        ListView listaConvidados = findViewById(R.id.listaConvidados);
        listaConvidados.setAdapter(convidadoAdapter);

        convidadoAdapter = new ConvidadoAdapter(this);

        convidadoViewModel = ViewModelProviders.of(this).get(ConvidadoViewModel.class);

        convidadoViewModel.getListaConvidados().observe(this, new Observer<List<Convidado>>() {
            @Override
            public void onChanged(@Nullable List<Convidado> convidados) {
                convidadoAdapter.setConvidados(convidados);
            }
        });
    }

    /**
     * Cadastra o convidado
     * @param view
     */
    public void cadastrarConvidado(View view){
        EditText textConvidado = findViewById(R.id.nomeConvidado);
        Convidado convidado = new Convidado();
        convidado.setNome(textConvidado.getText().toString());

        convidadoViewModel.inserir(convidado);
    }
}
