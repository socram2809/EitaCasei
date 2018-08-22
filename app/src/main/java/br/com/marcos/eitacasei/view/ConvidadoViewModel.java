package br.com.marcos.eitacasei.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.marcos.eitacasei.dominio.Convidado;
import br.com.marcos.eitacasei.dominio.Presente;
import br.com.marcos.eitacasei.repository.ConvidadoRepository;
import br.com.marcos.eitacasei.repository.PresenteRepository;

/**
 * Created by Marcos on 21/08/18.
 */
public class ConvidadoViewModel extends AndroidViewModel{

    private ConvidadoRepository convidadoRepository;

    private LiveData<List<Convidado>> listaConvidados;

    public ConvidadoViewModel(@NonNull Application application){
        super(application);
        convidadoRepository = new ConvidadoRepository(application);
        listaConvidados = convidadoRepository.buscarTodos();
    }

    public void inserir(Convidado convidado){
        convidadoRepository.inserir(convidado);
    }

    public void atualizar(Convidado convidado){
        convidadoRepository.atualizar(convidado);
    }

    public void remover(Convidado convidado){
        convidadoRepository.remover(convidado);
    }

    public LiveData<List<Convidado>> getListaConvidados() {
        return listaConvidados;
    }
}
