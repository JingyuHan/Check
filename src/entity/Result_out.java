package entity;

import java.util.Date;
import java.util.List;

public class Result_out {
	
	private String ooid;
	private int price_sh;
	private int price_ac;
	private String user;
	private String customer;
	private Date date;
	private String note;
	private List<Order_out> oolist;
	
	public Result_out(){
		
	}
	
	public Result_out(List<Order_out> oolist,Orders o){
		this.ooid = o.getOid();
		this.oolist = oolist;
		this.price_sh = o.getPrice_sh();
		this.price_ac = o.getPrice_ac();
		this.user = o.getUser();
		this.customer = o.getCustomer();
		this.date = o.getDate();
		this.note = o.getNote();
	}
	
	public String getOoid() {
		return ooid;
	}
	public void setOoid(String ooid) {
		this.ooid = ooid;
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
	public List<Order_out> getOolist() {
		return oolist;
	}
	public void setOolist(List<Order_out> oolist) {
		this.oolist = oolist;
	}
	
	
}
