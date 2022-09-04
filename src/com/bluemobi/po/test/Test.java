package com.bluemobi.po.test;

import com.appcore.model.AbstractObject;
/**
 * 【】持久化对象
 * 数据库表：test
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-16 10:59:33
 *
 */
public class Test extends AbstractObject {
    
    private static final long serialVersionUID = 1L;
    
    //
    private Integer id; 
    //
    private String name; 
    //
    private String address; 
    //
    private Integer age; 

    /**获取  属性*/
    public Integer getId() {
        return id;
    }
    
    /** 设置  属性*/
    public void setId(Integer id) {
        this.id = id;
    }
    /**获取  属性*/
    public String getName() {
        return name;
    }
    
    /** 设置  属性*/
    public void setName(String name) {
        this.name = name;
    }
    /**获取  属性*/
    public String getAddress() {
        return address;
    }
    
    /** 设置  属性*/
    public void setAddress(String address) {
        this.address = address;
    }
    /**获取  属性*/
    public Integer getAge() {
        return age;
    }
    
    /** 设置  属性*/
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Test");
        sb.append("{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}