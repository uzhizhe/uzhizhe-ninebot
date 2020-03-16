package com.uzhizhe.ninebot.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.util.Arrays;
import java.util.List;

/**
 * @Desc string 工具类
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-11-09
 */
public final class StringUtil {
    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";
    private static HanyuPinyinOutputFormat format;

    static {
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    private StringUtil() {
    }


    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    public static String toPinYin(String str) throws Exception {
        return PinyinHelper.toHanYuPinyinString(str, format, "", true);
    }

    public static String toPinYinIgnoreSpace(String inputString) throws Exception {
        StringBuffer output = new StringBuffer();
        if (inputString != null && inputString.length() > 0 && !"null".equals(inputString)) {
            char[] input = inputString.trim().toCharArray();
            for (char ch : input) {
                if (Character.toString(ch).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(ch, format);
                    output.append(temp[0]);
                } else if (ch != ' ') {
                    output.append(ch);
                }
            }
        }
        return output.toString();
    }

    /**
     * 字符串json 格式化
     *
     * @param s s
     * @return string
     */
    public static String JsonFormart(String s) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();

        for (int index = 0; index < s.length(); ++index) {
            char c = s.charAt(index);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }

            switch (c) {
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '[':
                case '{':
                    jsonForMatStr.append(c + "\n");
                    ++level;
                    break;
                case ']':
                case '}':
                    jsonForMatStr.append("\n");
                    --level;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
            }
        }

        return jsonForMatStr.toString();
    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();

        for (int levelI = 0; levelI < level; ++levelI) {
            levelStr.append("\t");
        }

        return levelStr.toString();
    }

    /**
     * string to list
     *
     * @param str   str
     * @param regex regex
     * @return list string
     */
    public static List<String> stringToList(String str, String regex) {
        String[] split = str.split(regex);
        return Arrays.asList(split);
    }


    public static void main(String[] args) {
        String str = "AA,BB,CC,DD,EE";
        String regex = ",";

        List<String> list = stringToList(str, regex);
        System.out.println(list);

    }
}
