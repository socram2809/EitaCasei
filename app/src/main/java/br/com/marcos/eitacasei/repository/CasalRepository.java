package br.com.marcos.eitacasei.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import br.com.marcos.eitacasei.bd.CasalBD;
import br.com.marcos.eitacasei.bd.ConvidadoBD;
import br.com.marcos.eitacasei.dao.CasalDao;
import br.com.marcos.eitacasei.dao.ConvidadoDao;
import br.com.marcos.eitacasei.dominio.Casal;
import br.com.marcos.eitacasei.dominio.Convidado;

/**
 * Created by Marcos on 22/08/18.
 */
public class CasalRepository {

    private LiveData<List<Casal>> listaCasais;

    private CasalDao casalDao;

    public CasalRepository(Application application){
        casalDao = CasalBD.getINSTANCE(application).casalDao();
    }

    public LiveData<List<Casal>> buscarTodos(){
        if(listaCasais == null){
            listaCasais = casalDao.buscarTodosCasais();
        }

        return listaCasais;
    }

    public void inserir(Casal casal){
        new InsertASync(casalDao).execute(casal);
    }

    public void atualizar(Casal casal) {
        new UpdateASync(casalDao).execute(casal);
    }

    public void remover(Casal casal){
        new DeleteASync(casalDao).execute(casal);
    }

    private class UpdateASync extends AsyncTask<Casal, Void, Void>{
        private CasalDao casalDao;

        public UpdateASync(CasalDao casalDao) {
            this.casalDao = casalDao;
        }

        @Override
        protected Void doInBackground(Casal... casais) {
            casalDao.atualizarCasal(casais[0]);
            return null;
        }
    }

    private class InsertASync extends AsyncTask<Casal, Void, Void>{
        private CasalDao casalDao;

        public InsertASync(CasalDao casalDao){
            this.casalDao = casalDao;
        }

        @Override
        protected Void doInBackground(Casal... casais){
            casalDao.inserirCasal(casais[0]);
            return null;
        }
    }

    private class DeleteASync extends AsyncTask<Casal, Void, Void>{
        private CasalDao casalDao;

        public DeleteASync(CasalDao casalDao){
            this.casalDao = casalDao;
        }

        @Override
        protected Void doInBackground(Casal... casais) {
            casalDao.removerCasal(casais[0]);
            return null;
        }
    }

}


