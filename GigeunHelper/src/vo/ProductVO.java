package vo;

import java.util.Date;

public class ProductVO {
	
	private int product_num;
	private String product_title;
	private String product_category;
	private String product_size;
	private int product_credit;
	private String product_file;
	private String product_origin_file;
	private int product_readcount;
	private int product_likecount;
	private int product_member_num;
	private Date product_date;
	private String product_free;
	private Date product_free_time;
	private int product_origin_credit;
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_size() {
		return product_size;
	}
	public void setProduct_size(String product_size) {
		this.product_size = product_size;
	}
	public int getProduct_credit() {
		return product_credit;
	}
	public void setProduct_credit(int product_credit) {
		this.product_credit = product_credit;
	}
	public String getProduct_file() {
		return product_file;
	}
	public void setProduct_file(String product_file) {
		this.product_file = product_file;
	}
	public String getProduct_origin_file() {
		return product_origin_file;
	}
	public void setProduct_origin_file(String product_origin_file) {
		this.product_origin_file = product_origin_file;
	}
	public int getProduct_readcount() {
		return product_readcount;
	}
	public void setProduct_readcount(int product_readcount) {
		this.product_readcount = product_readcount;
	}
	public int getProduct_likecount() {
		return product_likecount;
	}
	public void setProduct_likecount(int product_likecount) {
		this.product_likecount = product_likecount;
	}
	public int getProduct_member_num() {
		return product_member_num;
	}
	public void setProduct_member_num(int product_member_num) {
		this.product_member_num = product_member_num;
	}
	public Date getProduct_date() {
		return product_date;
	}
	public void setProduct_date(Date product_date) {
		this.product_date = product_date;
	}
	public String getProduct_free() {
		return product_free;
	}
	public void setProduct_free(String product_free) {
		this.product_free = product_free;
	}
	public Date getProduct_free_time() {
		return product_free_time;
	}
	public void setProduct_free_time(Date product_free_time) {
		this.product_free_time = product_free_time;
	}
	public int getProduct_origin_credit() {
		return product_origin_credit;
	}
	public void setProduct_origin_credit(int product_origin_credit) {
		this.product_origin_credit = product_origin_credit;
	}
	@Override
	public String toString() {
		return "ProductVO [product_num=" + product_num + ", product_title=" + product_title + ", product_category="
				+ product_category + ", product_size=" + product_size + ", product_credit=" + product_credit
				+ ", product_file=" + product_file + ", product_origin_file=" + product_origin_file
				+ ", product_readcount=" + product_readcount + ", product_likecount=" + product_likecount
				+ ", product_member_num=" + product_member_num + ", product_date=" + product_date + ", product_free="
				+ product_free + ", product_free_time=" + product_free_time + ", product_origin_credit="
				+ product_origin_credit + "]";
	}
}
