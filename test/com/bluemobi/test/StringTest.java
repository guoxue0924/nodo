package com.bluemobi.test;

public class StringTest {

    
    private static boolean wildMatch(String pattern, String str) {
        pattern = toJavaPattern(pattern);
        return java.util.regex.Pattern.matches(pattern, str);
    }

    private static String toJavaPattern(String pattern) {
        String result = "^";
        char metachar[] = { '$', '^', '[', ']', '(', ')', '{', '|', '*', '+', '?', '.', '/' };
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            boolean isMeta = false;
            for (int j = 0; j < metachar.length; j++) {
                if (ch == metachar[j]) {
                    result += "/" + ch;
                    isMeta = true;
                    break;
                }
            }
            if (!isMeta) {
                if (ch == '*') {
                    result += ".*";
                } else {
                    result += ch;
                }

            }
        }
        result += "$";
        return result;
    }
    
    
    private static void test(String pattern, String str) {
        System.out.println(pattern+" " + str + " =>> " + wildMatch(pattern, str));        
    }
    
    
    public static void main(String[] args) {
        
        String requestURL = "http://localhost:8080/index:index";
        
        int begin = requestURL.indexOf("://")+3;
        String requestURL2 = requestURL.substring(begin);
        int end = requestURL2.indexOf(":");
        String domain = requestURL2.substring(0, end);
        
        System.out.println(domain);

    }

    

}
