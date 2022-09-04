package com.bluemobi.to.comment;

import java.util.Date;
import java.util.List;

import com.appcore.model.AbstractObject;

/**
 * 评论列表展示 ClassName: CommentDetailTO Date: 2016年1月25日下午4:40:02
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
public class CommentDetailTO extends AbstractObject {

	private static final long serialVersionUID = 5702535590573266638L;
	// id
	private Integer id;
	// 商品满意度评分
	private Byte rankBase;
	// 评论内容
	private String content;

	private Date ctime;

	private String goodsName;

	private String goodsImage;

	private Long skuId;

	private List<String> filePaths;

	private String nickname;

	private String avatar;
	
	private Byte rankLogistics;
	
	private Byte rankSpeed;
	
	private Integer userid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Byte getRankBase() {
		return rankBase;
	}

	public void setRankBase(Byte rankBase) {
		this.rankBase = rankBase;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public List<String> getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(List<String> filePaths) {
		this.filePaths = filePaths;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Byte getRankLogistics() {
		return rankLogistics;
	}

	public void setRankLogistics(Byte rankLogistics) {
		this.rankLogistics = rankLogistics;
	}

	public Byte getRankSpeed() {
		return rankSpeed;
	}

	public void setRankSpeed(Byte rankSpeed) {
		this.rankSpeed = rankSpeed;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
