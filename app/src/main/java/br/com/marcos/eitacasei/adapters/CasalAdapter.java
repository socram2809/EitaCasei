package br.com.marcos.eitacasei.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Casal;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 27/08/18.
 */
public class CasalAdapter extends BaseAdapter {

    private ArrayList<Casal> casais;

    private Context context;

    public CasalAdapter(Context context, ArrayList<Casal> casais) {
        this.context = context;
        this.casais = casais;
    }

    @Override
    public int getCount() {
        return casais.size();
    }

    @Override
    public Object getItem(int i) {
        return casais.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = LayoutInflater.from(context)
                .inflate(R.layout.casal_layout, parent, false);

        TextView textNoivo = rowView.findViewById(R.id.noivo);
        TextView textNoiva = rowView.findViewById(R.id.noiva);
        TextView textLogin = rowView.findViewById(R.id.login);

        Casal casal = (Casal) getItem(position);

        textNoivo.setText(casal.getNoivo());
        textNoiva.setText(casal.getNoiva());
        textLogin.setText(casal.getLogin());

        return rowView;
    }

    public void setCasais(List<Casal> casais){
        this.casais.clear();
        this.casais.addAll(casais);
    }
}
