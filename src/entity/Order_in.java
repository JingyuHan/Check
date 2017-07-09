package entity;



//货品类
public class Order_in {
	private int id;//自增长id
	private String oiid;//进货id
	private String gname;//货物id
	private int price;
	private int amount;
	
	
	
	public Order_in(){
		
	}
	
	public Order_in(int id,String oiid, String gname, int price,int amount) {
		
		this.id = id;
		this.oiid = oiid;
		this.gname = gname;
		this.price = price;
		this.amount = amount;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOiid() {
		return oiid;
	}

	public void setOiid(String oiid) {
		this.oiid = oiid;
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
		return "Order_in [id=" + id + ", oiid=" + oiid + ", gname=" + gname
				+ ", price=" + price + ", amount="
				+ amount + "]";
	}

	

	
	
	
}
