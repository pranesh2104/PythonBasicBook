package com.example.puthon_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;


import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


public class Slide extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private ViewPager slide_page;
    private LinearLayout dot_page;

    TextView[] dots;

    int current_page;

    slideAdapter slideAdapter;
    Button pre;
    Button nex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slide);
        //Once A permission Granded Then User Again Open App the First Slide Only Will Show Only For 1 second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(EasyPermissions.hasPermissions(Slide.this,perms)){
                    MainActivity.redirectActivity(Slide.this,MainActivity.class);
                    finish();
                }
            }
        },750);

        slide_page=findViewById(R.id.slide_layout);
        dot_page=(LinearLayout) findViewById(R.id.dot_layout);


        pre=findViewById(R.id.previous);
        nex=findViewById(R.id.next);

        slideAdapter=new slideAdapter(this);

        slide_page.setAdapter(slideAdapter);
        dotIndicator(0);
        slide_page.addOnPageChangeListener(listener);

        //IF USer Click Next Button Go To Next Slide
        nex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slide_page.setCurrentItem(current_page + 1);

                if(current_page == dots.length-1){
                    storage();
                }
            }
        });

        //IF user Click Back Button Go To Previous Slide
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slide_page.setCurrentItem(current_page - 1);

            }
        });

    }

    //It Is Used To Indicate The Which SLide is Currently Present Using Dot
    public  void dotIndicator(int position){

     dots =new TextView[3];
     dot_page.removeAllViews();
     for(int i=0;i<dots.length;i++){

         dots[i]=new TextView(this);
         dots[i].setText(Html.fromHtml("&#8226;"));
         dots[i].setTextSize(35);
         dots[i].setTextColor(Color.parseColor("#636363"));

         dot_page.addView(dots[i]);

        }
    //Curent SLide Dot Color
     if(dots.length > 0){

         dots[position].setTextColor(Color.parseColor("#FFFFFF"));

     }
    }
    // It is Used To Listen The Slide Page And Connect the Button's(Back and Next) and Dot
    ViewPager.OnPageChangeListener listener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            dotIndicator(position);

            current_page=position;
            //First Slide
            if(position==0){
                nex.setEnabled(true);
                pre.setEnabled(false);
                pre.setVisibility(View.INVISIBLE);

                nex.setText("Next");
                pre.setText("");
            }
            //Last Slide
            else if(position== dots.length-1){

                nex.setEnabled(true);
                pre.setEnabled(true);
                pre.setVisibility(View.VISIBLE);

                nex.setText("Finish");
                pre.setText("Back");
            }
            //Center Slide
            else {
                nex.setEnabled(true);
                pre.setEnabled(true);
                pre.setVisibility(View.VISIBLE);

                nex.setText("Next");
                pre.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    //User Reach The Last Slide Then Click The Finish Button it will Ask The Storage Permission
    String perms= Manifest.permission.READ_EXTERNAL_STORAGE;

    public void storage(){
        //IF user Give Granded then it will go Main Activity
        if(EasyPermissions.hasPermissions(Slide.this,perms)){

            MainActivity.redirectActivity(Slide.this,MainActivity.class);
            Toast.makeText(Slide.this,"Thank You", Toast.LENGTH_LONG).show();

        }
        //If user Denied Then it Will Show the AlertDialog
        else{
            EasyPermissions.requestPermissions(Slide.this,"App Needs Storage!!",
                    102,perms);

        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {


    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

        //IF user Continously denied the permission it will go to Phone Setting of App
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){

            new AppSettingsDialog.Builder(this).build().show();
        }
        //Indicate The each time Denied Permission
        else
        {
            Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
        }
    }


}