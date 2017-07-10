package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Buyers;
import service.BuyersDAO;

public class BuyersDAOImpl implements BuyersDAO{

	@SuppressWarnings("unchecked")
	public List<Buyers> queryAllBuyers() {
		Transaction tx = null;
		List<Buyers> list = null;
		String hql = "";
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		try{
			
			hql = "from Buyers";
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
	public List<Buyers> queryNameLike(String bname){
		Transaction tx = null;
		List<Buyers> list = null;
		String hql = "";
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		try{
			
			hql = "from Buyers where bname like '%"+bname+"%'";
			Query query = session.createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(10);
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
	
	public Buyers queryBuyerByBid(String bid) {
		Transaction tx = null;
		Buyers b = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			b = (Buyers)session.get(Buyers.class, Integer.parseInt(bid));
			tx.commit();
			return b;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return b;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	
	
	public Buyers queryBuyerByBname(String bname) {
		Transaction tx = null;
		String hql = null;
		Buyers b = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Buyers where bname = '"+ bname +"'";
			Query query = session.createQuery(hql);
			b = (Buyers)query.uniqueResult();
			tx.commit();
			return b;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return b;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	public boolean addBuyers(Buyers b) {
		b.setBid(0);
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.save(b);
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

	public boolean updateBuyers(Buyers b) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(b);
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

	

	public boolean deleteBuyers(String bid) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Buyers b = (Buyers)session.get(Buyers.class, Integer.parseInt(bid));
			session.delete(b);
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
