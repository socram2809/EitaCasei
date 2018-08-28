package br.com.marcos.eitacasei.activities;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
     * Adapter de Convidados
     */
    private ConvidadoAdapter convidadoAdapter;

    /**
     * Convidado a ser mantido
     */
    private Convidado convidado;

    /**
     * Gerencia os dados relativos a UI em relação aos convidados
     */
    private ConvidadoViewModel convidadoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_convidados);

        if(savedInstanceState == null){
            convidado = new Convidado();
        }

        convidadoAdapter = new ConvidadoAdapter(this, new ArrayList<Convidado>());

        ListView listaConvidados = findViewById(R.id.listaConvidados);
        listaConvidados.setAdapter(convidadoAdapter);

        convidadoViewModel = ViewModelProviders.of(this).get(ConvidadoViewModel.class);

        convidadoViewModel.getListaConvidados().observe(this, new Observer<List<Convidado>>() {
            @Override
            public void onChanged(@Nullable List<Convidado> convidados) {
                convidadoAdapter.setConvidados(convidados);
                convidadoAdapter.notifyDataSetChanged();
            }
        });

        registerForContextMenu(listaConvidados);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_selecao, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.editar:
                editaConvidado((Convidado) convidadoAdapter.getItem(info.position));
                return true;
            case R.id.remover:
                removeConvidado((Convidado) convidadoAdapter.getItem(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Edita o convidado
     */
    private void editaConvidado(Convidado convidado){
        EditText textConvidado = findViewById(R.id.nomeConvidado);
        textConvidado.setText(convidado.getNome());
        this.convidado = convidado;
    }

    private void removeConvidado(Convidado convidado){
        convidadoViewModel.remover(convidado);
        this.convidado = new Convidado();
    }

    /**
     * Mantém o convidado
     * @param view
     */
    public void cadastrarConvidado(View view){
        EditText textConvidado = findViewById(R.id.nomeConvidado);
        convidado.setNome(textConvidado.getText().toString());

        if(convidado.getId() == 0) {
            convidadoViewModel.inserir(convidado);
        }else{
            convidadoViewModel.atualizar(convidado);
        }

        convidado = new Convidado();

        textConvidado.setText("");
    }

    /**
     * Volta para a listagem de presentes
     * @param view
     */
    public void cancelar(View view){
        finish();
    }
}
