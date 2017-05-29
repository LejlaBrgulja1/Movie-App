package com.example.lejla.spirala1;


import android.support.v7.app.ActionBar;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Lejla on 12.04.2017..
 */

public class FragmentOGlumcu extends Fragment {
    Glumac glumac;
    ArrayList<Glumac> glumci;
    Button ib;
    Boolean a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View iv=inflater.inflate(R.layout.fragment_o_glumcu, container, false);
        if(getArguments()!=null && getArguments().containsKey("glumac")){
            glumac=getArguments().getParcelable("glumac");


            TextView imeiprezime = (TextView)iv.findViewById(R.id.textViewImePrezime);
            imeiprezime.setText(glumac.getIme()+" "+glumac.getPrezime());

            TextView godine = (TextView)iv.findViewById(R.id.textViewGodina);
            if(glumac.getGodinaSmrti().equals("/")) godine.setText(glumac.getGodinaRodjenja());
            else godine.setText(glumac.getGodinaRodjenja()+"-"+glumac.getGodinaSmrti());

            TextView mjesto = (TextView)iv.findViewById(R.id.textViewMjesto);
            mjesto.setText(glumac.getMjestoRodjenja());

            TextView spol = (TextView)iv.findViewById(R.id.textViewSpol);
            spol.setText(getString(R.string.spol) + glumac.getSpol());

            ScrollView l = (ScrollView) iv.findViewById(R.id.pozadina) ;
            if(glumac.getSpol().equals("f")) l.setBackgroundDrawable(getResources().getDrawable(R.drawable.pozadina_glumice));
            else l.setBackgroundDrawable(getResources().getDrawable(R.drawable.pozadina_glumci));

            TextView imd = (TextView)iv.findViewById(R.id.textViewImdb);
            imd.setText(glumac.getImbdLink());
            imd.setLinksClickable(true);

            TextView biografija = (TextView)iv.findViewById(R.id.textViewBiografija);
            biografija.setText(glumac.getBiografija());
            biografija.setMovementMethod(new ScrollingMovementMethod());

//            String s = glumac.getSlika();
            ImageView slika = (ImageView)iv.findViewById(R.id.imageView);
           // slika.setImageResource(this.getResources().getIdentifier("com.example.lejla.spirala1:drawable/" + s, null, null));
            Picasso.with(iv.getContext()).load(glumac.getSlika()).into((ImageView)iv.findViewById(R.id.imageView));


            ib = (Button)iv.findViewById(R.id.buttonShare);

            a=getArguments().containsKey("layout")&&getArguments().getString("layout").equals("siroki");
            if(a){
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300);
                slika.setLayoutParams(layoutParams);
                ib.setLayoutParams(new LinearLayout.LayoutParams(300, 80));
            }else {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(360, 360);
                slika.setLayoutParams(layoutParams);
                ib.setLayoutParams(new LinearLayout.LayoutParams(360, 80));
            }




            ib.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    shareIt();
                }
            });
        }
        return iv;
    }

    private void shareIt() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String tekst = glumac.getBiografija();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, tekst);
        //if(sharingIntent.resolveActivity(getPackageManager()) != null){
        startActivity(Intent.createChooser(sharingIntent,getString(R.string.tekst_dijeljenja) ));
        //}
    }


}
