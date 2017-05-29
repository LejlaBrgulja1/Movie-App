package com.example.lejla.spirala1;

import android.app.IntentService;
import android.content.Intent;
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
import java.util.ArrayList;

/**
 * Created by Lejla on 26.05.2017..
 */

public class ServisZanr extends IntentService {
    public static int STATUS_RUNNING=1;
    public static int STATUS_FINISHED=0;
    public static int STATUS_ERROR=2;

    public ServisZanr(){
        super(null);
    }

    public ServisZanr(String name){
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
                ArrayList<Zanr> rez;
                rez = uzmiPodatke(url);
                if (rez.size() > 0 && rez!=null) {
                    bundle.putParcelableArrayList("result",rez);
                    receiver.send(STATUS_FINISHED, bundle);

                }

            } catch (Exception e) {
                bundle.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, bundle);

            }
        }
    }

    public ArrayList<Zanr> uzmiPodatke(String urlPretrage) {

        ArrayList<Zanr> rez;
        try {
            URL url1 = new URL("https://api.themoviedb.org/3/genre/movie/list?api_key=75ec4e2cf73234b17d61036c619d8f62");
            HttpURLConnection urlConnection1 = (HttpURLConnection) url1.openConnection();
            InputStream in1 = new BufferedInputStream(urlConnection1.getInputStream());
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(in1));
            String line1 = "";
            String rezultat1 = "";
            while ((line1 = bufferedReader1.readLine()) != null) {
                rezultat1 += line1;
            }
            ArrayList<Zanr> pomocni=new ArrayList<Zanr>();
            JSONObject jo1 = new JSONObject(rezultat1);
            JSONArray items1 = jo1.getJSONArray("genres");
            for(int i=0; i < items1.length(); i++) {
                JSONObject vrstaZanra=items1.getJSONObject(i);
                int a =vrstaZanra.getInt("id");
                pomocni.add(new Zanr(String.valueOf(a),vrstaZanra.getString("name")));
            }

            //http://api.themoviedb.org/3/discover/movie?api_key=75ec4e2cf73234b17d61036c619d8f62&with_cast=62&sort_by=primary_release_date.desc
            //62 je id glumca
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
            ArrayList<Zanr> zanrovi = new ArrayList<Zanr>();
            int j=0;
            ArrayList<String> nazivi=new ArrayList<String>();
            for(int i=0; i < items.length(); i++){
                JSONObject movie = items.getJSONObject(i);

                JSONArray id_zanrova = movie.getJSONArray("genre_ids");
                String z=new String();

                if(id_zanrova.length()!=0) z = id_zanrova.getString(0);

                for (Zanr p: pomocni
                     ) {
                    if(p.getNaziv().equals(z) && !nazivi.contains(p.getSlika()) && j<7){ nazivi.add(p.getSlika()); zanrovi.add(new Zanr(p.getSlika(),"zanr1")); j++;}
                }
                //Zanr m = new Zanr();

                //   Glumac m = new Glumac(name,"", "1980", "2010", "nema",rating.toString()," biografije","glumica2","https://google.com","biografija");
              //  zanrovi.add(m);
            }
            rez=zanrovi;
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
