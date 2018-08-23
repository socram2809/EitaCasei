package br.com.marcos.eitacasei.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.marcos.eitacasei.dominio.Casal;
import br.com.marcos.eitacasei.dominio.Convidado;
import br.com.marcos.eitacasei.repository.CasalRepository;
import br.com.marcos.eitacasei.repository.ConvidadoRepository;

/**
 * Created by Marcos on 22/08/18.
 */
public class CasalViewModel extends AndroidViewModel{

    private CasalRepository casalRepository;

    private LiveData<List<Casal>> listaCasais;

    public CasalViewModel(@NonNull Application application){
        super(application);
        casalRepository = new CasalRepository(application);
        listaCasais = casalRepository.buscarTodos();
    }

    public void inserir(Casal casal){
        casalRepository.inserir(casal);
    }

    public void atualizar(Casal casal){
        casalRepository.atualizar(casal);
    }

    public void remover(Casal casal){
        casalRepository.remover(casal);
    }

    public LiveData<List<Casal>> getListaCasais() {
        return listaCasais;
    }
}
