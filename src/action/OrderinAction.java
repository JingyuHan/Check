package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import entity.Order_in;
import entity.Orders;
import entity.Result_in;
import service.OrderinDAO;
import service.impl.OrderinDAOImpl;

public class OrderinAction extends SuperAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String query(){
		
		//分页显示，每页的内容数目
		int EachPageContentNum = 2;
				
				
		OrderinDAO oidao = new OrderinDAOImpl();
		
		int pageNow = 0;
		String pageLast = request.getParameter("oi_pageLast");
		System.out.println(pageLast);
		if("0".equals(pageLast)){
			pageNow = Integer.parseInt(request.getParameter("oi_pageNext"));
		}else{
			pageNow = Integer.parseInt(pageLast);
		}
		session.setAttribute("oi_pageNow", pageNow);		
		if(pageNow>1){
			session.setAttribute("oi_pageLast", pageNow-1);
		}else{
			session.removeAttribute("oi_pageLast");
		}
		
		List<Orders> list = oidao.queryAllOrderin((pageNow-1)*EachPageContentNum,EachPageContentNum+1);
		if(list!=null && list.size()>0){
			if(list.size()==EachPageContentNum+1){
				list.remove(EachPageContentNum);
				session.setAttribute("oi_pageNext", pageNow+1);
			}else{
				session.setAttribute("oi_pageNext",0);
			}
			session.setAttribute("orderin_list", list);
		}
		
		
		return "in_query_success";
		
	}
	
	public String add(){
		OrderinDAO oidao = new OrderinDAOImpl();
		ActionContext context=ActionContext.getContext();    
		if(oidao.addOrder_ins(context))
			return "in_add_success";
		else
			return "in_add_fail";
	}
	
	public String detail(){
		OrderinDAO oidao = new OrderinDAOImpl();
		String oiid = request.getParameter("oiid");
		
		List<Order_in> oilist = oidao.queryOrderinByOiid(oiid);
		Orders o = oidao.queryOrderByOiid(oiid);
		session.setAttribute("order_detail", o);
		session.setAttribute("orderin_detail", oilist);
		return "in_datail_success";
		
	}
	
	//删除进货订单的动作
	public String delete(){
		OrderinDAO oidao = new OrderinDAOImpl();
		String oiid = request.getParameter("oiid");
		oidao.deleteOrderins(oiid);
		return "in_delete_success";
		
	}
	
	public String Result() throws Exception{
		OrderinDAO odao = new OrderinDAOImpl();
		
		String gname = request.getParameter("gname");
		String b_date = request.getParameter("b_date");
		String e_date = request.getParameter("e_date");
		String user = request.getParameter("user");
		String customer = request.getParameter("customer");
		String note = request.getParameter("note");
		
		List<Result_in> rilist = odao.Result(gname,b_date,e_date,user,customer,note);
		
		session.setAttribute("Result_list", rilist);
		return "in_Result_success";
	}
}
