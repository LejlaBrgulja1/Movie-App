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

public class PocetnaReziseri extends AppCompatActivity {
    Button dugmeGlumci;
    Button dugmeReziseri;
    Button dugmeZanrovi;
    ListView lista;
    ArrayList<Reziser> reziseri;
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
        reziseri = new ArrayList<Reziser>();
        Reziser r1 = new Reziser("Steven", "Spielberg");
        Reziser r2 = new Reziser("Quentin", "Tarantino");
        Reziser r3 = new Reziser("Woody", "Allen");
        Reziser r4 = new Reziser("Alfred", "Hitchcock");
        Reziser r5 = new Reziser("Stanley", "Kubrick");
        Reziser r6 = new Reziser("Chuck", "Lorre");

        reziseri.add(r1);
        reziseri.add(r2);
        reziseri.add(r3);
        reziseri.add(r4);
        reziseri.add(r5);
        reziseri.add(r6);

        final ReziserAdapter adapter = new ReziserAdapter(this, reziseri);
        lista.setAdapter(adapter);


        dugmeGlumci.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PocetnaReziseri.this, Pocetna.class);
                startActivity(intent);
            }
        });

        dugmeZanrovi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PocetnaReziseri.this, PocetnaZanrovi.class);
                startActivity(intent);
            }
        });
    }

}
