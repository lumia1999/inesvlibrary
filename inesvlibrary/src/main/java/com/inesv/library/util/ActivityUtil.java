package com.inesv.library.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by lumia on 2017/8/30.
 * <p>
 * activity相关工具类
 */

public class ActivityUtil {
    /**
     * activity跳转
     *
     * @param context
     * @param activity
     */
    public static void jumpActivity(Context context, Class<?> activity) {
        jumpActivity(context, activity, null);
    }

    public static void jumpActivityForRersult(Activity activity, Class<?> cla, int code) {
        Intent intent = new Intent(activity, cla);
        activity.startActivityForResult(intent, code);
    }

    public static void jumpActivityForRersult(Activity activity, Class<?> cla, Bundle bundle, int code) {
        Intent intent = new Intent(activity, cla);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, code);
        // activity.overridePendingTransition();
    }

    /**
     * activity跳转
     *
     * @param context
     * @param activity
     */
    public static void jumpActivity(Context context, Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转到登录界面
     *
     * @param context
     * @param activity
     */
    public static void jumpLogin(Context context, Class<?> activity) {
        ActivityStackUtil.getScreenManager().clearAllActivity();
        jumpActivity(context, activity);
    }

    /**
     * activity跳转
     *
     * @param context
     * @param intent
     */
    public static void jumpActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    public static void getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        jumpActivity(context, localIntent);
    }
}
