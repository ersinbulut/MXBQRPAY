package com.example.androidbarcode.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.androidbarcode.model.Personel;
import java.util.ArrayList;

public class Veritabani extends SQLiteOpenHelper {

    public Veritabani(@Nullable Context context) {
        super(context, "Personel.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS filmler(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "imdbID TEXT, " +
                "Title TEXT, " +
                "Type TEXT, " +
                "Year TEXT, " +
                "Poster TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long personelEkleParametrleri(int id, String adsoyad, String tc, String sicilno, String birim, String adres, String telefon, String lokasyon, String kullanici_adi, String kullanici_sifre){
        ContentValues veriler = new ContentValues();
        veriler.put("id" , id);
        veriler.put("adsoyad", adsoyad);
        veriler.put("tc", tc);
        veriler.put("birim",birim);
        veriler.put("adres", adres);
        veriler.put("telefon", telefon);
        veriler.put("lokasyon", lokasyon);
        veriler.put("kullanici_adi", kullanici_adi);
        veriler.put("kullanici_sifre", kullanici_sifre);

        SQLiteDatabase db = getWritableDatabase();
        long cevap = db.insert("personels", null, veriler);
        return cevap;
    }
    /*
    public long personelEkle(Personel yeniPersonel){
        ContentValues veriler = new ContentValues();
        veriler.put("adsoyad", yeniPersonel.getAdsoyad());
        veriler.put("tc", yeniPersonel.getTc());
        veriler.put("sicilno", yeniPersonel.getSicilno());
        veriler.put("birim", yeniPersonel.getBirim());
        veriler.put("adres", yeniPersonel.getAdres());
        veriler.put("telefon", yeniPersonel.getTelefon());
        veriler.put("lokasyon", yeniPersonel.getLokasyon());
        //kullanıcı giriş bilgileri
        veriler.put("kullanici_adi", yeniPersonel.getKullanici_adi());
        veriler.put("kullanici_sifre", yeniPersonel.getKullanici_sifre());

        SQLiteDatabase db = getWritableDatabase();
        long cevap = db.insert("personels", null, veriler);
        return cevap;
    }*/

    public ArrayList<Personel> personelleriListele(){
        ArrayList<Personel> personeller = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, imdbID, Title, Type, Year, Poster FROM filmler", null);
        if(c.moveToFirst()){
            do{
                int id = c.getInt(0);
                String adsoyad = c.getString(1);
                String tc = c.getString(2);
                String sicilno = c.getString(3);
                String birim = c.getString(4);
                String adres = c.getString(5);
                String telefon = c.getString(6);
                String lokasyon = c.getString(7);

                String kullanici_adi = c.getString(8);
                String kullanici_sifre = c.getString(9);
                //System.out.println(id +" - "+ ad +" - " + yil +" - " + tur);
                Personel p = new Personel(id, null, adsoyad, tc, sicilno, birim, adres,telefon,lokasyon,kullanici_adi,kullanici_sifre);
                personeller.add(p);
            }while(c.moveToNext());
        }
        c.close();
        return personeller;
    }

    public long personelEkle(EditText edAdSoyad, EditText edTCKimlik, EditText edSicilNo, EditText edBirim, EditText edAdres, EditText edTelefon, EditText edLokasyon, EditText edKullaniciAdi, EditText edSifre) {
        ContentValues veriler = new ContentValues();
        veriler.put("adsoyad", String.valueOf(edAdSoyad.getText()));
        veriler.put("tc", String.valueOf(edTCKimlik));
        veriler.put("sicilno", String.valueOf(edSicilNo));
        veriler.put("birim", String.valueOf(edBirim));
        veriler.put("adres", String.valueOf(edAdres));
        veriler.put("telefon", String.valueOf(edTelefon));
        veriler.put("lokasyon", String.valueOf(edLokasyon));
        //kullanıcı giriş bilgileri
        veriler.put("kullanici_adi", String.valueOf(edKullaniciAdi));
        veriler.put("kullanici_sifre", String.valueOf(edSifre));

        SQLiteDatabase db = getWritableDatabase();
        long cevap = db.insert("personels", null, veriler);
        return cevap;
    }
}
