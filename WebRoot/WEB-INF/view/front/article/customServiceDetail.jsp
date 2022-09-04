<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../index/wrapper.prefix_front.jsp"/>


<div class="nch-breadcrumb-layout">
    <div class="nch-breadcrumb wrapper"><i class="icon-home"></i>
            <span><a href="http://b2c2.icooper.biz/shop">首页</a></span><span class="arrow">&gt;</span>
                <span>${categoryTitle }</span><span class="arrow">&gt;</span><span>文章内容</span>
          </div>
  </div>
  <jsp:include page="../article/article.nav.jsp"/>
<link href="http://b2c2.icooper.biz/shop/templates/default/css/layout.css" rel="stylesheet" type="text/css">
<div class="nch-container wrapper">
 
  
    
    <div class="right">
    <div class="nch-article-con" style=" margin-top:-30px;">
      <h1>${contentDetail.title }</h1>
      <h2><fmt:formatDate value="${contentDetail.mtime }" pattern="yyyy-MM-dd HH:mm:ss"/></h2>
      <div class="default">
        <p>${contentDetail.body }</p>
      </div>
     <!--  <div class="more_article"> <span class="fl">上一篇：
                <a target="_blank" href="http://www.bluemobi.cn/">火爆销售中</a> <time>2014-01-16 17:31</time>
                </span> <span class="fr">下一篇：
                <a href="http://b2c2.icooper.biz/shop/index.php?act=article&amp;op=show&amp;article_id=37">如何扩充水印字体库</a> <time>2014-01-16 17:31</time>
                </span> </div> -->
    </div>
  </div>

    <div class="tc mb20">  <div class="pagination"> <ul><li><span>首页</span></li><li><span>上一页</span></li><li><span class="currentpage">1</span></li><li><span>下一页</span></li><li><span>末页</span></li></ul> </div></div>
  
  </div>
</div>
<div class="clear"></div>

<jsp:include page="../index/wrapper.suffix_front.jsp"/>