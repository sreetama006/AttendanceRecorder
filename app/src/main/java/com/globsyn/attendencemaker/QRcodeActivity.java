package com.globsyn.attendencemaker;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class QRcodeActivity extends AppCompatActivity {

    Button button,fetchbutton,generateBtn;
    ImageView imageView;
    TextView showOtpTextView;
    int otp;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceOtp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReferenceOtp=firebaseDatabase.getReference("otp");

       // showOtpTextView = findViewById(R.id.text_view_otp);
      //  generateBtn = findViewById(R.id.button_generte_otp);





        imageView = findViewById(R.id.image_view);
        button = findViewById(R.id.button_generate);
        fetchbutton=findViewById(R.id.fetch_button);
        fetchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QRcodeActivity.this,TeacherAttendencelistActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generate();
            }
        });
    }

   // public void generateOTP(){
        //Random random = new Random();
        //otp = random.nextInt(9000) + 1000;
       // showOtpTextView.setText(String.valueOf(otp));

    public void generate(){

        Random random = new Random();
        otp = random.nextInt(9000) + 1000;

        String id=databaseReferenceOtp.push().getKey();
        databaseReferenceOtp.setValue(otp);

            MultiFormatWriter writer = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = writer.encode(String.valueOf(otp), BarcodeFormat.QR_CODE, 200,200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();


            }
        }
    }







