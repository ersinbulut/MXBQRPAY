package com.example.androidbarcode;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    private CodeScanner mCodeScanner;
    private CodeScannerView mCodeScannerView;
    TextView tv;
    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_2, container, false);

        tv=view.findViewById(R.id.textView13);
        tv.setText("KAREKOD TARAYICI");

        if (ContextCompat.checkSelfPermission(getActivity().getApplication(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 123);
        } else {
            startScanning();
        }
        mCodeScannerView = view.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(getActivity().getApplication(), mCodeScannerView);
        mCodeScanner.startPreview();   // this line is very important, as you will not be able to scan your code without this, you will only get blank screen


        return view;
    }

    private void startScanning() {
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplication(), result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //now if you want to scan again when you click on scanner then do this.
        mCodeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        getParentFragment().getActivity().onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getActivity(), "İzin Verildi", Toast.LENGTH_SHORT).show();
                startScanning();
            } else {
                Toast.makeText(getActivity(), "İzin Reddedildi", Toast.LENGTH_SHORT).show();
            }
        }
    }





}
