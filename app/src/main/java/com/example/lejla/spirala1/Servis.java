package com.example.lejla.spirala1;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Lejla on 25.05.2017..
 */

public class Servis extends IntentService {
    public static int STATUS_RUNNING=1;
    public static int STATUS_FINISHED=0;
    public static int STATUS_ERROR=2;

    public Servis(){
        super(null);
    }

    public Servis(String name){
        super(name);
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String url = intent.getStringExtra("url");
        Bundle bundle = new Bundle();

        if(!TextUtils.isEmpty(url)) {
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);
            try {
                ArrayList<Glumac> rez;
                rez = uzmiPodatke(url);
                if (rez.size() > 0 && rez!=null) {
                    bundle.putParcelableArrayList("result",rez);
                    receiver.send(STATUS_FINISHED, bundle);

                }

            } catch (Exception e) {
                bundle.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, bundle);
                Log.d("dojava","belajj");
            }
        }
    }

    public ArrayList<Glumac> uzmiPodatke(String urlPretrage) {

        ArrayList<Glumac> rez;
        try {
            URL url = new URL(urlPretrage);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            String rezultat = "";

            while ((line = bufferedReader.readLine()) != null) {
                rezultat += line;
            }
            if (in != null) {
                in.close();
            }

            JSONObject jo = new JSONObject(rezultat);
            JSONArray items = jo.getJSONArray("results");
            ArrayList<Glumac> glumci = new ArrayList<Glumac>();
            for(int i=0; i < items.length(); i++){
                JSONObject artist = items.getJSONObject(i);
                String id= artist.getString("id");
                String name = artist.getString("name");
                Double rating = artist.getDouble("popularity");
                DecimalFormat df = new DecimalFormat("#.##");
                String r=df.format(rating);
                id = URLEncoder.encode(id, "utf-8");
                URL url1 = new URL("https://api.themoviedb.org/3/person/"+id+"?api_key=75ec4e2cf73234b17d61036c619d8f62");
                HttpURLConnection urlConnection1 = (HttpURLConnection) url1.openConnection();
                InputStream in1 = new BufferedInputStream(urlConnection1.getInputStream());
                BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(in1));
                String line1 = "";
                String rezultat1 = "";
                while ((line1 = bufferedReader1.readLine()) != null) {
                    rezultat1 += line1;
                }
                JSONObject jedanGlumac = new JSONObject(rezultat1);
                String datum_rodjenja=jedanGlumac.getString("birthday");
                String datum_smrti=jedanGlumac.getString("deathday");
                String spol=jedanGlumac.getString("gender");
                if(spol=="1") spol="f";
                else spol="m";
                String biografija=jedanGlumac.getString("biography");
                String mjesto_rodjenja=jedanGlumac.getString("place_of_birth");
                String imdb_link="http://www.imdb.com/name/"+jedanGlumac.getString("imdb_id");
                String slika = "https://image.tmdb.org/t/p/w500/"+jedanGlumac.getString("profile_path");
                Glumac m = new Glumac(name,"", datum_rodjenja, datum_smrti,mjesto_rodjenja,r,slika,spol,imdb_link,biografija);
                m.setID(id);

             //   Glumac m = new Glumac(name,"", "1980", "2010", "nema",rating.toString()," biografije","glumica2","https://google.com","biografija");
                glumci.add(m);
            }
            DBOpenHelper mdbo=new DBOpenHelper(getApplicationContext(), DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.DATABASE_VERSION);
            ArrayList<Glumac> prikazi = mdbo.getAllGlumci();

            for (Glumac g: glumci
                 ) {
                for (Glumac p: prikazi
                     ) {
                    if(g.getID().equals(p.getID())) g.setBookmarked(true);
                }
            }
            rez=glumci;
            return rez;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }


        return null;
    }

}

