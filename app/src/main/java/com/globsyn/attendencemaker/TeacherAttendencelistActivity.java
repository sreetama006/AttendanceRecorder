package com.globsyn.attendencemaker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherAttendencelistActivity extends AppCompatActivity {

    DatabaseReference databaseStudents;
    ArrayList<Student> mList = new ArrayList<>();
    AdapterStudent adapterStudent;
    ListView listView;
    Button logoutbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendencelist);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();


        databaseStudents = FirebaseDatabase.getInstance().getReference("users");

        listView=findViewById(R.id.new_list);
        logoutbutton=findViewById(R.id.logout);

logoutbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(TeacherAttendencelistActivity.this,TeacherLogoutActivity.class);
        startActivity(intent);
    }
});
        adapterStudent=new AdapterStudent(this,mList);
        listView.setAdapter(adapterStudent);
        mList = new ArrayList<>();
        fetchData();
    }



    private void fetchData() {
        databaseStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot studentsnapshot : dataSnapshot.getChildren()) {
                    Student user = studentsnapshot.getValue(Student.class);
                    mList.add(user);

                    //  Toast.makeText(TeacheAttendencelistActivity.this, "" + user.getName(), Toast.LENGTH_SHORT).show();
                    //studentlist.add(user);
                }

                adapterStudent.setData(mList);
                // AdapterStudent adapter=new AdapterStudent(TeacheAttendencelistActivity.this,mList);
                //listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(TeacherAttendencelistActivity.this, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    }

