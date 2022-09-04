package com.bluemobi.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.to.ResultTO;

@Controller(value = "advertContentAPIController")
@RequestMapping("api/advertContent")
public class AnnotationTest {
    
    
    @RequestMapping(value = "getAdvertContent")
    @ResponseBody
    public ResultTO getAdvertContent(HttpServletRequest request) {
        return null;
    }
    
    @RequestMapping(value = "getAdvertContent2")
    @ResponseBody
    public ResultTO getAdvertContent2(HttpServletRequest request) {
        return null;
    }
    
    

    /**
     * @author haojian
     * @date 2016-3-1 下午4:32:19 
     * @param args
     * @return void
     */

    public static void main(String[] args) {
        
        AnnotationTest test = new AnnotationTest();
        
        String controller = test.getController();
        System.out.println("controller===" + controller);
        
        String classRequestMapping = test.getClassRequestMapping();
        System.out.println("classRequestMapping===" + classRequestMapping);
        
        List<String> methodRequestMappingList = test.getMethodRequestMapping();
        for(String methodRequestMapping : methodRequestMappingList){
            System.out.println("methodRequestMapping===" + methodRequestMapping);
        }
        
    }
    
  
    
    /**
     * 获取RequestMapping中的url路径
     * @author haojian
     * @date 2016-3-1 下午5:01:03 
     * @param Annotation[] 类 或者 方法 的 注解数组
     * @return
     * @return String
     */
    private <T> String getRequestMapping(Annotation[] annotations){ 
        
        String url = null;
        for(Annotation a:annotations){
            
            if(RequestMapping.class.equals(a.annotationType())){
                System.out.println("-------RequestMapping---------");
                RequestMapping r = (RequestMapping)a;
                String[] ss = r.value();
                if(ss.length>0){
                    url = ss[0];
                    System.out.println("url==="+url);
                }
            }   
        }
        return url;
    }
    
    /**
     * 获取类级别的 RequestMapping
     * @author haojian
     * @date 2016-3-1 下午5:08:12 
     * @return
     * @return String
     */
    public String getClassRequestMapping(){
        
        Annotation[] annotations = this.getClass().getAnnotations();
        String url = this.getRequestMapping(annotations);
        
        return url;
    }
    
    /**
     * 获取方法级别的 RequestMapping
     * @author haojian
     * @date 2016-3-1 下午5:08:28 
     * @return
     * @return List<String>
     */
    public List<String> getMethodRequestMapping(){
        List<String> urlList = new ArrayList<String>();
        Method[] mm = this.getClass().getMethods();
        for(Method m:mm){
            Annotation[] annotations = m.getAnnotations();
            String url = this.getRequestMapping(annotations);
            if(url!=null){
                urlList.add(url);
            }
        }
        return urlList;
    }

    
    
    /**
     * 获取控制器的beanName
     * @author haojian
     * @date 2016-3-1 下午5:08:00 
     * @return
     * @return String
     */
    public String getController(){
        
        String beanName = "";
        Annotation[] annotations = AnnotationTest.class.getAnnotations();
        for(Annotation a:annotations){
            
            if(Controller.class.equals(a.annotationType())){
                System.out.println("-------Controller---------");
                Controller c = (Controller)a;
                beanName = c.value();
                System.out.println("beanName==="+beanName);
                continue;
            }
           
        }
        
        return beanName;
    }
    
    

}
