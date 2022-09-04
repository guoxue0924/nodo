<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="nch-breadcrumb-layout">
    <input type="hidden" id="validateEmail" value="${validateEmail }">
</div>


 <script type="text/javascript">
    
    	var validateEmail = eval(document.getElementById('validateEmail')).value;
    	if(validateEmail!=""){
    		if(validateEmail==1){
    			alert("邮箱已绑定");
    		} 
    		if(validateEmail==0){
    			alert("邮箱未绑定成功");
    		}	
    	}
    	location.href='http://localhost:8080/nodo/front/index/index'; 
</script>
 
