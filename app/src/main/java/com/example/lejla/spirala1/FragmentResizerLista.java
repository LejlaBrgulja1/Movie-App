package com.example.lejla.spirala1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Lejla on 12.04.2017..
 */

public class FragmentResizerLista extends Fragment implements  MojResultReceiver.Receiver  {
    ListView lv;
    MojResultReceiver mReceiver;
    static ArrayList<Reziser> reziseri = new ArrayList<Reziser>();
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
        lv.setAdapter(null);
        if(FragmentGlumciLista.CITANJE_IZ_BAZE==true){
            DBOpenHelper mdbo=new DBOpenHelper(getActivity(), DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.DATABASE_VERSION);
            ArrayList<Reziser> rezultat = mdbo.getAllReziseri(FragmentGlumciLista.ID_GLUMCA);
            reziseri=rezultat;
            ReziserAdapter aa = new ReziserAdapter(getActivity(), reziseri);
            lv.setAdapter(aa);
            lv.deferNotifyDataSetChanged();

        }else {
            if (FragmentGlumciLista.ID_GLUMCA == "") {
            } else if (stari != FragmentGlumciLista.ID_GLUMCA || stari == null) {
                running = true;
                if (stari == null) stari = FragmentGlumciLista.ID_GLUMCA;
                stari = FragmentGlumciLista.ID_GLUMCA;
                Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), ServisReziser.class);
                mReceiver = new MojResultReceiver(new Handler());
                mReceiver.setReceiver(FragmentResizerLista.this);
                String s = "";
                try {
                    s = URLEncoder.encode(FragmentGlumciLista.ID_GLUMCA, "utf-8");
                } catch (UnsupportedEncodingException e) {
                }
                intent.putExtra("url", "http://api.themoviedb.org/3/discover/movie?api_key=75ec4e2cf73234b17d61036c619d8f62&with_cast=" + s + "&sort_by=primary_release_date.desc");
                intent.putExtra("receiver", mReceiver);
                getActivity().startService(intent);
            } else if (stari == FragmentGlumciLista.ID_GLUMCA) {
                ReziserAdapter aa = new ReziserAdapter(getActivity(), reziseri);
                lv.setAdapter(aa);
                lv.deferNotifyDataSetChanged();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!running) {
            ReziserAdapter aa = new ReziserAdapter(getActivity(), reziseri);
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
            case 3:
                ArrayList<Reziser> results = resultData.getParcelableArrayList("result");
                reziseri=results;
                try {
                    lv = (ListView) getView().findViewById(R.id.lista_obicna);
                    ReziserAdapter aa = new ReziserAdapter(getActivity(), results);
                    lv.setAdapter(aa);
                    lv.deferNotifyDataSetChanged();
                }catch (Exception e){}
                break;
            case 2:
                String error = resultData.getString(Intent.EXTRA_TEXT);
                break;
        }
    }
}
