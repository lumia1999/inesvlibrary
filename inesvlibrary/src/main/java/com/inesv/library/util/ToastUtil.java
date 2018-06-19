package com.inesv.library.util;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.inesv.library.R;


/**
 * Created by chan on 2016/11/9.
 * <p>
 * 自定义toast
 */

public class ToastUtil {
    private boolean mShowTime;
    private boolean mIsShow;
    private WindowManager mWdm;
    private View mToastView;


    public static void showToast(Context context, String text, int time) {
        boolean isMainThread = Looper.myLooper() == Looper.getMainLooper();
        if (!isMainThread) {
            Looper.prepare();
        }
        LayoutInflater mInflate = LayoutInflater.from(context);
        View view = mInflate.inflate(R.layout.layout_toast, null);
        TextView textView = view.findViewById(R.id.chapterName);
        textView.setText(text);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(time);
        toast.setView(view);
        toast.show();
        if (!isMainThread) {
            Looper.loop();
        }
    }

    public static void showShortToast(Context context, String text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context, String text) {
        showToast(context, text, Toast.LENGTH_LONG);
    }

    public void removeView() {
        if (mWdm != null && mToastView != null && mIsShow) {
            mWdm.removeView(mToastView);
            mIsShow = false;
        }
    }
}
