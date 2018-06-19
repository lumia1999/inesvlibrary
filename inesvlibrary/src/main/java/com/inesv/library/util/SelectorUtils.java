package com.inesv.library.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.CompoundButton;

public class SelectorUtils {

    /**
     * 设置底部tab文字颜色
     *
     * @paramradioButton控件
     * @paramnormal正常时的颜色值
     * @paramchecked选中时的颜色值
     */
    public static void setSelectorColor(CompoundButton compoundButton, int normal, int checked) {
        int[] colors = new int[]{normal, checked, normal};
        int[][] states = new int[3][];
        states[0] = new int[]{-android.R.attr.state_checked};
        states[1] = new int[]{android.R.attr.state_checked};
        states[2] = new int[]{};
        ColorStateList colorStateList = new ColorStateList(states, colors);
        compoundButton.setTextColor(colorStateList);
    }

    /**
     * 设置底部tab图标
     *
     * @paramradioButton控件
     * @paramdrawableNormal常态时的图片
     * @paramdrawableSelect选中时的图片
     */

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void setSelectorDrawable(CompoundButton compoundButton, Drawable drawableNormal, Drawable drawableSelect) {
        StateListDrawable drawable = new StateListDrawable();
        //选中
        drawable.addState(new int[]{android.R.attr.state_checked}, drawableSelect);
        //未选中
        drawable.addState(new int[]{-android.R.attr.state_checked}, drawableNormal);
        compoundButton.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
    }
}
