package com.bluemobi.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionTest {
    


    /**
     * @author haojian
     * @date 2015-11-21 下午6:16:13 
     * @param args
     * @return void
     */

    public static void main(String[] args) {
        
        List<Hao> list = new ArrayList<Hao>();
        for(int i=100;i<110;i++){
            Hao hao = new Hao(i,"hao-"+i);
            list.add(hao);
        }
        
        
        String[] ss = CollectionTest.collectionToStringArray(list);
        for(String s:ss){
            System.out.println(s);
        }
        
    }
    
    /**
     * 运行正常
     * @author haojian
     * @date 2016-1-28 下午2:11:23 
     * @param coll
     * @return
     * @return String[]
     */
    public static <T> String[] collectionToStringArray(Collection<T> coll){
        String[] ss = new String[coll.size()];
        int i = 0;
        for(T t:coll){
            ss[i] = t.toString();
            i++;
        }
        return ss;
    }
    
    /**
     * 运行报错  Object[] 不能直接转 String[]
     * @author haojian
     * @date 2016-1-28 下午2:11:07 
     * @param coll
     * @return
     * @return String[]
     */
    public static <T> String[] collectionToStringArray2(Collection<T> coll){
        String[] ss = new String[coll.size()];
        ss = coll.toArray(ss);
        return ss;
    }
    
    /**
     * 运行报错  Object[] 不能直接转 String[]
     * @author haojian
     * @date 2016-1-28 下午2:11:18 
     * @param coll
     * @return
     * @return String[]
     */
    public static <T> String[] collectionToStringArray3(Collection coll){
        String[] ss = new String[coll.size()];
        coll.toArray(ss);
        return ss;
    }

}

class Hao{
    private int i;
    private String n;
    public Hao(){
        
    }
    public Hao(int i,String n){
        this.i = i;
        this.n = n;
    }
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    public String getN() {
        return n;
    }
    public void setN(String n) {
        this.n = n;
    }
    
}
