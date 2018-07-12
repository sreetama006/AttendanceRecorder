package com.globsyn.attendencemaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TeacherLogoutActivity extends AppCompatActivity {
    private static final String NEW_FILE = "new_file";
    private static final String KEY_NAME = "name";

    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_logout);

//        ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();


        textView1 = findViewById(R.id.text_view1);
//        SharedPreferences sharedPreferences = getSharedPreferences(NEW_FILE, MODE_PRIVATE);
//        String name = sharedPreferences.getString(KEY_NAME, null);
//

//        textView1.setText(name);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu2, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_profile1:
                break;
            case R.id.menu_logout1:
                showDialoge();
                break;
        }
        return true;
    }
    private void showDialoge(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences sharedPreferences = getSharedPreferences(NEW_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();

                Intent intent3 = new Intent(TeacherLogoutActivity.this,TeacherLoginActivity.class);
                startActivity(intent3);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}





