package action;




import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UsersDAO;
import service.impl.UsersDAOImpl;

import com.opensymphony.xwork2.ModelDriven;
import entity.Users;

public class UsersAction extends SuperAction implements ModelDriven<Users>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user = new Users();
	
	@SkipValidation
	//查询所有管理员
	public String query(){
		UsersDAO udao = new UsersDAOImpl();
		List<Users> list = udao.queryAllusers();
		//放进session中
		if(list!=null && list.size()>0){
			session.setAttribute("users_list", list);
			
		}
		return "query_success";
		
	}

	@SkipValidation
	//添加货品
	public String add() throws Exception{
		Users u = new Users();
		u.setUid(0);
		u.setUsername(request.getParameter("username"));
		u.setPassword(request.getParameter("password"));
		UsersDAO udao = new UsersDAOImpl();
		udao.addUsers(u);
		return "add_success";
	}
	@SkipValidation
	//删除货品的动作
	public String delete(){
		UsersDAO udao = new UsersDAOImpl();
		String uid = request.getParameter("uid");
		udao.deleteUsers(uid);
		return "delete_success";
		
	}
	@SkipValidation
	//修改货品动作
	public String modify(){
		//获得传递过来的学生编号
		String uid = request.getParameter("uid");
		UsersDAO udao = new UsersDAOImpl();
		Users u = udao.queryUserByUid(uid);
		//保存在会话中
		session.setAttribute("modify_good", u);
		return "modify_success";
		
	}
	@SkipValidation
	//保存修改后货品的动作
	public String save() throws Exception{
		Users u = new Users();
		u.setUid(Integer.parseInt(request.getParameter("uid")));
		u.setUsername(request.getParameter("username"));
		u.setPassword(request.getParameter("passowrd"));
		UsersDAO udao = new UsersDAOImpl();
		udao.updateUsers(u);
		return "save_success";
	}
	
	//用户登录动作
	public String login(){
		UsersDAO udao = new UsersDAOImpl();
		if(udao.usersLogin(user)){
			//在session中保存登录成功的用户名
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		}else{
			return "login_failure";
		}
	}
	
	@SkipValidation
	//用户注销功能
	public String logout(){
		if(session.getAttribute("loginUserName")!=null){
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}

	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if("".equals(user.getUsername().trim())){
			this.addFieldError("usernameError","用户名不能为空！");
		}
		if(user.getPassword().length()<6){
			this.addFieldError("passwordError",	 "密码长度不少于6位！");
		}
		
	}

	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
