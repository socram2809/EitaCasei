package br.com.marcos.eitacasei.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Convidado;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 06/05/18.
 */

public class ConvidadoAdapter extends BaseAdapter {

    private ArrayList<Convidado> convidados;

    private Context context;

    public ConvidadoAdapter(Context context, ArrayList<Convidado> convidados) {
        this.context = context;
        this.convidados = convidados;
    }

    @Override
    public int getCount() {
        return convidados.size();
    }

    @Override
    public Object getItem(int i) {
        return convidados.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = LayoutInflater.from(context)
                .inflate(R.layout.convidado_layout, parent, false);

        TextView textConvidado = rowView.findViewById(R.id.convidado);

        Convidado convidado = (Convidado) getItem(position);

        textConvidado.setText(convidado.getNome());

        return rowView;
    }

    public void setConvidados(List<Convidado> convidados){
        this.convidados.clear();
        this.convidados.addAll(convidados);
    }
}
