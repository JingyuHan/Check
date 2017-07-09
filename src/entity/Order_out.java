package entity;



//货品类
public class Order_out {
	private int id;//自增长id
	private String ooid;//出货订单id
	private String gname;//货品id
	private int price;
	private int amount;
	
	
	
	public Order_out(){
		
	}
	
	public Order_out(int id,String ooid, String gname,int price,int amount) {
		
		this.id = id;
		this.ooid = ooid;
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

	public String getOoid() {
		return ooid;
	}

	public void setOoid(String ooid) {
		this.ooid = ooid;
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
		return "Order_out [id=" + id + ", ooid=" + ooid + ", gname=" + gname
				+ ", price=" + price + ", amount="
				+ amount + "]";
	}

	

	
	
	
}
