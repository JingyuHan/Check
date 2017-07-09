package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Sellers;
import service.SellersDAO;

public class SellersDAOImpl implements SellersDAO{

	@SuppressWarnings("unchecked")
	public List<Sellers> queryAllSellers() {
		Transaction tx = null;
		List<Sellers> list = null;
		String hql = "";
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		try{
			
			hql = "from Sellers";
			Query query = session.createQuery(hql);
			
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

	//输入框自动提示，模糊查找
	public List<Sellers> queryNameLike(String sname){
		Transaction tx = null;
		List<Sellers> list = null;
		String hql = "";
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		try{
			
			hql = "from Sellers where sname like '%"+sname+"%'";
			Query query = session.createQuery(hql);
			
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
	
	public Sellers querySellerBySid(String sid) {
		Transaction tx = null;
		Sellers s = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			s = (Sellers)session.get(Sellers.class, Integer.parseInt(sid));
			tx.commit();
			return s;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return s;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	
	
	public Sellers querySellerBySname(String sname) {
		Transaction tx = null;
		String hql = null;
		Sellers s = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Sellers where sname = '"+ sname +"'";
			Query query = session.createQuery(hql);
			s = (Sellers)query.uniqueResult();
			tx.commit();
			return s;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return s;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	public boolean addSellers(Sellers s) {
		s.setSid(0);
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.save(s);
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

	public boolean updateSellers(Sellers s) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(s);
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

	

	public boolean deleteSellers(String sid) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Sellers s = (Sellers)session.get(Sellers.class, Integer.parseInt(sid));
			session.delete(s);
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

}
