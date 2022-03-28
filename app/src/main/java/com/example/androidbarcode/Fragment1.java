package com.example.androidbarcode;

import static android.content.Context.WINDOW_SERVICE;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

///////
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
/////////

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    TextView tv;
    private EditText edtValue;
    private ImageView qrImage;
    private String inputValue;
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private Fragment1 activity;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_1, container, false);
        tv=view.findViewById(R.id.textView14);
        tv.setText("KAREKOD OLUŞTURUCU");

        qrImage = view.findViewById(R.id.qr_image);
        edtValue = view.findViewById(R.id.edt_value);
        activity = Fragment1.this;


        view.findViewById(R.id.generate_barcode).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                inputValue = edtValue.getText().toString().trim();
                if (inputValue.length() > 0) {
                    //WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                    WindowManager manager = (WindowManager) getLayoutInflater().getContext().getSystemService(Context.WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(
                            inputValue, null,
                            QRGContents.Type.TEXT,
                            smallerDimension);
                    qrgEncoder.setColorBlack(Color.BLACK);
                    qrgEncoder.setColorWhite(Color.WHITE);
                    try {
                        bitmap = qrgEncoder.getBitmap();
                        qrImage.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    edtValue.setError(getResources().getString(R.string.value_required));
                }
            }
        });
/*
        view.findViewById(R.id.save_barcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        boolean save = new QRGSaver().save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
                        String result = save ? "Image Saved" : "Image Not Saved";
                        Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
                        edtValue.setText(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }
            }
        });
*/



        return view;
    }
}
