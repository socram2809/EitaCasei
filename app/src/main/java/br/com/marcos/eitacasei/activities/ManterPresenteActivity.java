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
import br.com.marcos.eitacasei.dominio.SerialBitmap;
import br.com.marcos.eitacasei.services.PresenteService;
import br.com.marcos.eitacasei.view.PresenteViewModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marcos on 06/05/18.
 */
public class ManterPresenteActivity extends AppCompatActivity {

    /**
     * Presente a ser mantido
     */
    private Presente presente;

    /**
     * Constante que identifica a subActivity de tirar foto
     */
    private static final int TIRAR_FOTO = 1;

    /**
     * Gerencia os dados relativos a UI em relação aos presentes
     */
    private PresenteViewModel presenteViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manter_presente);

        presenteViewModel = ViewModelProviders.of(this).get(PresenteViewModel.class);

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
            presente.setId(new Long(0));

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

        if(presente.getId() == 0){
            presenteViewModel.inserir(presente);
        }else{
            presenteViewModel.atualizar(presente);
        }

        //cadastrarPresente(presente);
    }

    //Mantém o presente pelo Webservice
    private void cadastrarPresente(Presente presente){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // Seta o nível de debug do Retrofit
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        //Adiciona o interceptor de log
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        PresenteService service = retrofit.create(PresenteService.class);

        Call<Presente> callPresente = service.manterPresente(presente);

        callPresente.enqueue(new Callback<Presente>(){

            @Override
            public void onResponse(Call<Presente> call, Response<Presente> response) {
                Presente presente = response.body();

                Toast.makeText(ManterPresenteActivity.this,
                        "Presente "+presente.getProduto()+" cadastrado com sucesso!",
                        Toast.LENGTH_SHORT).show();

                Intent telaListaPresentes = new Intent(ManterPresenteActivity.this, ListaPresentesActivity.class);

                startActivity(telaListaPresentes);
            }

            @Override
            public void onFailure(Call<Presente> call, Throwable t) {
                Log.e(ManterPresenteActivity.this.getClass().getName(), "ERRO", t);
            }
        });
    }

    /**
     * Volta para a listagem de presentes
     * @param view
     */
    public void cancelar(View view){
        finish();
    }
}
