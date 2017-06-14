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
 * Created by Lejla on 28.05.2017..
 */

public class ServisReziser extends IntentService{
        public static int STATUS_RUNNING=1;
        public static int STATUS_FINISHED=3;
        public static int STATUS_ERROR=2;

        public ServisReziser(){
            super(null);
        }

        public ServisReziser(String name){
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
                    ArrayList<Reziser> rez;
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

        public ArrayList<Reziser> uzmiPodatke(String urlPretrage) {

            ArrayList<Reziser> rez;
            try {

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
                ArrayList<Reziser> reziseri = new ArrayList<Reziser>();
                ArrayList<Reziser> pomocni = new ArrayList<Reziser>();
                ArrayList<String> nazivi=new ArrayList<String>();
                int k=0;
                for(int i=0; i < items.length(); i++){
                    JSONObject movie = items.getJSONObject(i);
                    String z=movie.getString("id");
                    z = URLEncoder.encode(z, "utf-8");
                    URL url1 = new URL("https://api.themoviedb.org/3/movie/"+z+"?api_key=75ec4e2cf73234b17d61036c619d8f62&append_to_response=credits");
                    HttpURLConnection urlConnection1 = (HttpURLConnection) url1.openConnection();
                    InputStream in1 = new BufferedInputStream(urlConnection1.getInputStream());
                    BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(in1));
                    String line1 = "";
                    String rezultat1 = "";
                    while ((line1 = bufferedReader1.readLine()) != null) {
                        rezultat1 += line1;
                    }
                    JSONObject jo1 = new JSONObject(rezultat1);
                    JSONObject items1 = jo1.getJSONObject("credits");
                    JSONArray crew = items1.getJSONArray("crew");

                    for(int j=0;j<crew.length(); j++) {
                        JSONObject radnik = crew.getJSONObject(j);
                        Reziser r =new Reziser(radnik.getString("name"),"");
                        r.setID(( String.valueOf(radnik.getInt("id"))));
                        if(radnik.getString("job").equals("Director"))pomocni.add(r);

                    }


                    for (Reziser p: pomocni
                            ) {
                        if( !nazivi.contains(p.getIme()) && k<7){
                            nazivi.add(p.getIme());
                            Reziser pp = new Reziser(p.getIme(),"");
                            pp.setID(p.getID());
                            reziseri.add(pp);
                            k++;
                        }
                    }
                    //Zanr m = new Zanr();

                    //   Glumac m = new Glumac(name,"", "1980", "2010", "nema",rating.toString()," biografije","glumica2","https://google.com","biografija");
                    //  zanrovi.add(m);
                }


                rez=reziseri;
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
