package com.kotion.mydemo;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.kotion.mydemo.api.MyApp;
import com.kotion.mydemo.utils.ScreenUtils;

public class ImageLoader {
    /**
     * 1.加载图片,无需手动调用此方法
     * 2.使用@BindingAdapter注解设置自定义属性的名称，imageUrl就是属性的名称，
     * 当ImageView中使用imageUrl属性时，会自动调用loadImage方法，
     */
    @BindingAdapter({"imgUrl"})
    public static void loadImg(ImageView img,String url){
        if (url!=null && !url.isEmpty()){
            Glide.with(img).load(url).into(img);
        }
    }
    /**
     * 处理瀑布流图片加载高度自适应的问题
     * @param img
     * @param url 图片地址
     * @param column 当前列表的列
     */
    @BindingAdapter({"imgUrl","column"})
    public static void loadImage(ImageView img,String url,int column){
        ViewGroup.LayoutParams layoutParams=img.getLayoutParams();
        int itemWidth=(ScreenUtils.Companion.getSreenWidth(MyApp.Companion.getApp())-16*3)/column;
        layoutParams.width=itemWidth;
        Glide.with(img).load(url)
                .override(itemWidth, Target.SIZE_ORIGINAL)
                .fitCenter()
                .into(img);
    }



    @BindingAdapter({"imgUrl","corner"})
    public static void loadCornerImg(ImageView img,String url,int cornner){
        if(url != null && !url.isEmpty()){
            RequestOptions options = new RequestOptions().circleCrop();
            Glide.with(img).load(url).apply(options).into(img);
        }

    }
}
