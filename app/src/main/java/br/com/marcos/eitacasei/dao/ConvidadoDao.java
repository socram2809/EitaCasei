package br.com.marcos.eitacasei.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.marcos.eitacasei.dominio.Convidado;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 21/08/18.
 */
@Dao
public interface ConvidadoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inserirConvidado(Convidado convidado);

    @Update
    public void atualizarConvidado(Convidado convidado);

    @Delete
    public void removerConvidado(Convidado convidado);

    @Query("SELECT * FROM Convidado ORDER BY id ASC")
    public LiveData<List<Convidado>> buscarTodosConvidados();
}
