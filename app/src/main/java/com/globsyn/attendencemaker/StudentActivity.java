package com.globsyn.attendencemaker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentActivity extends AppCompatActivity {
    Button studentlogin,studentregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        studentlogin=findViewById(R.id.studentlogin);
        studentregister=findViewById(R.id.studentregister);

        studentregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentActivity.this,StudentRegisterActivity.class);
                startActivity(intent);
            }
        });
        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(StudentActivity.this,StudentLoginActivity.class);
                startActivity(intent2);
            }
        });
    }

}



