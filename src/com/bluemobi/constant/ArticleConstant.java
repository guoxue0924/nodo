package com.bluemobi.constant;

/**
 * 文章模块常量
 * ClassName: ArticleConstant
 * Date: 2016年2月22日下午1:25:23

 * @author kevin
 * @version 
 * @since JDK 7
 */
public class ArticleConstant {

    // 文章状态类型
 	public enum ARTICLE_STATUS {
 		WAIT_PULISH(0, "未发布"),
 		PULISHED(1, "已发布"),
 		DELETED(-1, "已删除");

 		Integer	code;
 		String	desc;

 		ARTICLE_STATUS(int code, String desc) {
 			this.code = code;
 			this.desc = desc;
 		}

 		public Integer getCode() {
 			return code;
 		}

 		public String getDesc() {
 			return desc;
 		}
 	}


}
