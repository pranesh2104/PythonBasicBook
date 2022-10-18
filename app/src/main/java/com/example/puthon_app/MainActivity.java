package com.example.puthon_app;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String send_id=" com.example.puthon_app.send_id";//It is used to Connect the two Activity(Using of This: one Activity but We can Load Number of PDF"S)
    DrawerLayout drawerLayout;
    TextView doc,bas_book;
    String basic_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer_layout);
        doc=findViewById(R.id.document);
        bas_book=findViewById(R.id.basic_guide);


    }


    //Open The Drawer
    public void ClickMenu(View view){
        
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);
    }
    //Close The Drawer
    public  void ClickLogo(View view){
        
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }
    //Back
    public void ClickBack(View view){

        MainActivity.redirectActivity(this,MainActivity.class);
    }
    //Home
    public void ClickHome(View view){
        recreate();
    }
    //Topics
    public void ClickTopics(View view){

        redirectActivity(this,Topics.class);

    }
    //Share
    public  void ClickShare(View view){

        share();
    }

    private void share() {

        ApplicationInfo api=getApplicationContext().getApplicationInfo();
        String apkpath=api.publicSourceDir;
        Intent i= new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        Uri file=Uri.parse(apkpath);
//        i.putExtra(Intent.EXTRA_STREAM, file);
        i.putExtra(Intent.EXTRA_TEXT,"Click Here:  https://drive.google.com/file/d/1rmPt4BK89a-YThOl3Lc1zoXS7tye5kPD/view?usp=sharing");
        startActivity(Intent.createChooser(i,"Share app Using"));

    }
    //About
    public void ClickAbout(View view){

        setContentView(R.layout.activity_setting);
    }



    //Logout
    public void ClickLogout(View view){

        logout(this);
    }
    //Logout Function
    public static void logout(Activity activity) {

        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle("Logout");

        builder.setMessage("Are You Sure You Want To Logout ?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                activity.finishAffinity();
                System.exit(0);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity,Class aclass) {

        Intent i=new Intent(activity,aclass);
        //set Flags
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        activity.startActivity(i);
    }

   //Documentation and Search
    public void document(View view) {

        netWorkPermission(this);
    }
    //NetWork Permission For WebView
    private void netWorkPermission(Activity activity) {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle(" Network Permission");

        builder.setMessage("This link opens outside the app and needs Internet Connection.Are you sure you want to proceed?");

        builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

              dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                setContentView(R.layout.web_view);
                ProgressBar p=findViewById(R.id.progressbar);

                WebView webView=findViewById(R.id.we_view);
                webView.loadUrl("https://docs.python.org/3/tutorial/index.html");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        p.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        p.setVisibility(View.INVISIBLE);
                    }
                });

                webView.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {

                        if(keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                            switch (i){
                                case KeyEvent.KEYCODE_BACK:
                                    if(webView.canGoBack()){
                                        webView.goBack();
                                    }
                            }
                        }
                        return false;
                    }
                });

            }
        });
        builder.show();

    }
    // Python Download
    public void download(View view) {

        dowloadPermission(this);
    }
    //Python Download NetWork Permission
    private void dowloadPermission(Activity activity) {


        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle(" Network Permission");

        builder.setMessage("This link opens outside the app and needs Internet Connection.Are you sure you want to proceed?");
        //Permission Denied
        builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

               dialogInterface.dismiss();
            }
        });
        //Permission granded
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                setContentView(R.layout.web_view);
                ProgressBar p=findViewById(R.id.progressbar);

                WebView webView=findViewById(R.id.we_view);
                webView.loadUrl("https://www.python.org/downloads/");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        p.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        p.setVisibility(View.INVISIBLE);
                    }
                });

                webView.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {

                        if(keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                            switch (i){
                                case KeyEvent.KEYCODE_BACK:
                                    if(webView.canGoBack()){
                                        webView.goBack();
                                    }
                            }
                        }
                        return false;
                    }
                });

            }
        });
        builder.show();
    }


    public void ClickBook(View view) {

        Intent i=new Intent(this,book.class);
        basic_book= bas_book.getText().toString();//Get Text From XML File
        i.putExtra(send_id,basic_book);//Send Text To Book Class
        startActivity(i);


    }
}
