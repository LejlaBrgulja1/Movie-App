package com.example.lejla.spirala1;

/*
 * Created by Lejla on 21.03.2017..
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Glumac implements Parcelable {
    private String ime;
    private String prezime;
    private String biografija;
    private String godinaRodjenja;
    private String godinaSmrti;

    private String mjestoRodjenja;
    private String slika;
    private String spol;
    private String imbdLink;
    private String rating;

    protected Glumac(Parcel in){
        this.ime = in.readString();
        this.prezime = in.readString();
        this.biografija = in.readString();
        this.rating = in.readString();
        this.godinaRodjenja = in.readString();
        this.godinaSmrti = in.readString();
        this.mjestoRodjenja = in.readString();
        this.slika = in.readString();
        this.spol = in.readString();
        this.imbdLink = in.readString();
    }

    public Glumac(String ime, String prezime, String godinaRodjenja, String godinaSmrti, String mjestoRodjenja, String rating, String slika, String spol, String imbdLink, String biografija) {
        this.ime = ime;
        this.prezime = prezime;
        this.biografija = biografija;
        this.rating = rating;
        this.godinaRodjenja = godinaRodjenja;
        this.godinaSmrti = godinaSmrti;
        this.mjestoRodjenja = mjestoRodjenja;
        this.slika = slika;
        this.spol = spol;
        this.imbdLink = imbdLink;
    }

    public static final Creator<Glumac> CREATOR =  new Creator<Glumac>() {
        @Override
        public Glumac createFromParcel(Parcel in) {
            return new Glumac(in);
        }

        @Override
        public Glumac[] newArray(int size) {
            return new Glumac[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(ime);
        dest.writeString(prezime);
        dest.writeString(biografija);
        dest.writeString(rating);
        dest.writeString(godinaRodjenja);
        dest.writeString(godinaSmrti);
        dest.writeString(mjestoRodjenja);
        dest.writeString(slika);
        dest.writeString(spol);
        dest.writeString(imbdLink);


    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public void setGodinaRodjenja(String godinaRodjenja) {
        this.godinaRodjenja = godinaRodjenja;
    }

    public void setGodinaSmrti(String godinaSmrti) {
        this.godinaSmrti = godinaSmrti;
    }

    public void setMjestoRodjenja(String mjestoRodjenja) {
        this.mjestoRodjenja = mjestoRodjenja;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public void setImbdLink(String imbdLink) {
        this.imbdLink = imbdLink;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getBiografija() {
        return biografija;
    }

    public String getGodinaRodjenja() {
        return godinaRodjenja;
    }

    public String getGodinaSmrti() {
        return godinaSmrti;
    }

    public String getMjestoRodjenja() {
        return mjestoRodjenja;
    }

    public String getSlika() {
        return slika;
    }

    public String getSpol() {
        return spol;
    }

    public String getImbdLink() {
        return imbdLink;
    }

    public String getRating() {
        return rating;
    }
}
