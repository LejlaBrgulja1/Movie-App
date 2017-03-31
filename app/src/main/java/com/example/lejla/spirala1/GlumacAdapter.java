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
 * Created by Lejla on 21.03.2017..
 **/

public class GlumacAdapter extends ArrayAdapter<Glumac> {


    public GlumacAdapter( @NonNull Context context,  ArrayList<Glumac> m){
        super(context,R.layout.element_liste_glumac, m);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View posebni_red = li.inflate(R.layout.element_liste_glumac, parent ,false);

        Glumac glumac = getItem(position);

        TextView tvIme_Prezime = (TextView)posebni_red.findViewById(R.id.textViewIme);
        TextView tvGodina = (TextView)posebni_red.findViewById(R.id.textViewGodina);
        TextView tvMjesto = (TextView)posebni_red.findViewById(R.id.textViewMjesto);
        TextView tvRating = (TextView)posebni_red.findViewById(R.id.textViewRating);
        ImageView slika = (ImageView) posebni_red.findViewById(R.id.imageView);

        tvIme_Prezime.setText(glumac.getIme()+" " + glumac.getPrezime());
        tvGodina.setText(glumac.getGodinaRodjenja());
        tvMjesto.setText(glumac.getMjestoRodjenja());
        tvRating.setText(glumac.getRating());


        slika.setImageResource(getContext().getResources().getIdentifier("com.example.lejla.spirala1:drawable/" + glumac.getSlika(), null, null));



        return posebni_red;
    }

}
