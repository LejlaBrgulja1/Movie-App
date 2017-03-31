package com.example.lejla.spirala1;

/**
 * Created by Lejla on 21.03.2017..
 */

public class Reziser {
    private String ime;
    private String prezime;

    public Reziser(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }
}
