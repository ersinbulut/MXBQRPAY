package com.example.androidbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    EditText email;
    //EditText sifre;
    //ImageView resim;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_3, container, false);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        email=view.findViewById(R.id.edKullaniciAdi);
        //sifre=view.findViewById(R.id.edSifre);

        email.setText(mUser.getEmail());
        //sifre.setText(": "+"******");

        return view;
    }
}
