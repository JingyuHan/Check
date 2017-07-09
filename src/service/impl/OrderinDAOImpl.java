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
import entity.Order_in;
import entity.Orders;
import entity.Result_in;
import service.GoodsDAO;
import service.OrderinDAO;

public class OrderinDAOImpl implements OrderinDAO{

	public List<Orders> queryAllOrderin(int beginIndex,int num) {
		Transaction tx = null;
		List<Orders> list = null;
		String hql = "";
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Orders where type = true order by oid";
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
	
	

	public List<Order_in> queryOrderinByOiid(String oiid) {
		Transaction tx = null;
		String hql = null;
		List<Order_in> oilist = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Order_in where oiid = '"+ oiid +"'";
			Query query = session.createQuery(hql);
			oilist = query.list();
			tx.commit();
			return oilist;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return oilist;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}
	
	public Orders queryOrderByOiid(String oiid) {
		Transaction tx = null;
		Orders o = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			o = (Orders)session.get(Orders.class, oiid);
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
	
	public boolean addOrder_in(Order_in oi){
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.save(oi);
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
	public boolean deleteOrder_in(Order_in oi){
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.delete(oi);
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
	
	public boolean addOrder_ins(ActionContext context){
		GoodsDAO gdao = new GoodsDAOImpl();
        Map<String, Object>  parameterMap=context.getParameters();     
        String oiid = null;
        String gname = null;
        int num = 0;
        String customer=((String[])parameterMap.get("customer"))[0];
        String user=((String[])parameterMap.get("user"))[0];
        String list[]=((String[])parameterMap.get("list"))[0].split("=");
        String note=((String[])parameterMap.get("note"))[0];
        int price_ac=Integer.parseInt(((String[])parameterMap.get("price_ac"))[0]);
        int price_sh=Integer.parseInt(list[list.length-1]);
        Date date = new Date();
       	oiid = GetOiid(date);
        for(int i = 0;i<list.length-1;i++){
        	String[] oiString = list[i].split("-");
        	gname = oiString[0];
        	num = Integer.parseInt(oiString[2]);
        	Order_in oi = new Order_in(0,oiid,gname,Integer.parseInt(oiString[1]),num);
        	addOrder_in(oi);
        	gdao.Goodsin(gname, num);
		}
        addOrders(new Orders(oiid,true,price_sh,price_ac,user,customer,date,note));
		return true;
        
	        
	        
			
	}
	
	
	
	
	public boolean deleteOrderins(String oiid) {
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<Order_in> list = null;
		String hql = "";
		GoodsDAO gdao = new GoodsDAOImpl();
		try{
			
			tx = session.beginTransaction();
			hql = "from Order_in where oiid = '"+ oiid +"'";
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
		
		for(Order_in oi:list){
			gdao.Goodsout(oi.getGname(),oi.getAmount());
			deleteOrder_in(oi);
		}
		deleteOrders(oiid);
		
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

	
	
	public List<Result_in> Result(String gname,String b_date,String e_date,String user,String customer,String note,int beginIndex,int num) {
		List<Result_in> rilist =new ArrayList<Result_in>();

		
			List<Orders> olist = queryOrderinLike(b_date,e_date,user,customer,note,gname,beginIndex,num);
			for(Orders o:olist){
				List<Order_in> oilist = queryOrderinByOiid(o.getOid());
				
					rilist.add(new Result_in(oilist,o));
				
			}
		
		
		
		
		return rilist;
	}
	
	//在总订单表中模糊查找符合条件的进货订单
	public List<Orders> queryOrderinLike(String b_date,String e_date,String user,String customer,String note,String gname,int beginIndex,int num){
		Transaction tx = null;
		List<Orders> list = null;
		String hql = "";
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Orders where type = true and user like '%"+user+"%' and customer like '%"+customer+"%' and date between '"+b_date+"' and '"+e_date+"' and note like '%"+note+"%' and oid in(SELECT oiid from Order_in WHERE gname LIKE '%"+gname+"%') order by oid";
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

	public String GetOiid(Date date){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
		return "I-"+sdf.format(date);
	}
}
