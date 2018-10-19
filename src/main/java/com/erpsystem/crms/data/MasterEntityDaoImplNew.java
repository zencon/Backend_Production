package com.erpsystem.crms.data;

import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_RECORD;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ATTR_COUNT;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_CURRENT_ENTITY_KEY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_EAID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_EAID_UPADTE;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_ID_FROM_ENTITYNAME;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_KEY_FROM_EAID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_IDENTIFIER;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_Record_FROM_ENTITY_KEY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.INSERT_MASTER_ENTITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.UPDATE_MASTER_ENTITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_FROM_ENTITY_KEY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_COUNT;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_UNAME_PASS;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.VALIDATE_TOKEN;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_Entity_BLOB;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_LIST;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_KEY_FROM_MOb;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_KEY_FROM_VEHICLENO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.erpsystem.crms.model.LoginModel;
import com.erpsystem.crms.model.MasterEntityModel;

import javax.sql.DataSource;

@Service
public class MasterEntityDaoImplNew extends AbstractDatabaseConfig implements IMasterEntityDaoNew {

	private PreparedStatement psmt;

	private static Connection conn = null;

	private int result = 0;

	@Autowired
	DataSource dataSource;

	public long getEntityId(final String entityName) throws Exception {

		long entityId = 0;
		String entityidRs = null;
		ResultSet rs = null;

		try {

			if (null != entityName) {

				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ENTITY_ID);
				psmt.setString(1, entityName);
				rs = psmt.executeQuery();

			}

			while (rs.next()) {
				entityidRs = rs.getString(1);
			}

			entityId = Long.parseLong(entityidRs);

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return entityId;

	}

	public long getEaId(final String ent_name, final String attr_name) throws Exception {

		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_EAID)) {

			// conn = getDbConn();
			// conn = dataSource.getConnection();

			// psmt = connn.prepareStatement(GET_EAID);
			psmtt.setString(1, ent_name);
			psmtt.setString(2, attr_name);

			rs = psmtt.executeQuery();

			while (rs.next()) {
				eaidRs = rs.getString(1);
			}

			if (null != eaidRs) {
				eaid = Long.parseLong(eaidRs);
			} else {
				eaid = 0;
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return eaid;

	}

	public long getLatestEntityKeyAndIncrement(final String ent_name) throws Exception {

		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection()) {

			// conn = getDbConn();
			psmt = connn.prepareStatement(GET_CURRENT_ENTITY_KEY);
			psmt.setString(1, ent_name);
			rs = psmt.executeQuery();

			while (rs.next()) {
				eaid = rs.getLong(1);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return eaid;

	}

	public void addDataInMasterEntity(MasterEntityModel masterEntityModel) throws Exception {
		// TODO Auto-generated method stub
		try (Connection connn = dataSource.getConnection()) {
			if (null != masterEntityModel) {
				// conn = getDbConn();
				psmt = connn.prepareStatement(INSERT_MASTER_ENTITY);
				psmt.setLong(1, masterEntityModel.getEaid());
				psmt.setLong(2, masterEntityModel.getEntityKey());
				psmt.setString(3, masterEntityModel.getValue());
				result = psmt.executeUpdate();
				// conn.commit();
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}
	}

	public long getIdentifier(String ent_name) throws Exception {
		// TODO Auto-generated method stub
		String entityKeyRs = null;
		long entityKey = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection()) {

			// conn = getDbConn();
			psmt = connn.prepareStatement(GET_IDENTIFIER);
			psmt.setString(1, ent_name);
			rs = psmt.executeQuery();

			while (rs.next()) {
				entityKey = rs.getLong(1);
			}

			// entityKey = Long.parseLong(entityKeyRs);

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return entityKey;
	}

	public long getEaidUpdate(String ent_name, String attr_name) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_EAID_UPADTE)) {

			// conn = getDbConn();
			// psmt = connn.prepareStatement(GET_EAID_UPADTE);
			psmtt.setString(1, ent_name);
			psmtt.setString(2, attr_name);

			rs = psmtt.executeQuery();

			while (rs.next()) {
				eaidRs = rs.getString(1);
			}

			if (null != eaidRs) {
				eaid = Long.parseLong(eaidRs);
			} else {
				eaid = 0;
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return eaid;

	}

	public long getEntityKeyFromEaid(long eaid) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long eaId = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_KEY_FROM_EAID)) {

			// conn = getDbConn();
			// psmtt = connn.prepareStatement(GET_ENTITY_KEY_FROM_EAID);
			psmtt.setLong(1, eaid);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				eaId = rs.getLong(1);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return eaId;

	}
	
	public JSONObject getEntityKeyFromMob(String value)throws Exception{
		
		
		ResultSet rs = null;
		String attr_name = null;
		long entity_key = 0;
		JSONObject jsonobj = new JSONObject();
		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_KEY_FROM_MOb)) {

			psmtt.setString(1, value);
			rs = psmtt.executeQuery();
			
			while(rs.next()) {
				value = rs.getString(1);
				
				attr_name = rs.getString(1);
				value = rs.getString(2);
				entity_key = rs.getLong(3);
				jsonobj.put(attr_name, value);
			}
		
			jsonobj.put("entity_key", entity_key);
		}
		catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		return jsonobj;
		
	}

public JSONObject getEntityKeyFromVehicleNo(String value)throws Exception{
		
		
		ResultSet rs = null;
		String attr_name = null;
		long entity_key = 0;
		JSONObject jsonobj = new JSONObject();
		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_KEY_FROM_VEHICLENO)) {

			psmtt.setString(1, value);
			rs = psmtt.executeQuery();
			
			while(rs.next()) {
				value = rs.getString(1);
				
				attr_name = rs.getString(1);
				value = rs.getString(2);
				entity_key = rs.getLong(3);
				jsonobj.put(attr_name, value);
			}
		
			jsonobj.put("entity_key", entity_key);
		}
		catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		return jsonobj;
		
	}
	public List<Map<String, String>> getAllPerson(long entityId) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try (Connection connn = dataSource.getConnection();

				PreparedStatement psmtt = connn.prepareStatement(GET_ALL_RECORD)) {
			// conn = getDbConn();
			// conn = dataSource.getConnection();
			// psmt = connn.prepareStatement(GET_ALL_RECORD);
			psmtt.setLong(1, entityId);
			rs = psmtt.executeQuery();

			Map<String, String> recordMap = new HashMap<>();
			List<Map<String, String>> jsonMapList = new ArrayList<>();

			int counter = 0;
			long attrCount = getAttrCount(entityId);
			while (rs.next()) {

				if (counter <= attrCount - 2) {

					recordMap.put(rs.getString(4), rs.getString(5));
					counter++;

					if (counter == attrCount - 1) {
						recordMap.put("entity_key_id", rs.getString(2));
						counter = 0;
						jsonMapList.add(recordMap);
						recordMap = new HashMap<>();
					}
				}
			}
			System.out.println(jsonMapList);
			return jsonMapList;
		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}

	}

	public List<Map<String, String>> getEntityByEntityIdList(long entityId, String entity_name, String attr_name,
			String value) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;

		JSONObject jsonObject = new JSONObject();

		try (Connection connn = dataSource.getConnection();

				PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY)) {

			// conn = dataSource.getConnection();
			// psmt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY);
			psmtt.setString(1, entity_name);
			psmtt.setString(2, attr_name);
			psmtt.setString(3, value);
			psmtt.setString(4, entity_name);
			rs = psmtt.executeQuery();

			Map<String, String> recordMap = new HashMap<>();
			List<Map<String, String>> jsonMapList = new ArrayList<>();

			int counter = 0;

			long attrCount = getAttrCount(entityId);

			while (rs.next()) {

				if (counter <= attrCount - 2) {

					recordMap.put(rs.getString(1), rs.getString(2));
					counter++;
					if (counter == attrCount - 1) {
						recordMap.put("entity_key_id", rs.getString(3));
						counter = 0;
						jsonMapList.add(recordMap);
						recordMap = new HashMap<>();
					}

				}
			}
			return jsonMapList;
		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

	}

	public List<Map<String, String>> getEntityByAttrId(long entityId, String entity_name, String attr_name,
			String value) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;

		JSONObject jsonObject = new JSONObject();

		try (Connection connn = dataSource.getConnection();

				PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY)) {

			// conn = dataSource.getConnection();
			// psmt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY);
			psmtt.setString(1, entity_name);
			psmtt.setString(2, attr_name);
			psmtt.setString(3, value);
			psmtt.setString(4, entity_name);
			rs = psmtt.executeQuery();

			Map<String, String> recordMap = new HashMap<>();
			List<Map<String, String>> jsonMapList = new ArrayList<>();

			int counter = 0;

			long attrCount = getAttrCount(entityId);

			while (rs.next()) {

				if (counter <= attrCount - 2) {

					recordMap.put(rs.getString(1), rs.getString(2));
					counter++;
					if (counter == attrCount - 1) {
						recordMap.put("entity_key", rs.getString(3));
						counter = 0;
						jsonMapList.add(recordMap);
						recordMap = new HashMap<>();
					}

				}
			}
			return jsonMapList;
		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

	}
	
	public long getAttrCount(final long entityId) throws Exception {

		ResultSet rs = null;
		long attrCount = 0;

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_ATTR_COUNT)) {
			// conn = dataSource.getConnection();
			// psmt = connn.prepareStatement(GET_ATTR_COUNT);
			psmtt.setLong(1, entityId);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				attrCount = rs.getLong(1);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}

		return attrCount;
	}

	public List<String> getCount(final String entityName) throws Exception {

		ResultSet rs = null;
		long attrCount = 0;
		List<String> attrList = new ArrayList<>();

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_COUNT)) {

			psmtt.setString(1, entityName);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				attrList.add(rs.getString(1));
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}

		return attrList;
	}

	public long getEntityIdFromEntityName(String entityName) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_ID_FROM_ENTITYNAME)) {

			// conn = dataSource.getConnection();
			// psmt = connn.prepareStatement(GET_ENTITY_ID_FROM_ENTITYNAME);
			psmtt.setString(1, entityName);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				entity_id = rs.getLong(1);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return entity_id;
	}

	//////////////////////////////////////////////////////////////////////////////
	public JSONObject getEntityById(long entity_key, String entity_name) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;
		String attr_name = null;
		String value = null;

		JSONObject jsonObject = new JSONObject();

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_Record_FROM_ENTITY_KEY)) {

			// conn = dataSource.getConnection();
			// psmt = connn.prepareStatement(GET_Record_FROM_ENTITY_KEY);
			psmtt.setLong(1, entity_key);
			psmtt.setString(2, entity_name);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				attr_name = rs.getString(1);
				value = rs.getString(2);
				jsonObject.put(attr_name, value);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		return jsonObject;

	}

	@Override
	public JSONObject getEntityBolbById(long entity_key, long entity_id) throws Exception {
		// TODO Auto-generated method stub
		String blob_value = null;
		// long entity_id = 0;
		ResultSet rs = null;
		String attr_name = null;
		String value = null;

		JSONObject jsonObject = new JSONObject();

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_Entity_BLOB)) {

			// conn = dataSource.getConnection();
			// psmt = connn.prepareStatement(GET_Record_FROM_ENTITY_KEY);
			psmtt.setLong(2, entity_key);
			psmtt.setLong(1, entity_id);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				blob_value = rs.getString(1);

				jsonObject.put("blobValue", blob_value);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		return jsonObject;

	}

	@Override
	public JSONObject getMultiBolbById(long entity_key, long entity_id) throws Exception {
		// TODO Auto-generated method stub
		String blob_value = null;
		// long entity_id = 0;
		ResultSet rs = null;
		String attr_name = null;
		String value = null;

		JSONArray jArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_Entity_BLOB)) {

			// conn = dataSource.getConnection();
			// psmt = connn.prepareStatement(GET_Record_FROM_ENTITY_KEY);
			psmtt.setLong(2, entity_key);
			psmtt.setLong(1, entity_id);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				blob_value = rs.getString(1);

				jArray.put(blob_value);

				jsonObject.put("blobValue", jArray);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		return jsonObject;

	}

	@Override
	public JSONObject getEntityByEntityId(String entity_name, String attr_name, String value) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;

		JSONObject jsonObject = new JSONObject();

		try (Connection connn = dataSource.getConnection();
				PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY)) {

			// conn = dataSource.getConnection();
			// psmt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY);
			psmtt.setString(1, entity_name);
			psmtt.setString(2, attr_name);
			psmtt.setString(3, value);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				// entity_name = rs.getString(1);
				attr_name = rs.getString(1);
				value = rs.getString(2);
				jsonObject.put(attr_name, value);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		return jsonObject;
	}

	public void updateDataInMasterEntity(MasterEntityModel masterEntityModel) throws Exception {
		// TODO Auto-generated method stub
		try (Connection connn = dataSource.getConnection()) {

			if (null != masterEntityModel) {

				// conn = getDbConn();
				psmt = connn.prepareStatement(UPDATE_MASTER_ENTITY);

				psmt.setString(1, masterEntityModel.getValue());
				psmt.setLong(2, masterEntityModel.getEaid());
				psmt.setLong(3, masterEntityModel.getEntityKey());

				result = psmt.executeUpdate();

			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}

	}

	@Override
	public void updateDataInMasterEntity(List<MasterEntityModel> listMasterEntityModel) throws Exception {

		PreparedStatement statement = null;

		try (Connection connn = dataSource.getConnection()) {

			// conn = dataSource.getConnection();
			statement = connn.prepareStatement(UPDATE_MASTER_ENTITY);

			final int batchSize = 150;
			int count = 0;

			for (MasterEntityModel masterEntityModel : listMasterEntityModel) {

				statement.setString(1, masterEntityModel.getValue());
				statement.setLong(2, masterEntityModel.getEaid());
				statement.setLong(3, masterEntityModel.getEntityKey());

				statement.addBatch();

				if (++count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			statement.executeBatch();

		} catch (Exception exception) {
			throw new Exception(exception);
		} finally {
			if (null != statement) {
				statement.close();
			}

			if (null != conn) {
				conn.close();
			}

		}

	}

	@Override
	public void addDataInMasterEntity(long entityKey, String entityName, Map<String, String> jsonMap) throws Exception {

		PreparedStatement statement = null;

		try (Connection connn = dataSource.getConnection()) {

			// conn = dataSource.getConnection();
			statement = connn.prepareStatement("INSERT INTO master_entity (eaid, entity_key,value) VALUES (?,?,?)");

			final int batchSize = 150;
			int count = 0;

			for (Map.Entry<String, String> entry : jsonMap.entrySet()) {

				// if (null != entityName && !entry.getKey().equalsIgnoreCase("entityName") ) {
				if (null != entityName && !entry.getKey().equalsIgnoreCase("entity_name")) {

					MasterEntityModel masterEntityModel = getmasterEntityModelDtlsNew(entityName, entry.getKey(),
							entry.getValue());
					
					

					long eaid = Long.valueOf(masterEntityModel.getEaid());

					statement.setLong(1, eaid);
					statement.setLong(2, entityKey);
					statement.setString(3, masterEntityModel.getValue());

					statement.addBatch();

				}

				if (++count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			statement.executeBatch();

		} catch (Exception exception) {
			throw new Exception(exception);
		} finally {
			if (null != statement) {
				statement.close();
			}

			if (null != conn) {
				conn.close();
			}

		}

	}

	public void addMultiImage(long entityid, String entitykey, byte[] image) throws Exception {

		PreparedStatement statement = null;

		try (Connection connn = dataSource.getConnection()) {

			// conn = dataSource.getConnection();
			statement = connn
					.prepareStatement("INSERT INTO master_image (entity_id, entity_key,blob_value) VALUES (?,?,?)");

			final int batchSize = 150;
			int count = 0;

			statement.setLong(1, entityid);
			statement.setString(2, entitykey);
			statement.setBytes(3, image);
			statement.addBatch();

			if (++count % batchSize == 0) {
				statement.executeBatch();
			}

			statement.executeBatch();

		} catch (Exception exception) {
			throw new Exception(exception);
		} finally {
			if (null != statement) {
				statement.close();
			}

			if (null != conn) {
				conn.close();
			}

		}

	}

	public void addDataInMasterEntity(long entityid, String entitykey, byte[] image) throws Exception {

		PreparedStatement statement = null;

		try (Connection connn = dataSource.getConnection()) {

			// conn = dataSource.getConnection();
			statement = connn
					.prepareStatement("INSERT INTO master_image (entity_id, entity_key,blob_value) VALUES (?,?,?)");

			final int batchSize = 150;
			int count = 0;
			statement.setLong(1, entityid);
			statement.setString(2, entitykey);
			statement.setBytes(3, image);
			statement.addBatch();

			if (++count % batchSize == 0) {
				statement.executeBatch();
			}

			statement.executeBatch();

		} catch (Exception exception) {
			throw new Exception(exception);
		} finally {
			if (null != statement) {
				statement.close();
			}

			if (null != conn) {
				conn.close();
			}

		}

	}
	
	

	@Override
	public void updateBlob(long entityid, String entityKey, byte[] image) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement statement = null;

		try (Connection connn = dataSource.getConnection()) {

			// conn = dataSource.getConnection();
			statement = connn
					.prepareStatement("UPDATE master_image SET blob_value = ? WHERE entity_id = ? AND entity_key = ?");

			final int batchSize = 150;
			int count = 0;
			statement.setLong(2, entityid);
			statement.setString(3, entityKey);
			statement.setBytes(1, image);
			statement.addBatch();

			if (++count % batchSize == 0) {
				statement.executeBatch();
			}

			statement.executeBatch();

		} catch (Exception exception) {
			throw new Exception(exception);
		} finally {
			if (null != statement) {
				statement.close();
			}

			if (null != conn) {
				conn.close();
			}

		}

	}

	private MasterEntityModel getmasterEntityModelDtlsNew(final String ent_name, final String attr_name,
			final String value) throws Exception {

		// step 1 : find eaid from ent_attr_view where ant_name = ? and attr_name =?;
		final long eaid = getEaId(ent_name, attr_name);

		// step 2 : find max entity_key from erp_view where entity_name;
		final long entityKey = getLatestEntityKeyAndIncrement(ent_name);

		MasterEntityModel masterDetailsModel = new MasterEntityModel();

		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(entityKey);
		masterDetailsModel.setValue(value);

		return masterDetailsModel;
	}

	@Override
	public String validateWithDb(String username, String password) throws Exception {

		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection();

				PreparedStatement psmtt = connn.prepareStatement(GET_UNAME_PASS)) {

			psmtt.setString(1, username);
			psmtt.setString(2, password);

			rs = psmtt.executeQuery();

			while (rs.next()) {

				String entityKey = rs.getString(1);

				if (null != entityKey && entityKey.length() > 0) {
					return entityKey;
				} else {
					return null;
				}

			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}

		return null;

	}

	@Override
	public boolean validateToken(String token) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection();

				PreparedStatement psmtt = connn.prepareStatement(VALIDATE_TOKEN)) {

			psmtt.setString(1, token);

			rs = psmtt.executeQuery();

			while (rs.next()) {

				String tokenChk = rs.getString(1);

				if (null != tokenChk) {
					return true;
				}
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}
		return false;
	}
	
	public  JSONArray getKeyList(String entity_name) throws Exception {
		JSONArray 		  list = new JSONArray();
		//PreparedStatement stmt = null;
		ResultSet 		  rset = null;
		System.out.println("hi");
		try (Connection connn = dataSource.getConnection();
				
		PreparedStatement stmt = connn.prepareStatement("select distinct entity_key from erp_view where entity_name=? order by entity_key")){
		System.out.println("stmt");
		stmt.setString(1, entity_name);
		rset = stmt.executeQuery();
		
		
		while (rset.next()) {
			list.put(rset.getInt("entity_key"));
		}
		return list;
		
		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rset, psmt, null);
		}
	}
	
	public  JSONObject getEntItem(String entity_name,int entity_key) throws Exception {
		JSONObject 		  item = new JSONObject();
		JSONObject 		  node = new JSONObject();
		PreparedStatement stmt  = null;
		ResultSet rs = null;
		

		try(Connection connn = dataSource.getConnection();){
						
				if ( entity_key == 0 ) {
					stmt = connn.prepareStatement("select 0 as entity_key,attr_name,'' as value,link_name from attr_list where entity_name = ? ");
					stmt.setString(1, entity_name);
				} else {
					stmt = connn.prepareStatement("select entity_key,attr_name,value,link_name from erp_view where entity_name = ? and entity_key = ?");
					stmt.setString(1, entity_name);
					stmt.setInt(2, entity_key);
				}
				/*PreparedStatement stmt = connn.prepareStatement("select entity_key,attr_name,value,link_name from erp_view where entity_name = ? and entity_key = ?")){
				{
		stmt.setString(1, entity_name);
		stmt.setInt(2, entity_key);
*/		rs = stmt.executeQuery();

	    while (rs.next()) {

	    	if (rs.getString("link_name") == null ) {
	    		
	    		item.put(rs.getString("attr_name"), rs.getString("value"));	    		

	    	} else {
	    		
	    		node = getEntItem(rs.getString("link_name"),rs.getInt("value"));
	    		if (node.length() == 0 ) {
	    			node = getEntItem(rs.getString("link_name"),0);
	    			item.put(rs.getString("attr_name"),node);
	    		}
	    		node.put("entity_key", rs.getInt("value"));
	    		item.put(rs.getString("attr_name"),node);
	    		
	    	} 

	    }
	    
	    return item;
				
	} catch (final Exception exception) {
		throw new Exception(exception);
	} 
		
	}/*finally {
		closeResources(conn, rs, psmt, null);
	}*/
			
	
	@Override
	public JSONObject getEntityList(String entity_name) throws Exception {
		// TODO Auto-generated method stub

		String eaidRs = null;
		long entity_id = 0;

		JSONObject item = new JSONObject();
		JSONObject node = new JSONObject();
		JSONArray list = new JSONArray();
		JSONArray elst = new JSONArray();
		// PreparedStatement stmt = null;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection();

			PreparedStatement stmt = connn.prepareStatement("select * from attr_list where entity_name = ?")) {
			stmt.setString(1, entity_name);

			rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("link_name") == null ) {
		    		
		    		item.put(rs.getString("attr_name"), new JSONArray());	    		

		    	} else {
		    		list = new JSONArray();
		    		System.out.println(rs.getString("link_name"));
		    		
		    		elst = getKeyList(rs.getString("link_name"));
		    		System.out.println("elst" + elst);
		    		
		    		for (int i=0;i<elst.length();i++) {
		    			
		    			node = getEntItem(rs.getString("link_name"),elst.getInt(i));
		    			node.put("entity_id", elst.getInt(i));
		    			list.put(node);
		    			System.out.println(node);
		    		}
		    		item.put(rs.getString("attr_name"),list);
		    	} 

		    }
		    
		    return item;

			

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		
	}
	
	

}
