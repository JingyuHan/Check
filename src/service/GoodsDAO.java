package service;

import java.util.List;

import entity.Goods;

public interface GoodsDAO {

	//查询全部货品
	public List<Goods> queryAllGoods(int beginIndex,int num );
	//通过货品编号gid查询货品
	public Goods queryGoodByGid(String gid);
	//通过货品名称gname查询货品
	public Goods queryGoodByGname(String gname);
	//添加货品
	public boolean addGoods(Goods g);
	//修改货品
	public boolean updateGoods(Goods g);
	//删除货品
	public boolean deleteGoods(String gid);
	//通过名字模糊查找，用于输入提示
	public List<Goods> queryNameLike(String gname);
	//增加库存
	public boolean Goodsin(String gname , int num);
	//减少库存
	public boolean Goodsout(String gname , int num);
}
