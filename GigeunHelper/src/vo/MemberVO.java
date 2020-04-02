package vo;


import java.util.Date;

public class MemberVO {

	
	private int member_num;
	private String  member_id;
	private String  member_pw;
	private String  member_mail;
	private String member_phone;
	private int member_credit;
	private Date member_date;
	private String member_join;
	private String member_name;
	
	

	public MemberVO() {}



	
	
	
	public MemberVO(String member_id, String member_pw, String member_phone, String member_name) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_phone = member_phone;
		this.member_name = member_name;
	}






	public MemberVO(int member_num, String member_id, String member_pw, String member_mail, String member_phone,
			int member_credit, Date member_date, String member_join, String member_name) {
		super();
		this.member_num = member_num;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_mail = member_mail;
		this.member_phone = member_phone;
		this.member_credit = member_credit;
		this.member_date = member_date;
		this.member_join = member_join;
		this.member_name = member_name;
	}






	public MemberVO(String member_id, String member_pw, String member_mail, String member_phone, String member_name) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_mail = member_mail;
		this.member_phone = member_phone;
		this.member_name = member_name;
	}






	@Override
	public String toString() {
		return "MemberVO [member_num=" + member_num + ", member_id=" + member_id + ", member_pw=" + member_pw
				+ ", member_mail=" + member_mail + ", member_phone=" + member_phone + ", member_credit=" + member_credit
				+ ", member_date=" + member_date + ", member_join=" + member_join + ", member_name=" + member_name
				+ "]";
	}






	public int getMember_num() {
		return member_num;
	}



	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}



	public String getMember_id() {
		return member_id;
	}



	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}



	public String getMember_pw() {
		return member_pw;
	}



	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}



	public String getMember_mail() {
		return member_mail;
	}



	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}



	public String getMember_phone() {
		return member_phone;
	}



	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}



	public int getMember_credit() {
		return member_credit;
	}



	public void setMember_credit(int member_credit) {
		this.member_credit = member_credit;
	}



	public Date getMember_date() {
		return member_date;
	}



	public void setMember_date(Date member_date) {
		this.member_date = member_date;
	}



	public String getMember_join() {
		return member_join;
	}



	public void setMember_join(String member_join) {
		this.member_join = member_join;
	}



	public String getMember_name() {
		return member_name;
	}



	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	
	
	

	
	
}
