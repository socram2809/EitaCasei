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
import br.com.marcos.eitacasei.adapters.CasalAdapter;
import br.com.marcos.eitacasei.adapters.PresenteAdapter;
import br.com.marcos.eitacasei.dominio.Casal;
import br.com.marcos.eitacasei.dominio.Presente;
import br.com.marcos.eitacasei.view.CasalViewModel;
import br.com.marcos.eitacasei.view.PresenteViewModel;

/**
 * Created by Marcos on 27/08/18.
 */
public class ListaCasaisActivity extends AppCompatActivity {

    /**
     * Adapter para o ListView de Casais
     */
    private CasalAdapter casalAdapter;

    /**
     * Casal a ser removido
     */
    private Casal casalSelecionado;

    /**
     * Gerencia os dados relativos a UI em relação aos casais
     */
    private CasalViewModel casalViewModel;

    /**
     * Constante que identifica a activity para cadastrar casais
     */
    private static final int CADASTRAR_CASAL = 1;

    /**
     * Constante que identifica a activity para editar casais
     */
    private static final int EDITAR_CASAL = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_casais);

        casalAdapter = new CasalAdapter(this, new ArrayList<Casal>());

        ListView listaCasais = findViewById(R.id.listaCasais);
        listaCasais.setAdapter(casalAdapter);

        casalViewModel = ViewModelProviders.of(this).get(CasalViewModel.class);

        casalViewModel.getListaCasais().observe(this, new Observer<List<Casal>>() {
            @Override
            public void onChanged(@Nullable List<Casal> casais) {
                casalAdapter.setCasais(casais);
                casalAdapter.notifyDataSetChanged();
            }
        });

        Button fab = (Button) findViewById(R.id.botaoCasal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ListaCasaisActivity.this, ManterCasalActivity.class),
                        CADASTRAR_CASAL);
            }
        });

        registerForContextMenu(listaCasais);
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
                editaCasal((Casal) casalAdapter.getItem(info.position));
                return true;
            case R.id.remover:
                removeCasal((Casal) casalAdapter.getItem(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Recebe o resultado da Activity de cadastro de casais
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == this.RESULT_OK && requestCode == CADASTRAR_CASAL){
            Casal casal = (Casal) data.getExtras().get(Casal.CASAL_INFO);
            casalViewModel.inserir(casal);
        }else if(resultCode == this.RESULT_OK && requestCode == EDITAR_CASAL){
            Casal casal = (Casal) data.getExtras().get(Casal.CASAL_INFO);
            casalViewModel.atualizar(casal);
        }
    }

    /**
     * Edita o casal
     */
    private void editaCasal(Casal casal){

        Intent editarCasal = new Intent(this, ManterCasalActivity.class);

        editarCasal.putExtra(Casal.CASAL_EDIT, casal);

        startActivityForResult(editarCasal, EDITAR_CASAL);
    }

    /**
     * Remove o casal
     */
    private void removeCasal(final Casal casal){

        casalSelecionado = casal;

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
                casalViewModel.remover(casalSelecionado);
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

    /**
     * Volta para a tela anterior
     * @param view
     */
    public void cancelar(View view){
        finish();
    }
}
