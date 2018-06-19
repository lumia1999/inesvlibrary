package com.inesv.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.inesv.library.R;
import com.inesv.library.util.ShapeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lumiachan on 2018/3/8.
 * <p>
 * 检测所在页面的所有的输入框都有值之后按钮会做出改变
 */

public class VisibleButton extends android.support.v7.widget.AppCompatTextView {
    private Map<Integer, Boolean> states;
    private List<TextView> editTexts;
    private int position;
    /**
     * 选中的时候边框宽度
     */
    private int strokeWidth;
    /**
     * 选中时候的填充色
     */
    private int fillColor;
    /**
     * 选中时候边框颜色
     */
    private int strokeColor;
    /**
     * 选中时候字体颜色
     */
    private int textColor;
    /**
     * 未选中的时候边框宽度
     */
    private int unstrokeWidth;
    /**
     * 未选中时候的填充色
     */
    private int unfillColor;
    /**
     * 未选中时候边框颜色
     */
    private int unstrokeColor;
    /**
     * 未选中时候字体颜色
     */
    private int untextColor;
    /**
     * 圆角
     */
    private int roundRadius;
    private Context context;
    private final String TAG = "ChangeButton";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public VisibleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        editTexts = new ArrayList<>();
        states = new HashMap<>();
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.inesv);
//        roundRadius = typedArray.getInt(R.styleable.inesv_roundRadius, 0);
//        strokeWidth = typedArray.getInt(R.styleable.inesv_strokeWidth, 0);
//        fillColor = typedArray.getColor(R.styleable.inesv_fillColor, Color.WHITE);
//        strokeColor = typedArray.getColor(R.styleable.inesv_strokeColor, Color.WHITE);
//        textColor = typedArray.getColor(R.styleable.inesv_textColor, Color.WHITE);
//        unstrokeWidth = typedArray.getInt(R.styleable.inesv_unstrokeWidth, 0);
//        unfillColor = typedArray.getColor(R.styleable.inesv_unfillColor, Color.WHITE);
//        unstrokeColor = typedArray.getColor(R.styleable.inesv_unstrokeColor, Color.WHITE);
//        untextColor = typedArray.getColor(R.styleable.inesv_untextColor, Color.WHITE);
        //初始化为未选中颜色
       // setBackground(ShapeUtil.commonColorShape(context, roundRadius, unstrokeWidth, unfillColor, unstrokeColor));
       // this.setTextColor(untextColor);
    }

    /**
     * 添加需要监控的文本框
     *
     * @param editText
     */
    public void addTextView(TextView editText) {
        setClickable(false);//因为onclicklistener会重写setClickable(),导致初始化设置的setClickable()无效，所以在这里重复调用!
        editTexts.add(editText);
        states.put(position, false);
        for (int j = 0; j < editTexts.size(); j++) {
            final int currentPosition = j;
            editTexts.get(j).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if (charSequence.length() > 0) {
                        states.put(currentPosition, true);
                    } else {
                        states.put(currentPosition, false);
                    }
                    if (checkAllTrue(states)) {//校验通过
                        VisibleButton.this.setVisibility(View.VISIBLE);
                    } else {//校验不通过
                        VisibleButton.this.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
        position += 1;
    }

    /**
     * 判断是否所有输入框都已经有值
     *
     * @param states
     * @return
     */
    private boolean checkAllTrue(Map<Integer, Boolean> states) {
        Collection<Boolean> c = states.values();
        int state = 0;
        for (Boolean b : c) {
            if (b) {
                state += 1;
            }
        }
        if (state < c.size()) {
            return false;
        } else {
            return true;
        }
    }
}
