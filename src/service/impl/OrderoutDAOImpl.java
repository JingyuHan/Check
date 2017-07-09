package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import db.MyHibernateSessionFactory;
import entity.Order_out;
import entity.Orders;
import entity.Result_out;
import service.GoodsDAO;
import service.OrderoutDAO;

public class OrderoutDAOImpl implements OrderoutDAO{

	public List<Orders> queryAllOrderout(int beginIndex,int num) {
		Transaction tx = null;
		List<Orders> list = null;
		String hql = "";
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Orders where type = false order by oid";
			Query query = session.createQuery(hql);
			query.setFirstResult(beginIndex);
			query.setMaxResults(num);
			list = query.list();
			
			tx.commit();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			
			tx.commit();
			return list;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	public List<Order_out> queryOrderoutByOoid(String ooid) {
		Transaction tx = null;
		String hql = null;
		List<Order_out> oolist = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Order_out where ooid = '"+ ooid +"'";
			Query query = session.createQuery(hql);
			oolist = query.list();
			tx.commit();
			return oolist;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return oolist;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}
	
	public Orders queryOrderByOoid(String ooid) {
		Transaction tx = null;
		Orders o = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			o = (Orders)session.get(Orders.class, ooid);
			tx.commit();
			return o;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return o;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}
	
	public boolean addOrder_out(Order_out oo) {
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.save(oo);
			tx.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}
	
	public boolean deleteOrder_out(Order_out oo){
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.delete(oo);
			tx.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}
	
	public boolean addOrder_outs(ActionContext context) {
		GoodsDAO gdao = new GoodsDAOImpl();
        Map<String, Object>  parameterMap=context.getParameters();     
        String ooid = null;
        String gname = null;
        int num = 0;
        String customer=((String[])parameterMap.get("customer"))[0];
        String user=((String[])parameterMap.get("user"))[0];
        String list[]=((String[])parameterMap.get("list"))[0].split("=");
        String note=((String[])parameterMap.get("note"))[0];
        int price_ac=Integer.parseInt(((String[])parameterMap.get("price_ac"))[0]);
        int price_sh=Integer.parseInt(list[list.length-1]);
        Date date = new Date();
       	ooid = GetOoid(date);
        for(int i = 0;i<list.length-1;i++){
        	String[] ooString = list[i].split("-");
        	gname = ooString[0];
        	num = Integer.parseInt(ooString[2]);
        	Order_out oo = new Order_out(0,ooid,gname,Integer.parseInt(ooString[1]),num);
        	addOrder_out(oo);
        	gdao.Goodsout(gname, num);
		}
        addOrders(new Orders(ooid,false,price_sh,price_ac,user,customer,date,note));
		return true;
	}

	

	public boolean deleteOrderouts(String ooid) {
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Order_out> list = null;
		String hql = "";
		GoodsDAO gdao = new GoodsDAOImpl();
		try{
			
			tx = session.beginTransaction();
			hql = "from Order_out where ooid = '"+ ooid +"'";
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			
			tx.commit();
			return false;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
		
		for(Order_out oo:list){
			gdao.Goodsin(oo.getGname(),oo.getAmount());
			deleteOrder_out(oo);
		}
		deleteOrders(ooid);
		
		return true;
	}

	public boolean addOrders(Orders order) {
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.save(order);
			tx.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}
	
	public boolean deleteOrders(String oid) {
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Orders order = (Orders)session.get(Orders.class, oid);
			session.delete(order);
			tx.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}
	
	public List<Result_out> Result(String gname,String b_date,String e_date,String user,String customer,String note,int beginIndex,int num) {
		List<Result_out> rolist =new ArrayList<Result_out>();
		
		List<Orders> olist = queryOrderoutLike(b_date,e_date,user,customer,note,gname,beginIndex,num);
		for(Orders o:olist){
			List<Order_out> oolist = queryOrderoutByOoid(o.getOid());
			
				rolist.add(new Result_out(oolist,o));
			
		}
		
		
		
		
		return rolist;
	}
	
	//在总订单表中模糊查找符合条件的进货订单
	public List<Orders> queryOrderoutLike(String b_date,String e_date,String user,String customer,String note,String gname,int beginIndex,int num){
		Transaction tx = null;
		List<Orders> list = null;
		String hql = "";
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Orders where type = false and user like '%"+user+"%' and customer like '%"+customer+"%' and date between '"+b_date+"' and '"+e_date+"' and note like '%"+note+"%' and oid in(SELECT ooid from Order_out WHERE gname LIKE '%"+gname+"%') order by oid";
			Query query = session.createQuery(hql);
			query.setFirstResult(beginIndex);
			query.setMaxResults(num);
			list = query.list();
			
			tx.commit();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			
			tx.commit();
			return list;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
		
	}
	
	public String GetOoid(Date date){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
		return "O-"+sdf.format(date);
	}



	

}
