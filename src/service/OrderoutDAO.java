package service;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import entity.Order_out;
import entity.Orders;
import entity.Result_out;

public interface OrderoutDAO {

	    //查询所有进货订单
		public List<Orders> queryAllOrderout(int beginIndex,int num);
		//通过订单号在总订单表中查询订单（进货，出货通用）
		public Orders queryOrderByOoid(String ooid);
		//通过出货订单号查询出货订单
		public List<Order_out> queryOrderoutByOoid(String ooid);
		//批量添加售货订单条目
		public boolean addOrder_outs(ActionContext context);
		//添加售货订单条目
		public boolean addOrder_out(Order_out oo);
		//在总订单表中添加售货订单
		public boolean addOrders(Orders order);
		//删除售货订单
		public boolean deleteOrderouts(String ooid);
		
		//条件查找售货订单，返回Result_out类型
		public List<Result_out> Result(String gname,String b_date,String e_date,String user,String customer,String note);
}
