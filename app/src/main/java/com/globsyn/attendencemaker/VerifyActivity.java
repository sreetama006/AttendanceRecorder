package com.globsyn.attendencemaker;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerifyActivity extends AppCompatActivity {
    Button scanbutton;
    TextView barcodeResultText;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        barcodeResultText = findViewById(R.id.text_barcode_result);
        scanbutton = findViewById(R.id.scan_bar);
        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify();
            }
        });

    }

    private void verify() {
        Intent intent = new Intent(this, StudentCameraActivity.class);
        startActivityForResult(intent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this, ", Toast.LENGTH_SHORT).show();
        if (requestCode == 0) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    barcodeResultText.setText(barcode.displayValue);
                    Toast.makeText(this, "" + barcode, Toast.LENGTH_SHORT).show();
                    verifyOtpFromDatabase();
                } else {
                    barcodeResultText.setText("no barcode result");

                }
            }
        }
    }

    private void verifyOtpFromDatabase() {
        databaseReference.child("otp").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String otpFromDatabase = dataSnapshot.getValue().toString();
                    String otp=barcodeResultText.getText().toString();
                if (otp.equals(otpFromDatabase)){
                    Intent intent = new Intent(VerifyActivity.this, AttendenceActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(VerifyActivity.this, "Oops!,please try again", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}



