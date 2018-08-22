package br.com.marcos.eitacasei.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.marcos.eitacasei.dominio.Presente;
import br.com.marcos.eitacasei.repository.PresenteRepository;

/**
 * Created by Marcos on 20/08/18.
 */
public class PresenteViewModel extends AndroidViewModel{

    private PresenteRepository presenteRepository;

    private LiveData<List<Presente>> listaPresentes;

    public PresenteViewModel(@NonNull Application application){
        super(application);
        presenteRepository = new PresenteRepository(application);
        listaPresentes = presenteRepository.buscarTodos();
    }

    public void inserir(Presente presente){
        presenteRepository.inserir(presente);
    }

    public void atualizar(Presente presente){
        presenteRepository.atualizar(presente);
    }

    public void remover(Presente presente){
        presenteRepository.remover(presente);
    }

    public LiveData<List<Presente>> getListaPresentes() {
        return listaPresentes;
    }
}
