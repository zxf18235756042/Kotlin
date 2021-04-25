package com.kotion.mydemo;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class ImageLoader {

    @BindingAdapter({"imgUrl"})
    public static void loadImg(ImageView img,String url){
        Log.i("TAG",url);
    }

}
