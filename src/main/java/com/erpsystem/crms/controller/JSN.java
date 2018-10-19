/*package com.erpsystem.crms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSN {

	public static void main(String[] args) throws IOException, JSONException {
		// TODO Auto-generated method stub

		String data = readFileToString();
		
		String name = null;
		JSONObject item = new JSONObject(data);
		item.put("entity", "person");
		
		JSONArray  list  = item.names();
		
		//long ent_key = getEntityKey(item.getString("entity"));
		for (int i=0;i<list.length();i++) {
			name = list.getString(i);
			//long eaid = getEAID(item.getString("entity"),name);
			System.out.println(name + ": " + item.get(name).toString() );
		}
		
		
	}
	
	private static String readFileToString() throws IOException {
		
		FileInputStream fis = new FileInputStream(new File("E://ZENCON//data.json"));
		byte[] buffer = new byte[10];
		StringBuilder sb = new StringBuilder();
		while (fis.read(buffer) != -1) {
			sb.append(new String(buffer));
			buffer = new byte[10];
		}
		fis.close();

		String content = sb.toString();
		return content;
	}

}*/