package entity;

import java.util.Date;
import java.util.List;

public class Result_in {
	
	private String oiid;
	private int price_sh;
	private int price_ac;
	private String user;
	private String customer;
	private Date date;
	private String note;
	private List<Order_in> oilist;
	
	public Result_in(){
		
	}
	
	public Result_in(List<Order_in> oilist,Orders o){
		this.oiid = o.getOid();
		this.oilist = oilist;
		this.price_sh = o.getPrice_sh();
		this.price_ac = o.getPrice_ac();
		this.user = o.getUser();
		this.customer = o.getCustomer();
		this.date = o.getDate();
		this.note = o.getNote();
	}
	
	public String getOiid() {
		return oiid;
	}
	public void setOiid(String oiid) {
		this.oiid = oiid;
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
	public List<Order_in> getOilist() {
		return oilist;
	}
	public void setOilist(List<Order_in> oilist) {
		this.oilist = oilist;
	}
	
	
}
