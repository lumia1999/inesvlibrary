package com.inesv.library.util;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by lumia on 2017/9/7.
 */

public class ShapeUtil {

    /**
     * @param context
     * @param roundRadius   圆角大小
     * @param strokeWidth   线宽度
     * @param fillColorId   填充色
     * @param strokeColorId 线颜色
     * @return
     */
    public static GradientDrawable commonShape(Context context, int roundRadius, int strokeWidth, int fillColorId, int strokeColorId) {
        int strokeColor = 0;
        if (strokeColorId > 0) {
            strokeColor = context.getResources().getColor(strokeColorId);//边线颜色
        }
        GradientDrawable gd = new GradientDrawable();//创建drawable
        if (fillColorId > 0) {
            int fillColor = context.getResources().getColor(fillColorId);//内部填充颜色
            gd.setColor(fillColor);
        }
        gd.setCornerRadius(DisplayUtil.dip2px(context, roundRadius));
        if (strokeWidth > 0) {
            gd.setStroke(strokeWidth, strokeColor);
        }
        return gd;
    }

    /**
     * @param context
     * @param roundRadius   圆角大小 4是左下角  3右下角  2 右上角  1 左上角
     * @param strokeWidth   线宽度
     * @param fillColorId   填充色
     * @param strokeColorId 线颜色
     * @return
     */
    public static GradientDrawable commonShape(Context context, float[] roundRadius, int strokeWidth, int fillColorId, int strokeColorId) {
        float[] roundRadiuss = new float[8];
        int strokeColor = 0;
        if (strokeColorId > 0) {
            strokeColor = context.getResources().getColor(strokeColorId);//边线颜色
        }
        GradientDrawable gd = new GradientDrawable();//创建drawable
        if (fillColorId > 0) {
            int fillColor = context.getResources().getColor(fillColorId);//内部填充颜色
            gd.setColor(fillColor);
        }
        if (roundRadius != null) {
            for (int i = 0; i < roundRadius.length; i++) {
                roundRadius[i] = DisplayUtil.dip2px(context, roundRadius[i]);
                roundRadiuss[((i + 1) * 2) - 1] = roundRadius[i];
                roundRadiuss[((i + 1) * 2) - 2] = roundRadius[i];
            }
            gd.setCornerRadii(roundRadiuss);
        }
        if (strokeWidth > 0) {
            gd.setStroke(strokeWidth, strokeColor);
        }
        return gd;
    }

    /**
     * @param context
     * @param roundRadius 圆角大小 圆角大小 4是左下角  3右下角  2 右上角  1 左上角
     * @param strokeWidth 线宽度
     * @param fillColor   填充色
     * @param strokeColor 线颜色
     * @return
     */
    public static GradientDrawable commonColorShape(Context context, int roundRadius, int strokeWidth, int fillColor, int strokeColor) {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        if (fillColor != 0) {
            gd.setColor(fillColor);
        }
        gd.setCornerRadius(DisplayUtil.dip2px(context, roundRadius));
        if (strokeWidth != 0) {
            gd.setStroke(strokeWidth, strokeColor);
        }
        return gd;
    }

    /**
     * @param context
     * @param roundRadius 圆角大小
     * @param strokeWidth 线宽度
     * @param fillColor   填充色
     * @param strokeColor 线颜色
     * @return
     */
    public static GradientDrawable commonColorShape(Context context, float[] roundRadius, int strokeWidth, int fillColor, int strokeColor) {
        float[] roundRadiuss = new float[8];
        GradientDrawable gd = new GradientDrawable();//创建drawable
        if (fillColor != 0) {
            gd.setColor(fillColor);
        }
        if (roundRadius != null) {
            for (int i = 0; i < roundRadius.length; i++) {
                roundRadius[i] = DisplayUtil.dip2px(context, roundRadius[i]);
                roundRadiuss[((i + 1) * 2) - 1] = roundRadius[i];
                roundRadiuss[((i + 1) * 2) - 2] = roundRadius[i];
            }
            gd.setCornerRadii(roundRadiuss);
        }
        if (strokeWidth != 0) {
            gd.setStroke(strokeWidth, strokeColor);
        }
        return gd;
    }
}
