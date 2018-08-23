package br.com.marcos.eitacasei.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.marcos.eitacasei.dao.CasalDao;
import br.com.marcos.eitacasei.dao.ConvidadoDao;
import br.com.marcos.eitacasei.dominio.Casal;
import br.com.marcos.eitacasei.dominio.Convidado;

/**
 * Created by Marcos on 22/08/18.
 */
@Database(entities = {Casal.class}, version = 1)
public abstract class CasalBD extends RoomDatabase{

    private static CasalBD INSTANCE;

    public static CasalBD getINSTANCE(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                    CasalBD.class, "casal_bd").build();
        }

        return INSTANCE;
    }

    public abstract CasalDao casalDao();
}
