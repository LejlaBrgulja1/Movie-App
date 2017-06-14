package com.example.lejla.spirala1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.lejla.spirala1.FragmentGlumciLista.glumci;

/**
 * Created by Lejla on 11.06.2017..
 */

public class FragmentFilmoviLista extends Fragment implements  MojResultReceiver.Receiver  {
    MojResultReceiver mReceiver;
    ListView lv;
    OnFilmClick oic;
    static ArrayList<Film> filmovi = new ArrayList<Film>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View iv= inflater.inflate(R.layout.fragment_glumci, container, false);
        Button dugmeSearch = (Button)iv.findViewById(R.id.SearchDugme);
        dugmeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), ServisFilm.class);
                mReceiver = new MojResultReceiver(new Handler());
                mReceiver.setReceiver(FragmentFilmoviLista.this);
                EditText e = (EditText) iv.findViewById(R.id.SearchEditText);
                String s = null;
                try {
                    s = e.getText().toString();
                    String p = URLEncoder.encode(s, "utf-8");
                    intent.putExtra("url", "http://api.themoviedb.org/3/search/movie?api_key=75ec4e2cf73234b17d61036c619d8f62&query=" + p);
                    intent.putExtra("receiver", mReceiver);
                    getActivity().startService(intent);
                } catch (UnsupportedEncodingException e1) {

                }
            }
        });
        return iv;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        lv = (ListView) getView().findViewById(R.id.lista_glumaca);
        FilmAdapter aa = new FilmAdapter(getActivity(), filmovi);
        lv.setAdapter(aa);
        try {
            oic = (FragmentFilmoviLista.OnFilmClick) getActivity();
        } catch (ClassCastException ee) {
            throw new ClassCastException(getActivity().toString() + "Treba implementirati on itemclick");
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oic.onFilmClicked(filmovi.get(position));
            }
        });

    }

    public interface OnFilmClick{
        public void onFilmClicked(Film m);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData){
        switch(resultCode){
            case 1:
                break;
            case 0:
                ArrayList<Film> results = resultData.getParcelableArrayList("result");
                filmovi=results;
                try {
                    lv = (ListView) getView().findViewById(R.id.lista_glumaca);
                    FilmAdapter aa = new FilmAdapter(getActivity(), results);
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
