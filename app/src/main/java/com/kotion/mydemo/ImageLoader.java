package com.kotion.mydemo;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoader {

    @BindingAdapter({"imgUrl"})
    public static void loadImg(ImageView img,String url){
        if (url!=null && !url.isEmpty()){
            Glide.with(img).load(url).into(img);
        }
    }



    @BindingAdapter({"imgUrl","corner"})
    public static void loadCornerImg(ImageView img,String url,int corner){
        if(url != null && !url.isEmpty()){
            RequestOptions options = new RequestOptions().circleCrop();
            Glide.with(img).load(url).apply(options).into(img);
        }

    }
}
