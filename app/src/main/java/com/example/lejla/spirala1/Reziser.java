package com.example.lejla.spirala1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lejla on 21.03.2017..
 */

public class Reziser  implements Parcelable {
    private String ime;
    private String prezime;

    public Reziser(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    protected Reziser(Parcel in){
        this.ime = in.readString();
        this.prezime = in.readString();

    }

    public static final Creator<Reziser> CREATOR =  new Creator<Reziser>() {
        @Override
        public Reziser createFromParcel(Parcel in) {
            return new Reziser(in);
        }

        @Override
        public Reziser[] newArray(int size) {
            return new Reziser[size];
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
