package com.bluemobi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式util
 * 
 * @author zhangzheng
 * @date 2016-1-20
 * 
 */
public class RegularUtil {

    /**
     * 匹配字符串，regex中的*号可以是【0到多个】字符
     * 
     * @auther zhangzheng
     * @date 2016-3-4 下午5:10:30
     * @param regex
     *            正则表达式
     * @param str
     *            需要校验的字符串
     * @return
     */
    public static boolean match(String regex, String str) {
        regex = "^\\" + regex;
        regex = regex.replace("*", "(\\b.*|\\B.*|$)");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    
    /**
     * 匹配字符串，regex中的*号必须是【1到多个】字符
     * 
     * @auther zhangzheng
     * @date 2016-3-4 下午5:10:30
     * @param regex
     *            正则表达式
     * @param str
     *            需要校验的字符串
     * @return
     */
    public static boolean matchAtLeastOneCharacter(String regex, String str) {
        regex = "^\\" + regex;
        regex = regex.replace("*", ".+");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    
    public static void main(String[] args) {
     
        String regEx = "/abc/*";
        String str = "/abc/dd";
        
        boolean b = RegularUtil.match(regEx, str);
        //boolean b = RegularUtil.matcherAtLeastOneCharacter(regEx, str);
        
        System.out.println(b);
        
    }
    

}
