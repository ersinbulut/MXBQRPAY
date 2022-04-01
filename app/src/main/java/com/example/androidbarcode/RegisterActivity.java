package com.example.androidbarcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity implements LocationListener {
    EditText AdSoyad,TCKimlik,SicilNo,Birim,Adres,Telefon,Lokasyon,KullaniciAdi,Sifre;
    Connection connection;
    //SharedPreferences ayarlar;
    FirebaseDatabase database;
    DatabaseReference myRef;

    private Button buttonKonumAl;
    private LocationManager konumYoneticisi;
    private String konumSaglayici = "gps";
    private int izinKontrol;

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

        buttonKonumAl = (Button) findViewById(R.id.buttonKonumAl);

        konumYoneticisi = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        buttonKonumAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                izinKontrol = ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if(izinKontrol != PackageManager.PERMISSION_GRANTED){
                    //uygulamanın manifestinde izin var ama kullanıcı izni onaylamışmı bunun kontrolu yapılır

                    ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);

                    //izin kontrolu daha önce yapılmış ve izine onay verilmemişse , izin alma diyalogu çıkar.
                }else{
                    //daha önce izine onay verilmişse burası çalışır.

                    Location konum = konumYoneticisi.getLastKnownLocation(konumSaglayici);

                    if (konum != null) {

                        onLocationChanged(konum);

                    } else {
                        Lokasyon.setText("Konum aktif değil");
                    }
                }

            }
        });


        //ayarlar=getSharedPreferences("ayarlar",MODE_PRIVATE);

         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("Personels");

        Button btnconnect=(Button) findViewById(R.id.button3);

        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edAdSoyad=AdSoyad.getText().toString();
                String edTCKimlik=TCKimlik.getText().toString();
                String edSicilNo=SicilNo.getText().toString();
                String edBirim=Birim.getText().toString();
                String edAdres=Adres.getText().toString();
                String edTelefon=Telefon.getText().toString();
                String edLokasyon=Lokasyon.getText().toString();
                String edKullaniciAdi=KullaniciAdi.getText().toString();
                String edSifre=Sifre.getText().toString();

                Personel yeniPersonel=new Personel("",edAdSoyad,edTCKimlik,edSicilNo,edBirim,edAdres,edTelefon,edLokasyon,edKullaniciAdi,edSifre);
                ConSQL c=new ConSQL();
                connection=c.conClass();
                if (c!=null){
                    try {
                    String sqlstatement="SELECT * FROM personel_table";
                    Statement smt=connection.createStatement();
                    ResultSet set= smt.executeQuery(sqlstatement);
                    while (set.next()){
                        AdSoyad.setText(set.getString(2));
                    }
                    connection.close();
                    }
                    catch (Exception e){
                        Log.e("Error",e.getMessage());
                    }
                }
            }
        });
    }
    @Override
    public void onLocationChanged(Location location) {

        Double enlem = location.getLatitude();
        Double boylam = location.getLongitude();
        //Double yukseklik = location.getAltitude();
        Lokasyon.setText(String.valueOf(enlem)+","+String.valueOf(boylam));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {

            izinKontrol = ContextCompat.checkSelfPermission(RegisterActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION);

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(getApplicationContext(), "İzin kabul edildi.", Toast.LENGTH_LONG).show();

                Location konum = konumYoneticisi.getLastKnownLocation(konumSaglayici);

                if (konum != null) {
                    System.out.println("Provider " + konumSaglayici + " seçildi.");
                    onLocationChanged(konum);
                } else {
                    Lokasyon.setText("Konum aktif değil");
                }

            } else {
                Toast.makeText(getApplicationContext(), "İzin reddedildi.", Toast.LENGTH_LONG).show();
            }
        }

    }
/*
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

        Toast.makeText(this, "shared EKLENDİ", Toast.LENGTH_SHORT).show();

    }
*/
    public void btnPersonelEkle(View view){
        String edAdSoyad=AdSoyad.getText().toString();
        String edTCKimlik=TCKimlik.getText().toString();
        String edSicilNo=SicilNo.getText().toString();
        String edBirim=Birim.getText().toString();
        String edAdres=Adres.getText().toString();
        String edTelefon=Telefon.getText().toString();
        String edLokasyon=Lokasyon.getText().toString();
        String edKullaniciAdi=KullaniciAdi.getText().toString();
        String edSifre=Sifre.getText().toString();

        Personel yeniPersonel=new Personel("",edAdSoyad,edTCKimlik,edSicilNo,edBirim,edAdres,edTelefon,edLokasyon,edKullaniciAdi,edSifre);
        myRef.push().setValue(yeniPersonel);
        Toast.makeText(this, "Personel Eklendi..", Toast.LENGTH_SHORT).show();
        finish();
    }
}
