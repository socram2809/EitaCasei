package br.com.marcos.eitacasei.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.marcos.eitacasei.R;
import br.com.marcos.eitacasei.dominio.Presente;

/**
 * Created by Marcos on 06/05/18.
 */
public class PresenteAdapter extends BaseAdapter {

    private List<Presente> presentes;

    private Activity activity;

    public PresenteAdapter(Activity activity) {
        this.activity = activity;
        presentes = new ArrayList<Presente>();
    }

    @Override
    public int getCount() {
        return presentes.size();
    }

    @Override
    public Object getItem(int i) {
        return presentes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = activity.getLayoutInflater()
                .inflate(R.layout.presente_layout, parent, false);

        TextView textProduto = rowView.findViewById(R.id.produto);
        ImageView fotoProduto = rowView.findViewById(R.id.foto);

        Presente presente = (Presente) getItem(position);

        textProduto.setText(presente.getProduto());

        byte[] bytes = Base64.decode(presente.getFoto(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        fotoProduto.setImageBitmap(bitmap);

        return rowView;
    }

    public void setPresentes(List<Presente> presentes){
        presentes.addAll(presentes);
        notifyDataSetChanged();
    }
}
