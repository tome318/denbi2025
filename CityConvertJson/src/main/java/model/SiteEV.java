package model;		
import java.io.Serializable;		
//p250 JavaBeans		
public class SiteEV implements Serializable {		
	//保管用	
	private int like; //よいねの数	
	private int dislike; //よくないねの数	
	//コンストラクタ	
	public SiteEV() {	
		like = 0;
		dislike = 0;
	}	
	//ゲッター（出す）、セッター（入れる）処理	
	public int getLike() {	
		return like;
	}	
	public void setLike(int like) {	
		this.like = like;
	}	
	public int getDislike() {	
		return dislike;
	}	
	public void setDislike(int dislike) {	
		this.dislike = dislike;
	}	
}		
