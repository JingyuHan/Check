package entity;



//ªı∆∑¿‡
public class Goods {
	private String gid;
	private String gname;
	private int price;
	private int amount;
	
	
	public Goods(){
		
	}
	
	public Goods(String gid, String gname, int price,int amount) {
		
		this.gid = gid;
		this.gname = gname;
		this.price = price;
		this.amount = amount;
		
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	

	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gname=" + gname + ", price=" + price
				+ ", amount=" + amount +  "]";
	}

	
	
	
}
