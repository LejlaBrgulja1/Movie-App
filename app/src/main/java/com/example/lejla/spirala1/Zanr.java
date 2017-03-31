package com.example.lejla.spirala1;

/**
 * Created by Lejla on 21.03.2017..
 */

public class Zanr {
    private String naziv;
    private String slika;

    public Zanr(String naziv, String slika) {
        this.naziv = naziv;
        this.slika = slika;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getSlika() {
        return slika;
    }
}
