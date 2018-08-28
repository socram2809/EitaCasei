package br.com.marcos.eitacasei.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Casal;
import br.com.marcos.eitacasei.dominio.Presente;
import br.com.marcos.eitacasei.view.CasalViewModel;
import br.com.marcos.eitacasei.view.PresenteViewModel;

/**
 * Created by Marcos on 05/05/18.
 */

public class LoginActivity extends AppCompatActivity {

    /**
     * Gerencia os dados relativos a UI em relação aos casais
     */
    private CasalViewModel casalViewModel;

    /**
     * Lista de casais
     */
    private List<Casal> casais;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if(savedInstanceState == null) {
            casais = new ArrayList<>();
        }

        casalViewModel = ViewModelProviders.of(this).get(CasalViewModel.class);

        casalViewModel.getListaCasais().observe(this, new Observer<List<Casal>>() {
            @Override
            public void onChanged(@Nullable List<Casal> casaisParaLogin) {
                casais.clear();
                casais.addAll(casaisParaLogin);
            }
        });

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
        EditText senha = findViewById(R.id.senha);

        boolean casalVerificado = false;

        //Verifica a autenticidade do casal
        for (Casal casal : casais) {
            if (login.getText().toString().equals(casal.getLogin())
                    && senha.getText().toString().equals(casal.getSenha())) {
                casalVerificado = true;
                break;
            }
        }

        if (casalVerificado) {

            SharedPreferences preferencias = getSharedPreferences("PREF_LOGIN", MODE_PRIVATE);

            SharedPreferences.Editor editor = preferencias.edit();

            editor.putString("login", login.getText().toString());

            editor.commit();

            Intent telaListaPresentes = new Intent(this, ListaPresentesActivity.class);

            startActivity(telaListaPresentes);

        } else {
            Toast toast = Toast.makeText(this, "Usuário/Senha inválidos",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * Redireciona para a página de cadastro de casais
     * @param view
     */
    public void cadastrarCasal(View view){

        Intent telaListaCasais = new Intent(this, ListaCasaisActivity.class);

        startActivity(telaListaCasais);

    }
}
