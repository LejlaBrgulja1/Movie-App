package com.example.lejla.spirala1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lejla on 21.03.2017..
 */

public class Zanr implements Parcelable {
    private String naziv;
    private String slika;
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private String ID;
    public Zanr(){

    }

    public Zanr(String naziv, String slika) {
        this.naziv = naziv;
        this.slika = slika;
    }
    protected Zanr(Parcel in){
        this.naziv = in.readString();
        this.slika = in.readString();

    }

    public static final Creator<Zanr> CREATOR =  new Creator<Zanr>() {
        @Override
        public Zanr createFromParcel(Parcel in) {
            return new Zanr(in);
        }

        @Override
        public Zanr[] newArray(int size) {
            return new Zanr[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(naziv);
        dest.writeString(slika);
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
