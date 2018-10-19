package com.erpsystem.crms.data;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.erpsystem.crms.model.LoginModel;
import com.erpsystem.crms.model.MasterEntityModel;

public interface IMasterEntityDaoNew {

	//ADDENTITY
	public long getEaId(String ent_name, String attr_name)throws Exception;

	public long getLatestEntityKeyAndIncrement(String entity_name)throws Exception;
	
	public void addDataInMasterEntity(MasterEntityModel masterEntityModel)throws Exception;

	public long getIdentifier(String entity_name) throws Exception;
	
	

	//UPDATEENTITY
	public long getEaidUpdate(String ent_name, String attr_name)throws Exception;
	
	public long getEntityKeyFromEaid(long eaid)throws Exception;
	
	public void updateDataInMasterEntity(List<MasterEntityModel> listMasterEntityModel)throws Exception;
	
	//GETALLENTITY
	public List <Map<String,String>> getAllPerson(long entityId) throws Exception;

	public long getEntityIdFromEntityName(String entityName)throws Exception;
	
	//GETENTITYBYID
	
	public JSONObject getEntityById (long entity_key, String entity_name ) throws Exception;

	public JSONObject getEntityByEntityId(String entity_name, String attr_name, String value)throws Exception;

	public List<Map<String, String>> getEntityByEntityIdList(long entityId, String entity_name, String attr_name, String value)throws Exception;

	public void addDataInMasterEntity(long entityKey, String ent_name, Map<String, String> jsonMap) throws Exception;
	
	public long getAttrCount(final long entityId) throws Exception;
	
	public List<String> getCount(final String entityName) throws Exception;

	public String validateWithDb(String username, String password) throws Exception;

	public boolean validateToken(String token)throws Exception;

	public long getEntityId(final String entityName) throws Exception;
	
	public void addDataInMasterEntity(long entityid , String entitykey, byte [] image)throws Exception;
	
	public JSONObject getEntityBolbById(long entity_key, long entity_id) throws Exception;
	
	public void addMultiImage(long entityid , String entitykey, byte [] image)throws Exception;
	
	public JSONObject getMultiBolbById(long entity_key, long entity_id) throws Exception;
	/*void updateDataInMasterEntity(long entityKey, String entityName, Map<String, String> jsonMap) throws Exception;*/

	public void updateBlob(long entityid, String entityKey, byte[] image)throws Exception;

	public JSONObject getEntityList(String entity_name) throws Exception;
	
	public  JSONArray getKeyList(String entity_name) throws Exception;
	
	public  JSONObject getEntItem(String entity_name,int entity_key) throws Exception;
	
	public List<Map<String, String>> getEntityByAttrId(long entityId, String entity_name, String attr_name,
			String value) throws Exception;
	
	public JSONObject getEntityKeyFromMob(String value )throws Exception;
	
	public JSONObject getEntityKeyFromVehicleNo(String value)throws Exception;
}	

	
