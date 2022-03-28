package com.example.androidbarcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbarcode.database.Veritabani;
import com.example.androidbarcode.model.Personel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText AdSoyad,TCKimlik,SicilNo,Birim,Adres,Telefon,Lokasyon,KullaniciAdi,Sifre;
    TextView edkey;
    FirebaseDatabase db;
    DatabaseReference ref;
    //Veritabani con;
    SharedPreferences ayarlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AdSoyad=findViewById(R.id.edAdSoyad);
        TCKimlik=findViewById(R.id.edTCKimlikNo);
        SicilNo=findViewById(R.id.edSicilNo);
        Birim=findViewById(R.id.edBirim);
        Adres=findViewById(R.id.edAdres);
        Telefon=findViewById(R.id.edTelefon);
        Lokasyon=findViewById(R.id.edLokasyon);
        KullaniciAdi=findViewById(R.id.edKullaniciAdi);
        Sifre=findViewById(R.id.edSifre);
        edkey=findViewById(R.id.edKey);

        ayarlar=getSharedPreferences("ayarlar",MODE_PRIVATE);


        db= FirebaseDatabase.getInstance();
        ref=db.getReference("Personels");

        AdSoyad.setText(ayarlar.getString("adsoyad",""));
    }

    public void Ekle(View v){
        ayarlar.edit().putString("adsoyad",AdSoyad.getText().toString()).apply();
        ayarlar.edit().putString("TCKimlik",AdSoyad.getText().toString()).apply();
        ayarlar.edit().putString("SicilNo",AdSoyad.getText().toString()).apply();
        ayarlar.edit().putString("Birim",AdSoyad.getText().toString()).apply();
        ayarlar.edit().putString("Adres",AdSoyad.getText().toString()).apply();
        ayarlar.edit().putString("Telefon",AdSoyad.getText().toString()).apply();
        ayarlar.edit().putString("Lokasyon",AdSoyad.getText().toString()).apply();
        ayarlar.edit().putString("KullaniciAdi",AdSoyad.getText().toString()).apply();
        ayarlar.edit().putString("Sifre",AdSoyad.getText().toString()).apply();

    }

    public void btnPersonelEkle(View view){

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        String edKey=edkey.getText().toString();
        String edAdSoyad=AdSoyad.getText().toString();
        String edTCKimlik=TCKimlik.getText().toString();
        String edSicilNo=SicilNo.getText().toString();
        String edBirim=Birim.getText().toString();
        String edAdres=Adres.getText().toString();
        String edTelefon=Telefon.getText().toString();
        String edLokasyon=Lokasyon.getText().toString();
        String edKullaniciAdi=KullaniciAdi.getText().toString();
        String edSifre=Sifre.getText().toString();

        Personel yeniPersonel=new Personel(edKey,edAdSoyad,edTCKimlik,edSicilNo,edBirim,edAdres,edTelefon,edLokasyon,edKullaniciAdi,edSifre);


        Veritabani vt=new Veritabani(this);
        vt.prsEkle(yeniPersonel);
        vt.close();
        Toast.makeText(this, "Personel Eklendi..", Toast.LENGTH_SHORT).show();

    }
}
