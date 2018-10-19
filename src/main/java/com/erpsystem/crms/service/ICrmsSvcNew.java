package com.erpsystem.crms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.erpsystem.crms.model.MasterEntityModel;

public interface ICrmsSvcNew {

	public void addRecordInSystem(final long entityKey,String entityName,Map<String,String> jsonMap)throws Exception;
	
	
	public long getEntityKeyFromSystem(final String entityName) throws Exception;
	
	//public long getEntityKeyFromSystemUpdate(final String entityName)throws Exception;

	public MasterEntityModel getMasterEntityList(String entityName, String key, String value, String entity_key)throws Exception;

	public List<JSONObject> getAllPerson(long entityId) throws Exception;


	public long getEntityIdFromEntityName(String entityName) throws Exception;
	
	public JSONObject getEntityById (long entity_key, String entity_name ) throws Exception;


	public JSONObject getEntityByEntityId(String entity_name, String attr_name, String value) throws Exception;


	public List<JSONObject> getEntityByEntityIdList(long entityId, String entity_name, String attr_name, String value)throws Exception;
	
	public long getAttrCount(final long entityId) throws Exception;
	
	public  List<String> getCount(final String entityName) throws Exception;


	public void updateRecordInSystem(List<MasterEntityModel> masterEntiyModelListt) throws Exception;


	public long validateWithDb(String username, String password) throws Exception;

	public boolean validateToken(String token)throws Exception;
	
	public long getEntityId(final String entityName) throws Exception;
	
	public void addRecordInSystem(long entityid , String entitykey, byte [] image)throws Exception;
	
	public JSONObject getEntityBolbById(long entity_key, long entity_id) throws Exception;
	
	public void addMultiImage(long entityid , String entitykey, byte [] image)throws Exception;

	public JSONObject getMultiBolbById(long entity_key, long entity_id) throws Exception;


	public void updateBlob(long entityid, String entityKey, byte[] image)throws Exception;


	public void updateMultiImage(long entityid, String entitykey, byte[] image)throws Exception;


	public List<JSONObject> getEntityData(long entityId)throws Exception;


	public JSONObject getEntityList(String entity_name)throws Exception;
	
	public  JSONArray getKeyList(String entity_name) throws Exception;
	
	public  JSONObject getEntItem(String entity_name,int entity_key) throws Exception;
	
	public List<JSONObject> getEntityByAttrId(long entityId, String entity_name, String attr_name, String value)
			throws Exception;
	
	public JSONObject getEntityKeyFromMob(String value )throws Exception;
	
	public JSONObject getEntityKeyFromVehicleNo(String value)throws Exception;
}
