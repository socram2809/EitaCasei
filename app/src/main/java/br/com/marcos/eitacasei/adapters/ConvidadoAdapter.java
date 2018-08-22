package br.com.marcos.eitacasei.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class ConvidadoAdapter extends ArrayAdapter<Convidado> {

    public ConvidadoAdapter(Context context) {
        super(context, 0, new ArrayList<Convidado>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = LayoutInflater.from(getContext())
                .inflate(R.layout.convidado_layout, parent, false);

        TextView textConvidado = rowView.findViewById(R.id.convidado);

        Convidado convidado = getItem(position);

        textConvidado.setText(convidado.getNome());

        return rowView;
    }

    public void setConvidados(List<Convidado> convidados){
        clear();
        addAll(convidados);
    }
}
