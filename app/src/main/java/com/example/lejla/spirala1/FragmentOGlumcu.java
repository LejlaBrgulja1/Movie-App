package com.example.lejla.spirala1;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.ActionBar;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Lejla on 12.04.2017..
 */

public class FragmentOGlumcu extends Fragment implements  MojResultReceiver.Receiver {
    Glumac glumac;
    ArrayList<Glumac> glumci;
    static ArrayList<Zanr> zanrovi = new ArrayList<Zanr>();
    static ArrayList<Reziser> reziseri = new ArrayList<Reziser>();
    Button ib;
    Boolean a;
    Button bookmark;
    MojResultReceiver mReceiver;
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
            bookmark = (Button) iv.findViewById(R.id.imageButtonBookmark);

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

            if(glumac.getBookmarked()==true) bookmark.setText(R.string.brisiIzBookmarks);
            //else  bookmark.setText(R.string.dodajUBookmarks);
            //else  bookmark.setText(R.string.dodajUBookmarks);



            ib.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    shareIt();
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    DBOpenHelper mdbo=new DBOpenHelper(getActivity(), DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.DATABASE_VERSION);
                    if(glumac.getBookmarked()){
                        Log.d("taggg",glumac.getID());
                        mdbo.deleteGlumac(glumac);
                        glumac.setBookmarked(false);
                        bookmark.setText(R.string.dodajUBookmarks);
                    }else {
                        glumac.setBookmarked(true);
                        if (mdbo.postojiLiGlumac(glumac.getID()) == false) {
                            bookmark.setText(R.string.brisiIzBookmarks);
                            mdbo.createGLUMAC(glumac);


                            Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), ServisZanr.class);
                            mReceiver = new MojResultReceiver(new Handler());
                            mReceiver.setReceiver(FragmentOGlumcu.this);
                            String s = "";
                            try {
                                s = URLEncoder.encode(glumac.getID(), "utf-8");
                            } catch (UnsupportedEncodingException e) {
                            }
                            intent.putExtra("url", "http://api.themoviedb.org/3/discover/movie?api_key=75ec4e2cf73234b17d61036c619d8f62&with_cast=" + s + "&sort_by=primary_release_date.desc");
                            intent.putExtra("receiver", mReceiver);
                            getActivity().startService(intent);

                            Intent intent1 = new Intent(Intent.ACTION_SYNC, null, getActivity(), ServisReziser.class);
                            try {
                                s = URLEncoder.encode(glumac.getID(), "utf-8");
                            } catch (UnsupportedEncodingException e) {
                            }
                            intent1.putExtra("url", "http://api.themoviedb.org/3/discover/movie?api_key=75ec4e2cf73234b17d61036c619d8f62&with_cast=" + s + "&sort_by=primary_release_date.desc");
                            intent1.putExtra("receiver", mReceiver);
                            getActivity().startService(intent1);
                        }
                    }
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

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData){
        String id=glumac.getID();
        switch(resultCode){
            case 1:
                break;
            case 0:
                ArrayList<Zanr> results = resultData.getParcelableArrayList("result");
                zanrovi=results;
                try {
                    DBOpenHelper mdbo=new DBOpenHelper(getActivity(), DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.DATABASE_VERSION);
                    for (Zanr z: zanrovi
                         ) {
                        if(mdbo.postojiLiZanr(z.getID())==false){
                            mdbo.createZANR(z);
                        }
                        mdbo.createEVIDENCIJA_ZANRA(z.getID(),id);
                    }

                }catch(Exception e){}
                break;
            case 2:
                String error = resultData.getString(Intent.EXTRA_TEXT);
                break;
            case 3:
                ArrayList<Reziser> resultss = resultData.getParcelableArrayList("result");
                reziseri=resultss;
                try {
                    DBOpenHelper mdbo=new DBOpenHelper(getActivity(), DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.DATABASE_VERSION);
                    for (Reziser r: reziseri
                            ) {
                        if(mdbo.postojiLiReziser(r.getID())==false){
                            mdbo.createREZISER(r);
                        }
                        mdbo.createEVIDENCIJA_REZISERA(r.getID(),id);
                    }

                }catch(Exception e){}
                break;
        }
    }
}
