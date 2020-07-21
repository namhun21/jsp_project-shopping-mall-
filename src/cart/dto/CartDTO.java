package cart.dto;

public class CartDTO {
	private int pid;
	private String uid;
	private int pcount;
	private int cartid;
	private String price;
	private String pname;
	private String product_img;
	
	public String getpName() {
		return pname;
	}
	public void setpName(String pname) {
		this.pname = pname;
	}
	
	
	public int getpId() {
		return pid;
	}
	public void setpId(int pid) {
		this.pid = pid;
	}
	public String getuId() {
		return uid;
	}
	public void setuId(String uid) {
		this.uid = uid;
	}
	public int getpCount() {
		return pcount;
	}
	public void setpCount(int pcount) {
		this.pcount = pcount;
	}
	public int getcartId() {
		return cartid;
	}
	public void setcartId(int cartid) {
		this.cartid = cartid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
}
