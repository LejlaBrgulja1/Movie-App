package com.example.lejla.spirala1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Lejla on 21.03.2017..
 **/

public class oGlumcu extends AppCompatActivity {

    ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_glumcu);
        String t = getIntent().getStringExtra("spol");

        ActionBar actionBar = getSupportActionBar();
        if(t.equals("m"))actionBar.setTitle("O glumcu: ");
        else actionBar.setTitle("O glumici: ");

        TextView imeiprezime = (TextView)findViewById(R.id.textViewImePrezime);
        imeiprezime.setText(getIntent().getStringExtra("imePrezime"));

        TextView godine = (TextView)findViewById(R.id.textViewGodina);
        if(getIntent().getStringExtra("godinaSmrti").equals("/")) godine.setText(getIntent().getStringExtra("godinaRodjenja"));
        else godine.setText(getIntent().getStringExtra("godinaRodjenja")+"-"+getIntent().getStringExtra("godinaSmrti"));

        TextView mjesto = (TextView)findViewById(R.id.textViewMjesto);
        mjesto.setText(getIntent().getStringExtra("mjestoRodjenja"));

        TextView spol = (TextView)findViewById(R.id.textViewSpol);
        spol.setText("Spol: " + getIntent().getStringExtra("spol"));


        LinearLayout l = (LinearLayout)findViewById(R.id.pozadina) ;
        if(getIntent().getStringExtra("spol").equals("f")) l.setBackgroundDrawable(getResources().getDrawable(R.drawable.pozadina_glumice));
        else l.setBackgroundDrawable(getResources().getDrawable(R.drawable.pozadina_glumci));

        TextView imd = (TextView)findViewById(R.id.textViewImdb);
        imd.setText(getIntent().getStringExtra("imdb"));
        imd.setLinksClickable(true);

        TextView biografija = (TextView)findViewById(R.id.textViewBiografija);
        biografija.setText(getIntent().getStringExtra("biografija"));
        biografija.setMovementMethod(new ScrollingMovementMethod());

        String s = getIntent().getStringExtra("slika");
        ImageView slika = (ImageView)findViewById(R.id.imageView);
        slika.setImageResource(this.getResources().getIdentifier("com.example.lejla.spirala1:drawable/" + s, null, null));

        ib = (ImageButton)findViewById(R.id.imageButtonShare);

        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shareIt();
            }
        });

    }

    private void shareIt() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String tekst = getIntent().getStringExtra("biografija");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, tekst);
        if(sharingIntent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }

    }

}
