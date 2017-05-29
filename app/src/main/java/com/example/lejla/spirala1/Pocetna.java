package com.example.lejla.spirala1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Pocetna extends Activity implements FragmentGlumciLista.OnItemClick, FragmentGlumciLista.StaviPrvogGlumca {


    public void setGlumci(ArrayList<Glumac> glumci) {
        this.glumci = glumci;
    }

    public void setReziseri(ArrayList<Reziser> reziseri) {
        this.reziseri = reziseri;
    }

    public void setZanrovi(ArrayList<Zanr> zanrovi) {
        this.zanrovi = zanrovi;
    }

    ArrayList<Glumac> glumci;
    ArrayList<Reziser> reziseri;
    ArrayList<Zanr> zanrovi;
    Podaci p = new Podaci();
    Boolean siriL = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);
        glumci = p.getGlumci();
        reziseri = p.getReziseri();
        zanrovi = p.getZanrovi();

        FragmentManager fm = getFragmentManager();
        FrameLayout sirokiDugmici = (FrameLayout) findViewById(R.id.dugmiciSirokiFrameLayout);

        if (sirokiDugmici != null) {
            siriL = true;
            //      Fragment f = getFragmentManager().popBackStack();
            for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
                fm.popBackStack();
            }
            FragmentDugmad500dp fd = (FragmentDugmad500dp) fm.findFragmentById(R.id.dugmiciSirokiFrameLayout);
            if (fd == null) {
                fd = new FragmentDugmad500dp();
                fm.beginTransaction().replace(R.id.dugmiciSirokiFrameLayout, fd).commit();
            }

            FragmentGlumciLista fgl;
            fgl = (FragmentGlumciLista) fm.findFragmentById(R.id.lijeviSirokiFrameLayout);
            if (fgl == null) {
                fgl = new FragmentGlumciLista();
                Bundle argumenti = new Bundle();
                //  argumenti.putParcelableArrayList("Glista", glumci);
                argumenti.putParcelableArrayList("Glista", FragmentGlumciLista.getGlumci());
                fgl.setArguments(argumenti);
                fm.beginTransaction().replace(R.id.lijeviSirokiFrameLayout, fgl).commit();
            } else {
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            FragmentOGlumcu fog;
            fog = (FragmentOGlumcu) fm.findFragmentById(R.id.desniSirokiFrameLayout);
            if (fog == null && FragmentGlumciLista.getGlumci()!=null) {
                Bundle arguments = new Bundle();
                //  arguments.putParcelable("glumac",glumci.get(0));
                if(FragmentGlumciLista.getGlumci().size()!=0)arguments.putParcelable("glumac", FragmentGlumciLista.getGlumci().get(0));
                arguments.putString("layout", "siroki");
                fog = new FragmentOGlumcu();
                fog.setArguments(arguments);
                fm.beginTransaction().replace(R.id.desniSirokiFrameLayout, fog).commit();

            } else {
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        } else {


            for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
                fm.popBackStack();
            }
            siriL = false;


            FragmentDugmad fd = (FragmentDugmad) fm.findFragmentById(R.id.MjestoIznad);
            if (fd == null) {
                fd = new FragmentDugmad();
                fm.beginTransaction().replace(R.id.MjestoIznad, fd).commit();
            }
     /*       FragmentGlumciLista FGL = (FragmentGlumciLista) fm.findFragmentById(R.id.MjestoIspod);
            if (FGL == null) {
                FGL = new FragmentGlumciLista();
                Bundle argumentii = new Bundle();
            //    argumentii.putParcelableArrayList("Glista", glumci);
                argumentii.putParcelableArrayList("Glista",FragmentGlumciLista.glumci);
                FGL.setArguments(argumentii);
                fm.beginTransaction().replace(R.id.MjestoIspod, FGL).commit();
            } else {
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }*/

            FragmentGlumciLista FGL = new FragmentGlumciLista();
            Bundle argumentii = new Bundle();
                //    argumentii.putParcelableArrayList("Glista", glumci);
            argumentii.putParcelableArrayList("Glista",FragmentGlumciLista.glumci);
            FGL.setArguments(argumentii);
            fm.beginTransaction().replace(R.id.MjestoIspod, FGL).commit();


        }

    }

    @Override
    public void onItemClicked(Glumac m) {

        Bundle arguments = new Bundle();
        arguments.putParcelable("glumac", m);
        FragmentOGlumcu fd = new FragmentOGlumcu();

        if (siriL) {
            arguments.putString("layout", "siroki");
            fd.setArguments(arguments);
            getFragmentManager().beginTransaction().replace(R.id.desniSirokiFrameLayout, fd).addToBackStack(null).commit();
        } else {
            fd.setArguments(arguments);
            getFragmentManager().beginTransaction().replace(R.id.MjestoIspod, fd).addToBackStack(null).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (siriL) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void staviPrvogGlumca() {
        if (siriL) {
            FragmentManager fm = getFragmentManager();
            FragmentOGlumcu fog;
            fog = (FragmentOGlumcu) fm.findFragmentById(R.id.desniSirokiFrameLayout);
            Bundle arguments = new Bundle();
            //  arguments.putParcelable("glumac",glumci.get(0));
            if(FragmentGlumciLista.getGlumci().size()!=0)arguments.putParcelable("glumac", FragmentGlumciLista.getGlumci().get(0));
            arguments.putString("layout", "siroki");
            fog = new FragmentOGlumcu();
            fog.setArguments(arguments);
            fm.beginTransaction().replace(R.id.desniSirokiFrameLayout, fog).commit();

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
