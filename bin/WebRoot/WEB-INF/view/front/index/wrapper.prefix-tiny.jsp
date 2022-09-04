<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="zh-CN" class="app">
<head>
<script type="text/javascript" language="JavaScript">
     // 动态获得地址配置
    var BASE_URL = '${BASE_URL}';
    var STATIC_URL = '${STATIC_URL}';
    var IMG_URL = '${IMG_URL}';
    var TEMP_IMG_URL = '${TEMP_IMG_URL}';
</script>
<jsp:include page="inc/header.jsp"/>
<!-- USER DEFINED ASSETS -->
<!-- USER DEFINED ASSETS END@-->
<title>{$_SITE_NAME}</title>
</head>