package com.globsyn.attendencemaker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentRegisterActivity extends AppCompatActivity {
    TextView Name,email,password,department,year;
    EditText editname,editemail,editpassword,editdept,edityear;
    Button registerstudent;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        auth = FirebaseAuth.getInstance();

        Name = findViewById(R.id.student_register_name);
        email = findViewById(R.id.student_create_email);
       password = findViewById(R.id.student_CREATE_PASSWORD);
       department= findViewById(R.id.student_register_dept);
       year = findViewById(R.id.student_register_year);

       editname = findViewById(R.id.student_name);
        editemail= findViewById(R.id.student_email);
        editpassword= findViewById(R.id.student_pass);
        editdept= findViewById(R.id.student_dept);
        edityear=findViewById(R.id.student_year);



        registerstudent = findViewById(R.id.student_register_button);

        registerstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentregister();
            }
        });

    }
    public void studentregister()

    {
        String name=editname.getText().toString();
        String year=edityear.getText().toString();
        String email= editemail.getText().toString();
        String password = editpassword.getText().toString();
        String dept=editdept.getText().toString();

        if(name.isEmpty()){
           editname.setError("name required");
            editname.requestFocus();
            return;
        }
        if(year.isEmpty()){
            edityear.setError("subject required");
            edityear.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editemail.setError("email required");
            editemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editpassword.setError("password required");
            editpassword.requestFocus();
            return;
        }
        if(dept.isEmpty()){
            editdept.setError("department required");
            editdept.requestFocus();
            return;
        }



        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(StudentRegisterActivity.this, "USER REGISTERED", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(StudentRegisterActivity.this, StudentLoginActivity.class);
                    startActivity(intent3);
                } else {

                    Toast.makeText(StudentRegisterActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StudentRegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


