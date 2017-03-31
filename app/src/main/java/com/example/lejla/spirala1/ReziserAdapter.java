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

public class ReziserAdapter extends ArrayAdapter<Reziser> {

    public ReziserAdapter(@NonNull Context context, ArrayList<Reziser> m){
        super(context,R.layout.element_liste_reziser, m);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View posebni_red = li.inflate(R.layout.element_liste_reziser, parent ,false);

        Reziser reziser = getItem(position);

        TextView tvIme_Prezime = (TextView)posebni_red.findViewById(R.id.textViewImePrezime);

        tvIme_Prezime.setText(reziser.getIme()+" " + reziser.getPrezime());

        return posebni_red;
    }


}
