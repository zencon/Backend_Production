/*package com.auth;

import static com.erpsystem.crms.util.IErpUtils.convertToJsonMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erpsystem.crms.model.LoginModel;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	login_base loginObj;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/test", method = RequestMethod.GET, consumes = { "application/json" }, produces = {
			"application/json" })
	public String login(@RequestBody String inputJson, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			List<AccessList> list;// = new List<AccessList>();
			AccessList l1=new AccessList(1,2,3);
			AccessList l2=new AccessList(3,2,2);
			
			list.add(l1);
			list.add(l2);
			
		int a[]= {1,2,3,4};
		loginObj.add_user("suraj", "sun", 1, 1, a);
		
		
		
		return "success";

	

	}

}
*/