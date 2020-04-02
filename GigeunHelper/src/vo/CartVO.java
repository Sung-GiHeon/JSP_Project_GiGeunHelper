package vo;

public class CartVO {
	private int cart_num;
	private String product_origin_file;
	private String product_title;
	private int product_credit;
	private String member_name;
	private int member_num;
	public int getCart_num() {
		return cart_num;
	}
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}
	public String getProduct_origin_file() {
		return product_origin_file;
	}
	public void setProduct_origin_file(String product_origin_file) {
		this.product_origin_file = product_origin_file;
	}
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}
	public int getProduct_credit() {
		return product_credit;
	}
	public void setProduct_credit(int product_credit) {
		this.product_credit = product_credit;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	@Override
	public String toString() {
		return "CartVO [cart_num=" + cart_num + ", product_origin_file=" + product_origin_file + ", product_title="
				+ product_title + ", product_credit=" + product_credit + ", member_name=" + member_name
				+ ", member_num=" + member_num + "]";
	}
	
}
