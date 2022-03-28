package com.example.androidbarcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbarcode.model.Personel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OzelAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Personel> personeller;

    public OzelAdapter(Context context, ArrayList<Personel> personeller) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.personeller = personeller;
    }

    @Override
    public int getCount() {
        return personeller.size();
    }//dizideki eleman sayısı

    @Override
    public Object getItem(int position) {
        return personeller.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_settings, parent, false);

        EditText edAdSoyad = convertView.findViewById(R.id.edAdSoyad);
        EditText edTCKimlik = convertView.findViewById(R.id.edTCKimlik);
        EditText edSicilNo = convertView.findViewById(R.id.edSicilNo);
        EditText edBirim = convertView.findViewById(R.id.edBirim);
        EditText edAdres = convertView.findViewById(R.id.edAdres);
        EditText edTelefon = convertView.findViewById(R.id.edTelefon);
        EditText edLokasyon = convertView.findViewById(R.id.edLokasyon);

        EditText edKullaniciAdi = convertView.findViewById(R.id.edKullaniciAdi);
        EditText edSifre = convertView.findViewById(R.id.edSifre);


        Personel p= personeller.get(position);
        edAdSoyad.setText(p.getAdsoyad());
        edTCKimlik.setText(p.getTc());
        edSicilNo.setText(p.getSicilno());
        edBirim.setText(p.getBirim());
        edAdres.setText(p.getAdres());
        edTelefon.setText(p.getTelefon());
        edLokasyon.setText(p.getLokasyon());

        edKullaniciAdi.setText(p.getKullanici_adi());
        edSifre.setText(p.getKullanici_sifre());



        return convertView;
    }
}

