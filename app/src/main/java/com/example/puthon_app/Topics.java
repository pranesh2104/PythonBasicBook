package com.example.puthon_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Topics extends AppCompatActivity {

    public static final String send_id=" com.example.puthon_app.send_id";//It is used to Connect the two Activity(Using of This: one Activity but We can Load Number of PDF"S)
    DrawerLayout drawerLayout;
    TextView ba,co,fu,ex,fh;
    String a,b,c,d,e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        drawerLayout =findViewById(R.id.drawer_layout);
        ba=findViewById(R.id.basic);
        co=findViewById(R.id.conditional);
        fu=findViewById(R.id.fun);
        ex=findViewById(R.id.exception);
        fh=findViewById(R.id.filehand);
    }



    public void ClickBack(View view){

        MainActivity.redirectActivity(this,MainActivity.class);
    }
    //Basic
    public void python_basic(View view) {

        Intent i=new Intent(this,book.class);
        a= ba.getText().toString();//Get Text From XML File
        i.putExtra(send_id,a);//Send Text To Book Class
        startActivity(i);
    }
    //Conditional
    public void conditional(View view) {
        Intent i=new Intent(this,book.class);
        b= co.getText().toString();//Get Text From XML File
        i.putExtra(send_id,b);//Send Text To Book Class
        startActivity(i);

   }
   //Looping
    public void fun(View view) {
        Intent i=new Intent(this,book.class);
        c= fu.getText().toString();//Get Text From XML File
        i.putExtra(send_id,c);//Send Text To Book Class
        startActivity(i);

    }
    //Exception Handling
    public void excep(View view) {

        Intent i=new Intent(this,book.class);
        d= ex.getText().toString();//Get Text From XML File
        i.putExtra(send_id,d);//Send Text To Book Class
        startActivity(i);
    }
    //File Handling
    public void file(View view) {

        Intent i=new Intent(this,book.class);
        e= fh.getText().toString();//Get Text From XML File
        i.putExtra(send_id,e);//Send Text To Book Class
        startActivity(i);
    }

}
