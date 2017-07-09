package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import entity.Order_out;
import entity.Orders;
import entity.Result_out;
import service.OrderoutDAO;
import service.impl.OrderoutDAOImpl;

public class OrderoutAction extends SuperAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String query(){
		
		//分页显示，每页的内容数目
		int EachPageContentNum = 2;
		
		OrderoutDAO oidao = new OrderoutDAOImpl();
		int pageNow = 0;
		String pageLast = request.getParameter("oo_pageLast");
		System.out.println(pageLast);
		if("0".equals(pageLast)){
			pageNow = Integer.parseInt(request.getParameter("oo_pageNext"));
		}else{
			pageNow = Integer.parseInt(pageLast);
		}
		session.setAttribute("oo_pageNow", pageNow);		
		if(pageNow>1){
			session.setAttribute("oo_pageLast", pageNow-1);
		}else{
			session.removeAttribute("oo_pageLast");
		}
		
	
		List<Orders> list = oidao.queryAllOrderout((pageNow-1)*EachPageContentNum,EachPageContentNum+1);
		if(list!=null && list.size()>0){
			if(list.size()==EachPageContentNum+1){
				list.remove(EachPageContentNum);
				session.setAttribute("oo_pageNext", pageNow+1);
			}else{
				session.setAttribute("oo_pageNext",0);
			}
			session.setAttribute("orderout_list", list);
		}
		
		
		return "out_query_success";
		
	}
	
	public String add(){
		OrderoutDAO oodao = new OrderoutDAOImpl();
		ActionContext context=ActionContext.getContext();    
		if(oodao.addOrder_outs(context))
			return "out_add_success";
		else
			return "out_add_fail";
	}
	
	public String detail(){
		OrderoutDAO oodao = new OrderoutDAOImpl();
		String ooid = request.getParameter("ooid");
		
		List<Order_out> oolist = oodao.queryOrderoutByOoid(ooid);
		Orders o = oodao.queryOrderByOoid(ooid);
		session.setAttribute("order_detail", o);
		session.setAttribute("orderout_detail", oolist);
		return "out_datail_success";
		
	}
	
	//删除售货订单的动作
		public String delete(){
			OrderoutDAO oodao = new OrderoutDAOImpl();
			String ooid = request.getParameter("ooid");
			oodao.deleteOrderouts(ooid);
			return "out_delete_success";
			
		}
		
		public String Result() throws Exception{
			OrderoutDAO odao = new OrderoutDAOImpl();
			
			String gname = request.getParameter("gname");
			String b_date = request.getParameter("b_date");
			String e_date = request.getParameter("e_date");
			String user = request.getParameter("user");
			String customer = request.getParameter("customer");
			String note = request.getParameter("note");
			
			List<Result_out> rilist = odao.Result(gname,b_date,e_date,user,customer,note);
			
			session.setAttribute("Result_list", rilist);
			return "out_Result_success";
		}
}
