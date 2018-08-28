package br.com.marcos.eitacasei.activities;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 06/05/18.
 */
public class ManterPresenteActivity extends AppCompatActivity {

    /**
     * Constante que identifica a subActivity de tirar foto
     */
    private static final int TIRAR_FOTO = 1;

    /**
     * Presente sendo manipulado
     */
    private Presente presente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manter_presente);

        Intent edicaoPresente = getIntent();

        //Caso seja a alteração de um presente
        if(edicaoPresente != null && edicaoPresente.getExtras() != null
                && edicaoPresente.getExtras().get(Presente.PRESENTE_EDIT) != null) {

            presente = (Presente) edicaoPresente.getExtras().get(Presente.PRESENTE_EDIT);
            EditText produto = findViewById(R.id.nomeProduto);
            ImageView foto = findViewById(R.id.fotoProduto);
            produto.setText(presente.getProduto());

            byte[] bytes = Base64.decode(presente.getFoto(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            foto.setImageBitmap(bitmap);

        }else{

            presente = new Presente();
            presente.setId(0);

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
     * Mantém o presente
     * @param view
     */
    public void manterPresente(View view){

        EditText nomeProduto = findViewById(R.id.nomeProduto);
        ImageView fotoProduto = findViewById(R.id.fotoProduto);
        presente.setProduto(nomeProduto.getText().toString());
        Bitmap bitmap = ((BitmapDrawable) fotoProduto.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte[] b=baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        presente.setFoto(temp);

        Intent telaListaPresentes = new Intent();

        telaListaPresentes.putExtra(Presente.PRESENTE_INFO, presente);
        setResult(RESULT_OK, telaListaPresentes);

        finish();
    }

    /**
     * Volta para a listagem de presentes
     * @param view
     */
    public void cancelar(View view){
        finish();
    }
}
