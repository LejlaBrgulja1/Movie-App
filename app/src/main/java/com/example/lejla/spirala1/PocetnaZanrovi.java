package com.example.lejla.spirala1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Lejla on 22.03.2017..
 **/

public class PocetnaZanrovi extends AppCompatActivity {
    Button dugmeGlumci;
    Button dugmeReziseri;
    Button dugmeZanrovi;
    ListView lista;
    ArrayList<Zanr> zanrovi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        dugmeGlumci = (Button) findViewById(R.id.button1);
        dugmeReziseri = (Button) findViewById(R.id.button2);
        dugmeZanrovi = (Button) findViewById(R.id.button3);

        lista = (ListView) findViewById(R.id.lista_elemenata);
        zanrovi = new ArrayList<Zanr>();
        Zanr z1 = new Zanr("Akcija", "zanr1");
        Zanr z2 = new Zanr("Komedija", "zanr2");
        Zanr z3 = new Zanr("Drama", "zanr3");
        Zanr z4 = new Zanr("Horor", "zanr4");
        Zanr z5 = new Zanr("Naucna Fantastika", "zanr5");

        zanrovi.add(z1);
        zanrovi.add(z2);
        zanrovi.add(z3);
        zanrovi.add(z4);
        zanrovi.add(z5);


        final ZanrAdapter adapter = new ZanrAdapter(this, zanrovi);
        lista.setAdapter(adapter);


        dugmeGlumci.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PocetnaZanrovi.this, Pocetna.class);
                startActivity(intent);
            }
        });

        dugmeReziseri.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PocetnaZanrovi.this, PocetnaReziseri.class);
                startActivity(intent);
            }
        });

    }

}
