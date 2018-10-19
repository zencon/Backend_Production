package com.erpsystem.crms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.erpsystem.crms.data.IMasterEntityDaoNew;
import com.erpsystem.crms.model.MasterEntityModel;
import com.erpsystem.crms.util.IErpUtils;

@Service
public class CrmsSvcNewImpl implements ICrmsSvcNew {

	public CrmsSvcNewImpl() {

	}

	@Autowired
	IMasterEntityDaoNew masterEntityDaoNew;

	@Transactional(propagation = Propagation.REQUIRED)
	public void addRecordInSystem(final long entityKey, final String ent_name, final Map<String, String> jsonMap)
			throws Exception {

		if (null != masterEntityDaoNew) {
			
		    masterEntityDaoNew.addDataInMasterEntity(entityKey, ent_name, jsonMap);
	

			
		}
	}

	@Override
	public void addRecordInSystem(long entityid, String entitykey, byte[] image) throws Exception {
		// TODO Auto-generated method stub
		if (null != masterEntityDaoNew) {

			masterEntityDaoNew.addDataInMasterEntity(entityid, entitykey, image);
		}

	}

	@Override
	public void updateBlob(long entityid, String entityKey, byte[] image) throws Exception {
		// TODO Auto-generated method stub
		masterEntityDaoNew.updateBlob(entityid, entityKey, image);

	}

	public long getEntityKeyFromSystem(final String entityName) throws Exception {

		long entityKey = masterEntityDaoNew.getIdentifier(entityName);
		long entity_key = entityKey + 1;

		return entity_key;

	}

	public long getEntityKeyFromSystemUpdate(final String entityName) throws Exception {

		long entityKey = masterEntityDaoNew.getIdentifier(entityName);
		return entityKey;

	}

	public List<String> getCount(final String entityName) throws Exception {

		List<String> attrList = masterEntityDaoNew.getCount(entityName);

		return attrList;

	}

	private MasterEntityModel getmasterEntityModelDtlsNew(final String ent_name, final String attr_name,
			final String value) throws Exception {

		// step 1 : find eaid from ent_attr_view where ant_name = ? and attr_name =?;
		final long eaid = masterEntityDaoNew.getEaId(ent_name, attr_name);

		// step 2 : find max entity_key from erp_view where entity_name;
		final long entityKey = masterEntityDaoNew.getLatestEntityKeyAndIncrement(ent_name);

		MasterEntityModel masterDetailsModel = new MasterEntityModel();

		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(entityKey);
		masterDetailsModel.setValue(value);

		return masterDetailsModel;
	}

	private MasterEntityModel getMasterEntityModelForUpdate(final String ent_name, final String attr_name,
			final String value, final String entity_key) throws Exception {

		final long eaid = masterEntityDaoNew.getEaidUpdate(ent_name, attr_name);

		// final long entityKey = masterEntityDaoNew.getEntityKeyFromEaid(eaid);

		MasterEntityModel masterDetailsModel = new MasterEntityModel();

		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(Long.parseLong(entity_key));
		masterDetailsModel.setValue(value);

		return masterDetailsModel;

	}

	/*
	 * @Transactional(propagation = Propagation.REQUIRED) public void
	 * updateRecordInSystem(String entityName, String key, String value, String
	 * entity_key) throws Exception { // TODO Auto-generated method stub if (null !=
	 * masterEntityDaoNew) { MasterEntityModel masterEntityModel =
	 * getMasterEntityModelForUpdate(entityName, key, value,entity_key);
	 * 
	 * masterEntityDaoNew.updateDataInMasterEntity(masterEntityModel); }
	 * 
	 * }
	 */

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRecordInSystem(List<MasterEntityModel> listMasterEntityModel) throws Exception {
		// TODO Auto-generated method stub

		masterEntityDaoNew.updateDataInMasterEntity(listMasterEntityModel);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MasterEntityModel getMasterEntityList(String entityName, String key, String value, String entity_key)
			throws Exception {
		// TODO Auto-generated method stub

		MasterEntityModel masterEntityModel = null;

		if (null != masterEntityDaoNew) {

			masterEntityModel = getMasterEntityModelForUpdate(entityName, key, value, entity_key);

		}

		return masterEntityModel;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<JSONObject> getAllPerson(long entityId) throws Exception {

		List<Map<String, String>> recordMapList = masterEntityDaoNew.getAllPerson(entityId);

		List<JSONObject> jsonObjectList = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();

		for (Map<String, String> jsonMap : recordMapList) {

			for (Map.Entry<String, String> entry : jsonMap.entrySet()) {

				jsonObject.put(entry.getKey(), entry.getValue());
			}

			jsonObjectList.add(jsonObject);
			jsonObject = new JSONObject();
		}

		return jsonObjectList;

	}

	public List<JSONObject> getEntityByEntityIdList(long entityId, String entity_name, String attr_name, String value)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> recordMapList = masterEntityDaoNew.getEntityByEntityIdList(entityId, entity_name,
				attr_name, value);

		List<JSONObject> jsonObjectList = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();

		for (Map<String, String> jsonMap : recordMapList) {

			for (Map.Entry<String, String> entry : jsonMap.entrySet()) {

				jsonObject.put(entry.getKey(), entry.getValue());
			}

			jsonObjectList.add(jsonObject);
			jsonObject = new JSONObject();
		}
		return jsonObjectList;
	}
	
	public List<JSONObject> getEntityByAttrId(long entityId, String entity_name, String attr_name, String value)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> recordMapList = masterEntityDaoNew.getEntityByAttrId(entityId, entity_name,
				attr_name, value);

		List<JSONObject> jsonObjectList = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();

		for (Map<String, String> jsonMap : recordMapList) {

			for (Map.Entry<String, String> entry : jsonMap.entrySet()) {

				jsonObject.put(entry.getKey(), entry.getValue());
			}

			jsonObjectList.add(jsonObject);
			jsonObject = new JSONObject();
		}
		return jsonObjectList;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long getEntityIdFromEntityName(String entityName) throws Exception {
		return masterEntityDaoNew.getEntityIdFromEntityName(entityName);
	}

	///////////////////////////
	@Transactional(propagation = Propagation.REQUIRED)
	public JSONObject getEntityById(long entity_key, String entity_name) throws Exception {
		// TODO Auto-generated method stub

		JSONObject jsonObj = masterEntityDaoNew.getEntityById(entity_key, entity_name);
		return jsonObj;
	}

	@Override
	public JSONObject getEntityBolbById(long entity_key, long entity_id) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonObj = masterEntityDaoNew.getEntityBolbById(entity_key, entity_id);
		return jsonObj;
	}

	public JSONObject getMultiBolbById(long entity_key, long entity_id) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonObj = masterEntityDaoNew.getMultiBolbById(entity_key, entity_id);
		return jsonObj;
	}

	public JSONObject getEntityByEntityId(String entity_name, String attr_name, String value) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonObject = masterEntityDaoNew.getEntityByEntityId(entity_name, attr_name, value);
		return jsonObject;
	}

	@Override
	public long validateWithDb(String username, String password) throws Exception {

		String entityKey = masterEntityDaoNew.validateWithDb(username, password);

		long entity_key = Long.parseLong(entityKey);
		long ranNo = IErpUtils.generateRandonNo();

		String random = Long.toString(ranNo);

		Map<String, String> jsonMap = new HashMap<String, String>();

		jsonMap.put("token", random);
		masterEntityDaoNew.addDataInMasterEntity(entity_key, "login", jsonMap);

		return ranNo;

	}

	public boolean validateToken(String token) throws Exception {

		return masterEntityDaoNew.validateToken(token);

	}

	@Override
	public long getAttrCount(long entityId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEntityId(String entityName) throws Exception {
		// TODO Auto-generated method stub
		return masterEntityDaoNew.getEntityId(entityName);
	}

	@Override
	public void addMultiImage(long entityid, String entitykey, byte[] image) throws Exception {
		// TODO Auto-generated method stub
		if (null != masterEntityDaoNew) {

			masterEntityDaoNew.addMultiImage(entityid, entitykey, image);

		}
	}

	@Override
	public void updateMultiImage(long entityid, String entitykey, byte[] image) throws Exception {
		// TODO Auto-generated method stub
		masterEntityDaoNew.updateBlob(entityid, entitykey, image);

	}

	@Override
	public List<JSONObject> getEntityData(long entityId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getEntityList(String entity_name) throws Exception {
		
		JSONObject list = new JSONObject();
		list = masterEntityDaoNew.getEntityList(entity_name);
		return list;
		// TODO Auto-generated method stub
		
			
		
	}

	@Override
	public JSONObject getEntItem(String entity_name, int entity_key) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject item = new JSONObject();
		item = masterEntityDaoNew.getEntItem(entity_name, entity_key);
		return item;
	}

	@Override
	public JSONArray getKeyList(String entity_name) throws Exception {
		// TODO Auto-generated method stub
		
		JSONArray item = new JSONArray();
		item = masterEntityDaoNew.getKeyList(entity_name);
		return item;
	}

	@Override
	public JSONObject getEntityKeyFromMob(String value)throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject jsonobj = new JSONObject();
		jsonobj = masterEntityDaoNew.getEntityKeyFromMob(value);
				
		return jsonobj;
	}

	@Override
	public JSONObject getEntityKeyFromVehicleNo(String value) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonobj = new JSONObject();
		jsonobj =masterEntityDaoNew.getEntityKeyFromVehicleNo(value);
		return jsonobj;
	}

	

	

}