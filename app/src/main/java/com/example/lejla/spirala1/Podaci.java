package com.example.lejla.spirala1;

import java.util.ArrayList;

/**
 * Created by Lejla on 12.04.2017..
 */

public class Podaci {
    ArrayList<Glumac> glumci;
    ArrayList<Reziser> reziseri;
    ArrayList<Zanr> zanrovi;

    public ArrayList<Glumac> getGlumci() {
        return glumci;
    }

    public ArrayList<Reziser> getReziseri() {
        return reziseri;
    }

    public ArrayList<Zanr> getZanrovi() {
        return zanrovi;
    }

    public Podaci(){
        glumci=new ArrayList<Glumac>();
        Glumac gm1= new Glumac("Johnny","Depp","1963","/","Owensboro, Kentucky, USA","7.4","glumac1","m","http://www.imdb.com/name/nm0000136","He was born John Christopher Depp II in Owensboro, Kentucky, on June 9, 1963, to Betty Sue (Wells), who worked as a waitress, and John Christopher Depp, a civil engineer.");
        Glumac gm2= new Glumac("Paul","Walker","1973","2013","Glendale, California, USA","6.8","glumac2","m","http://www.imdb.com/name/nm0908094","Paul William Walker IV was born in Glendale, California. He grew up together with his brothers, Caleb and Cody, and sisters, Ashlie and Amie. Their parents, Paul William Walker III, a sewer contractor, and Cheryl (Crabtree) Walker, a model, separated around September 2004. ");
        Glumac gm3= new Glumac("Hugh","Laurie","1959","/","Oxford, Oxfordshire, England, UK","8.2","glumac3","m","http://www.imdb.com/name/nm0491402","Hugh was born in Oxford, England on June 11, 1959, to Patricia (Laidlaw) and William George Ranald Mundell \"Ran\" Laurie, a doctor, both of Scottish descent. He was educated at Eton and Cambridge. Son of an Olympic gold medalist in the sport, he rowed for the England youth team (1977) and for Cambridge (1980).");

        Glumac gf1= new Glumac("Sofia","Vergara","1972","/","Barranquilla, Colombia","8.5","glumica1","f","http://www.imdb.com/name/nm0005527","Sofía Margarita Vergara Vergara was born and raised in Barranquilla, Colombia. Her mother, Margarita Vergara Dávila de Vergara, is a housewife. Her father, Julio Enrique Vergara Robayo, provides cattle to the meat industry. ");
        Glumac gf2= new Glumac("Sandra","Oh","1971","/","Nepean, Ontario, Canada","7.6","glumica2","f","http://www.imdb.com/name/nm0644897","Sandra Oh was born to Korean parents in the Ottawa suburb of Nepean, Ontario, Canada. Her father, Oh Junsu, a businessman, and her mother, Oh Young-nam, a biochemist, were married in Seoul, South Korea. They both attended graduate school at the University of Toronto.");
        Glumac gf3= new Glumac("Kaley","Cuoco","1985","/","Camarillo, California, USA","6.7","glumica3","f","http://www.imdb.com/name/nm0192505","Kaley Christine Cuoco was born in Camarillo, California, to Layne Ann (Wingate) and Gary Carmine Cuoco, a realtor. She is of Italian (father) and German and English (mother) descent.Cuoco was home-schooled, and lives in Ventura County, California with her family. ");

        glumci.add(gm1);
        glumci.add(gf1);
        glumci.add(gm2);
        glumci.add(gf2);
        glumci.add(gm3);
        glumci.add(gf3);

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
    }

}
