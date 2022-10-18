package com.example.puthon_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class slideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public slideAdapter(Context context){

        this.context=context;
    }

    //Slide Image Array
    public int[] slide_image={

            R.drawable.ic_favorite_24,
            R.drawable.python,
            R.drawable.ic_storage

    };
    //Slide Headings Array
    public String[] slide_heading={

            "THANK YOU",
            "PYTHON",
            "PERMISSION"

    };
    //Slide Description Array
    public String[] slide_des={
            "Thank You For Choosing Us!",
            "Here we see about Python in Easy Way!",
            "It Need Storage Permission Only!"
    };


    @Override
    public int getCount() {

        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout)object;
    }

    @NonNull
    @Override
    //it is Used to Change the Slide Image and Heading and Description.
    //if user Click the Next Button the content will change using Array's(Slide Image Array,Slide Headings Array,Slide Description Array)
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_image,container,false);

        ImageView imageView=view.findViewById(R.id.slide_images);
        TextView textView=view.findViewById(R.id.slide_title);
        TextView textView1=view.findViewById(R.id.slide_descr);

       imageView.setImageResource(slide_image[position]);
       textView.setText(slide_heading[position]);
       textView1.setText(slide_des[position]);

       container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);

    }
}
