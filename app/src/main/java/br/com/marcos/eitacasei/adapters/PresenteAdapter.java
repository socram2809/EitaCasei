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
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 06/05/18.
 */

public class PresenteAdapter extends ArrayAdapter<Presente> {

    private final Context context;
    ArrayList<Presente> presentes = new ArrayList<Presente>();

    public PresenteAdapter(Context context, ArrayList<Presente> presentes) {
        super(context, R.layout.presente_layout, presentes);
        this.context = context;
        this.presentes = presentes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = inflater.inflate(R.layout.presente_layout, parent, false);

        TextView textProduto = rowView.findViewById(R.id.produto);
        ImageView fotoProduto = rowView.findViewById(R.id.foto);

        Presente presente = presentes.get(position);

        textProduto.setText(presente.getProduto());
        fotoProduto.setImageBitmap(presente.getFoto().getBitmap());

        return rowView;
    }
}
