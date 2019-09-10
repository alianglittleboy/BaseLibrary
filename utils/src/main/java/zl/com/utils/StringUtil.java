/**
 * <p>
 * Copyright: Copyright (c) 2012
 * Company: ZTE
 * Description: 字符串工具类实现文件
 * </p>
 *
 * @Title StringUtil.java
 * @Package com.zte.iptvclient.android.common
 * @version 1.0
 * @author jamesqiao10065075
 * @date 2012-02-08
 * IPTV客户端Android通用功能包
 */

/** IPTV客户端Android通用功能包  */
package zl.com.utils;


import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:张亮
 * @Description： 汇集字符串处理相关的一些功能函数。
 * @Date:
 */
public final class StringUtil {

    /** 日志标签 */
    public static final String LOG_TAG = "StringUtil";

    /**********************************删除空格类型**********************************/
    /** 删除全部空格 */
    public static final int TYPE_REMOVE_SPACE_ALL = 0;
    /** 删除左边空格 */
    public static final int TYPE_REMOVE_SPACE_LEFT = 1;
    /** 删除右边空格 */
    public static final int TYPE_REMOVE_SPACE_RIGHT = 2;
    /** 删除两端空格 */
    public static final int TYPE_REMOVE_SPACE_BOTH = 3;

    /**
     * 不使用默认构造函数
     */
    private StringUtil() {

    }

    /**
     *  判断某个字符串是否已某个子字符串开头，当strTarget或strPrefix为null或空字符串的时候，返回false，其他根据实际情况返回。
     * @date 2012-4-16 
     * @param strTarget 待比较的目标字符串
     * @param strPrefix 待搜索的前缀字符串
     * @return 字符串是否已某个子字符串开头，当strTarget或strPrefix为null或空字符串的时候，返回false，其他根据实际情况返回。
     */
    public static boolean startsWith(String strTarget, String strPrefix) {
        if (isEmptyString(strTarget) || isEmptyString(strPrefix)) {
            return false;
        } else {
            return strTarget.startsWith(strPrefix);
        }
    }

    /**
     * 判空的依据是null或者空字符串
     * @date 2012-2-8 
     * @param strTarget 待判断的目标字符串
     * @return 如果是null或者是空字符串，则返回true，否则，返回false。
     */
    public static boolean isEmptyString(String strTarget) {
        return ((null == strTarget) || (0 == strTarget.length()));
    }

    /**
     *
     * 获取非空字符串，如果不为空，直接返回；为空则用""代替。
     * @author jamesqiao10065075
     * @date 2012-3-23 
     * @param strTarget 目标字符串
     * @return 如果目标字符串为null，则返回""；否则返回目标字符串。
     */
    public static String getStringNotNull(String strTarget) {
        if (null != strTarget) {
            return strTarget;
        } else {
            return "";
        }
    }

    /**
     *
     * getStringFromObject
     * <p>
     * Description: 从Object对象得到String
     * <p>
     * @date 2013年9月9日
     * @author Randy.Wang 10125301
     * @param object
     * @return
     */
    public static String getStringFromObject(Object object) {
        if (null == object) {
            return "";
        }
        return object.toString();
    }

    /**
     *
     * 判断两个字符串是否相同，依据是否都为null或者内容是否相同。
     * @date 2012-6-7 
     * @author jamesqiao10065075
     * @param strSource 字符串1
     * @param strTarget 字符串2
     * @return 如果都为null或者内容相同，则返回true；否则返回false。
     */
    public static boolean isSameString(String strSource, String strTarget) {
        //都不为null
        if (null != strSource && null != strTarget) {
            return strSource.equals(strTarget);
        }
        //两个都为null
        else if (null == strSource && null == strTarget) {
            return true;
        } else
        //有一个为null
        {
            return false;
        }
    }

    /**
     *
     * 获取拆分后指定位置的单个字符串，根据分隔符拆分字符串，并返回对应位置的字符串
     * @author jamesqiao10065075
     * @date 2012-3-23 
     * @param strSrc 用分隔符分割的原始字符串
     * @param strSeperator 分隔符
     * @param iPos 指定位置（基于0）
     * @return 对应位置的字符串，如果入参非法，则返回null;如果分隔符之间无内容，返回空串。
     */
    public static String getSplitedString(String strSrc, String strSeperator, int iPos) {
        Log.d(LOG_TAG, "strSrc=" + strSrc + ",strSeperator=" + strSeperator + ",iPos="
                + iPos);

        if (null == strSrc) {
            Log.w(LOG_TAG, "strSrc is null!");
            return null;
        } else if ("".equals(strSrc) || isEmptyString(strSeperator)) {
            Log.w(LOG_TAG, "strSrc is empty or strSeperator is empty!");
            return strSrc;
        }

        String[] arStrings = splitString(strSrc, strSeperator);

        Log.d(LOG_TAG, "length=" + arStrings.length);
        if (iPos >= 0 && iPos < arStrings.length) {
            return getStringNotNull(arStrings[iPos]);
        } else {
            Log.w(LOG_TAG, "Pos out of range[0," + arStrings.length + "]");
            return null;
        }
    }

    /**
     *
     * 分隔字符串，使用指定分隔符分隔字符串，如果两个分隔符之间没有字符，则返回空串
     * @author jamesqiao10065075
     * @date 2012-3-23 
     * @param strTarget 源字符串
     * @param strSeperator 分隔符
     * @return 分隔后的字符串数组
     */
    public static String[] splitString(String strTarget, String strSeperator) {
        List<String> listRets = new ArrayList<String>();
        if (isEmptyString(strTarget) || isEmptyString(strSeperator)) {
            return (String[]) listRets.toArray(new String[1]);
        }

        //下一个分隔符位置
        int iLastPos = 0;
        int iPos = strTarget.indexOf(strSeperator, iLastPos);
        if (iPos >= 0) {
            while (iPos >= 0) {
                if (iPos > iLastPos) {
                    listRets.add(strTarget.substring(iLastPos, iPos));
                } else
                //两个分隔符紧邻
                {
                    listRets.add("");
                }

                iLastPos = iPos + 1;

                iPos = strTarget.indexOf(strSeperator, iLastPos);
            }

            //最后一段
            if (iLastPos <= (strTarget.length() - 1)) {
                listRets.add(strTarget.substring(iLastPos));
            }
            //最后一个
            else {
                listRets.add("");
            }
        } else
        //整体作为一个单元项
        {
            listRets.add(strTarget);
        }

        return (String[]) listRets.toArray(new String[1]);
    }

    /**
     *
     * 去除字符串中的两端的空格
     * @author jamesqiao10065075
     * @date 2012-3-27 
     * @param strSource 来源字符串
     * @return 去除空格后的字符串
     */
    public static String removeSpace(String strSource) {
        return removeSpace(strSource, TYPE_REMOVE_SPACE_BOTH);
    }

    /**
     *
     * 去除字符串中的空格， 支持删除全部空格、只删除左边空格、只删除右边空格，删除两端空格
     * @author jamesqiao10065075
     * @date 2012-3-27 
     * @param strSource 来源字符串
     * @param iRemoveType TYPE_REMOVE_SPACE_ALL等于全部，TYPE_REMOVE_SPACE_LEFT等于左边，
     *                    TYPE_REMOVE_SPACE_RIGHT等于右边，TYPE_REMOVE_SPACE_BOTH等于两端
     * @return 去除空格后的字符串
     */
    public static String removeSpace(String strSource, int iRemoveType) {
        if (isEmptyString(strSource)) {
            Log.w(LOG_TAG, "strSource is empty!");
            return "";
        }

        String strReturn = strSource;

        switch (iRemoveType) {
            case TYPE_REMOVE_SPACE_ALL: //去除全部空格
            {
                strReturn = strReturn.replace(" ", "");
                break;
            }
            case TYPE_REMOVE_SPACE_LEFT: //去除左边空格
            case TYPE_REMOVE_SPACE_RIGHT: //去除右边空格
            case TYPE_REMOVE_SPACE_BOTH: //去除两端空格
            {
                //去除左边或者两端空格
                if (TYPE_REMOVE_SPACE_BOTH == iRemoveType
                        || TYPE_REMOVE_SPACE_LEFT == iRemoveType) {
                    int i = 0;
                    int n = strReturn.length();
                    for (i = 0; i < n; i++) {
                        if (strReturn.charAt(i) != ' ') {
                            break;
                        }
                    }

                    if (i < n) {
                        strReturn = strReturn.substring(i);
                    } else {
                        strReturn = "";
                    }
                }
                //去除右边或者两端空格
                if (TYPE_REMOVE_SPACE_BOTH == iRemoveType
                        || TYPE_REMOVE_SPACE_RIGHT == iRemoveType) {
                    int i = 0;
                    int n = strReturn.length();
                    for (i = n - 1; i >= 0; i--) {
                        if (strReturn.charAt(i) != ' ') {
                            break;
                        }
                    }

                    if (i >= 0) {
                        strReturn = strReturn.substring(0, i + 1);
                    } else {
                        strReturn = "";
                    }
                }
                break;
            }
            default: //
            {
                break;
            }
        }

        return strReturn;
    }

    /**
     *
     * 整型值转换为字节
     * @date 2013-1-14 
     * @author jamesqiao10065075
     * @param iValue 整型值
     * @return
     */
    public static byte[] toLH(int iValue) {
        byte[] byData = new byte[4];

        byData[0] = (byte) (iValue & 0xff);
        byData[1] = (byte) (iValue >> 8 & 0xff);
        byData[2] = (byte) (iValue >> 16 & 0xff);
        byData[3] = (byte) (iValue >> 24 & 0xff);

        return byData;
    }

    /**
     *
     * 字节转换为字符串形式
     * @date 2013-1-14 
     * @author jamesqiao10065075
     * @param byData
     * @return
     */
    public static String bytesToString(byte[] byData) {
        if (null == byData) {
            Log.w(LOG_TAG, "byData is null!");
            return null;
        }
        StringBuffer sbResult = new StringBuffer("");
        int iLength = byData.length;
        for (int i = 0; i < iLength; i++) {
            sbResult.append((char) (byData[i] & 0xff));
        }

        return sbResult.toString();
    }

    /**
     * 去除字符串里括号之间的内容
     * @date 2012-11-6 
     * @author zte
     * @param strTarget 原始字符串
     * @return 去除以后的字符串
     */
    public static String trimContentBetweenBrackets(String strTarget) {
        if (StringUtil.isEmptyString(strTarget)) {
            Log.w(LOG_TAG, "strTarget is empty!");
            return "";
        }

        return strTarget.replaceAll("\\(.*\\)", "");
    }

    /**
     *
     * 字符串中关键字匹配后，使用特殊颜色高亮显示，若有多个关键字对应多种颜色高亮，则
     *      将结果SpannableString再次作为参数传入函数中,此时strSrc将不起作用.
     * @author juncc
     * @date 2013-1-15 
     * @param strSrc 源字符串
     * @param strPattern 关键字字符串,允许使用正则表达式
     * @param iColor 匹配后,关键字所显示的颜色,可以使用Color类中的颜色常量
     * @return 若strSrc 与 dstSpannable均为空，则返回null，否则，返回包含特定规则的SpannableString实例
     */
    public static SpannableString setTextColor(String strSrc, String strPattern,
                                               int iColor) {
        if (isEmptyString(strSrc)) {
            return null;
        }

        return setTextColor(new SpannableString(strSrc), strPattern, iColor);
    }

    /**
     *
     * 字符串中关键字匹配后，使用特殊颜色高亮显示，若有多个关键字对应多种颜色高亮，则
     *      将结果SpannableString再次作为参数传入函数中,此时strSrc将不起作用.
     * @author juncc
     * @date 2013-1-15 
     * @param strPattern 关键字字符串,允许使用正则表达式
     * @param iColor 匹配后,关键字所显示的颜色,可以使用Color类中的颜色常量
     * @param dstSpannable 包含匹配规则的SpannableString实例
     * @return 若strSrc 与 dstSpannable均为空，则返回null，否则，返回包含特定规则的SpannableString实例
     */
    public static SpannableString setTextColor(SpannableString dstSpannable,
                                               String strPattern, int iColor) {
        //若s为空，使用strSrc创建一个新的SpannableString
        if (dstSpannable == null) {
            Log.w(LOG_TAG, "dstSpannable is null!");
            return null;
        }

        //若关键字为空，则返回s
        if (isEmptyString(strPattern)) {
            Log.w(LOG_TAG, "strPattern is empty!");
            return dstSpannable;
        }

        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(dstSpannable);

        //使所有关键字高亮显示
        while (matcher.find()) {
            int iS = matcher.start();
            int iE = matcher.end();

            ForegroundColorSpan span = new ForegroundColorSpan(iColor);

            dstSpannable.setSpan(span, iS, iE, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return dstSpannable;
    }

    /**
     *
     * 这里写方法名
     * <p>
     * Description: 这里用一句话描述这个方法的作用
     * <p>
     * @date 2014年12月20日
     * @author Administrator
     * @param strSrc
     * @param strPattern
     * @param iTextSize
     * @return
     */
    public static SpannableString setTextSize(String strSrc, String strPattern,
                                              int iTextSize) {
        if (isEmptyString(strSrc)) {
            Log.w(LOG_TAG, "strSrc is empty!");
            return null;
        }

        //若s为空，使用strSrc创建一个新的SpannableString
        SpannableString dstSpannable = new SpannableString(strSrc);

        return setTextSize(dstSpannable, strPattern, iTextSize);
    }

    /**
     *
     * 这里写方法名
     * <p>
     * Description: 这里用一句话描述这个方法的作用
     * <p>
     * @date 2014年12月20日
     * @author Administrator
     * @param
     * @param strPattern
     * @param iTextSize
     * @return
     */
    public static SpannableString setTextSize(SpannableString dstSpannable,
                                              String strPattern, int iTextSize) {
        if (null == dstSpannable) {
            Log.w(LOG_TAG, "dstSpannable is null!");
            return null;
        }

        //若关键字为空，则返回s
        if (isEmptyString(strPattern)) {
            return dstSpannable;
        }

        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(dstSpannable);

        //使所有关键字高亮显示
        while (matcher.find()) {
            int iS = matcher.start();
            int iE = matcher.end();

            AbsoluteSizeSpan span = new AbsoluteSizeSpan(iTextSize);

            dstSpannable.setSpan(span, iS, iE, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return dstSpannable;
    }

    /**
     *
     * 检查输入的邮箱地址是否合法  
     * <p>
     * Description: 检查输入的邮箱地址是否合法  
     * <p>
     * @date 2014年4月16日
     * @author jamesqiao10065075
     * @param email 邮箱地址
     * @return email合法，返回true，否则返回false。
     */
    public static boolean checkEmail(String email) {
        if (isEmptyString(email)) {
            return false;
        }

        boolean bIsValid = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            bIsValid = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOG_TAG, e.getMessage());
            bIsValid = false;
        }

        return bIsValid;
    }

    /**
     * ByteBuffer 转换 String 
     * @param buffer
     * @return
     */
    public static String getString(ByteBuffer buffer, String charsetName) {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try {
            charset = Charset.forName(charsetName);
            decoder = charset.newDecoder();
            // charBuffer = decoder.decode(buffer);//用这个的话，只能输出来一次结果，第二次显示为空
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    //使用正则表达式判断电话号码
    public static boolean isMobileNO(String tel) {
        Pattern p = Pattern.compile("^(13[0-9]|15([0-3]|[5-9])|14[5,7,9]|17[1,3,5,6,7,8]|18[0-9])\\d{8}$");
        Matcher m = p.matcher(tel);
        System.out.println(m.matches() + "---");
        return m.matches();
    }


    /**
     * 字符型转int型
     * @param str
     * @return
     */
    public static int getInt(String str) {
        int value = -1;
        if (isEmptyString(str)) {
            return value;
        }
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return value;
    }
}
