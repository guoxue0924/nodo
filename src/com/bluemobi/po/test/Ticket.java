package com.bluemobi.po.test;

import com.appcore.model.AbstractObject;
/**
 * 【车票】持久化对象
 * 数据库表：ticket
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-16 12:03:08
 *
 */
public class Ticket extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //车票id
    private Integer id; 
    //车票名称
    private String name; 
    //剩余数量
    private Integer count;
    //发车日期
    private String date;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString(){
        return "id="+id+", 车次="+name+", 剩余数量="+count+", 发车时间="+date;
    }

    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof Ticket) {
            Ticket test = (Ticket)obj;
            if(this.getId().equals(test.getId())){
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }
    
}