package br.com.marcos.eitacasei.activities;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.adapters.PresenteAdapter;
import br.com.marcos.eitacasei.dominio.Presente;
import br.com.marcos.eitacasei.view.PresenteViewModel;

/**
 * Created by Marcos on 06/05/18.
 */
public class ListaPresentesActivity extends AppCompatActivity {

    /**
     * Adapter para o ListView de Presentes
     */
    private PresenteAdapter presentesAdapter;

    /**
     * Presente a ser removido
     */
    private Presente presenteSelecionado;

    /**
     * Gerencia os dados relativos a UI em relação aos presentes
     */
    private PresenteViewModel presenteViewModel;

    /**
     * Constante que identifica a activity para cadastrar presentes
     */
    private static final int CADASTRAR_PRESENTE = 1;

    /**
     * Constante que identifica a activity para editar presentes
     */
    private static final int EDITAR_PRESENTE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_presentes);

        presentesAdapter = new PresenteAdapter(this, new ArrayList<Presente>());

        ListView listaPresentes = findViewById(R.id.listaPresentes);
        listaPresentes.setAdapter(presentesAdapter);

        presenteViewModel = ViewModelProviders.of(this).get(PresenteViewModel.class);

        presenteViewModel.getListaPresentes().observe(this, new Observer<List<Presente>>() {
            @Override
            public void onChanged(@Nullable List<Presente> presentes) {
                presentesAdapter.setPresentes(presentes);
                presentesAdapter.notifyDataSetChanged();
            }
        });

        Button fab = (Button) findViewById(R.id.botaoPresente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ListaPresentesActivity.this, ManterPresenteActivity.class),
                        CADASTRAR_PRESENTE);
            }
        });

        registerForContextMenu(listaPresentes);
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
                editaPresente((Presente) presentesAdapter.getItem(info.position));
                return true;
            case R.id.remover:
                removePresente((Presente) presentesAdapter.getItem(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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
            case R.id.cadastrarCasal:
                Intent telaListaCasal = new Intent(this, ListaCasaisActivity.class);
                startActivity(telaListaCasal);
                return true;
            case R.id.logout:
                Intent telaLogin = new Intent(this, LoginActivity.class);
                startActivity(telaLogin);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Recebe o resultado da Activity de cadastro de presentes
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == this.RESULT_OK && requestCode == CADASTRAR_PRESENTE){
            Presente presente = (Presente) data.getExtras().get(Presente.PRESENTE_INFO);
            presenteViewModel.inserir(presente);
        }else if(resultCode == this.RESULT_OK && requestCode == EDITAR_PRESENTE){
            Presente presente = (Presente) data.getExtras().get(Presente.PRESENTE_INFO);
            presenteViewModel.atualizar(presente);
        }
    }

    /**
     * Edita o presente
     */
    private void editaPresente(Presente presente){

        Intent editarPresente = new Intent(this, ManterPresenteActivity.class);

        editarPresente.putExtra(Presente.PRESENTE_EDIT, presente);

        startActivityForResult(editarPresente, EDITAR_PRESENTE);
    }

    /**
     * Remove o presente
     */
    private void removePresente(final Presente presente){

        presenteSelecionado = presente;

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Define o título
        builder.setTitle("Remoção");
        //Define a mensagem
        builder.setMessage("Deseja realmente remover esse presente?");
        //Define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenteViewModel.remover(presenteSelecionado);
            }
        });
        //Define um botão como negativo
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Não faz nada...
            }
        });
        //Cria o alert dialog
        AlertDialog alert = builder.create();
        //Exibe
        alert.show();
    }
}
