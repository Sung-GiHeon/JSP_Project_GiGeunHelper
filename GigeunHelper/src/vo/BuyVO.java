package vo;

import java.sql.Timestamp;

public class BuyVO {

	private String product_origin_file;
	private String product_title;
	private String member_name;
	private Timestamp buy_date;
	
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
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public Timestamp getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Timestamp buy_date) {
		this.buy_date = buy_date;
	}
	@Override
	public String toString() {
		return "BuyVO [product_origin_file=" + product_origin_file + ", product_title=" + product_title
				+ ", member_name=" + member_name + ", buy_date=" + buy_date + "]";
	}
}
