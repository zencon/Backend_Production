package com.erpsystem.crms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoginSystem {

	@Autowired
	JdbcTemplate jdbctemp;
	
	public LoginSystem() {
		super();
	}

	
	
	public int add_access(int branch_id, int entity_id, int activity)

	{

		//jdbctemp.queryForObject("insert into access_code(branch,entity,activity values(?,?,?)",new Object[] { branch_id, entity_id, activity }, Integer.class);

		jdbctemp.execute("insert into access_code(branch,entity,activity) values(1,2,3)");
		return 11;

	}

	
	
}
