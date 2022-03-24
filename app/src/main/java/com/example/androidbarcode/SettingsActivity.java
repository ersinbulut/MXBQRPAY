package com.example.androidbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SettingsActivity extends AppCompatActivity {
    //Connection connection;
    EditText edKullaniciAdi;
    //EditText edKullaniciSifre;

    String kullanici_adi;//Girişten gelen kullanıcı adı
    //String kullanici_sifre;//Girişten gelen kullanıcı adı
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Girişten MainActivity aracılığı ile gelen kullanıcı adı alınıyor.
        kullanici_adi = getIntent().getStringExtra("kullanici_adi");
        //kullanici_sifre = getIntent().getStringExtra("kullanici_sifre");
        edKullaniciAdi=findViewById(R.id.edKullaniciAdi);
        //edKullaniciSifre=findViewById(R.id.edSifre);

        edKullaniciAdi.setText(kullanici_adi.toString());
        //edKullaniciSifre.setText(kullanici_sifre.toString());
/*
        Button btnconnect=(Button) findViewById(R.id.btnKaydet);

        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edAdSoyad=(EditText) findViewById(R.id.edAdSoyad);
                ConSQL c=new ConSQL();
                connection=c.conClass();
                if (c!=null){
                    try {
                    String sqlstatement ="Select * from personel_table";
                    Statement smt=connection.createStatement();
                    ResultSet set=smt.executeQuery(sqlstatement);
                    while (set.next()){
                        edAdSoyad.setText(set.getString(2));
                    }
                    connection.close();
                    }
                    catch (Exception e){
                        Log.e("Error:",e.getMessage());
                    }
                }
            }
        });*/
    }
}