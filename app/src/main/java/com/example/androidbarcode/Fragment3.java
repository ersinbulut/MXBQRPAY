package com.example.androidbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidbarcode.database.Veritabani;
import com.example.androidbarcode.model.Personel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    EditText AdSoyad,TCKimlik,SicilNo,Birim,Adres,Telefon,Lokasyon,KullaniciAdi,Sifre,SifreTekrar;
    Button button;
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

        button=view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kayıt güncelleme
                Map<String,Object> bilgiler=new HashMap<>();
                bilgiler.put("adsoyad",AdSoyad.getText().toString());
                bilgiler.put("tc",TCKimlik.getText().toString());
                bilgiler.put("sicilno",SicilNo.getText().toString());
                bilgiler.put("birim",Birim.getText().toString());
                bilgiler.put("adres",Adres.getText().toString());
                bilgiler.put("telefon",Telefon.getText().toString());
                bilgiler.put("lokasyon",Lokasyon.getText().toString());
                bilgiler.put("kullanici_adi",KullaniciAdi.getText().toString());
                bilgiler.put("kullanici_sifre",Sifre.getText().toString());
                //bu kod firebase veritabanındaki id değeridir.
                myRef.child("-MzybTMj2ktt_qp2rCLK").updateChildren(bilgiler);

                kisiGuncelle();
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");


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



        kisiArama();
        //kisileriListele();
        return view;
    }

/*
    public void kisileriListele(){
        String url="http://mxbinteractive.com/QRAPP/tum_kisiler.php";

        StringRequest istek=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Cevap",response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray kisilerListe=jsonObject.getJSONArray("kisiler");

                    for (int i=0;i<kisilerListe.length();i++){
                        JSONObject k =kisilerListe.getJSONObject(i);

                        int user_id=k.getInt("user_id");
                        String user_adsoyad=k.getString("user_adsoyad");
                        String user_tc=k.getString("user_tc");
                        String user_sicilno=k.getString("user_sicilno");
                        String user_birim=k.getString("user_birim");
                        String user_adres=k.getString("user_adres");
                        String user_telefon=k.getString("user_telefon");
                        String user_lokasyon=k.getString("user_lokasyon");
                        String user_kullaniciadi=k.getString("user_kullaniciadi");
                        String user_sifre=k.getString("user_sifre");

                        Log.e("user_id",String.valueOf(user_id));
                        Log.e("user_adsoyad",user_adsoyad);
                        Log.e("user_tc",user_tc);
                        Log.e("user_sicilno",user_sicilno);
                        Log.e("user_birim",user_birim);
                        Log.e("user_adres",user_adres);
                        Log.e("user_telefon",user_telefon);
                        Log.e("user_lokasyon",user_lokasyon);
                        Log.e("user_kullaniciadi",user_kullaniciadi);
                        Log.e("user_sifre",user_sifre);
                        Log.e("*******","********");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(getContext()).add(istek);
    }

*/

    public void kisiGuncelle(){
        String url="http://mxbinteractive.com/QRAPP/update_kisiler.php";

        StringRequest istek=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Cevap",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                //Kullanici

                params.put("user_tc",TCKimlik.getText().toString());
                params.put("user_adsoyad", AdSoyad.getText().toString());
                params.put("user_sicilno", SicilNo.getText().toString());
                params.put("user_birim", Birim.getText().toString());
                params.put("user_adres", Adres.getText().toString());
                params.put("user_telefon", Telefon.getText().toString());
                params.put("user_lokasyon", Lokasyon.getText().toString());
                //Login
                params.put("user_kullaniciadi", KullaniciAdi.getText().toString());
                params.put("user_sifre", Sifre.getText().toString());


                return params;
            }
        };

        Volley.newRequestQueue(getContext()).add(istek);
        Toast.makeText(getContext(), "Bilgiler güncellendi..", Toast.LENGTH_SHORT).show();
    }

    public void kisiArama(){
        String url="http://mxbinteractive.com/QRAPP/tum_kisiler_arama.php";

        StringRequest istek=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Cevap",response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray kisilerListe=jsonObject.getJSONArray("users");

                    for (int i=0;i<kisilerListe.length();i++){
                        JSONObject k =kisilerListe.getJSONObject(i);

                        int user_id=k.getInt("users_id");
                        String user_adsoyad=k.getString("user_adsoyad");
                        String user_tc=k.getString("user_tc");
                        String user_sicilno=k.getString("user_sicilno");
                        String user_birim=k.getString("user_birim");
                        String user_adres=k.getString("user_adres");
                        String user_telefon=k.getString("user_telefon");
                        String user_lokasyon=k.getString("user_lokasyon");
                        String user_kullaniciadi=k.getString("user_kullaniciadi");
                        String user_sifre=k.getString("user_sifre");


                        Log.e("user_id",String.valueOf(user_id));
                        Log.e("user_adsoyad",user_adsoyad);
                        Log.e("user_tc",user_tc);
                        Log.e("user_sicilno",user_sicilno);
                        Log.e("user_birim",user_birim);
                        Log.e("user_adres",user_adres);
                        Log.e("user_telefon",user_telefon);
                        Log.e("user_lokasyon",user_lokasyon);
                        Log.e("user_kullaniciadi",user_kullaniciadi);
                        Log.e("user_sifre",user_sifre);
                        Log.e("*******","********");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                params.put("user_tc", TCKimlik.getText().toString());

                return params;
            }
        };

        Volley.newRequestQueue(getContext()).add(istek);
    }

}
