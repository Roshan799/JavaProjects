package com.example.roshan.notesapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    Button ok;
    EditText ttl,cn;
    DataBaseSql db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ok=(Button)findViewById(R.id.button);
        ttl=(EditText)findViewById(R.id.editText);
        cn=(EditText)findViewById(R.id.editText2);


        db=new DataBaseSql(this);
        Cursor r=db.getTitles();
        final ArrayList<String> arrayList=new ArrayList<String>();
        int i=0;

        while (r.moveToNext())
        {
            arrayList.add(i,r.getString(0));
            i++;
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t=ttl.getText().toString();
                String c=cn.getText().toString();
                if(t.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please insert title",Toast.LENGTH_LONG).show();
                }
                else if(arrayList.contains(t))
                {
                    Toast.makeText(getApplicationContext(),"Give different title",Toast.LENGTH_LONG).show();
                }
                else {
                    boolean isInserted = db.insertData(t, c);
                    if (isInserted) {
                        Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                    startActivity(new Intent(AddActivity.this,MainActivity.class));
                    finish();
                }
            }
        });
    }
}
