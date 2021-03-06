package com.example.lejla.spirala1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ArrayList<Film> filmovi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View iv=inflater.inflate(R.layout.fragment_dugmadi, container, false);



        Button dugmeGlumci=(Button)iv.findViewById(R.id.button1);
        Button dugmeReziseri=(Button)iv.findViewById(R.id.button2);
        Button dugmeZanrovi=(Button)iv.findViewById(R.id.button3);
        Button dugmeFilmovi=(Button)iv.findViewById(R.id.button4);
        glumci=p.getGlumci();
        reziseri = p.getReziseri();
        zanrovi = p.getZanrovi();
        filmovi=new ArrayList<Film>();




        dugmeReziseri.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fm=getFragmentManager();
                FragmentResizerLista frl = new FragmentResizerLista();
                Bundle argumenti=new Bundle();
                argumenti.putParcelableArrayList("Rlista",reziseri);
                frl.setArguments(argumenti);
//                fm.beginTransaction().replace(R.id.MjestoIspod,frl).addToBackStack(null).commit();
                fm.beginTransaction().replace(R.id.MjestoIspod,frl).commit();

           //     fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

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
               // fm.beginTransaction().replace(R.id.MjestoIspod,fzl).addToBackStack(null).commit();
                fm.beginTransaction().replace(R.id.MjestoIspod,fzl).commit();
              //  fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });
        dugmeFilmovi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fm=getFragmentManager();
                FragmentFilmoviLista fzl = new FragmentFilmoviLista();
                Bundle argumenti=new Bundle();
                argumenti.putParcelableArrayList("Flista",filmovi);
                fzl.setArguments(argumenti);
                // fm.beginTransaction().replace(R.id.MjestoIspod,fzl).addToBackStack(null).commit();
                fm.beginTransaction().replace(R.id.MjestoIspod,fzl).commit();
                //  fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });

        dugmeGlumci.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fm=getFragmentManager();
                FragmentGlumciLista fzl = new FragmentGlumciLista();
                Bundle argumenti=new Bundle();
                argumenti.putParcelableArrayList("Glista",glumci);
                fzl.setArguments(argumenti);
                // fm.beginTransaction().replace(R.id.MjestoIspod,fzl).addToBackStack(null).commit();
                fm.beginTransaction().replace(R.id.MjestoIspod,fzl).commit();
                //  fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });
        return iv;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }



}
