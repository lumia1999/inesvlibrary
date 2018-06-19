package com.inesv.library.util;

/**
 * Created by chan on 2016/11/9.
 * <p>
 * 日志工具类
 */

import android.text.TextUtils;
import android.util.Log;

import java.util.List;


public class LogUtil {
    private static boolean isShowLog = false;

    /**
     * 设置是否打印log
     *
     * @param isShowLog
     */
    public static void isShowLog(boolean isShowLog) {
        LogUtil.isShowLog = isShowLog;
    }

    /**
     * 日志信息打印
     */
    public static void showLog(String tag, String msg) {
        if (isShowLog) {
            Log.v(tag, msg);
        }
    }

    /**
     * 日志输出级别NONE
     */
    public static final int LEVEL_NONE = 0;
    /**
     * 日志输出级别V
     */
    public static final int LEVEL_VERBOSE = 1;
    /**
     * 日志输出级别D
     */
    public static final int LEVEL_DEBUG = 2;
    /**
     * 日志输出级别I
     */
    public static final int LEVEL_INFO = 3;
    /**
     * 日志输出级别W
     */
    public static final int LEVEL_WARN = 4;
    /**
     * 日志输出级别E
     */
    public static final int LEVEL_ERROR = 5;
    /**
     * 是否允许输出log
     */
    private static int mDebuggable = LEVEL_ERROR;
    /**
     * 用于记时的变量
     */
    private static long mTimestamp = 0;
    /**
     * 写文件的锁对象
     */
    private static final Object mLogLock = new Object();

    /**
     * 以级别为 d 的形式输出LOG
     */
    public static void v(String mTag, String msg) {
        if (mDebuggable >= LEVEL_VERBOSE && isShowLog) {
            Log.v(mTag, msg);
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    public static void d(String mTag, String msg) {
        if (mDebuggable >= LEVEL_DEBUG && isShowLog) {
            Log.d(mTag, msg);
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     */
    public static void i(String mTag, String msg) {
        if (mDebuggable >= LEVEL_INFO && isShowLog) {
            Log.i(mTag, msg);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG
     */
    public static void w(String mTag, String msg) {
        if (mDebuggable >= LEVEL_WARN && isShowLog) {
            Log.w(mTag, msg);
        }
    }

    /**
     * 以级别为 w 的形式输出Throwable
     */
    public static void w(String mTag, Throwable tr) {
        if (mDebuggable >= LEVEL_WARN && isShowLog) {
            Log.w(mTag, "", tr);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG信息和Throwable
     */
    public static void w(String mTag, String msg, Throwable tr) {
        if (mDebuggable >= LEVEL_WARN && null != msg && isShowLog) {
            Log.w(mTag, msg, tr);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */
    public static void e(String mTag, String msg) {
        if (mDebuggable >= LEVEL_ERROR && isShowLog) {
            Log.e(mTag, msg);
        }
    }

    /**
     * 以级别为 e 的形式输出Throwable
     */
    public static void e(String mTag, Throwable tr) {
        if (mDebuggable >= LEVEL_ERROR && isShowLog) {
            Log.e(mTag, "", tr);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    public static void e(String mTag, String msg, Throwable tr) {
        if (mDebuggable >= LEVEL_ERROR && null != msg && isShowLog) {
            Log.e(mTag, msg, tr);
        }
    }

    /**
     * 以级别为 e 的形式输出msg信息,附带时间戳，用于输出一个时间段起始点
     *
     * @param msg 需要输出的msg
     */
    public static void msgStartTime(String mTag, String msg) {
        mTimestamp = System.currentTimeMillis();
        if (!TextUtils.isEmpty(msg)) {
            e(mTag, "[Started：" + mTimestamp + "]" + msg);
        }
    }

    /**
     * 以级别为 e 的形式输出msg信息,附带时间戳，用于输出一个时间段结束点* @param msg 需要输出的msg
     */
    public static void elapsed(String mTag, String msg) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - mTimestamp;
        mTimestamp = currentTime;
        e(mTag, "[Elapsed：" + elapsedTime + "]" + msg);
    }

    public static <T> void printList(String mTag, List<T> list) {
        if (list == null || list.size() < 1) {
            return;
        }
        int size = list.size();
        i(mTag, "---begin---");
        for (int i = 0; i < size; i++) {
            i(mTag, i + ":" + list.get(i).toString());
        }
        i(mTag, "---end---");
    }

    public static <T> void printArray(String mTag, T[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        int length = array.length;
        i(mTag, "---begin---");
        for (int i = 0; i < length; i++) {
            i(mTag, i + ":" + array[i].toString());
        }
        i(mTag, "---end---");
    }
}