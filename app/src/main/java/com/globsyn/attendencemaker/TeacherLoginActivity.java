package com.globsyn.attendencemaker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherLoginActivity extends AppCompatActivity {

    EditText emailedit,passwordedit;
    Button loginbutton;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        auth=FirebaseAuth.getInstance();

        emailedit=findViewById(R.id.teacher_email_edit);
        passwordedit=findViewById(R.id.teacher_password_edit);
       loginbutton=findViewById(R.id.teacher_login_button);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login();
            }
        });

    }
    private void login(){
        String email=emailedit.getText().toString();
        String password=passwordedit.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(!task.isSuccessful()){
                   Toast.makeText(TeacherLoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
               }
               else{
                   Intent intent=new Intent(TeacherLoginActivity.this,QRcodeActivity.class);
                   startActivity(intent);
                   finish();
               }
            }
        });
    }
}
