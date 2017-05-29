package com.example.lejla.spirala1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Lejla on 12.04.2017..
 */

public class FragmentZanroviLista extends Fragment implements  MojResultReceiver.Receiver  {
    ListView lv;
    static ArrayList<Zanr> zanrovi = new ArrayList<Zanr>();
    MojResultReceiver mReceiver;
    static String stari;
    static boolean running=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_lista, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv=(ListView)getView().findViewById(R.id.lista_obicna);
        //http://api.themoviedb.org/3/discover/movie?api_key=75ec4e2cf73234b17d61036c619d8f62&with_cast=62&sort_by=primary_release_date.desc
        //62 je id glumca
        lv.setAdapter(null);
        if(FragmentGlumciLista.ID_GLUMCA==""){}
        else if(stari!=FragmentGlumciLista.ID_GLUMCA || stari==null) {
            if(stari==null)stari=FragmentGlumciLista.ID_GLUMCA;
            stari=FragmentGlumciLista.ID_GLUMCA;
            if (FragmentGlumciLista.ID_GLUMCA != null) {
                running=true;
                Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), ServisZanr.class);
                mReceiver = new MojResultReceiver(new Handler());
                mReceiver.setReceiver(FragmentZanroviLista.this);
                String s = "";
                try {
                    s = URLEncoder.encode(FragmentGlumciLista.ID_GLUMCA, "utf-8");
                } catch (UnsupportedEncodingException e) {
                }
                intent.putExtra("url", "http://api.themoviedb.org/3/discover/movie?api_key=75ec4e2cf73234b17d61036c619d8f62&with_cast=" + s + "&sort_by=primary_release_date.desc");
                intent.putExtra("receiver", mReceiver);
                getActivity().startService(intent);
            }
        }else if(stari==FragmentGlumciLista.ID_GLUMCA){
            ZanrAdapter aa = new ZanrAdapter(getActivity(), zanrovi);
            lv.setAdapter(aa);
            lv.deferNotifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!running) {
            ZanrAdapter aa = new ZanrAdapter(getActivity(), zanrovi);
            lv.setAdapter(aa);
            lv.deferNotifyDataSetChanged();
        }
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData){
        running=false;
        switch(resultCode){
            case 1:
                break;
            case 0:
                ArrayList<Zanr> results = resultData.getParcelableArrayList("result");
                zanrovi=results;
                try {
                    lv = (ListView) getView().findViewById(R.id.lista_obicna);
                    ZanrAdapter aa = new ZanrAdapter(getActivity(), results);
                    lv.setAdapter(aa);
                    lv.deferNotifyDataSetChanged();
                }catch(Exception e){}
                break;
            case 2:
                String error = resultData.getString(Intent.EXTRA_TEXT);
                break;
        }
    }

}
