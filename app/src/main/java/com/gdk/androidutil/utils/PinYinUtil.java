package com.gdk.androidutil.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * @author gdk
 * @date 2017/5/8
 */

public class PinYinUtil {
    /**
     * 将字符串中的中文提取首字母并转为小写
     *
     * @param inputString:
     * @return
     * 2015-01-08
     */
    public static String getPingYinShort(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        // 把字符串转化成字符数组
        char[] input = inputString.trim().toCharArray();
        StringBuffer shortString = new StringBuffer();
        try {
            String[] temp;
            // \\u4E00是unicode编码，判断是不是中文
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    // 将汉语拼音的全拼存到temp数组
                    temp = PinyinHelper.toHanyuPinyinStringArray(
                            input[i], format);
                    // 取拼音的第一个读音
                    shortString.append(temp[0].substring(0, 1).toLowerCase());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shortString.toString();
    }
}
