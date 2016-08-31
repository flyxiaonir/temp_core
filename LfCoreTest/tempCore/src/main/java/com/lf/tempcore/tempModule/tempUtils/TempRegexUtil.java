package com.lf.tempcore.tempModule.tempUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Author：longf on 2015/11/16 13:50
 * Created by Administrator on 2015/11/16.
 */
public class TempRegexUtil {
    /**
     * 验证字符串是否为数字
     */
    public boolean isNumeric(String str) {

        return str.matches("(?:-?[1-9]\\d*\\.?\\d*)|-?(0\\.\\d*[1-9])");
    }

    /**
     * 验证Email地址是否正确
     *
     * @param str 验证字符串
     * @return 是否为Email
     */
    public boolean isEmail(String str) {
        return str
                .matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }

    /**
     * 验证两个日期是否为同一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public boolean isTheSameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
                && (c1.get(Calendar.DAY_OF_MONTH) == c2
                .get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 比较两个日期是否为同一月
     *
     * @param d1
     * @param d2
     * @return
     */
    public boolean isTheSameMonth(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH));
    }

    /**
     * 检验用户浏览权限
     *
     * @param uidList 允许浏览用户的id字符串 以","隔开
     * @param currUid 当前用户id
     * @param uid     发布者id
     * @return -1 该用户没有权限 0 仅自己可见 1 可以浏览
     */
    public int scanPermission(String uidList, int currUid, int uid) {
        if (currUid == uid) {
            return 1;
        }
        String rex = "(^-1$)|(^-1,)|(^%d$)|(^%d,)|(,%d,)|(,%d$)";
        rex = String.format(rex, currUid, currUid, currUid, currUid);
        if (uidList != null && uidList.matches(rex)) {
            return 1;
        }
        if ("0".equals(uidList)) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 验证当前连接是否为图片连接字符串
     *
     * @param photoUrl 验证图片连接
     * @return
     */
    public boolean isPhotoUrl(String photoUrl) {
        return photoUrl != null && !"".equals(photoUrl);
    }

    /**
     * 检查 email输入是否正确
     * 正确的书写格 式为 username@domain
     * @param value
     * @return
     */
    public static boolean checkEmail(String value, int length) {
        return value.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")&&value.length()<=length;
    }

    /**
     * 检查电话输入 是否正确
     * 正确格 式 012-87654321、0123-87654321、0123－7654321
     * @param value
     * @return
     */
    public static boolean checkTel(String value) {
        return value.matches("\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)");
    }

    /**
     * 检查手机输入 是否正确
     *
     * @param value
     * @return
     */
    public static boolean checkMobile(String value) {
        return value.matches("^[1][3,5,7,8]+\\d{9}");
    }

    /**
     * 检查中文名输 入是否正确
     *
     * @param value
     * @return
     */
    public static boolean checkChineseName(String value, int length) {
        return value.matches("^[\u4e00-\u9fa5]+$")&&value.length()<=length;
    }
    /**
     * 检查HTML 中首尾空行或空格
     * @param value
     * @return
     */
    public static boolean checkBlank(String value){
        return value.matches("^\\s*|\\s*$");
    }
    /**
     * 检查字符串是 否含有HTML标签
     * @param value
     * @return
     */

    public static boolean checkHtmlTag(String value){
        return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
    }
    /**
     * 检查URL是 否合法
     * @param value
     * @return
     */
    public static boolean checkURL(String value){
        return value.matches("[a-zA-z]+://[^\\s]*");
    }
    /**
     * 检查IP是否 合法
     * @param value
     * @return
     */
    public static boolean checkIP(String value){
        return value.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
    }
    /**
     * 检查ID是否 合法，开头必须是大小写字母，其他位可以有大小写字符、数字、下划线
     * @param value
     * @return
     */
    public static boolean checkID(String value){
        return value.matches("[a-zA-Z][a-zA-Z0-9_]{4,15}$");
    }
    /**
     * 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
     * @param value
     * @return
     */

    public static boolean checkQQ(String value){
        return value.matches("[1-9][0-9]{4,13}");
    }
    /**
     * 检查邮编是否 合法
     * @param value
     * @return
     */
    public static boolean checkPostCode(String value){
        return value.matches("[1-9]\\d{5}(?!\\d)");
    }
    /**
     * 检查身份证是 否合法,15位或18位
     * @param value
     * @return
     */
    public static boolean checkIDCard(String value){
        return value.matches("\\d{15}|\\d{18}");
    }
    /**
     * 检查输入是否 超出规定长度
     * @param length
     * @param value
     * @return
     */
    public static boolean checkLength(String value, int length) {
        return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length;
    }

    /**
     * 检查是否为空 字符串,空：true,不空:false
     *
     * @param value
     * @return
     */
    public static boolean checkNull(String value){
        return value == null || "".equals(value.trim());
    }

}
