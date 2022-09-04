package com.bluemobi.test;


public class TestArray {

    /**
     * @author haojian
     * @date 2016-2-2 下午4:56:00 
     * @param args
     * @return void
     */

    public static void main(String[] args) {
        
        TestArray.testArray1("a", 9999);
        TestArray.testArray1("a", new int[]{11,2,333,4,555});
        
        //不支持此写法，编译报错
        //TestArray.testArray2("a", 9999);
        TestArray.testArray2("a", new int[]{11,2,333,4,555});
        
    }
    
    
    public static void testArray1(String name, int... ii){
        for(int i:ii){
            System.out.println(i);
        }
    }
    
    public static void testArray2(String name, int[] ii){
        for(int i:ii){
            System.out.println(i);
        }
    }

}
