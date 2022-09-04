package com.bluemobi.test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    public static void main(String[] args) {
        
        String matchStr = "/abc*ss";
        
        String str = "/abc/yyy/";
        
        System.out.println(Test.match2(str, matchStr));
        
    }
    
    
    public static boolean match2(String str,String matchStr){  
        
        String begin = "^\\";
        String end = "$";
        String all = "\\\\b.*";
        matchStr = begin + matchStr + end;
        System.out.println("1----"+matchStr);
        matchStr = matchStr.replaceAll("\\*", all);
        System.out.println("2----"+matchStr);

       
        //Pattern pattern = Pattern.compile("^\\/front/[a-zA-Z0-9]*/cas/\\b.*$");
        Pattern pattern = Pattern.compile(matchStr);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
        
    }
    
    
    public static boolean match(String str,String matchStr){  
        
        String begin = "^\\";
        String end = "$";
        String letter = "[a-zA-Z0-9]*";
        String all = "\\\\b.*";
        matchStr = begin + matchStr + end;
        System.out.println("1----"+matchStr);
        matchStr = matchStr.replaceAll("\\*\\*", all);
        System.out.println("2----"+matchStr);
        matchStr = matchStr.replaceAll("\\*", letter);
        System.out.println("3----"+matchStr);
       
        //Pattern pattern = Pattern.compile("^\\/front/[a-zA-Z0-9]*/cas/\\b.*$");
        Pattern pattern = Pattern.compile(matchStr);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
        
    }
    
    public static void test4(){
        
    }
    
    public static void test5(){
        
    }
    
    public static void test1(){
        ByteBuffer buffer= ByteBuffer.allocate(4);
        
        //buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.order(ByteOrder.BIG_ENDIAN);
        
        buffer.putInt(1);
        
        buffer.flip();
        byte[] bb = new byte[4];
        buffer.get(bb);
        
        for(int i=0;i<bb.length;i++){
            System.out.println(bb[i]);
        }
    }
    
}
