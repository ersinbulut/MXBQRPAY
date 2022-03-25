package com.example.androidbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidbarcode.database.Veritabani;
import com.example.androidbarcode.model.Personel;

public class RegisterActivity extends AppCompatActivity {
    EditText edAdSoyad,edTCKimlik,edSicilNo,edBirim,edAdres,edTelefon,edLokasyon,edKullaniciAdi,edSifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edAdSoyad=findViewById(R.id.edAdSoyad);
        edTCKimlik=findViewById(R.id.edTCKimlik);
        edSicilNo=findViewById(R.id.edSicilNo);
        edBirim=findViewById(R.id.edBirim);
        edAdres=findViewById(R.id.edAdres);
        edTelefon=findViewById(R.id.edTelefon);
        edLokasyon=findViewById(R.id.edLokasyon);
        edKullaniciAdi=findViewById(R.id.edKullaniciAdi);
        edSifre=findViewById(R.id.edSifre);
    }
    public void personelEkle(View v){

        Veritabani vt = new Veritabani(RegisterActivity.this);
        vt.personelEkle(edAdSoyad,edTCKimlik,edSicilNo,edBirim,edAdres,edTelefon,edLokasyon,edKullaniciAdi,edSifre);
        Toast.makeText(RegisterActivity.this, "Personel eklendi.", Toast.LENGTH_SHORT).show();
        vt.close();
        Intent i = new Intent(this,MainMenuActivity.class);
        startActivity(i);
    }
}
