package br.com.marcos.eitacasei.services;

import java.util.List;

import br.com.marcos.eitacasei.dominio.Presente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Marcos on 09/06/18.
 */
public interface PresenteService {

    /**
     * Lista os presentes do casal
     * @return
     */
    @GET("/presente")
    @Headers({
         "Accept: application/json"
    })
    Call<List<Presente>> listarPresentes();

    /**
     * Cadastra o presente do casal
     * @return
     */
    @POST("/presente")
    @Headers({
            "Accept: application/json"
    })
    Call<Presente> inserirPresente(@Body Presente presente);

    /**
     * Consulta um presente específico
     * @return
     */
    @GET("/presente/{id}")
    @Headers({
            "Accept: application/json"
    })
    Call<Presente> consultaPresente(@Path("id")Long idPresente);

    /**
     * Deleta um presente específico
     * @return
     */
    @DELETE("/presente/{id}")
    @Headers({
            "Accept: application/json"
    })
    Call<Presente> deletaPresente(@Path("id")Long idPresente);
}
