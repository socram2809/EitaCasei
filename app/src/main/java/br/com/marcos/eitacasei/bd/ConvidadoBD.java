package br.com.marcos.eitacasei.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.marcos.eitacasei.dao.ConvidadoDao;
import br.com.marcos.eitacasei.dao.PresenteDao;
import br.com.marcos.eitacasei.dominio.Convidado;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 21/08/18.
 */
@Database(entities = {Convidado.class}, version = 1)
public abstract class ConvidadoBD extends RoomDatabase{

    private static ConvidadoBD INSTANCE;

    public static ConvidadoBD getINSTANCE(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                    ConvidadoBD.class, "convidado_bd").build();
        }

        return INSTANCE;
    }

    public abstract ConvidadoDao convidadoDao();
}
