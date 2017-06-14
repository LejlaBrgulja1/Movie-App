package com.example.lejla.spirala1;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.text.TextUtils;

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
import java.util.ArrayList;

/**
 * Created by Lejla on 11.06.2017..
 */

public class ServisFilm extends IntentService {
    public static int STATUS_RUNNING=1;
    public static int STATUS_FINISHED=0;
    public static int STATUS_ERROR=2;

    public ServisFilm(){
        super(null);
    }

    public ServisFilm(String name){
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
                ArrayList<Film> rez;
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

    public ArrayList<Film> uzmiPodatke(String urlPretrage) {

        ArrayList<Reziser> rez;
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
            ArrayList<Film> filmovi = new ArrayList<Film>();

            for(int i=0; i < items.length(); i++){
                JSONObject movie = items.getJSONObject(i);
                String z=movie.getString("title");
                filmovi.add(new Film(z,""));
            }

            return filmovi;
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
