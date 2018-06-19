package com.inesv.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.inesv.library.R;
import com.inesv.library.util.ShapeUtil;
import com.inesv.library.util.TextUtil;


/**
 * Created by lumiachan on 2018/3/13.
 * <p>
 * 发送验证码倒计时控件
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class CountDownView extends android.support.v7.widget.AppCompatTextView {
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
    /**
     * 未开始时候的文本
     */
    private String textContent;
    /**
     * 倒计时时间
     */
    private int countdownNumber;
    private final String TAG = "CountDownView";
    /**
     * 倒计时
     */
    private CountDownTimer countDownTimer;
    private Context context;


    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.inesv);
        textContent = TextUtil.getText(this);
        countdownNumber = typedArray.getInt(R.styleable.inesv_countdownNumber, 0);
        roundRadius = typedArray.getInt(R.styleable.inesv_roundRadius, 0);
        strokeWidth = typedArray.getInt(R.styleable.inesv_strokeWidth, 0);
        fillColor = typedArray.getColor(R.styleable.inesv_fillColor, Color.WHITE);
        strokeColor = typedArray.getColor(R.styleable.inesv_strokeColor, Color.WHITE);
        textColor = typedArray.getColor(R.styleable.inesv_textColor, Color.WHITE);
        unstrokeWidth = typedArray.getInt(R.styleable.inesv_unstrokeWidth, 0);
        unfillColor = typedArray.getColor(R.styleable.inesv_unfillColor, Color.WHITE);
        unstrokeColor = typedArray.getColor(R.styleable.inesv_unstrokeColor, Color.WHITE);
        untextColor = typedArray.getColor(R.styleable.inesv_untextColor, Color.WHITE);
        this.context = context;
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        this.setText(textContent);
        CountDownView.this.setBackground(ShapeUtil.commonColorShape(context, roundRadius, unstrokeWidth, unfillColor, unstrokeColor));
        countDownTimer = new CountDownTimer(countdownNumber * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                CountDownView.this.setText(millisUntilFinished / 1000 + " s");
                CountDownView.this.setClickable(false);
                CountDownView.this.setTextColor(textColor);
                CountDownView.this.setBackground(ShapeUtil.commonColorShape(context, roundRadius, strokeWidth, fillColor, strokeColor));
            }

            @Override
            public void onFinish() {
                CountDownView.this.setText(textContent);
                CountDownView.this.setClickable(true);
                CountDownView.this.setTextColor(untextColor);
                CountDownView.this.setBackground(ShapeUtil.commonColorShape(context, roundRadius, unstrokeWidth, unfillColor, unstrokeColor));
            }
        };
    }

    /**
     * 开始倒计时
     */
    public void startCountDownView() {
        countDownTimer.start();
    }

    /**
     * 取消倒计时
     */
    public void cancelCountDownView() {
        countDownTimer.cancel();
    }
}
