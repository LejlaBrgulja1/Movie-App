package com.example.lejla.spirala1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Lejla on 14.04.2017..
 */

public class FragmentDugmad500dp extends Fragment {
    Podaci p=new Podaci();
    ArrayList<Glumac> glumci;
    ArrayList<Reziser> reziseri;
    ArrayList<Zanr> zanrovi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View iv=inflater.inflate(R.layout.fragment_500dp_dugmici, container, false);
        Button dugmeGlumci=(Button)iv.findViewById(R.id.button1SirokiLayout);
        Button dugmeOstalo=(Button)iv.findViewById(R.id.button2SirokiLayout);
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
                fm.beginTransaction().replace(R.id.lijeviSirokiFrameLayout,fgl).addToBackStack(null).commit();

                Bundle arguments=new Bundle();
                arguments.putParcelable("glumac",glumci.get(0));
                arguments.putString("layout","siroki");
                FragmentOGlumcu fd = new FragmentOGlumcu();
                fd.setArguments(arguments);
                getFragmentManager().beginTransaction().replace(R.id.desniSirokiFrameLayout,fd).addToBackStack(null).commit();

            }
        });

        dugmeOstalo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fm=getFragmentManager();
                FragmentResizerLista frl = new FragmentResizerLista();
                Bundle argumenti=new Bundle();
                argumenti.putParcelableArrayList("Rlista",reziseri);
                frl.setArguments(argumenti);
                fm.beginTransaction().replace(R.id.lijeviSirokiFrameLayout,frl).addToBackStack(null).commit();

                FragmentZanroviLista fzl = new FragmentZanroviLista();
                Bundle arguments=new Bundle();
                arguments.putParcelableArrayList("Zlista",zanrovi);
                fzl.setArguments(arguments);
                fm.beginTransaction().replace(R.id.desniSirokiFrameLayout,fzl).addToBackStack(null).commit();


            }
        });


        return iv;
    }
}
