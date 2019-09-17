package com.easy.easycan.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.easy.easycan.R;
import com.easy.easycan.util.img.progress.EasyGlideApp;
import com.easy.easycan.util.img.progress.GlideRequest;
import com.easy.easycan.util.img.progress.GlideRequests;


/**
 * @auther mac
 * Date 2019-09-11
 */
public class ImageLoader {


    private static final int radius = SizeUtils.dp2px(Utils.getApp(),15);
    public static final String TAG = ImageLoader.class.getSimpleName();

    public static void loadImage(Context context, String url, ImageView view){
        EasyGlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.banner)
                .into(view);
    }

    private static boolean isDestroy(Context context) {
        if (context == null) {
            return true;
        }
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return true;
        }

        return false;
    }



    public static void loadRadiusImage(Context context, String url, ImageView view){
        loadRadiusImage(context,url,view,radius);
    }

    public static void loadRadiusImage(Context context, String url, ImageView view,int radius){
        GlideRequests glideRequests = EasyGlideApp.with(context);
        GlideRequest<Drawable> glideRequest = glideRequests.load(url);

        if (isDestroy(context)) {
            return;
        }
        if (TextUtils.isEmpty(url)){
            view.setBackgroundResource(R.drawable.banner);
            return;
        }
        if (url.endsWith("gif")) {
            EasyGlideApp.with(context)
                    .asGif()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(radius,0)))
                    .into(view);
        } else {
            EasyGlideApp.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.banner)
                    .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(radius,0)))
                    .into(view);
        }
    }

    public static void loadCircleImage(Context context, String url, ImageView view){
        loadRadiusImage(context,url,view,R.drawable.banner);
    }
    public static void loadCircleImage(Context context, String url, ImageView view, @DrawableRes int placeholder){
        EasyGlideApp.with(context)
                .load(url)
                .centerCrop()
                .circleCrop()
                .into(view);
    }
}
