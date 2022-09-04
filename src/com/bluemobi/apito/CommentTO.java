package com.bluemobi.apito;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 【评论】
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class CommentTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

	//用户id
	private Integer userid;
	//昵称
	private String nickname;
	//图像地址
	private String avatar;
	//评论内容
	private String content;
	//商品满意度评分
	private Integer rankBase;
	//物流满意度评分
	private Integer rankLogistics;
	//发货速度满意度评分
	private Integer rankSpeed;


    /**设置用户id*/
	public void setUserid(Integer userid){
		this.userid=userid;
	}
	/**获取用户id*/
	public Integer getUserid(){
		return this.userid;
	}
    /**设置昵称*/
	public void setNickname(String nickname){
		this.nickname=nickname;
	}
	/**获取昵称*/
	public String getNickname(){
		return this.nickname;
	}
    /**设置图像地址*/
	public void setAvatar(String avatar){
		this.avatar=avatar;
	}
	/**获取图像地址*/
	public String getAvatar(){
		return this.avatar;
	}
    /**设置评论内容*/
	public void setContent(String content){
		this.content=content;
	}
	/**获取评论内容*/
	public String getContent(){
		return this.content;
	}
    /**设置商品满意度评分*/
	public void setRankBase(Integer rankBase){
		this.rankBase=rankBase;
	}
	/**获取商品满意度评分*/
	public Integer getRankBase(){
		return this.rankBase;
	}
    /**设置物流满意度评分*/
	public void setRankLogistics(Integer rankLogistics){
		this.rankLogistics=rankLogistics;
	}
	/**获取物流满意度评分*/
	public Integer getRankLogistics(){
		return this.rankLogistics;
	}
    /**设置发货速度满意度评分*/
	public void setRankSpeed(Integer rankSpeed){
		this.rankSpeed=rankSpeed;
	}
	/**获取发货速度满意度评分*/
	public Integer getRankSpeed(){
		return this.rankSpeed;
	}


}
