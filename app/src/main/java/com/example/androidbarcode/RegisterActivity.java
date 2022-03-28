package com.example.androidbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidbarcode.database.Veritabani;
import com.example.androidbarcode.model.Personel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText AdSoyad,TCKimlik,SicilNo,Birim,Adres,Telefon,Lokasyon,KullaniciAdi,Sifre;
    FirebaseDatabase db;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AdSoyad=findViewById(R.id.edTC);
        TCKimlik=findViewById(R.id.edTCKimlik);
        SicilNo=findViewById(R.id.edSicilNo);
        Birim=findViewById(R.id.edBirim);
        Adres=findViewById(R.id.edAdres);
        Telefon=findViewById(R.id.edTelefon);
        Lokasyon=findViewById(R.id.edLokasyon);
        KullaniciAdi=findViewById(R.id.edKullaniciAdi);
        Sifre=findViewById(R.id.edSifre);

        db=FirebaseDatabase.getInstance();
        ref=db.getReference("personeller");
    }
    public void personelEkle(View v){

        Veritabani vt = new Veritabani(RegisterActivity.this);
        vt.personelEkle(AdSoyad,TCKimlik,SicilNo,Birim,Adres,Telefon,Lokasyon,KullaniciAdi,Sifre);
        Toast.makeText(RegisterActivity.this, "Personel eklendi.", Toast.LENGTH_SHORT).show();
        vt.close();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


    public void btnEkle(View view){
        String edAdSoyad=AdSoyad.getText().toString();
        String edTCKimlik=TCKimlik.getText().toString();
        String edSicilNo=SicilNo.getText().toString();
        String edBirim=Birim.getText().toString();
        String edAdres=Adres.getText().toString();
        String edTelefon=Telefon.getText().toString();
        String edLokasyon=Lokasyon.getText().toString();
        String edKullaniciAdi=KullaniciAdi.getText().toString();
        String edSifre=Sifre.getText().toString();

        final Personel yeniPersonel=new Personel(1,edAdSoyad,edTCKimlik,edSicilNo,edBirim,edAdres,edTelefon,edLokasyon,edKullaniciAdi,edSifre);

        //Firabase ekleme kodları
        ContentValues veriler = new ContentValues();
        veriler.put("AdSoyad",edAdSoyad);
        veriler.put("TCKimlik",edTCKimlik);
        veriler.put("SicilNo",edSicilNo);
        veriler.put("Birim",edBirim);
        veriler.put("Adres",edAdres);
        veriler.put("Telefon",edTelefon);
        veriler.put("Lokasyon",edLokasyon);
        veriler.put("KullaniciAdi",edKullaniciAdi);
        veriler.put("Sifre",edSifre);
        /*
        long cevap = db.insert("personels",null,veriler);//YENİ EKLENEN KAYDIN ID SİNİ CEVAP OLARAK VERİR
        //HATA OLURSA -1 SONUCU VERİR
        return cevap;*/


    }
}
