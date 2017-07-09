package service.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Users;
import service.UsersDAO;

public class UsersDAOImpl implements UsersDAO{

	public boolean usersLogin(Users u) {
		// TODO Auto-generated method stub
		
		//事务对象
		Transaction tx = null;
		String hql="";
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx =session.beginTransaction();
			hql = "from Users where username=? and password=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getUsername());
			query.setParameter(1, u.getPassword());
			List list = query.list();
			tx.commit();
			if(list.size()>0){
				
				return true;
			}else{
				
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	public List<Users> queryAllusers() {
		Transaction tx = null;
		List<Users> list = null;
		String hql = "";
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		try{
			
			hql = "from Users";
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

	public void addUsers(Users u) {
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(u);
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
		
	}

	public void deleteUsers(String uid) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Users u = (Users)session.get(Users.class, Integer.parseInt(uid));
			session.delete(u);
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
		
	}

	public Users queryUserByUid(String uid) {
		Transaction tx = null;
		Users u = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			u = (Users)session.get(Users.class, Integer.parseInt(uid));
			tx.commit();
			return u;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return u;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	public void updateUsers(Users u) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(u);
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

}
