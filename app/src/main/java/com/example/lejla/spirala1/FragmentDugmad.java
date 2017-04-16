package com.example.lejla.spirala1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Lejla on 12.04.2017..
 */

public class FragmentDugmad extends Fragment {

    Podaci p=new Podaci();
    ArrayList<Glumac> glumci;
    ArrayList<Reziser> reziseri;
    ArrayList<Zanr> zanrovi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View iv=inflater.inflate(R.layout.fragment_dugmadi, container, false);



        Button dugmeGlumci=(Button)iv.findViewById(R.id.button1);
        Button dugmeReziseri=(Button)iv.findViewById(R.id.button2);
        Button dugmeZanrovi=(Button)iv.findViewById(R.id.button3);
        glumci=p.getGlumci();
        reziseri = p.getReziseri();
        zanrovi = p.getZanrovi();



        dugmeGlumci.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fm=getFragmentManager();
                FragmentGlumciLista fgl = new FragmentGlumciLista();
                Bundle argumenti=new Bundle();
                argumenti.putParcelableArrayList("Glista",glumci);
                fgl.setArguments(argumenti);
                fm.beginTransaction().replace(R.id.MjestoIspod,fgl).addToBackStack(null).commit();
            }
        });

        dugmeReziseri.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fm=getFragmentManager();
                FragmentResizerLista frl = new FragmentResizerLista();
                Bundle argumenti=new Bundle();
                argumenti.putParcelableArrayList("Rlista",reziseri);
                frl.setArguments(argumenti);
                fm.beginTransaction().replace(R.id.MjestoIspod,frl).addToBackStack(null).commit();


            }
        });

        dugmeZanrovi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fm=getFragmentManager();
                FragmentZanroviLista fzl = new FragmentZanroviLista();
                Bundle argumenti=new Bundle();
                argumenti.putParcelableArrayList("Zlista",zanrovi);
                fzl.setArguments(argumenti);
                fm.beginTransaction().replace(R.id.MjestoIspod,fzl).addToBackStack(null).commit();


            }
        });


        return iv;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

}
