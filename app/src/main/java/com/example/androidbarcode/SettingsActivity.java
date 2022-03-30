package com.example.androidbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidbarcode.database.Veritabani;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SettingsActivity extends AppCompatActivity {
    //Connection connection;
    EditText edKullaniciAdi;

    FirebaseDatabase database;
    DatabaseReference myRef;
    Veritabani veritabani;

    String kullanici_adi;//Girişten gelen kullanıcı adı
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        edKullaniciAdi=findViewById(R.id.edKullaniciAdi);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Personels");


       edKullaniciAdi.setText((CharSequence) veritabani.personelleriListele());


        /*
        //Girişten MainActivity aracılığı ile gelen kullanıcı adı alınıyor.
        kullanici_adi = getIntent().getStringExtra("kullanici_adi");

        edKullaniciAdi=findViewById(R.id.edKullaniciAdi);
        //edKullaniciSifre=findViewById(R.id.edSifre);

        edKullaniciAdi.setText(kullanici_adi.toString());
        */





    }
}