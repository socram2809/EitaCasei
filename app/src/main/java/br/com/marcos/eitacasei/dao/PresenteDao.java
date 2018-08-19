package br.com.marcos.eitacasei.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 19/08/18.
 */
@Dao
public interface PresenteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inserirPresente(Presente presente);

    @Update
    public void atualizarPresente(Presente presente);

    @Delete
    public void removerPresente(Presente presente);

    @Query("SELECT * FROM Presente ORDER BY id ASC")
    public LiveData<List<Presente>> buscarTodosPresentes();
}
