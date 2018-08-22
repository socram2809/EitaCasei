package br.com.marcos.eitacasei.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import br.com.marcos.eitacasei.bd.PresenteBD;
import br.com.marcos.eitacasei.dao.PresenteDao;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 19/08/18.
 */
public class PresenteRepository {

    private LiveData<List<Presente>> listaPresentes;

    private PresenteDao presenteDao;

    public PresenteRepository(Application application){
        presenteDao = PresenteBD.getINSTANCE(application).presenteDao();
    }

    public LiveData<List<Presente>> buscarTodos(){
        if(listaPresentes == null){
            listaPresentes = presenteDao.buscarTodosPresentes();
        }

        return listaPresentes;
    }

    public void inserir(Presente presente){
        new InsertASync(presenteDao).execute(presente);
    }

    public void atualizar(Presente presente) {
        new UpdateASync(presenteDao).execute(presente);
    }

    public void remover(Presente presente){
        new DeleteASync(presenteDao).execute(presente);
    }

    private class UpdateASync extends AsyncTask<Presente, Void, Void>{
        private PresenteDao presenteDao;

        public UpdateASync(PresenteDao presenteDao) { this.presenteDao = presenteDao;}

        @Override
        protected Void doInBackground(Presente... presentes) {
            presenteDao.atualizarPresente(presentes[0]);
            return null;
        }
    }

    private class InsertASync extends AsyncTask<Presente, Void, Void>{
        private PresenteDao presenteDao;

        public InsertASync(PresenteDao presenteDao){
            this.presenteDao = presenteDao;
        }

        @Override
        protected Void doInBackground(Presente... presentes){
            presenteDao.inserirPresente(presentes[0]);
            return null;
        }
    }

    private class DeleteASync extends AsyncTask<Presente, Void, Void>{
        private PresenteDao presenteDao;

        public DeleteASync(PresenteDao presenteDao){
            this.presenteDao = presenteDao;
        }

        @Override
        protected Void doInBackground(Presente... presentes) {
            presenteDao.removerPresente(presentes[0]);
            return null;
        }
    }

}


