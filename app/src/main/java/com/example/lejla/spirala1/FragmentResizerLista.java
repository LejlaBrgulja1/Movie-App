package com.example.lejla.spirala1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Lejla on 12.04.2017..
 */

public class FragmentResizerLista extends Fragment {
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_glumci, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Reziser> reziseri = new ArrayList<Reziser>();

        if (getArguments().containsKey("Rlista")) {
            reziseri = getArguments().getParcelableArrayList("Rlista");

            lv=(ListView) getView().findViewById(R.id.lista_glumaca);
            ReziserAdapter aa = new ReziserAdapter(getActivity(), reziseri);
            lv.setAdapter(aa);
        }

    }
}
