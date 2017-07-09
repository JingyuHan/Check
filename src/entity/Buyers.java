package entity;
//ÓÃ»§Àà
public class Buyers {
	private int bid;
	private String bname;
	private String tel;
	private String address;
	
	public Buyers(){
		
	}
			
	public Buyers(int bid, String bname, String tel,String address) {
		
		this.bid = bid;
		this.bname = bname;
		this.tel = tel;
		this.address = address;
		
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
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
		return "Sellers [bid=" + bid + ", bname=" + bname + ", tel=" + tel
				+ ", address=" + address + "]";
	}

	
}
