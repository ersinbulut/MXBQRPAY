package com.example.androidbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.androidbarcode.database.Veritabani;
import com.example.androidbarcode.model.Personel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    EditText AdSoyad,TCKimlik,SicilNo,Birim,Adres,Telefon,Lokasyon,KullaniciAdi,Sifre,SifreTekrar;
   // EditText email;
   // EditText sifre;
    //ImageView resim;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    FirebaseDatabase database;
    DatabaseReference myRef;
    Veritabani veritabani;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_3, container, false);

        AdSoyad=view.findViewById(R.id.edAdSoyad);
        TCKimlik=view.findViewById(R.id.edTCKimlikNo);
        SicilNo=view.findViewById(R.id.edSicilNo);
        Birim=view.findViewById(R.id.edBirim);
        Adres=view.findViewById(R.id.edAdres);
        Telefon=view.findViewById(R.id.edTelefon);
        Lokasyon=view.findViewById(R.id.edLokasyon);
        KullaniciAdi=view.findViewById(R.id.edKullaniciAdi);
        Sifre=view.findViewById(R.id.edSifre);
        SifreTekrar=view.findViewById(R.id.edSifreTekrar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Personels");


        // Veri tabanındaki verileri okuma
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d:dataSnapshot.getChildren()){
                    Personel personel = d.getValue(Personel.class);
                    String key=d.getKey();
                    personel.setId(key);

                    AdSoyad.setText(personel.getAdsoyad());
                    TCKimlik.setText(personel.getTc());
                    SicilNo.setText(personel.getSicilno());
                    Birim.setText(personel.getBirim());
                    Adres.setText(personel.getAdres());
                    Telefon.setText(personel.getTelefon());
                    Lokasyon.setText(personel.getLokasyon());
                    KullaniciAdi.setText(personel.getKullanici_adi());
                    Sifre.setText(personel.getKullanici_sifre());
                    SifreTekrar.setText(personel.getKullanici_sifre());
                }

                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        return view;
    }
}
