package com.example.lejla.spirala1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Lejla on 12.04.2017..
 */

public class FragmentZanroviLista extends Fragment {
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_glumci, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Zanr> zanrovi = new ArrayList<Zanr>();

        if (getArguments().containsKey("Zlista")) {
            zanrovi = getArguments().getParcelableArrayList("Zlista");

            lv=(ListView) getView().findViewById(R.id.lista_glumaca);
            ZanrAdapter aa = new ZanrAdapter(getActivity(), zanrovi);
            lv.setAdapter(aa);
        }

    }


}
