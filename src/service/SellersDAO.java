package service;

import java.util.List;
import entity.Sellers;

public interface SellersDAO {

	//查询全部厂家
	public List<Sellers> queryAllSellers();
	//通过厂家编号sid查询厂家
	public Sellers querySellerBySid(String sid);
	//通过厂家名称sname查询厂家
	public Sellers querySellerBySname(String sname);
	//添加厂家
	public boolean addSellers(Sellers s);
	//修改厂家
	public boolean updateSellers(Sellers s);
	//删除厂家
	public boolean deleteSellers(String sid);
	//通过厂家名称模糊查找，用于输入提示
	public List<Sellers> queryNameLike(String gname);
}
