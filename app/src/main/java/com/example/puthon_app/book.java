package com.example.puthon_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class book extends AppCompatActivity {

   TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        //Getting Text From Topics Class
        Intent i =getIntent();
        String receive_id=i.getStringExtra(Topics.send_id);

         PDFView pdfView=findViewById(R.id.pdfview);
         t=findViewById(R.id.title);

         if(receive_id.equals("1 ->PYTHON BASICS")){
             t.setText("PYTHON BASICS");
             pdfView.fromAsset("basic_of_python.pdf").load();
         }
        if(receive_id.equals("2 ->CONDITIONAL  AND LOOPING STATEMENTS")){
            t.setText("CONDITIONAL AND LOOPING STATEMENTS");
            t.setTextSize(13);
            pdfView.fromAsset("conditional_looping_statements.pdf").load();
        }
        if(receive_id.equals("3 ->FUNCTIONS")){
            t.setText("FUNCTIONS");
            pdfView.fromAsset("functions.pdf").load();
        }
        if(receive_id.equals("4 ->EXCEPTION HANDLING")){
            t.setText("EXCEPTION HANDLING");
            pdfView.fromAsset("Exceptions.pdf").load();
        }

        if(receive_id.equals("5 ->FILE HANDLING")){
            t.setText("FILE HANDLING");
            pdfView.fromAsset("File_Handling.pdf").load();
        }
        if(receive_id.equals("PYTHON BASIC BOOK")){
            t.setText("BASIC BOOK");
            pdfView.fromAsset("Basic_Book.pdf").load();
        }



    }

    //Go to Main Activity
    public void ClickBack(View view) {

        MainActivity.redirectActivity(this,MainActivity.class);
    }
}