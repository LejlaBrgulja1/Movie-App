package com.example.lejla.spirala1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.LoginFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

/**
 * Created by Lejla on 10.06.2017..
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="SpiralaBaza.db";
    public static final String DATABASE_TABLE_GLUMCI="Glumci";
    public static final int DATABASE_VERSION=1;
    public static final String GLUMAC_ID="_id";
    public static final String GLUMAC_IME="ime";
    public static final String GLUMAC_PREZIME="prezime";
    public static final String GLUMAC_BIOGRAFIJA="biografija";
    public static final String GLUMAC_GODINARODJENJA="godinaRodjenja";
    public static final String GLUMAC_GODINASMRTI="godinaSmrti";
    public static final String GLUMAC_MJESTORODJENJA="mjestoRodjenja";
    public static final String GLUMAC_SLIKA="slika";
    public static final String GLUMAC_SPOL="spol";
    public static final String GLUMAC_IMDBLINK="imdbLink";
    public static final String GLUMAC_RATING="rating";
    private static final String GLUMCI_TABLE_CREATE = "create table " + DATABASE_TABLE_GLUMCI + "(" + GLUMAC_ID + " integer primary key , "+ GLUMAC_IME + " text, " + GLUMAC_PREZIME + " text, "+ GLUMAC_BIOGRAFIJA+ " text, "+ GLUMAC_GODINARODJENJA+ " text, "+ GLUMAC_GODINASMRTI+ " text, "+ GLUMAC_MJESTORODJENJA+ " text, "+ GLUMAC_SLIKA+ " text, "+ GLUMAC_SPOL+ " text, "+ GLUMAC_IMDBLINK+ " text, "+ GLUMAC_RATING +" text );";

    public static final String DATABASE_TABLE_ZANROVI="Zanrovi";
    public static final String ZANR_ID="_id";
    public static final String ZANR_NAZIV="naziv";
    public static final String ZANR_SLIKA="slika";
    private static final String ZANROVI_TABLE_CREATE = "create table " + DATABASE_TABLE_ZANROVI + "(" + ZANR_ID + " integer primary key, "+ ZANR_NAZIV + " text not null, " + ZANR_SLIKA + " text);";

    public static final String DATABASE_TABLE_REZISERI="Reziseri";
    public static final String REZISER_ID="_id";
    public static final String REZISER_IME="ime";
    public static final String REZISER_PREZIME="prezime";
    private static final String REZISERI_TABLE_CREATE = "create table " + DATABASE_TABLE_REZISERI + "(" + REZISER_ID + " integer primary key, "+ REZISER_IME + " text not null, " + REZISER_PREZIME +" text);";

    public static final String DATABASE_TABLE_EVIDENCIJA_ZANROVA="Evidencija_Zanrova";
    public static final String ID_EVIDENCIJE_ZANROVA="_id";
    public static final String ZANR_ID_E="IDzanra";
    public static final String GLUMAC_ID_E="IDglumca";
    private static final String ZANROVI_EVIDENCIJA_TABLE_CREATE = "create table " + DATABASE_TABLE_EVIDENCIJA_ZANROVA + "(" + ID_EVIDENCIJE_ZANROVA + " integer primary key autoincrement, "+ ZANR_ID_E + " text , " + GLUMAC_ID_E +" text);";

    public static final String DATABASE_TABLE_EVIDENCIJA_REZISERA="Evidencija_Rezisera";
    public static final String ID_EVIDENCIJE_REZISERA="_id";
    public static final String REZISER_ID_E="IDrezisera";
    private static final String REZISERI_EVIDENCIJA_TABLE_CREATE = "create table " + DATABASE_TABLE_EVIDENCIJA_REZISERA + "(" + ID_EVIDENCIJE_REZISERA + " integer primary key autoincrement, "+ REZISER_ID_E + " text, " + GLUMAC_ID_E +" text);";


    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(GLUMCI_TABLE_CREATE);
        db.execSQL(ZANROVI_TABLE_CREATE);
        db.execSQL(REZISERI_TABLE_CREATE);
        db.execSQL(ZANROVI_EVIDENCIJA_TABLE_CREATE);
        db.execSQL(REZISERI_EVIDENCIJA_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE " + DATABASE_TABLE_GLUMCI);
        db.execSQL("DROP TABLE " + DATABASE_TABLE_ZANROVI);
        db.execSQL("DROP TABLE " + DATABASE_TABLE_REZISERI);
        db.execSQL("DROP TABLE " + DATABASE_TABLE_EVIDENCIJA_ZANROVA);
        db.execSQL("DROP TABLE " + DATABASE_TABLE_EVIDENCIJA_REZISERA);
        onCreate(db);
    }


    public void obrisiSve(){
        SQLiteDatabase db=this.getWritableDatabase();

        db.execSQL("DROP TABLE " + DATABASE_TABLE_GLUMCI);
        db.execSQL("DROP TABLE " + DATABASE_TABLE_ZANROVI);
        db.execSQL("DROP TABLE " + DATABASE_TABLE_REZISERI);
        db.execSQL("DROP TABLE " + DATABASE_TABLE_EVIDENCIJA_ZANROVA);
        db.execSQL("DROP TABLE " + DATABASE_TABLE_EVIDENCIJA_REZISERA);

        db.execSQL(GLUMCI_TABLE_CREATE);
        db.execSQL(ZANROVI_TABLE_CREATE);
        db.execSQL(REZISERI_TABLE_CREATE);
        db.execSQL(ZANROVI_EVIDENCIJA_TABLE_CREATE);
        db.execSQL(REZISERI_EVIDENCIJA_TABLE_CREATE);
    }

    public void createGLUMAC(Glumac g) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GLUMAC_ID, g.getID());
        values.put(GLUMAC_IME, g.getIme());
        values.put(GLUMAC_PREZIME, g.getPrezime());
        values.put(GLUMAC_BIOGRAFIJA, g.getBiografija());
        values.put(GLUMAC_GODINARODJENJA, g.getGodinaRodjenja());
        values.put(GLUMAC_GODINASMRTI, g.getGodinaSmrti());
        values.put(GLUMAC_MJESTORODJENJA, g.getMjestoRodjenja());
        values.put(GLUMAC_SLIKA, g.getSlika());
        values.put(GLUMAC_SPOL, g.getSpol());
        values.put(GLUMAC_IMDBLINK, g.getImbdLink());
        values.put(GLUMAC_RATING, g.getRating());

        db.insert(DATABASE_TABLE_GLUMCI, null, values);
    }

    public void createZANR(Zanr z) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ZANR_ID, z.getID());
        values.put(ZANR_NAZIV, z.getNaziv());
        values.put(ZANR_SLIKA, z.getSlika());

        db.insert(DATABASE_TABLE_ZANROVI, null, values);
    }

    public void createREZISER(Reziser r) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(REZISER_ID, r.getID());
        values.put(REZISER_IME, r.getIme());
        values.put(REZISER_PREZIME, r.getPrezime());

        db.insert(DATABASE_TABLE_REZISERI, null, values);
    }

    public void createEVIDENCIJA_ZANRA(String idZanra,String idGlumca) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ZANR_ID_E, idZanra);
        values.put(GLUMAC_ID_E, idGlumca);

        db.insert(DATABASE_TABLE_EVIDENCIJA_ZANROVA, null, values);
    }

    public void createEVIDENCIJA_REZISERA(String idRezisera,String idGlumca) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REZISER_ID_E, idRezisera);
        values.put(GLUMAC_ID_E, idGlumca);
        db.insert(DATABASE_TABLE_EVIDENCIJA_REZISERA, null, values);

    }

    public ArrayList<Glumac> getAllGlumci() {
        ArrayList<Glumac> glumci = new ArrayList<Glumac>();
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_GLUMCI;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Glumac g = new Glumac();
                g.setID((c.getString(c.getColumnIndex(GLUMAC_ID))));
                g.setIme((c.getString(c.getColumnIndex(GLUMAC_IME))));
                g.setPrezime((c.getString(c.getColumnIndex(GLUMAC_PREZIME))));
                g.setBiografija((c.getString(c.getColumnIndex(GLUMAC_BIOGRAFIJA))));
                g.setGodinaRodjenja((c.getString(c.getColumnIndex(GLUMAC_GODINARODJENJA))));
                g.setGodinaSmrti((c.getString(c.getColumnIndex(GLUMAC_GODINASMRTI))));
                g.setMjestoRodjenja((c.getString(c.getColumnIndex(GLUMAC_MJESTORODJENJA))));
                g.setSlika((c.getString(c.getColumnIndex(GLUMAC_SLIKA))));
                g.setSpol((c.getString(c.getColumnIndex(GLUMAC_SPOL))));
                g.setImbdLink((c.getString(c.getColumnIndex(GLUMAC_IMDBLINK))));
                g.setRating((c.getString(c.getColumnIndex(GLUMAC_RATING))));

                glumci.add(g);
            } while (c.moveToNext());
        }
        c.close();
        return glumci;
    }


    public ArrayList<Reziser> getAllReziseri() {
        ArrayList<Reziser> reziseri = new ArrayList<Reziser>();
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_REZISERI;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Reziser g = new Reziser();
                g.setID((c.getString(c.getColumnIndex(REZISER_ID))));
                g.setIme((c.getString(c.getColumnIndex(REZISER_IME))));
                g.setPrezime((c.getString(c.getColumnIndex(REZISER_PREZIME))));

                reziseri.add(g);
            } while (c.moveToNext());
            c.close();
        }

        return reziseri;
    }

    public ArrayList<Zanr> getAllZanrovi() {
        ArrayList<Zanr> zanrovi = new ArrayList<Zanr>();
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_ZANROVI;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Zanr g = new Zanr();
                g.setID((c.getString(c.getColumnIndex(ZANR_ID))));
                g.setNaziv((c.getString(c.getColumnIndex(ZANR_NAZIV))));
                g.setSlika((c.getString(c.getColumnIndex(ZANR_SLIKA))));

                zanrovi.add(g);
            } while (c.moveToNext());
            c.close();
        }

        return zanrovi;
    }

    public boolean postojiLiGlumac(String idGlumca){
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_GLUMCI + " WHERE " + GLUMAC_ID +" = " + idGlumca;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.getCount() != 0){c.close(); return true;}
        c.close();
        return false;
    }

    public boolean postojiLiZanr(String idZanra){
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_ZANROVI + " WHERE " + ZANR_ID +" = " + idZanra;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.getCount() != 0){c.close(); return true;}
        c.close();

        return false;
    }

    public boolean postojiLiReziser(String idRezisera){
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_REZISERI + " WHERE " + REZISER_ID +" = " + idRezisera;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() != 0) {c.close(); return true;}
        c.close();
        return false;
    }


    public ArrayList<Reziser> getAllReziseri(String idGlumca) {
        ArrayList<Reziser> reziseri = new ArrayList<Reziser>();
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_REZISERI + " tr, "
                + DATABASE_TABLE_EVIDENCIJA_REZISERA + " ter WHERE tr."
                + REZISER_ID + " = ter." + REZISER_ID_E + " AND ter." + GLUMAC_ID_E
                + " = '"  + idGlumca + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Reziser g = new Reziser();
                g.setID((c.getString(c.getColumnIndex(REZISER_ID))));
                g.setIme((c.getString(c.getColumnIndex(REZISER_IME))));
                g.setPrezime((c.getString(c.getColumnIndex(REZISER_PREZIME))));

                reziseri.add(g);
            } while (c.moveToNext());
        }
        c.close();
        return reziseri;
    }

    public ArrayList<Zanr> getAllZanrovi(String idGlumca) {
        ArrayList<Zanr> zanrovi = new ArrayList<Zanr>();
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_ZANROVI + " tr, "
                + DATABASE_TABLE_EVIDENCIJA_ZANROVA + " ter WHERE tr."
                + ZANR_ID + " = ter." + ZANR_ID_E + " AND ter." + GLUMAC_ID_E
                + " = '"  + idGlumca + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Zanr g = new Zanr();
                g.setID((c.getString(c.getColumnIndex(ZANR_ID))));
                g.setNaziv((c.getString(c.getColumnIndex(ZANR_NAZIV))));
                g.setSlika((c.getString(c.getColumnIndex(ZANR_SLIKA))));

                zanrovi.add(g);
            } while (c.moveToNext());
        }
        c.close();
        return zanrovi;
    }

    public ArrayList<Glumac> getAllGlumci(String idRezisera) {
        ArrayList<Glumac> glumci = new ArrayList<Glumac>();
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE_GLUMCI + " gl, "
                + DATABASE_TABLE_EVIDENCIJA_REZISERA + " ter WHERE gl."
                + GLUMAC_ID + " = ter." + GLUMAC_ID_E + " AND ter." + REZISER_ID_E
                + " = '"  + idRezisera + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Glumac g = new Glumac();
                g.setID((c.getString(c.getColumnIndex(GLUMAC_ID_E))));
                g.setIme((c.getString(c.getColumnIndex(GLUMAC_IME))));
                g.setPrezime((c.getString(c.getColumnIndex(GLUMAC_PREZIME))));
                g.setBiografija((c.getString(c.getColumnIndex(GLUMAC_BIOGRAFIJA))));
                g.setGodinaRodjenja((c.getString(c.getColumnIndex(GLUMAC_GODINARODJENJA))));
                g.setGodinaSmrti((c.getString(c.getColumnIndex(GLUMAC_GODINASMRTI))));
                g.setMjestoRodjenja((c.getString(c.getColumnIndex(GLUMAC_MJESTORODJENJA))));
                g.setSlika((c.getString(c.getColumnIndex(GLUMAC_SLIKA))));
                g.setSpol((c.getString(c.getColumnIndex(GLUMAC_SPOL))));
                g.setImbdLink((c.getString(c.getColumnIndex(GLUMAC_IMDBLINK))));
                g.setRating((c.getString(c.getColumnIndex(GLUMAC_RATING))));
                glumci.add(g);
            } while (c.moveToNext());
            c.close();
        }

        return glumci;
    }

    public void deleteGlumac(Glumac g){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("delete from "+ DATABASE_TABLE_GLUMCI + " WHERE " + GLUMAC_ID + " = "+g.getID());

        String whereClause = " _id=? ";
        String[] whereArgs = new String[] { g.getID() };
        db.delete(DATABASE_TABLE_GLUMCI, whereClause, whereArgs);
        whereClause=GLUMAC_ID_E + "=? ";
        db.delete(DATABASE_TABLE_EVIDENCIJA_REZISERA, whereClause,whereArgs);

        whereClause=GLUMAC_ID_E + "=? ";
        db.delete(DATABASE_TABLE_EVIDENCIJA_ZANROVA, whereClause,whereArgs);

        ArrayList<Zanr> zanrovi=getAllZanrovi();
        for (Zanr z: zanrovi
             ) {
            String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_EVIDENCIJA_ZANROVA + " WHERE " + ZANR_ID_E +" = " + z.getID();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.getCount() == 0){
                whereClause=ZANR_ID + "=? ";
                whereArgs = new String[] { z.getID() };
                db.delete(DATABASE_TABLE_ZANROVI, whereClause, whereArgs);
            }
            c.close();
        }

        ArrayList<Reziser> reziseri = getAllReziseri();
        for (Reziser r: reziseri
                ) {
            String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_EVIDENCIJA_REZISERA + " WHERE " + REZISER_ID_E +" = " + r.getID();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.getCount() == 0){
                whereClause=REZISER_ID + "=? ";
                whereArgs = new String[] { r.getID() };
                db.delete(DATABASE_TABLE_REZISERI, whereClause, whereArgs);
            }
            c.close();
        }

    }


}
