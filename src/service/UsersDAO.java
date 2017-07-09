package service;

import java.util.List;

import entity.Users;

//用户业务逻辑接口
public interface UsersDAO {
	//用户登录方法
	public boolean usersLogin(Users u);
	//查询所有管理员
	public List<Users> queryAllusers();
	
	public void addUsers(Users u);
	public void deleteUsers(String uid);
	public Users queryUserByUid(String uid);
	public void updateUsers(Users u);
	
}
