package com.erpsystem.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class login_base {

	@Autowired
	private JdbcTemplate jdbctemp;

	public login_base() {

	}

	public double login_test(String user, String pass)

	{

		int flag = 0;

		flag = jdbctemp.queryForObject("select id from user where name=? and pass=?", new Object[] { user, pass },
				Integer.class);

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

	public double addActiveUser(String user)

	{

		double token = Math.random();

		int t = jdbctemp.queryForObject("insert into active_user(user,token) values(?,?)", new Object[] { user, token },
				Integer.class);

		return token;

	}

	public int add_user(String user_name, String Pass, int branch_id, int emp_per_id, int[] access_list)

	{

		System.out.println(user_name);

		// jdbctemp.queryForObject("insert into user(name,pass,branch,personid) values
		// (?,?,?,?)",new Object[] {user_name,Pass,branch_id,emp_per_id},Void.class);
		jdbctemp.execute("insert into user(name,pass,branch,personid) values ('abc','aa',1,1)");

		for (int i = 0; i < access_list.length; i++)

		{

			map_user_access(user_name, access_list[i]);

		}

		return 1;

	}

	public int add_access(int branch_id, int entity_id, int activity)

	{

		int t = jdbctemp.queryForObject("insert into access_code(branch,entity,activity values(?,?,?)",
				new Object[] { branch_id, entity_id, activity }, Integer.class);

		return -1;

	}

	public int map_user_access(String username, int access_id)

	{

		int t = jdbctemp.queryForObject("insert into user_acess_map(userid,accesscode) values (?,?)",
				new Object[] { username, access_id }, Integer.class);

		return 1;

	}

	public int auth_test_login(String user, String token, int branch_id, int Entity_id, int act_id)

	{

		int active_user_id = jdbctemp.queryForObject(
				"SELECT * FROM active_user WHERE USER=? AND token=? AND TIMESTAMP BETWEEN TIMESTAMP(DATE_SUB(NOW(), INTERVAL 480 MINUTE)) AND TIMESTAMP(NOW())",
				new Object[] { user, token }, Integer.class);

		if (active_user_id > 0)

		{

			int flag = auth_test_access(user, branch_id, Entity_id, act_id);

			if (flag == 1)

			{

				return 1;

			}

			else

			{

				return -1;

			}

		}

		return -1;

	}

	public int auth_test_access(String username, int branch_id, int entity_id, int act_id)

	{

		int access_code_id = jdbctemp.queryForObject(
				"select id from access_code where branch=? and entity=? and activity=?",
				new Object[] { branch_id, entity_id, act_id }, Integer.class);

		int flag = jdbctemp.queryForObject("select id from user_access_map where userid=? and accesscode=?",
				new Object[] { username, access_code_id }, Integer.class);

		if (flag > 0)

		{

			return 1;

		}

		else

		{

			return -1;

		}

	}

	public List<AccessList> get_User_access_list(String user)

	{

		List<AccessList> access_list = jdbctemp.queryForList(
				"select branch,entity,activity from access_code where id in(select access_code from user_acess_map where userid=?)",
				new Object[] { user }, AccessList.class);

		return access_list;

	}

	public List<AccessList> access_list_all()

	{

		List<AccessList> access_list = jdbctemp.queryForList("select branch,entity,activity from access_code",
				AccessList.class);

		return access_list;

	}

}