package br.com.marcos.eitacasei.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Casal;
import br.com.marcos.eitacasei.dominio.Usuario;

/**
 * Created by Marcos on 05/05/18.
 */

public class ManterCasalActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manter_casal);
    }

    /**
     * Cadastra o casal
     * @param view
     */
    public void cadastrarCasal(View view){
        EditText login = findViewById(R.id.login);
        EditText senha = findViewById(R.id.senha);
        EditText noivo = findViewById(R.id.noivo);
        EditText noiva = findViewById(R.id.noiva);

        Casal casal = new Casal();
        casal.setUsuario(new Usuario());
        casal.getUsuario().setLogin(login.getText().toString());
        casal.getUsuario().setSenha(senha.getText().toString());
        casal.setNoivo(noivo.getText().toString());
        casal.setNoiva(noiva.getText().toString());

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {

            String FILENAME = "casais.txt";

            File folder;

            //Memória Interna
			folder = getFilesDir();

            try {

                File arquivo = new File(folder, FILENAME);

                FileOutputStream fos = new FileOutputStream(arquivo);
                fos.write(casal.toString().getBytes());
                fos.close();

                SharedPreferences preferencias = getSharedPreferences("PREF_LOGIN", MODE_PRIVATE);

                SharedPreferences.Editor editor = preferencias.edit();

                editor.putString("login", login.getText().toString());

                editor.commit();

                Intent telaLogin = new Intent(this, LoginActivity.class);

                startActivity(telaLogin);

            } catch (Exception e) {
                Log.e("casal", "Erro salvando arquivo", e);
            }

        }
    }
}
