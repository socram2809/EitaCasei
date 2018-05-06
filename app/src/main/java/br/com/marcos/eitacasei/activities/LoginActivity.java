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
import br.com.marcos.eitacasei.dominio.Convidado;
import br.com.marcos.eitacasei.dominio.Usuario;

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
        EditText senha = findViewById(R.id.senha);

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {

            String FILENAME = "casais.txt";

            File folder;

            //Memória Interna
            folder = getFilesDir();

            BufferedReader br = null;
            FileReader fr = null;

            try {

                File arquivo = new File(folder, FILENAME);

                fr = new FileReader(arquivo);
                br = new BufferedReader(fr);

                String sCurrentLine;

                List<Map<String, String>> usuarios = new ArrayList<Map<String, String>>();

                boolean usuarioVerificado = false;

                Map<String, String> usuarioLogado = new LinkedHashMap<String, String>();

                while ((sCurrentLine = br.readLine()) != null) {
                    Map<String, String> usuario = new LinkedHashMap<String, String>();
                    for (String keyValue : sCurrentLine.split(", ")) {
                        String[] parts = keyValue.split("=", 2);
                        usuario.put(parts[0], parts[1]);
                    }
                    usuarios.add(usuario);
                }

                //Verifica a autenticidade do usuário
                for (Map<String, String> usr : usuarios) {
                    if (login.getText().toString().equals(usr.get("login"))
                            && senha.getText().toString().equals(usr.get("senha"))) {
                        usuarioVerificado = true;
                        usuarioLogado = usr;
                        break;
                    }
                }

                if (usuarioVerificado) {

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

            } catch(FileNotFoundException e){

                Toast toast = Toast.makeText(this, "Nenhum usuário cadastrado",
                        Toast.LENGTH_LONG);
                toast.show();

                Log.e("eitacasei", "Arquivo não encontrado", e);

            } catch(IOException e) {

                Log.e("eitacasei", "Erro ao abrir arquivo", e);

            } finally {

                try {

                    if (br != null)
                        br.close();

                    if (fr != null)
                        fr.close();

                } catch (IOException ex) {

                    Log.e("eitacasei", "Erro ao fechar conexão com o arquivo", ex);

                }

            }
        }
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
