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

public class FragmentGlumciLista extends Fragment {
    OnItemClick oic;
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_glumci, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Glumac> glumci = new ArrayList<Glumac>();


        if (getArguments().containsKey("Glista")) {
            glumci = getArguments().getParcelableArrayList("Glista");

            lv=(ListView) getView().findViewById(R.id.lista_glumaca);
            GlumacAdapter aa = new GlumacAdapter(getActivity(), glumci);
            lv.setAdapter(aa);
        }
        try{
            oic=(OnItemClick)getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + "Treba implementirati on itemclick");
        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oic.onItemClicked(position);
            }});
    }

    public interface OnItemClick{
        public void onItemClicked(int pos);
    }
}
