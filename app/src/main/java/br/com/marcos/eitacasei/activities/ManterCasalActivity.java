package br.com.marcos.eitacasei.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Casal;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 05/05/18.
 */

public class ManterCasalActivity extends AppCompatActivity {

    /**
     * Casal sendo manipulado
     */
    private Casal casal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manter_casal);

        Intent edicaoCasal = getIntent();

        //Caso seja a alteração de um presente
        if(edicaoCasal != null && edicaoCasal.getExtras() != null
                && edicaoCasal.getExtras().get(Casal.CASAL_EDIT) != null) {

            casal = (Casal) edicaoCasal.getExtras().get(Casal.CASAL_EDIT);
            EditText login = findViewById(R.id.login);
            EditText senha = findViewById(R.id.senha);
            EditText noivo = findViewById(R.id.noivo);
            EditText noiva = findViewById(R.id.noiva);
            login.setText(casal.getLogin());
            senha.setText(casal.getSenha());
            noivo.setText(casal.getNoivo());
            noiva.setText(casal.getNoiva());

        }else{

            casal = new Casal();
            casal.setId(0);

        }
    }

    /**
     * Mantém o casal
     * @param view
     */
    public void manterCasal(View view){
        EditText login = findViewById(R.id.login);
        EditText senha = findViewById(R.id.senha);
        EditText noivo = findViewById(R.id.noivo);
        EditText noiva = findViewById(R.id.noiva);

        casal.setLogin(login.getText().toString());
        casal.setSenha(senha.getText().toString());
        casal.setNoivo(noivo.getText().toString());
        casal.setNoiva(noiva.getText().toString());

        SharedPreferences preferencias = getSharedPreferences("PREF_LOGIN", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();

        editor.putString("login", login.getText().toString());

        editor.commit();

        Intent telaListaCasais = new Intent();

        telaListaCasais.putExtra(Casal.CASAL_INFO, casal);
        setResult(RESULT_OK, telaListaCasais);

        finish();
    }

    /**
     * Volta para a listagem de casais
     * @param view
     */
    public void cancelar(View view){
        finish();
    }
}
