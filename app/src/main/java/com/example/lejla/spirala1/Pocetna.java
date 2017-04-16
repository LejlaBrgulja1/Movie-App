package com.example.lejla.spirala1;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class Pocetna extends Activity implements FragmentGlumciLista.OnItemClick {


    ArrayList<Glumac> glumci;
    ArrayList<Reziser> reziseri;
    ArrayList<Zanr> zanrovi;
    Podaci p=new Podaci();
    Boolean siriL=false;
    Boolean bosanski=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);


        glumci=p.getGlumci();
        reziseri = p.getReziseri();
        zanrovi = p.getZanrovi();
        FragmentManager fm=getFragmentManager();
        FrameLayout sirokiDugmici = (FrameLayout)findViewById(R.id.dugmiciSirokiFrameLayout);

        if(sirokiDugmici!=null) {
            siriL=true;
            for(int i = 0; i < fm.getBackStackEntryCount(); i++){
                fm.popBackStack();
            }
            FragmentDugmad500dp fd = (FragmentDugmad500dp) fm.findFragmentById(R.id.dugmiciSirokiFrameLayout);
            if (fd == null) {
                fd = new FragmentDugmad500dp();
                fm.beginTransaction().replace(R.id.dugmiciSirokiFrameLayout, fd).commit();
            }

            FragmentGlumciLista fgl;
            fgl=(FragmentGlumciLista)fm.findFragmentById(R.id.lijeviSirokiFrameLayout);
            if(fgl==null) {
                fgl=new FragmentGlumciLista();
                Bundle argumenti = new Bundle();
                argumenti.putParcelableArrayList("Glista", glumci);
                fgl.setArguments(argumenti);
                fm.beginTransaction().replace(R.id.lijeviSirokiFrameLayout,fgl).commit();
            }else {
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            FragmentOGlumcu fog;
            fog=(FragmentOGlumcu) fm.findFragmentById(R.id.desniSirokiFrameLayout);
            if(fog==null) {
                Bundle arguments=new Bundle();
                arguments.putParcelable("glumac",glumci.get(0));
                arguments.putString("layout","siroki");
                fog = new FragmentOGlumcu();
                fog.setArguments(arguments);
                fm.beginTransaction().replace(R.id.desniSirokiFrameLayout,fog).commit();

            }else {
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }else {
            for(int i = 0; i < fm.getBackStackEntryCount(); i++){
                fm.popBackStack();
            }
            siriL=false;
            FragmentDugmad fd = (FragmentDugmad) fm.findFragmentById(R.id.MjestoIznad);
            if (fd == null) {
                fd = new FragmentDugmad();
                fm.beginTransaction().replace(R.id.MjestoIznad, fd).commit();
            }
            FragmentGlumciLista FGL = (FragmentGlumciLista) fm.findFragmentById(R.id.MjestoIspod);
            if (FGL == null) {
                FGL = new FragmentGlumciLista();
                Bundle argumentii = new Bundle();
                argumentii.putParcelableArrayList("Glista", glumci);
                FGL.setArguments(argumentii);
                fm.beginTransaction().replace(R.id.MjestoIspod, FGL).commit();
            } else {
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }

    }

    @Override
    public void onItemClicked(int pos){

        Bundle arguments=new Bundle();
        arguments.putParcelable("glumac",glumci.get(pos));
        FragmentOGlumcu fd = new FragmentOGlumcu();



        if(siriL) {
            arguments.putString("layout","siroki");
            fd.setArguments(arguments);
            getFragmentManager().beginTransaction().replace(R.id.desniSirokiFrameLayout, fd).addToBackStack(null).commit();
        }else{
            fd.setArguments(arguments);
            getFragmentManager().beginTransaction().replace(R.id.MjestoIspod, fd).addToBackStack(null).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(siriL) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}


/*
final GlumacAdapter adapter = new GlumacAdapter(this,glumci);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){

                Intent intent = new Intent(Pocetna.this, oGlumcu.class);
                intent.putExtra("imePrezime",glumci.get(i).getIme() + " " + glumci.get(i).getPrezime());
                intent.putExtra("godinaRodjenja",glumci.get(i).getGodinaRodjenja());
                intent.putExtra("godinaSmrti",glumci.get(i).getGodinaSmrti());
                intent.putExtra("mjestoRodjenja",glumci.get(i).getMjestoRodjenja());
                intent.putExtra("spol",glumci.get(i).getSpol());
                intent.putExtra("imdb",glumci.get(i).getImbdLink());
                intent.putExtra("slika",glumci.get(i).getSlika());
                intent.putExtra("biografija",glumci.get(i).getBiografija());
                startActivity(intent);
            }
        });

        dugmeReziseri.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Pocetna.this, PocetnaReziseri.class);
                startActivity(intent);
            }
        });

        dugmeZanrovi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Pocetna.this, PocetnaZanrovi.class);
                startActivity(intent);
            }
        });
        */
