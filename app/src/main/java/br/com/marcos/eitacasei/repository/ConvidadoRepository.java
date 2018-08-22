package br.com.marcos.eitacasei.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import br.com.marcos.eitacasei.bd.ConvidadoBD;
import br.com.marcos.eitacasei.bd.PresenteBD;
import br.com.marcos.eitacasei.dao.ConvidadoDao;
import br.com.marcos.eitacasei.dao.PresenteDao;
import br.com.marcos.eitacasei.dominio.Convidado;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 21/08/18.
 */
public class ConvidadoRepository {

    private LiveData<List<Convidado>> listaConvidados;

    private ConvidadoDao convidadoDao;

    public ConvidadoRepository(Application application){
        convidadoDao = ConvidadoBD.getINSTANCE(application).convidadoDao();
    }

    public LiveData<List<Convidado>> buscarTodos(){
        if(listaConvidados == null){
            listaConvidados = convidadoDao.buscarTodosConvidados();
        }

        return listaConvidados;
    }

    public void inserir(Convidado convidado){
        new InsertASync(convidadoDao).execute(convidado);
    }

    public void atualizar(Convidado convidado) {
        new UpdateASync(convidadoDao).execute(convidado);
    }

    public void remover(Convidado convidado){
        new DeleteASync(convidadoDao).execute(convidado);
    }

    private class UpdateASync extends AsyncTask<Convidado, Void, Void>{
        private ConvidadoDao convidadoDao;

        public UpdateASync(ConvidadoDao convidadoDao) {
            this.convidadoDao = convidadoDao;
        }

        @Override
        protected Void doInBackground(Convidado... convidados) {
            convidadoDao.atualizarConvidado(convidados[0]);
            return null;
        }
    }

    private class InsertASync extends AsyncTask<Convidado, Void, Void>{
        private ConvidadoDao convidadoDao;

        public InsertASync(ConvidadoDao convidadoDao){
            this.convidadoDao = convidadoDao;
        }

        @Override
        protected Void doInBackground(Convidado... convidados){
            convidadoDao.inserirConvidado(convidados[0]);
            return null;
        }
    }

    private class DeleteASync extends AsyncTask<Convidado, Void, Void>{
        private ConvidadoDao convidadoDao;

        public DeleteASync(ConvidadoDao convidadoDao){
            this.convidadoDao = convidadoDao;
        }

        @Override
        protected Void doInBackground(Convidado... convidados) {
            convidadoDao.removerConvidado(convidados[0]);
            return null;
        }
    }

}


