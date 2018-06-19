package com.inesv.library.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioGroup;

import com.inesv.library.util.DisplayUtil;
import com.inesv.library.util.SelectorUtils;

public class TabbarButton extends android.support.v7.widget.AppCompatRadioButton {
    private Context context;

    public TabbarButton(Context context) {
        super(context);
        this.context = context;
        //设置布局参数
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT, 1.0f);
        layoutParams.setMargins(0, DisplayUtil.dip2px(context, 5), 0, 0);
        this.setLayoutParams(layoutParams);
        this.setButtonDrawable(null);
        // this.setTextSize();
    }

    public TabbarButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabbarButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TabbarButton setTextContent(String content) {
        this.setText(content);
        this.setGravity(Gravity.CENTER_HORIZONTAL);
        return this;
    }

    public TabbarButton setTextColor(int normalTextColor, int checkedTextColor) {
        SelectorUtils.setSelectorColor(this, getResources().getColor(normalTextColor), getResources().getColor(checkedTextColor));//设置颜色选择器
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public TabbarButton setDrawable(int normalResid, int checkedResId) {
        this.setCompoundDrawablePadding(DisplayUtil.dip2px(context, 5));
        SelectorUtils.setSelectorDrawable(this, getResources().getDrawable(normalResid), getResources().getDrawable(checkedResId));
        return this;
    }
}
