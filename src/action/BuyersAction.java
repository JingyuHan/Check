package action;



import java.util.List;

import entity.Buyers;
import service.BuyersDAO;
import service.impl.BuyersDAOImpl;



//客户Action类
public class BuyersAction extends SuperAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//查询所有客户
	public String query(){
		BuyersDAO bdao = new BuyersDAOImpl();
		List<Buyers> list = bdao.queryAllBuyers();
		//放进session中
		if(list!=null && list.size()>0){
			session.setAttribute("buyers_list", list);
			
		}else{
			session.removeAttribute("buyers_list");
		}
		return "buy_query_success";
		
	}
	
	//删除厂家的动作
		public String delete(){
			BuyersDAO bdao = new BuyersDAOImpl();
			String bid = request.getParameter("bid");
			bdao.deleteBuyers(bid);
			return "buy_delete_success";
			
		}
		
		//通过bid查找客户
		public String findByBid(){
			BuyersDAO bdao = new BuyersDAOImpl();
			Buyers b = bdao.queryBuyerByBid(request.getParameter("bid"));
			if(b!=null){
				session.setAttribute("find_good",b);
				return "buy_find_success";
			}else{
				return "buy_find_fail";
			}
			
		}
		
		//通过名称查找货品
		public String findByBname(){
			BuyersDAO bdao = new BuyersDAOImpl();
			Buyers b = bdao.queryBuyerByBname(request.getParameter("bname"));
			if(b!=null){
				session.setAttribute("find_good",b);
				return "buy_find_success";
			}else{
				return "buy_find_fail";
			}
			
		}
		
		//添加货品
		public String add() throws Exception{
			Buyers b = new Buyers();
			b.setBid(0);
			b.setBname(request.getParameter("bname"));
			b.setTel(request.getParameter("tel"));
			b.setAddress(request.getParameter("address"));
			BuyersDAO bdao = new BuyersDAOImpl();
			bdao.addBuyers(b);
			return "buy_add_success";
		}
		
		
		//修改货品动作
		public String modify(){
			//获得传递过来的学生编号
			String bid = request.getParameter("bid");
			BuyersDAO bdao = new BuyersDAOImpl();
			Buyers b = bdao.queryBuyerByBid(bid);
			//保存在会话中
			session.setAttribute("modify_good", b);
			return "buy_modify_success";
			
		}
		
		//保存修改后货品的动作
		public String save() throws Exception{
			Buyers b = new Buyers();
			b.setBid(Integer.parseInt(request.getParameter("bid")));
			b.setBname(request.getParameter("bname"));
			b.setTel(request.getParameter("tel"));
			b.setAddress(request.getParameter("address"));
			BuyersDAO bdao = new BuyersDAOImpl();
			bdao.updateBuyers(b);
			return "buy_save_success";
		}
		
		

		
		
}
