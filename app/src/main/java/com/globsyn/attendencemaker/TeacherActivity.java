package com.globsyn.attendencemaker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherActivity extends AppCompatActivity {
    Button teacherlogin,teacherregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        teacherlogin=findViewById(R.id.loginteacher);
        teacherregister=findViewById(R.id.registerteacher);

        teacherregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherActivity.this,TeacherRegisterActivity.class);
                startActivity(intent);
            }
        });
        teacherlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(TeacherActivity.this,TeacherLoginActivity.class);
                startActivity(intent2);
            }
        });
    }

}
