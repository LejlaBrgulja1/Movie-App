package com.example.lejla.spirala1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lejla on 22.03.2017..
 **/

public class ZanrAdapter  extends ArrayAdapter<Zanr> {

    public ZanrAdapter(@NonNull Context context, ArrayList<Zanr> m){
        super(context,R.layout.element_liste_zanr, m);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View posebni_red = li.inflate(R.layout.element_liste_zanr, parent ,false);

        Zanr zanr = getItem(position);

        TextView tvNaziv = (TextView)posebni_red.findViewById(R.id.textViewNaziv);
        ImageView slika = (ImageView) posebni_red.findViewById(R.id.imageView);

        tvNaziv.setText(zanr.getNaziv());
        slika.setImageResource(getContext().getResources().getIdentifier("com.example.lejla.spirala1:drawable/" + zanr.getSlika(), null, null));

        return posebni_red;
    }

}
