package service;

import java.util.List;
import entity.Buyers;

public interface BuyersDAO {

	//查询全部客户
	public List<Buyers> queryAllBuyers();
	//通过客户编号sid查询客户
	public Buyers queryBuyerByBid(String bid);
	//通过客户名称sname查询客户
	public Buyers queryBuyerByBname(String bname);
	//添加客户
	public boolean addBuyers(Buyers b);
	//修改客户
	public boolean updateBuyers(Buyers b);
	//删除客户
	public boolean deleteBuyers(String bid);
	//通过客户名称模糊查找，用于输入提示
	public List<Buyers> queryNameLike(String bname);
}
