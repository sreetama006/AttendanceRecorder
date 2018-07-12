package com.globsyn.attendencemaker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AttendenceActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button savebutton;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReferenceUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReferenceUser = firebaseDatabase.getReference("users");



        textView=findViewById(R.id.entername);
        editText=findViewById(R.id.nameedit);
        savebutton=findViewById(R.id.savebutton);


        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudentname();

                String id=databaseReferenceUser.push().getKey();



            }
        });

    }
    private void addStudentname()
    {
        String student_name=editText.getText().toString();
        if(!TextUtils.isEmpty(student_name))
        {

            String id=databaseReferenceUser.push().getKey();

            Student student=new Student(id,student_name);
            databaseReferenceUser.child(id).setValue(student);
            Toast.makeText(this, "student name added", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(AttendenceActivity.this,StudentLogoutActivity.class);
            startActivity(intent);

        }
        else{
            Toast.makeText(this, "You should enter your name", Toast.LENGTH_SHORT).show();
        }




    }

}





