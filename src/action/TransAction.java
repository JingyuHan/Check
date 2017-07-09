package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import service.BuyersDAO;
import service.GoodsDAO;
import service.SellersDAO;
import service.impl.BuyersDAOImpl;
import service.impl.GoodsDAOImpl;
import service.impl.SellersDAOImpl;
import entity.Buyers;
import entity.Goods;
import entity.Sellers;



import net.sf.json.JSONArray;





public class TransAction extends SuperAction{
	private static final long serialVersionUID = 1L;
	
	public String autoGoods() throws IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String jsonp = request.getParameter("callback");
		List<String[]> list = new ArrayList<String[]>();
		GoodsDAO sdao = new GoodsDAOImpl();
		String gname =new String( request.getParameter("gname").getBytes("iso-8859-1"), "utf-8");
		List<Goods> glist = sdao.queryNameLike(gname);
		for(Goods g:glist){
			list.add(new String[]{g.getGname(),Integer.toString(g.getPrice()),Integer.toString(g.getAmount())});		
		}
		response.getWriter().write(jsonp+"({'result':"+JSONArray.fromObject(list).toString()+"})");
		return null;
	}
	
	public String autoSellers() throws IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String jsonp = request.getParameter("callback");
		List<String[]> list = new ArrayList<String[]>();
		SellersDAO sdao = new SellersDAOImpl();
		String sname =new String( request.getParameter("sname").getBytes("iso-8859-1"), "utf-8");
		List<Sellers> slist = sdao.queryNameLike(sname);
		for(Sellers s:slist){
			list.add(new String[]{s.getSname(),s.getTel(),s.getAddress()});		
		}
		response.getWriter().write(jsonp+"({'result':"+JSONArray.fromObject(list).toString()+"})");
		return null;
	}
	
	public String autoBuyers() throws IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String jsonp = request.getParameter("callback");
		List<String[]> list = new ArrayList<String[]>();
		BuyersDAO bdao = new BuyersDAOImpl();
		String bname =new String( request.getParameter("bname").getBytes("iso-8859-1"), "utf-8");
		List<Buyers> blist = bdao.queryNameLike(bname);
		for(Buyers b:blist){
			list.add(new String[]{b.getBname(),b.getTel(),b.getAddress()});		
		}
		response.getWriter().write(jsonp+"({'result':"+JSONArray.fromObject(list).toString()+"})");
		return null;
	}

}
