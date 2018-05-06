package br.com.marcos.eitacasei.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Convidado;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 06/05/18.
 */

public class ConvidadoAdapter extends ArrayAdapter<Convidado> {

    private final Context context;
    ArrayList<Convidado> convidados = new ArrayList<Convidado>();

    public ConvidadoAdapter(Context context, ArrayList<Convidado> convidados) {
        super(context, R.layout.convidado_layout, convidados);
        this.context = context;
        this.convidados = convidados;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = inflater.inflate(R.layout.convidado_layout, parent, false);

        TextView textConvidado = rowView.findViewById(R.id.convidado);

        Convidado convidado = convidados.get(position);

        textConvidado.setText(convidado.getNome());

        return rowView;
    }
}
