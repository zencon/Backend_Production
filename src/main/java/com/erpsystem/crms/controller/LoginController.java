package com.erpsystem.crms.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	JdbcTemplate js;
	/////////////////////////////////////////////////////////////////////////////////////////////////////
/*	@RequestMapping(value="/test",method=RequestMethod.GET)
	public AuthLoginTestPojo ctAuth_test_login() {
		AuthLoginTestPojo auth=new AuthLoginTestPojo();
		auth.setAct_id(2);
		auth.setToken("adasd455");
		auth.setBranch_id(1);
		auth.setUser("test");
		auth.setEntity_id(101);
		
	return auth;
	}
*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/login_test",method=RequestMethod.POST, consumes = { "application/json" }, produces = {
	"application/json" })
		
	public String ctLogin_test(@RequestBody user u, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//js.execute("insert into user_acess_map(userid,accesscode) values(14,21)");
		
		double a=login_test(u.user,u.pass);
		return ""+a;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/add_user",method=RequestMethod.POST, consumes = { "application/json" }, produces = {
	"application/json" })
	public String ctAdd_user(@RequestBody AdduserPojo adj, HttpServletRequest request, HttpServletResponse response ) throws Exception {
	
		//js.execute("insert into user_acess_map(userid,accesscode) values(14,21)");
//		int[] access_list= {1,2,3};
//		double a=add_user("test","test",1,1,access_list);
		double a=add_user(adj.user_name, adj.Pass, adj.branch_id, adj.emp_per_id, adj.access_list);
		if(a==1)
		{
			return "success";
		}
		else
		{
			return "failed";
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/add_access",method=RequestMethod.POST, consumes = { "application/json" }, produces = {
	"application/json" })
	public String ctAddaccessUser(@RequestBody AddAccessPojo acc , HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//js.execute("insert into user_acess_map(userid,accesscode) values(14,21)");
		
		int flag=add_access(acc.branchid,acc.entityid, acc.activity);
		if(flag==1)
		{
		return "success";	
		}
		else
		{
			return "fail";
		}
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/auth_test_login",method=RequestMethod.POST, consumes = { "application/json" }, produces = {
	"application/json" })
	public String ctAuth_test_login(@RequestBody AuthLoginTestPojo auth, HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//js.execute("insert into user_acess_map(userid,accesscode) values(14,21)");
		int flag=auth_test_login(auth.user, auth.token,auth.branch_id,auth.Entity_id,auth.act_id);
	System.err.println(auth.branch_id);
		if(flag==1)
		{
			return "success";
		
		}
		else {
			
		return "fail";	
	}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/user_access_list",method=RequestMethod.POST, consumes = { "application/json" }, produces = {
	"application/json" })
	public List<AccessList> ctuserAccessList(@RequestBody String user, HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//js.execute("insert into user_acess_map(userid,accesscode) values(14,21)");
		
	
		return get_User_access_list(user);	
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/access_list",method=RequestMethod.GET, consumes = { "application/json" }, produces = {
	"application/json" })
	public List<AccessList> ctAccessList(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//js.execute("insert into user_acess_map(userid,accesscode) values(14,21)");
		
	
		return access_list_all();	
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/logout/{user}",method=RequestMethod.GET, consumes = { "application/json" }, produces = {
	"application/json" })
	public String ctlogout(@PathVariable String user, HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		js.update("delete from active_user where user=?",user);
		
	
		return "Success";	
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public double login_test(String user, String pass)

	{

		int flag = 0;

		SqlRowSet rs = js.queryForRowSet("select id from user where name=? and pass=?", user,pass);

		if(rs.next())
		{
			
		flag=rs.getInt(1);
		if (flag > 0)

		{

			double token = addActiveUser(user);

			return token;

		}

		else

		{

			return -1.0;

		}
		}
		else
		{
			return -1;
		}

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public double addActiveUser(String user)

	{

		double token = Math.random();

	/*	PreparedStatement ps;
		ps.setString(1, user);
		ps.setString(2, ""+token);

*/		js.update("insert into active_user(user,token) values(?,?)",user,token);//, new Object[] { user, token },Void.class);

		return token;

	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public int add_user(String user_name, String Pass, int branch_id, int emp_per_id, int[] access_list)

	{

	js.update("insert into user(name,pass,branch,personid) values (?,?,?,?)", user_name,Pass,branch_id,emp_per_id);
		
		for (int i = 0; i < access_list.length; i++)

		{

			map_user_access(user_name, access_list[i]);

		}

		return 1;

	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int map_user_access(String username, int access_id)

	{

		int t = js.update("insert into user_acess_map(userid,accesscode) values (?,?)", username, access_id);

		return 1;

	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int add_access(int branch_id, int entity_id, int activity)
	{

		 js.update("insert into access_code(branch,entity,activity) values(?,?,?)",branch_id, entity_id, activity);

		return 1;

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	public int auth_test_login(String user, String token, int branch_id, int Entity_id, int act_id)
	{
		int flag=-1;

		SqlRowSet active_user_id = js.queryForRowSet("SELECT * FROM active_user WHERE USER=? AND token=? AND TIMESTAMP BETWEEN TIMESTAMP(DATE_SUB(NOW(), INTERVAL 480 MINUTE)) AND TIMESTAMP(NOW())", user, token);//("SELECT * FROM active_user WHERE USER=? AND token=? AND TIMESTAMP BETWEEN TIMESTAMP(DATE_SUB(NOW(), INTERVAL 480 MINUTE)) AND TIMESTAMP(NOW())",new Object[] { user, token }, Integer.class);
//System.out.println(active_user_id);
		if (active_user_id.next())
		{
			
System.out.println("test level2");
			flag = auth_test_access(user, branch_id, Entity_id, act_id);
System.out.println("flag update="+flag);
			
		}

	return flag;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int auth_test_access(String username, int branch_id, int entity_id, int act_id)
	{
		
		System.err.println("username="+username+"branch="+branch_id+"  enity"+entity_id+"activity="+act_id);
		SqlRowSet rs = js.queryForRowSet("select id from user_acess_map where userid=? and accesscode in (select id from access_code where branch=? and entity=? and activity=?)",username,branch_id, entity_id, act_id);
		if(rs.next()) {
			System.out.println("found access code="+rs.getInt(1));
			return 1;
			} else {
				return -1;
			}		

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public List<AccessList> get_User_access_list(String user)

	{
/*
		List<AccessList> access_list = js.queryForList("select branch,entity,activity from access_code where id in(select accesscode from user_acess_map where userid=?)",new Object[] { user }, AccessList.class);
*/
		System.err.println("user="+user);
		 List<AccessList> alist=new ArrayList<AccessList>();

			SqlRowSet access_list = js.queryForRowSet("select branch,entity,activity from access_code where id in(select accesscode from user_acess_map where userid=?)",user);
			while(access_list.next())
			{
				AccessList al=new AccessList(access_list.getInt(2), access_list.getInt(1), access_list.getInt(3));
				
				alist.add(al);
			}
					
					return alist;

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public List<AccessList> access_list_all()

	{
		 List<AccessList> alist=new ArrayList<AccessList>();

		SqlRowSet access_list = js.queryForRowSet("select branch,entity,activity from access_code");//(,
				
while(access_list.next())
{
	AccessList al=new AccessList(access_list.getInt(2), access_list.getInt(1), access_list.getInt(3));
	
	alist.add(al);
}
		
		return alist;

	}

}
////////************/////////////************/////////////////////////////////////////////////////////
class user
{
String user;
String pass;
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}

}

class AdduserPojo
{
	String user_name;
	String Pass;
	int branch_id;
	int emp_per_id;
	int[] access_list;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	public int getEmp_per_id() {
		return emp_per_id;
	}
	public void setEmp_per_id(int emp_per_id) {
		this.emp_per_id = emp_per_id;
	}
	public int[] getAccess_list() {
		return access_list;
	}
	public void setAccess_list(int[] access_list) {
		this.access_list = access_list;
	}
	
}

class AddAccessPojo
{
	int branchid;
	int entityid;
	int activity;
	public int getBranchid() {
		return branchid;
	}
	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
	public int getEntityid() {
		return entityid;
	}
	public void setEntityid(int entityid) {
		this.entityid = entityid;
	}
	public int getActivity() {
		return activity;
	}
	public void setActivity(int activity) {
		this.activity = activity;
	}
	
}

class AuthLoginTestPojo
{
	String user;
	String token;
	int branch_id;
	int Entity_id;
	int act_id;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	public int getEntity_id() {
		return Entity_id;
	}
	public void setEntity_id(int entity_id) {
		Entity_id = entity_id;
	}
	public int getAct_id() {
		return act_id;
	}
	public void setAct_id(int act_id) {
		this.act_id = act_id;
	}
	
}