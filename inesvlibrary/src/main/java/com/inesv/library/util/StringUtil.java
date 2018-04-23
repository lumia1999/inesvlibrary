package com.inesv.library.util;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chan on 2016/11/9.
 * <p>
 * 字符串工具类
 */

public class StringUtil {
    private final static String TAG = "StringUtil";

    /**
     * 公共正则字符串匹配
     *
     * @param reg            匹配规则正则表达式
     * @param matcherContent 需要匹配的内容
     * @return
     */
    public static boolean isCommonMatcher(String reg, String matcherContent) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(matcherContent);
        return m.matches();
    }

    /**
     * 检测手机号是否合法
     *
     * @param phoneNumber 手机号
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        String phoneReg = "^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0&3&6-8])|(14[5&7]))\\d{8}$";
        return isCommonMatcher(phoneReg, phoneNumber);
    }

    /**
     * 区号+座机号码+分机号码
     *
     * @param fixedPhone
     * @return
     */
    public static boolean isFixedPhone(String fixedPhone) {
        String reg = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
                "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        return Pattern.matches(reg, fixedPhone);
    }

    public static boolean isIDCard(String idcard) {
        String reg = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
        return Pattern.matches(reg, idcard);
    }

    public static boolean isEmail(String email) {
        String reg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return Pattern.matches(reg, email);
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || "null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean commonRegex(String content, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        return m.matches();
    }

    public static String formatDate(long time, String regex) {
        DateFormat df = new SimpleDateFormat(regex);
        return df.format(time);
    }

    public static String formatDate(long time) {
        DateFormat df = new SimpleDateFormat("MM-dd hh:mm");
        return df.format(time);
    }

    public static String formatCurrentDate() {
        return formatDate(System.currentTimeMillis(), "yyyy-MM-dd hh:mm:ss");
    }

    public static String getSubString(Activity activity, TextView tv, String content, int maxLine) {
        float width = tv.getPaint().measureText(content);
        //这里只是为了方便，用屏幕宽度代替了textview控件宽度，如果需要精准控制，可以换成控件宽度
        float tvWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
        if (width / tvWidth > (maxLine - 0.5)) {
            return content.substring(0, (int) (content.length() / (width / tvWidth))) + "...";
        }
        LogUtil.e(TAG, "width:" + width + "width / tvWidth:" + width / tvWidth);
        return content;
    }

    /**
     * 字符串是否有内容
     *
     * @param content
     * @return
     */
    public static boolean isNull(String content) {
        if (content == null || "".equals(content)) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] getTimeNumber() {
        int[] times = new int[6];
        Calendar c = Calendar.getInstance();
        times[0] = c.get(Calendar.YEAR);
        times[1] = c.get(Calendar.MONTH);
        times[2] = c.get(Calendar.DATE);
        times[3] = c.get(Calendar.HOUR_OF_DAY);
        times[4] = c.get(Calendar.MINUTE);
        times[5] = c.get(Calendar.SECOND);
        return times;
    }

    /**
     * 判断字符串是否是整数
     *
     * @param s
     * @return
     */
    public static boolean isInt(String s) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher((CharSequence) s);
        return matcher.matches();
    }

    /**
     * 提取字符串数字索引
     *
     * @param strInput
     * @return
     */
    public static List<int[]> getNumberIndexFromStr(String strInput) {
        List<int[]> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d{1,}");
        Matcher matcher = pattern.matcher(strInput);
        int index = 0;
        while (matcher.find()) {
            int[] i = new int[2];
            String value = matcher.group(0);
            int start = strInput.indexOf(value, index);
            int end = start + value.length();
            index = start + 1;
            i[0] = start;
            i[1] = end;
            list.add(i);
        }
        return list;
    }

    /**
     * 网页版地图  只有地址
     *
     * @param addr
     * @return
     */
    public static String getHtmlAddressUrl(String addr) {
        String strUrl = "http://api.map.baidu.com/geocoder?address=%1$s&output=html";
        return String.format(strUrl, addr);
    }

    /**
     * 校验数据是否合法，不合法就提示
     *
     * @param context
     * @param content 需要校验的内容
     * @param tip     检验不通过的提示语
     */
    public static void checkData(Context context, String content, String tip) {
        if (isEmpty(content)) {
            ToastUtil.showShortToast(context, tip);
            return;
        }
    }

    /**
     * 比对两个数据是否一致，不一致就提示
     *
     * @param context
     * @param content
     * @param againContent
     * @param tip          不一致提示语
     */
    public static void checkData(Context context, String content, String againContent, String tip) {
        if (content == null) {
            return;
        }
        if (!content.equals(againContent)) {
            ToastUtil.showShortToast(context, tip);
            return;
        }
    }

    public static String getTime(Date date) {
        return getTime(date, "yyyy-MM-dd");
    }

    public static String getTime(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static String getCurrentDate() {
        return getCurrentDate("yyyy-MM-dd-hh-mm-ss");
    }

    public static String getCurrentDate(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(System.currentTimeMillis());
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DATE);
    }


    public static String getFommatNumber(double number) {
        DecimalFormat df = new DecimalFormat("######0.00000000");
        return df.format(number);
    }

    public static String checkData(Context context, String notice, TextView textView) {
        String content = TextUtil.getText(textView);
        if (isEmpty(content)) {
            if (!isEmpty(notice)) {
                ToastUtil.showShortToast(context, notice);
            }
            breakCode(content);
            return null;
        } else {
            return content;
        }
    }

    private static void breakCode(String content) {
        if (isEmpty(content)) {
            return;
        }
    }
}
