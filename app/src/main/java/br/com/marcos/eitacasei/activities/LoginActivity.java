package br.com.marcos.eitacasei.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Casal;

/**
 * Created by Marcos on 05/05/18.
 */

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Recupera o login, caso já tenha sido feito no aplicativo
        SharedPreferences preferencias = getSharedPreferences("PREF_LOGIN", MODE_PRIVATE);
        EditText login = findViewById(R.id.login);
        login.setText(preferencias.getString("login", ""));
    }

    /**
     * Realiza o login
     * @param view
     */
    public void logar(View view){
        EditText login = findViewById(R.id.login);

        SharedPreferences preferencias = getSharedPreferences("PREF_LOGIN", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();

        editor.putString("login", login.getText().toString());

        editor.commit();

        /*Intent telaPrincipal = new Intent(this, TwitterActivity.class);

        telaPrincipal.putExtra(Tweet.TWEET_INFO, login.getText().toString());

        startActivity(telaPrincipal);*/
    }

    /**
     * Redireciona para a página de cadastro de usuário
     * @param view
     */
    public void cadastrarUsuario(View view){

        Intent telaCadastroUsuario = new Intent(this, ManterCasalActivity.class);

        startActivity(telaCadastroUsuario);

    }
}
