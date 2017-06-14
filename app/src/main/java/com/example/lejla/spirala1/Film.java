package com.example.lejla.spirala1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lejla on 11.06.2017..
 */

public class Film implements Parcelable {

    private String naziv;
    private String detalji;

    public String getDetalji() {
        return detalji;
    }

    public void setDetalji(String detalji) {
        this.detalji = detalji;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public Film(){}

    public Film(String n, String d){
        naziv=n;
        detalji=d;
    }

    public Film(Parcel p){
        this.naziv=p.readString();
        this.detalji=p.readString();
    }
    public static final Creator<Film> CREATOR =  new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(naziv);
        dest.writeString(detalji);
    }

}
