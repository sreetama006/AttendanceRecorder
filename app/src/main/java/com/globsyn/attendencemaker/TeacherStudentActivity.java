package com.globsyn.attendencemaker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherStudentActivity extends AppCompatActivity implements View.OnClickListener{
    Button teacher,student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_student);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();


        teacher=findViewById(R.id.teacherbutton);
        student=findViewById(R.id.studentbutton);

        teacher.setOnClickListener(this);
        student.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.teacherbutton:
                Intent intent=new Intent(TeacherStudentActivity.this,TeacherActivity.class);
                startActivity(intent);
                break;
            case R.id.studentbutton:
                Intent intent1=new Intent(TeacherStudentActivity.this,StudentActivity.class);
                startActivity(intent1);
                break;


        }
    }
}
