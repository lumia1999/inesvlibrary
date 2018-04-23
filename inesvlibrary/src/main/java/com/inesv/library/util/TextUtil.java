package com.inesv.library.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by lumia on 2017/8/31.
 * <p>
 * 文本工具类
 */

public class TextUtil {

    /**
     * 获取textView内容
     *
     * @param textView
     * @return
     */
    public static String getText(TextView textView) {
        String text = null;
        if (textView != null) {
            text = textView.getText().toString().replace(" ", "").trim();
        } else {
            return null;
        }
        return text;
    }

    /**
     * 是否显示edittext密码
     *
     * @param editText
     * @param isShow
     */
    public static void showPassword(EditText editText, boolean isShow) {
        if (isShow) {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    /**
     * 复制内容到粘贴板
     *
     * @param context
     * @param content
     */
    public static void clipContent(Context context, String content) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", content);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }

    //设置EditText可输入和不可输入状态
    public static void editTextable(EditText editText, boolean editable) {
        if (!editable) { // disable editing password
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            editText.setClickable(false); // user navigates with wheel and selects widget
        } else { // enable editing of password
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.setClickable(true);
        }
    }
}
