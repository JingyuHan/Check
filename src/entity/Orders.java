package entity;

import java.util.Date;

//货品类
public class Orders {
	private String oid;//进货、出货订单id
	private boolean type;
	private int price_sh;//应付款
	private int price_ac;//实付款
	private String user;
	private String customer;
	private Date date;
	private String note;
	
	
	public Orders(){
		
	}
	
	public Orders(String oid,boolean type,int price_sh,int price_ac, String user,String customer,Date date,String note) {
		
		this.oid = oid;
		this.type = type;
		this.price_sh = price_sh;
		this.price_ac = price_ac;
		this.user = user;
		this.customer = customer;
		this.date = date;
		this.note = note;
	}

	

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public int getPrice_sh() {
		return price_sh;
	}

	public void setPrice_sh(int price_sh) {
		this.price_sh = price_sh;
	}

	public int getPrice_ac() {
		return price_ac;
	}

	public void setPrice_ac(int price_ac) {
		this.price_ac = price_ac;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", type=" + type + ", price_sh="
				+ price_sh + ", price_ac=" + price_ac + ", user=" + user
				+ ", customer=" + customer + ", date=" + date + ", note="
				+ note + "]";
	}

	
	
	
}
