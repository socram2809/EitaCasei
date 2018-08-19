package br.com.marcos.eitacasei.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.marcos.eitacasei.dao.PresenteDao;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 19/08/18.
 */
@Database(entities = {Presente.class}, version = 1)
public abstract class PresenteBD extends RoomDatabase{

    private static PresenteBD INSTANCE;

    public static PresenteBD getINSTANCE(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                    PresenteBD.class, "presente_bd").build();
        }

        return INSTANCE;
    }

    public abstract PresenteDao presenteDao();
}
