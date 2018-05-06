package br.com.marcos.eitacasei.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.adapters.PresenteAdapter;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 06/05/18.
 */

public class ListaPresentesActivity extends Activity {

    /**
     * Adapter para o ListView de Presentes
     */
    private PresenteAdapter presentesAdapter;

    /**
     * Lista dos presentes do casal
     */
    private ArrayList<Presente> presentes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_presentes);

        //Trata o caso dessa Activity ter sido chamada pela de criação de Presentes
        Intent presenteCriado = getIntent();
        if(presenteCriado != null && presenteCriado.getExtras() != null
                && presenteCriado.getExtras().get(Presente.PRESENTE_INFO) != null){
            presentes = (ArrayList<Presente>) presenteCriado.getExtras().get(Presente.PRESENTE_INFO);
        }

        if(savedInstanceState != null){
            presentes = (ArrayList<Presente>) savedInstanceState.getSerializable("presentes");
        }

        if(presentes == null) {
            presentes = new ArrayList<Presente>();
        }

        ListView listaPresentes = findViewById(R.id.listaPresentes);
        presentesAdapter = new PresenteAdapter(this, presentes);
        listaPresentes.setAdapter(presentesAdapter);

        presentesAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cadastrarConvidado:
                Intent telaListaConvidado = new Intent(this, ListaConvidadosActivity.class);
                startActivity(telaListaConvidado);
                return true;
            case R.id.logout:
                Intent telaLogin = new Intent(this, LoginActivity.class);
                startActivity(telaLogin);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("presentes",presentes);
    }

    /**
     * Redireciona para a tela de cadastro de presentes
     * @param view
     */
    public void cadastrarPresente(View view){
        Intent telaCadastroPresente = new Intent(this, ManterPresenteActivity.class);

        telaCadastroPresente.putExtra(Presente.PRESENTE_INFO, presentes);

        startActivity(telaCadastroPresente);
    }
}
