package com.example.androidbarcode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    RecyclerView recyclerView;
    //ArrayList<Urun> urunArrayList;
    OzelAdapter ozelAdapter;

    TextView tv;

    FirebaseDatabase db;
    DatabaseReference ref;
    ValueEventListener urunlerListener;

//    private FirebaseAuth mAuth;
//    private FirebaseUser mUser;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_2, container, false);

//        mAuth = FirebaseAuth.getInstance();
//        mUser = mAuth.getCurrentUser();

        tv=view.findViewById(R.id.textView13);
        tv.setText("KAREKOD TARAYICI");

        //urunArrayList = new ArrayList<>();

        recyclerView=view.findViewById(R.id.recyclerView1);
        //ozelAdapter=new OzelAdapter(view,urunArrayList);
        //recyclerView.setAdapter(ozelAdapter);

        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);




        db = FirebaseDatabase.getInstance();
        ref = db.getReference("favoriler");


        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
