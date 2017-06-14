package com.example.lejla.spirala1;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lejla on 11.06.2017..
 */

public class FragmentOFilmu  extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    Film film;
    int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR=2;
    View iv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        iv = inflater.inflate(R.layout.layout_o_filmu, container, false);
        Button spasi = (Button) iv.findViewById(R.id.buttonSpasi);
        if (getArguments() != null && getArguments().containsKey("film")) {
            film = getArguments().getParcelable("film");
            TextView imeiprezime = (TextView) iv.findViewById(R.id.textViewNaziv);
            imeiprezime.setText(film.getNaziv());

        }

        spasi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePicker dp = (DatePicker)iv.findViewById(R.id.datePicker);
                EditText e = (EditText) iv.findViewById(R.id.editTextDetalji);
                film.setDetalji(e.getText().toString());
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(dp.getYear(), dp.getMonth() , dp.getDayOfMonth(), 7, 30);

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                     ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
                }

                ContentResolver cr = getActivity().getContentResolver();
                ContentValues cv = new ContentValues();
                cv.put(CalendarContract.Events.TITLE,film.getNaziv());
                cv.put(CalendarContract.Events.DESCRIPTION,film.getDetalji());
                cv.put(CalendarContract.Events.DTSTART, beginTime.getTimeInMillis());
                cv.put(CalendarContract.Events.DTEND, beginTime.getTimeInMillis()+60*60*1000);
                cv.put(CalendarContract.Events.CALENDAR_ID,2);
                cv.put(CalendarContract.Events.EVENT_TIMEZONE,Calendar.getInstance().getTimeZone().getID());
                Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI,cv);

/*
                if (Build.VERSION.SDK_INT >= 14) {
                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                            .putExtra(CalendarContract.Events.TITLE, film.getNaziv())
                            .putExtra(CalendarContract.Events.DESCRIPTION, film.getDetalji())
                            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                    startActivity(intent);
                }

                else {
                    Calendar cal = Calendar.getInstance();
                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");
                    intent.putExtra("beginTime", beginTime.getTimeInMillis());
                    intent.putExtra("allDay", true);
                    intent.putExtra("title", film.getDetalji());
                    startActivity(intent);
                }
*/

            }
        });
        return iv;
    }

}


