/**
 * Created by George on 15/5/13.
 */
$(function(){
	
	$("#oldPwd").val('');
	
    /* 用户修改资料 */
    $("#userInfoSubmit").click(function(){
        $("#userInfoForm").ajaxSubmit({
            success:function(data){
                if(data.status == 1){
                    alert(data.error);
                }else{
                    alert('修改成功！');
                }
            }
        });
    });

    /* 修改密码提交 */
    $("#passwordSubmit").click(function(){
        $("#passwordForm").ajaxSubmit({
            success:function(data){
                if(data.status == 1){
                    alert(data.error);
                }else{
                    alert('修改成功！');
                }
            }
        });
    });
});
