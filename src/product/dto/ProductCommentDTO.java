package product.dto;

public class ProductCommentDTO {
	private String repid;
	private String pid;
	private String userid;
	private String repregist;
	private String comments;
	private int isdelete;
	public String getRepid() {
		return repid;
	}
	public void setRepid(String repid) {
		this.repid = repid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRepregist() {
		return repregist;
	}
	public void setRepregist(String repregist) {
		this.repregist = repregist;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	
}
