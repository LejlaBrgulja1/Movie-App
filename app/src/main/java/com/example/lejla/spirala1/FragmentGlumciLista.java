package com.example.lejla.spirala1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lejla on 12.04.2017..
 */

public class FragmentGlumciLista extends Fragment implements  MojResultReceiver.Receiver {
    OnItemClick oic;
    StaviPrvogGlumca spg;
    ListView lv;
    Podaci p;
    static String ID_GLUMCA="";
    static boolean running=false;
    public static boolean CITANJE_IZ_BAZE=false;

    public static String getIdGlumca() {
        return ID_GLUMCA;
    }

    static ArrayList<Glumac> glumci=new ArrayList<Glumac>();

    public static ArrayList<Glumac> getGlumci() {
        return glumci;
    }

    MojResultReceiver mReceiver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View iv= inflater.inflate(R.layout.fragment_glumci, container, false);

        Button dugmeSearch = (Button)iv.findViewById(R.id.SearchDugme);

        dugmeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = ((EditText)iv.findViewById(R.id.SearchEditText)).getText().toString();

                if(search.length()>= 7 && search.substring(0,6).equals("actor:")){
                    String imeGlumca=search.substring(6);

                    DBOpenHelper mdbo=new DBOpenHelper(getActivity(), DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.DATABASE_VERSION);
                    ArrayList<Glumac> rezultat = mdbo.getAllGlumci();
                    ArrayList<Glumac> prikazi = new ArrayList<Glumac>();
                    for (Glumac g : rezultat
                         ) {
                        g.setBookmarked(true);
                        if(g.getIme().contains(imeGlumca)) prikazi.add(g);
                    }
                    CITANJE_IZ_BAZE=true;
                    lv = (ListView) getView().findViewById(R.id.lista_glumaca);
                    glumci=prikazi;
                    GlumacAdapter aa = new GlumacAdapter(getActivity(), prikazi);
                    lv.setAdapter(aa);
                    lv.deferNotifyDataSetChanged();
                    spg = (Pocetna) getActivity();
                    spg.staviPrvogGlumca();
                }else if(search.length()>= 10 && search.substring(0,9).equals("director:")){
                    String imeRezisera=search.substring(9);

                    DBOpenHelper mdbo=new DBOpenHelper(getActivity(), DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.DATABASE_VERSION);
                    ArrayList<Reziser> reziseri=mdbo.getAllReziseri();
                    ArrayList<Glumac> prikazi = new ArrayList<Glumac>();
                    for (Reziser r : reziseri
                         ) {
                        if(r.getIme().contains(imeRezisera)){
                            ArrayList<Glumac> rezultat = mdbo.getAllGlumci(r.getID());
                            for (Glumac g : rezultat
                                    ) {
                                g.setBookmarked(true);
                                boolean postoji=false;
                                for (Glumac gg : prikazi
                                     ) {
                                    if(g.getID().equals(gg.getID())) postoji=true;
                                }
                                if(postoji==false)prikazi.add(g);
                            }
                        }
                    }
                    CITANJE_IZ_BAZE=true;
                    lv = (ListView) getView().findViewById(R.id.lista_glumaca);
                    glumci=prikazi;
                    GlumacAdapter aa = new GlumacAdapter(getActivity(), prikazi);
                    lv.setAdapter(aa);
                    lv.deferNotifyDataSetChanged();
                    spg = (Pocetna) getActivity();
                    spg.staviPrvogGlumca();
                }else{
                    CITANJE_IZ_BAZE=false;
                    running = true;
                    Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), Servis.class);
                    mReceiver = new MojResultReceiver(new Handler());
                    mReceiver.setReceiver(FragmentGlumciLista.this);
                    EditText e = (EditText) iv.findViewById(R.id.SearchEditText);
                    String s = null;
                    try {
                        s = e.getText().toString();
                        String p = URLEncoder.encode(s, "utf-8");
                        intent.putExtra("url", "http://api.themoviedb.org/3/search/person?api_key=75ec4e2cf73234b17d61036c619d8f62&query=" + p);
                        intent.putExtra("receiver", mReceiver);
                        getActivity().startService(intent);
                    } catch (UnsupportedEncodingException e1) {

                    }
                }

            }
        });

        return  iv;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        lv = (ListView) getView().findViewById(R.id.lista_glumaca);
        GlumacAdapter aa = new GlumacAdapter(getActivity(), glumci);
        lv.setAdapter(aa);
            try {
                oic = (OnItemClick) getActivity();
            } catch (ClassCastException ee) {
                throw new ClassCastException(getActivity().toString() + "Treba implementirati on itemclick");
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ID_GLUMCA=glumci.get(position).getID();
                    oic.onItemClicked(glumci.get(position));
                }
            });

    }

    @Override
    public void onResume() {
        super.onResume();
        if(glumci!=null && !running) {
            GlumacAdapter aa = new GlumacAdapter(getActivity(), glumci);
            lv.setAdapter(aa);
            lv.deferNotifyDataSetChanged();
            FragmentManager fm = getFragmentManager();
            FrameLayout test = (FrameLayout)getActivity().findViewById(R.id.desniSirokiFrameLayout);
            if(test!=null){
         //       FragmentOGlumcu fog = (FragmentOGlumcu) fm.findFragmentById(R.id.desniSirokiFrameLayout);
                Bundle arguments = new Bundle();
                if(FragmentGlumciLista.getGlumci().size()!=0)arguments.putParcelable("glumac", FragmentGlumciLista.getGlumci().get(0));
                arguments.putString("layout", "siroki");
                FragmentOGlumcu fog = new FragmentOGlumcu();
                fog.setArguments(arguments);
                fm.beginTransaction().replace(R.id.desniSirokiFrameLayout, fog).commit();
            }
        }
    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData){
        running=false;
        switch(resultCode){
            case 1:
                break;
            case 0:
                ArrayList<Glumac> results = resultData.getParcelableArrayList("result");
                glumci=results;
                try {
                    lv = (ListView) getView().findViewById(R.id.lista_glumaca);
                    GlumacAdapter aa = new GlumacAdapter(getActivity(), results);
                    lv.setAdapter(aa);
                    lv.deferNotifyDataSetChanged();
                    spg = (Pocetna) getActivity();
                    spg.staviPrvogGlumca();
                }catch (Exception e){}
                break;
            case 2:
                String error = resultData.getString(Intent.EXTRA_TEXT);
                //      Toast.makeText(this,error,Toast.LENGTH_LONG).show();
                break;
        }
    }

    public interface StaviPrvogGlumca{
        public void staviPrvogGlumca();
    }


    public interface OnItemClick{
        public void onItemClicked(Glumac m);
    }
}
