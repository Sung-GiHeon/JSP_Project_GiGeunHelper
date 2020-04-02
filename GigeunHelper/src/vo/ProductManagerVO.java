package vo;

import java.sql.Timestamp;

public class ProductManagerVO {
	private int product_num;
	private String product_origin_file;
	private String product_titile;
	private String member_name;
	private int product_credit;
	private String product_free;
	private int product_origin_credit;
	private Timestamp product_free_date;
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getProduct_origin_file() {
		return product_origin_file;
	}
	public void setProduct_origin_file(String product_origin_file) {
		this.product_origin_file = product_origin_file;
	}
	public String getProduct_titile() {
		return product_titile;
	}
	public void setProduct_titile(String product_titile) {
		this.product_titile = product_titile;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getProduct_credit() {
		return product_credit;
	}
	public void setProduct_credit(int product_credit) {
		this.product_credit = product_credit;
	}
	public String getProduct_free() {
		return product_free;
	}
	public void setProduct_free(String product_free) {
		this.product_free = product_free;
	}
	public int getProduct_origin_credit() {
		return product_origin_credit;
	}
	public void setProduct_origin_credit(int product_origin_credit) {
		this.product_origin_credit = product_origin_credit;
	}
	public Timestamp getProduct_free_date() {
		return product_free_date;
	}
	public void setProduct_free_date(Timestamp product_free_date) {
		this.product_free_date = product_free_date;
	}
	@Override
	public String toString() {
		return "ProductManagerVO [product_num=" + product_num + ", product_origin_file=" + product_origin_file
				+ ", product_titile=" + product_titile + ", member_name=" + member_name + ", product_credit="
				+ product_credit + ", product_free=" + product_free + ", product_origin_credit=" + product_origin_credit
				+ ", product_free_date=" + product_free_date + "]";
	}
	
	
}
