package com.inesv.library.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;


/**
 * Created by lumia on 2017/8/28.
 * <p>
 * glide图片加载工具类
 */

public class GlideUtil {
    private static int resId;

    public static void setPlaceholder(int resId) {
        GlideUtil.resId = resId;
    }

    private final static String TAG = "PicassoUtil";

    public static void loadImage(Context context, int resId, ImageView imageView) {
        Glide.with(context).load(resId).into(imageView);
    }

    public static void loadImage(Context context, Bitmap bitmap, ImageView imageView) {
        Glide.with(context).load(bitmap).asBitmap().into(imageView);
    }

    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        loadImage(context, imageUrl, -1, -1, -1, false, false, -1, imageView);
    }

    public static void loadImage(Context context, String imageUrl, int defaultRes, ImageView imageView) {
        loadImage(context, imageUrl, defaultRes, -1, -1, false, false, -1, imageView);
    }

    public static void loadImage(Context context, String imageUrl, int defaultRes, int errorRes, ImageView imageView) {
        loadImage(context, imageUrl, defaultRes, errorRes, -1, false, false, -1, imageView);
    }

    public static void loadImage(Context context, String imageUrl, int defaultRes, int errorRes, int mode, ImageView imageView) {
        loadImage(context, imageUrl, defaultRes, errorRes, -1, false, false, -1, imageView);
    }

    /**
     * @param context
     * @param imageUrl   图片url
     * @param defaultRes 默认占位图
     * @param errorRes   图片加载失败图
     * @param mode       加载模式
     * @param isGif      是否是gif
     * @param isCircle   是否是圆形图片
     * @param imageView  容器
     */
    public static void loadImage(Context context, String imageUrl, int defaultRes, int errorRes, int mode, boolean isGif, boolean isCircle, int round, ImageView imageView) {
        DrawableTypeRequest<String> drawableTypeRequest = Glide.with(context).load(imageUrl);
        if (defaultRes > 0) {
            drawableTypeRequest.placeholder(defaultRes);
        }
        if (errorRes > 0) {
            drawableTypeRequest.error(errorRes);
        }
        //加载模式
        if (mode == 0) {
            drawableTypeRequest.centerCrop();
        } else if (mode == 1) {
            drawableTypeRequest.fitCenter();
        }
        //gif
        if (isGif) {
            drawableTypeRequest.asGif();
        }
        //圆形图片
        if (isCircle) {
            drawableTypeRequest.transform(new GlideCircleTransform(context));
        }
        //圆角
        if (round > 0) {
            drawableTypeRequest.transform(new GlideRoundTransform(context, round));
        }
        drawableTypeRequest.into(imageView);
    }

    public static void loadCircleImage(Context context, String imageUrl, ImageView imageView) {
        loadCircleImage(context, -1, -1, imageUrl, imageView);
    }

    public static void loadCircleImage(Context context, int defaultRes, int errorRes, String imageUrl, ImageView imageView) {
        loadImage(context, imageUrl, defaultRes, errorRes, -1, false, true, -1, imageView);
    }

    public static void loadRoundImage(Context context, String imageUrl, int round, ImageView imageView) {
        loadImage(context, imageUrl, resId, resId, -1, false, false, round, imageView);
    }
}
