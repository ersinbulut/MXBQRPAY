package com.example.androidbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidbarcode.database.Veritabani;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void personelEkle(View v){
        Veritabani vt = new Veritabani(RegisterActivity.this);
        //long cevap = vt.personelEkle();
        Toast.makeText(RegisterActivity.this, "Personel eklendi.", Toast.LENGTH_SHORT).show();
        vt.close();
        Intent i = new Intent(this,MainMenuActivity.class);
        startActivity(i);
    }
}
