package com.globsyn.attendencemaker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherRegisterActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5;
    EditText et1, et2, et3, et4,et5;
    Button registerbutton;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        auth = FirebaseAuth.getInstance();

        tv1 = findViewById(R.id.teacher_register_name);
        tv2 = findViewById(R.id.teacher_register_dept);
        tv3 = findViewById(R.id.subject);
        tv4 = findViewById(R.id.teacher_create_username);
        tv5 = findViewById(R.id.teacher_CREATE_PASSWORD);

        et1 = findViewById(R.id.full_name);
        et2 = findViewById(R.id.dept);
        et3 = findViewById(R.id.user_name);
        et4 = findViewById(R.id.pass_word);
        et5=findViewById(R.id.edit_subject);



        registerbutton = findViewById(R.id.teacher_register_button);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register();


            }
        });
    }

    public void register()

    {
        String name=et1.getText().toString();
        String subject=et5.getText().toString();
        String email= et3.getText().toString();
        String password = et4.getText().toString();
        String dept=et2.getText().toString();

       if(name.isEmpty()){
          et1.setError("name required");
           et1.requestFocus();
           return;
      }
        if(subject.isEmpty()){
            et1.setError("subject required");
            et1.requestFocus();
            return;
        }
        if(email.isEmpty()){
            et1.setError("email required");
            et1.requestFocus();
            return;
        }
        if(password.isEmpty()){
            et1.setError("password required");
            et1.requestFocus();
            return;
        }
        if(dept.isEmpty()){
            et1.setError("department required");
            et1.requestFocus();
            return;
        }



        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(TeacherRegisterActivity.this, "USER REGISTERED", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(TeacherRegisterActivity.this, QRcodeActivity.class);
                    startActivity(intent3);
                } else {

                    Toast.makeText(TeacherRegisterActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TeacherRegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
