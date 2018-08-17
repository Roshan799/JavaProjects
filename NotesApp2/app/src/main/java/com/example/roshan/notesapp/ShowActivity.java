package com.example.roshan.notesapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    TextView show;
    DataBaseSql db;
    PopupWindow popupWindow;
    ImageView d,ed;
    EditText edt;
    String nd;
    Button b,ys,nt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        final View parent=findViewById(R.id.show_act);
        show = (TextView) findViewById(R.id.textView5);
        d=(ImageView) findViewById(R.id.delete);
        ed=(ImageView)findViewById(R.id.edit);
        db=new DataBaseSql(this);



        Intent receiveIntent = ShowActivity.this.getIntent();
        final String k=receiveIntent.getStringExtra("title");
        Cursor res = db.getData(k);
        final StringBuffer buffer = new StringBuffer();
        if (res.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
        } else {

            while (res.moveToNext())
            {
                buffer.append(res.getString(0));
            }
        }
        show.setText(buffer);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) ShowActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.delete_popup,null);
                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
                ys=(Button)customView.findViewById(R.id.yes);
                nt=(Button)customView.findViewById(R.id.no);
                ys.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows=db.deleteData(k);
                        if(deletedRows!=0)
                        {
                            Toast.makeText(getApplicationContext(),"File deleted",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"File deleted",Toast.LENGTH_LONG).show();
                        }
                        popupWindow.dismiss();
                        Intent in=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(in);
                        finish();
                    }
                });
                nt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });

            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setText("");
                LayoutInflater layoutInflater = (LayoutInflater) ShowActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.edit_popup,null);

                //instantiate popup window
                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                //display the popup window
                popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                edt=(EditText)customView.findViewById(R.id.new_data);
                b=(Button)customView.findViewById(R.id.conf_btn);
                edt.append(buffer);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nd=edt.getText().toString();
                        boolean isupdated=db.updateData(k,nd);
                        if(isupdated)
                        {
                            Toast.makeText(getApplicationContext(),"Data updated",Toast.LENGTH_LONG).show();
                            popupWindow.dismiss();
                            Intent in=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(in);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Data not updated",Toast.LENGTH_LONG).show();
                            popupWindow.dismiss();
                            Intent in=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(in);
                            finish();
                        }

                    }
                });

            }
        });

    }

}
