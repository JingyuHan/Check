package service;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import entity.Order_in;
import entity.Orders;
import entity.Result_in;

public interface OrderinDAO {

		
		//查询所有进货订单
		public List<Orders> queryAllOrderin(int beginIndex,int num);
		//通过订单号在总订单表中查询订单（进货，出货通用）
		public Orders queryOrderByOiid(String oiid);
		//通过进货订单号查询进货订单
		public List<Order_in> queryOrderinByOiid(String oiid);
		//批量添加进货订单条目
		public boolean addOrder_ins(ActionContext context);
		//添加进货订单条目
		public boolean addOrder_in(Order_in oi);
		//在总订单表中添加进货订单
		public boolean addOrders(Orders order);
		//删除进货订单
		public boolean deleteOrderins(String oiid);
		
		//条件查找进货订单，返回Result_in类型
		public List<Result_in> Result(String gname,String b_date,String e_date,String user,String customer,String note,int beginIndex,int num);
}
