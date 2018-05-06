package br.com.marcos.eitacasei.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Presente;
import br.com.marcos.eitacasei.dominio.SerialBitmap;

/**
 * Created by Marcos on 06/05/18.
 */

public class ManterPresenteActivity extends Activity {

    /**
     * Constante que identifica a subActivity de tirar foto
     */
    private static final int TIRAR_FOTO = 1;

    /**
     * Lista de Presentes
     */
    private ArrayList<Presente> presentes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manter_presente);

        Intent telaAnterior = getIntent();

        presentes = (ArrayList<Presente>) telaAnterior.getExtras().get(Presente.PRESENTE_INFO);

        if(presentes == null){
            presentes = new ArrayList<Presente>();
        }
    }

    /**
     * Modifica a foto de perfil
     * @param view
     */
    public void mudaFoto(View view){
        Intent tirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(tirarFoto, TIRAR_FOTO);
    }

    /**
     * Recebe o resultado de subActivities
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == this.RESULT_OK && requestCode == TIRAR_FOTO){
            ImageView foto = findViewById(R.id.fotoProduto);

            foto.setImageBitmap((Bitmap) data.getExtras().get("data"));
        }
    }

    /**
     * Insere o presente
     * @param view
     */
    public void inserirPresente(View view){
        EditText nomeProduto = findViewById(R.id.nomeProduto);
        ImageView fotoProduto = findViewById(R.id.fotoProduto);
        Presente presente = new Presente();
        presente.setProduto(nomeProduto.getText().toString());
        presente.setFoto(new SerialBitmap(((BitmapDrawable)fotoProduto.getDrawable()).getBitmap()));

        Intent telaListaPresentes = new Intent(this, ListaPresentesActivity.class);

        presentes.add(presente);

        telaListaPresentes.putExtra(Presente.PRESENTE_INFO, presentes);

        startActivity(telaListaPresentes);
    }
}
