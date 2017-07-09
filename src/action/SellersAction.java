package action;



import java.util.List;

import entity.Sellers;
import service.SellersDAO;
import service.impl.SellersDAOImpl;



//厂家Action类
public class SellersAction extends SuperAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//查询所有厂家
	public String query(){
		SellersDAO sdao = new SellersDAOImpl();
		List<Sellers> list = sdao.queryAllSellers();
		//放进session中
		if(list!=null && list.size()>0){
			session.setAttribute("sellers_list", list);
			
		}
		return "sell_query_success";
		
	}
	
	//删除厂家的动作
		public String delete(){
			SellersDAO sdao = new SellersDAOImpl();
			String sid = request.getParameter("sid");
			sdao.deleteSellers(sid);
			return "sell_delete_success";
			
		}
		
		//通过sid查找厂家
		public String findBySid(){
			SellersDAO sdao = new SellersDAOImpl();
			Sellers s = sdao.querySellerBySid(request.getParameter("sid"));
			if(s!=null){
				session.setAttribute("find_good",s);
				return "sell_find_success";
			}else{
				return "sell_find_fail";
			}
			
		}
		
		//通过名称查找货品
		public String findBySname(){
			SellersDAO sdao = new SellersDAOImpl();
			Sellers s = sdao.querySellerBySname(request.getParameter("sname"));
			if(s!=null){
				session.setAttribute("find_good",s);
				return "sell_find_success";
			}else{
				return "sell_find_fail";
			}
			
		}
		
		//添加货品
		public String add() throws Exception{
			Sellers s = new Sellers();
			s.setSid(0);
			s.setSname(request.getParameter("sname"));
			s.setTel(request.getParameter("tel"));
			s.setAddress(request.getParameter("address"));
			SellersDAO sdao = new SellersDAOImpl();
			sdao.addSellers(s);
			return "sell_add_success";
		}
		
		
		//修改货品动作
		public String modify(){
			//获得传递过来的学生编号
			String sid = request.getParameter("sid");
			SellersDAO sdao = new SellersDAOImpl();
			Sellers s = sdao.querySellerBySid(sid);
			//保存在会话中
			session.setAttribute("modify_good", s);
			return "sell_modify_success";
			
		}
		
		//保存修改后货品的动作
		public String save() throws Exception{
			Sellers s = new Sellers();
			s.setSid(Integer.parseInt(request.getParameter("sid")));
			s.setSname(request.getParameter("sname"));
			s.setTel(request.getParameter("tel"));
			s.setAddress(request.getParameter("address"));
			SellersDAO sdao = new SellersDAOImpl();
			sdao.updateSellers(s);
			return "sell_save_success";
		}
		
		

		
		
}
