package entity;
//”√ªß¿‡
public class Sellers {
	private int sid;
	private String sname;
	private String tel;
	private String address;
	
	public Sellers(){
		
	}
			
	public Sellers(int sid, String sname, String tel,String address) {
		
		this.sid = sid;
		this.sname = sname;
		this.tel = tel;
		this.address = address;
		
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Sellers [sid=" + sid + ", sname=" + sname + ", tel=" + tel
				+ ", address=" + address + "]";
	}

	
}
