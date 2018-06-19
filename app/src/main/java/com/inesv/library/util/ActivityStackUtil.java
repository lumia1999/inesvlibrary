package com.inesv.library.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by lumiachan on 2018/1/20.
 */

public class ActivityStackUtil {
    private static Stack<Activity> mActivityStack = new Stack<Activity>();
    private static ActivityStackUtil instance = new ActivityStackUtil();

    private ActivityStackUtil() {
    }

    public static ActivityStackUtil getScreenManager() {
        return instance;
    }

    // 弹出当前activity并销毁
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    // 将当前Activity推入栈中
    public void pushActivity(Activity activity) {
        mActivityStack.add(activity);
    }

    // 退出栈中所有Activity
    public void clearAllActivity() {
        while (!mActivityStack.isEmpty()) {
            Activity activity = mActivityStack.pop();
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
