package br.com.marcos.eitacasei.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.marcos.eitacasei.dominio.Casal;
import br.com.marcos.eitacasei.dominio.Convidado;

/**
 * Created by Marcos on 22/08/18.
 */
@Dao
public interface CasalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inserirCasal(Casal casal);

    @Update
    public void atualizarCasal(Casal casal);

    @Delete
    public void removerCasal(Casal casal);

    @Query("SELECT * FROM Casal ORDER BY id ASC")
    public LiveData<List<Casal>> buscarTodosCasais();
}
