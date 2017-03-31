package com.example.lejla.spirala1;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Pocetna extends AppCompatActivity {

    Button dugmeGlumci;
    Button dugmeReziseri;
    Button dugmeZanrovi;
    ListView lista;
    ArrayList<Glumac> glumci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        dugmeGlumci = (Button)findViewById(R.id.button1);
        dugmeReziseri = (Button)findViewById(R.id.button2);
        dugmeZanrovi = (Button)findViewById(R.id.button3);

        lista = (ListView)findViewById(R.id.lista_elemenata);
        glumci=new ArrayList<Glumac>();
        Glumac gm1= new Glumac("Johnny","Depp","1963","/","Owensboro, Kentucky, USA","7.4","glumac1","m","http://www.imdb.com/name/nm0000136","He was born John Christopher Depp II in Owensboro, Kentucky, on June 9, 1963, to Betty Sue (Wells), who worked as a waitress, and John Christopher Depp, a civil engineer.");
        Glumac gm2= new Glumac("Paul","Walker","1973","2013","Glendale, California, USA","6.8","glumac2","m","http://www.imdb.com/name/nm0908094","Paul William Walker IV was born in Glendale, California. He grew up together with his brothers, Caleb and Cody, and sisters, Ashlie and Amie. Their parents, Paul William Walker III, a sewer contractor, and Cheryl (Crabtree) Walker, a model, separated around September 2004. ");
        Glumac gm3= new Glumac("Hugh","Laurie","1959","/","Oxford, Oxfordshire, England, UK","8.2","glumac3","m","http://www.imdb.com/name/nm0491402","Hugh was born in Oxford, England on June 11, 1959, to Patricia (Laidlaw) and William George Ranald Mundell \"Ran\" Laurie, a doctor, both of Scottish descent. He was educated at Eton and Cambridge. Son of an Olympic gold medalist in the sport, he rowed for the England youth team (1977) and for Cambridge (1980).Hugh was born in Oxford, England on June 11, 1959, to Patricia (Laidlaw) and William George Ranald Mundell \"Ran\" Laurie, a doctor, both of Scottish descent. He was educated at Eton and Cambridge. Son of an Olympic gold medalist in the sport, he rowed for the England youth team (1977) and for Cambridge (1980).Hugh was born in Oxford, England on June 11, 1959, to Patricia (Laidlaw) and William George Ranald Mundell \"Ran\" Laurie, a doctor, both of Scottish descent. He was educated at Eton and Cambridge. Son of an Olympic gold medalist in the sport, he rowed for the England youth team (1977) and for Cambridge (1980).Hugh was born in Oxford, England on June 11, 1959, to Patricia (Laidlaw) and William George Ranald Mundell \"Ran\" Laurie, a doctor, both of Scottish descent. He was educated at Eton and Cambridge. Son of an Olympic gold medalist in the sport, he rowed for the England youth team (1977) and for Cambridge (1980).");

        Glumac gf1= new Glumac("Sofia","Vergara","1972","/","Barranquilla, Colombia","8.5","glumica1","f","http://www.imdb.com/name/nm0005527","Sofía Margarita Vergara Vergara was born and raised in Barranquilla, Colombia. Her mother, Margarita Vergara Dávila de Vergara, is a housewife. Her father, Julio Enrique Vergara Robayo, provides cattle to the meat industry. ");
        Glumac gf2= new Glumac("Sandra","Oh","1971","/","Nepean, Ontario, Canada","7.6","glumica2","f","http://www.imdb.com/name/nm0644897","Sandra Oh was born to Korean parents in the Ottawa suburb of Nepean, Ontario, Canada. Her father, Oh Junsu, a businessman, and her mother, Oh Young-nam, a biochemist, were married in Seoul, South Korea. They both attended graduate school at the University of Toronto.");
        Glumac gf3= new Glumac("Kaley","Cuoco","1985","/","Camarillo, California, USA","6.7","glumica3","f","http://www.imdb.com/name/nm0192505","Kaley Christine Cuoco was born in Camarillo, California, to Layne Ann (Wingate) and Gary Carmine Cuoco, a realtor. She is of Italian (father) and German and English (mother) descent.Cuoco was home-schooled, and lives in Ventura County, California with her family. ");

        glumci.add(gm1);
        glumci.add(gf1);
        glumci.add(gm2);
        glumci.add(gf2);
        glumci.add(gm3);
        glumci.add(gf3);

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

    }
}
