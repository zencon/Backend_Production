package com.erpsystem.crms.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface IErpUtils {

	public static Map<String,String> convertToJsonMap(String json) throws IOException, JSONException {
		
		Map<String,String> hm=new HashMap<String,String>();
		
		if (null != json) {
			
			String data = json.toString();
			String name = null;
			JSONObject item = new JSONObject(data);

			JSONArray list = item.names();
		
			for (int i = 0; i < list.length(); i++) {
				
				name = list.getString(i);
				
				System.out.println(name + ": " + item.get(name).toString());
				
				hm.put(name, item.get(name).toString());
				
			}
			
			System.out.println(hm);
		}
		
		return hm;
	}
	

	public static long generateRandonNo() throws Exception {
		// TODO Auto-generated method stub
		
		Random rand = new Random();

		 int  no = rand.nextInt(50) + 1;
		 
		return no;
	}
	
	
	public static byte[] basestringToString(String imagestring)throws Exception{
		byte[] decodedString = null;
		
        try {
            byte[] name = Base64.getEncoder().encode(imagestring.getBytes());
            decodedString = Base64.getDecoder().decode(new String(name).getBytes("UTF-8"));
            System.out.println(new String(decodedString));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return decodedString ;
	}
}
