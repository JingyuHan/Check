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
		
		OrderoutDAO oodao = new OrderoutDAOImpl();
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
		
	
		List<Orders> list = oodao.queryAllOrderout((pageNow-1)*EachPageContentNum,EachPageContentNum+1);
		if(list!=null && list.size()>0){
			if(list.size()==EachPageContentNum+1){
				list.remove(EachPageContentNum);
				session.setAttribute("oo_pageNext", pageNow+1);
			}else{
				session.setAttribute("oo_pageNext",0);
			}
			session.setAttribute("orderout_list", list);
		}else{
			session.removeAttribute("orderout_list");
			session.setAttribute("oo_pageNext",0);
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
			
			//分页显示，每页的内容数目
			int EachPageContentNum = 2;
			String gname = null;
			String b_date = null;
			String e_date = null;
			String user = null;
			String customer = null;
			String note = null;
			
			OrderoutDAO odao = new OrderoutDAOImpl();
			
			int pageNow = 0;
			String pageLast = request.getParameter("oo_pageLast");
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
			
			
			
			if("0".equals(request.getParameter("flag"))){
				gname = request.getParameter("gname");
				b_date = request.getParameter("b_date");
				e_date = request.getParameter("e_date");
				user = request.getParameter("user");
				customer = request.getParameter("customer");
				note = request.getParameter("note");
				
				session.setAttribute("gname", gname);
				session.setAttribute("b_date", b_date);
				session.setAttribute("e_date", e_date);
				session.setAttribute("user", user);
				session.setAttribute("customer", customer);
				session.setAttribute("note", note);
			}else{
				
			
				gname = (String)session.getAttribute("gname");
				b_date = (String)session.getAttribute("b_date");
				e_date = (String)session.getAttribute("e_date");
				user = (String)session.getAttribute("user");
				customer = (String)session.getAttribute("customer");
				note = (String)session.getAttribute("note");
			
				
			}
			
			List<Result_out> rolist = odao.Result(gname,b_date,e_date,user,customer,note,(pageNow-1)*EachPageContentNum,EachPageContentNum+1);
			if(rolist!=null && rolist.size()>0){
				if(rolist.size()==EachPageContentNum+1){
					rolist.remove(EachPageContentNum);
					session.setAttribute("oo_pageNext", pageNow+1);
				}else{
					session.setAttribute("oo_pageNext",0);
				}
				session.setAttribute("Result_list", rolist);
			}else{
				session.removeAttribute("Result_list");
				session.setAttribute("oo_pageNext",0);
			}
			
			return "out_Result_success";
		}
}
