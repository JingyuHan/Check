package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;

import entity.Goods;
import service.GoodsDAO;

public class GoodsDAOImpl implements GoodsDAO{

	@SuppressWarnings("unchecked")
	public List<Goods> queryAllGoods(int beginIndex,int num) {
		Transaction tx = null;
		List<Goods> list = null;
		String hql = "";
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		try{
			
			hql = "from Goods order by gid";
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

	//输入框自动提示，模糊查找
	public List<Goods> queryNameLike(String gname){
		Transaction tx = null;
		List<Goods> list = null;
		String hql = "";
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		try{
			
			hql = "from Goods where gname like '%"+gname+"%'";
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
	
	public Goods queryGoodByGid(String gid) {
		Transaction tx = null;
		Goods s = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			s = (Goods)session.get(Goods.class, gid);
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

	
	
	public Goods queryGoodByGname(String gname) {
		Transaction tx = null;
		String hql = null;
		Goods g = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Goods where gname = '"+ gname +"'";
			Query query = session.createQuery(hql);
			g = (Goods)query.uniqueResult();
			tx.commit();
			return g;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return g;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	public boolean addGoods(Goods g) {
		g.setGid(getNewGid());//设置新货品编号
		Transaction tx = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			session.save(g);
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

	public boolean updateGoods(Goods g) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(g);
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

	
	
	public boolean Goodsin(String gname, int num) {
		Goods g = queryGoodByGname(gname);
		g.setAmount(g.getAmount() + num);
		return updateGoods(g);
	}

	public boolean Goodsout(String gname, int num) {
		Goods g = queryGoodByGname(gname);
		g.setAmount(g.getAmount() - num);
		return updateGoods(g);
	}

	public boolean deleteGoods(String gid) {
		Transaction tx = null;
		
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Goods s = (Goods)session.get(Goods.class, gid);
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
	
	private String getNewGid(){
		Transaction tx = null;
		String hql = "";
		String gid = null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			//获得当前最大编号
			hql = "select max(gid) from Goods";
			Query query = session.createQuery(hql);
			gid = (String)query.uniqueResult();
			if(gid==null||"null".equals(gid)){
				//给默认的编号
				gid = "HF000001";
				
			}else{
				
				
				String temp = gid.substring(2);//取后七位
				int i = Integer.parseInt(temp);//转成数字类型
				temp = String.valueOf(++i);
				int len = temp.length();
				//凑够七位
				for(int j=0; j<6-len; j++){
					temp="0"+temp;
				}
				
				gid = "HF"+temp;
				
			}
			tx.commit();
			return gid;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return null;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
	}

}
