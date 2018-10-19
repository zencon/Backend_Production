/**
 * 
 */
package com.erpsystem.crms.constants;

/**
 * @author AMRUTA
 *
 */
public class CrmsSqlQueryConstants {
	
	public static final String INSERT_MASTER_ENTITY = "INSERT INTO master_entity (eaid, entity_key,value) VALUES (?,?,?);";

	public static final String UPDATE_MASTER_ENTITY = "UPDATE master_entity SET value = ? where eaid = ? and  entity_key = ?";
	
	public static final String UPDATE_MASTER_BLOB = "UPDATE master_image SET blob_value = ? WHERE entity_id = ? AND entity_key = ?";
		
	public static final String GET_ENTITY_ID = "SELECT entity_id FROM entity_data WHERE name = ?;";
	
	public static final String GET_ATTRIBUTE_ID = "SELECT attribute_id FROM attribute_entity WHERE name = ?;";
	
	public static final String GET_EA_ID = "SELECT eaid FROM ent_attr_view WHERE ent_name = ? and attr_name = ?;";
	
	public static final String GET_EAID = "SELECT eaid FROM ent_attr_view WHERE ent_name = ? and attr_name = ?;";
	
	public static final String GET_EAID_UPADTE = "SELECT eaid FROM ent_attr_view WHERE ent_name = ? and attr_name = ?;";
	
	public static final String GET_CURRENT_ENTITY_KEY = "SELECT max(entity_key) FROM erp_view WHERE entity_name = ?;";
	
	public static final String GET_ENTITY_KEY_FROM_EAID = "SELECT entity_key FROM master_entity WHERE eaid = ?;";
	
	public static final String GET_ENTITY_ID_FROM_ENTITYNAME = "SELECT entity_id FROM erp_view WHERE entity_name = ?;";
	
	public static final String GET_ENTITY_KEY_UPD = "SELECT entity_key FROM master_entity WHERE eaid = ?;";
	
	public static final String GET_IDENTIFIER = "SELECT max(entity_key) FROM erp_view WHERE entity_name = ?;";
	
	public static final String GET_ENTITY_KEY_FROM_MOb = "SELECT attr_name,VALUE,entity_key FROM erp_view WHERE entity_key = (SELECT entity_key FROM erp_view WHERE  eaid= '4' AND VALUE = ? ) AND entity_name = 'person'; ";
	
	public static final String GET_ENTITY_KEY_FROM_VEHICLENO= " SELECT attr_name,VALUE,entity_key FROM erp_view WHERE entity_key = (SELECT entity_key FROM erp_view WHERE  eaid= '84' AND VALUE = ? ) AND entity_name = 'vehicle'; ";

	public static final String VALIDATE_TOKEN ="SELECT attr_name,VALUE FROM erp_view WHERE entity_name = 'login' AND attr_name='token' AND VALUE = ?";
	
	public static final String GET_UNAME_PASS = "SELECT * FROM \r\n" + 
			"(SELECT Q1.ENTITY_KEY AS ENTITY_KEY,Q1.ENTITY_NAME AS ENTITY_NAME,Q1.USERNAME AS USERNAME,Q2.PASS AS PASSWORD FROM ((SELECT ERP_VIEW_USER.ENTITY_ID AS ENTITY_ID,ERP_VIEW_USER.ENTITY_NAME AS ENTITY_NAME,ERP_VIEW_USER.VALUE AS USERNAME,ERP_VIEW_USER.ENTITY_KEY AS ENTITY_KEY \r\n" + 
			"FROM erp_view ERP_VIEW_USER \r\n" + 
			"WHERE entity_name = 'login' AND attr_name='username' AND entity_key \r\n" + 
			"IN((SELECT DISTINCT(entity_key) FROM erp_view))) Q1),\r\n" + 
			"((SELECT ERP_VIEW_PASS.ENTITY_ID AS ENTITY_ID,ERP_VIEW_PASS.ENTITY_NAME AS ENTITY_NAME,ERP_VIEW_PASS.VALUE AS PASS,ERP_VIEW_PASS.ENTITY_KEY AS ENTITY_KEY \r\n" + 
			"FROM erp_view ERP_VIEW_PASS \r\n" + 
			"WHERE entity_name = 'login' AND attr_name='password' AND entity_key \r\n" + 
			"IN((SELECT DISTINCT(entity_key) FROM erp_view))) Q2) WHERE Q1.ENTITY_KEY = Q2.ENTITY_KEY) LOGIN_TABLE WHERE USERNAME=? AND PASSWORD=?;";
	
	public static final String GET_USER_DTLS_BY_MOB = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'person' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 4 and e.entity_name='person'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";

	public static final String GET_USER_DTLS_BY_ID = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'person' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 1 and e.entity_name='person'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_ENQUIRY_DTLS_BY_ID = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'enquiry' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 19 and e.entity_name='enquiry'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_ENQUIRY__BY_PERSONID = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'enquiry' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 19 and e.entity_name='enquiry'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_COMM_DTLS_BY_ID = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'communication' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 30 and e.entity_name='communication'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_COMM_BY_PERSON_ID = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'communication' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 30 and e.entity_name='communication'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_TASK_DTLS_BY_ID = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'task' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 34 and e.entity_name='task'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_EMP_DTLS_BY_ID = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'employee' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 38 and e.entity_name='employee'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_Designation = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'designation' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 42 and e.entity_name='designation'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_BRANCH = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'branch' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 44 and e.entity_name='branch'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_ENQ_SOURCE = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'enquirysource' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 48 and e.entity_name='enquirysource'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_ENQ_FOR = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'enquiryfor' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 50 and e.entity_name='enquiryfor'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_CITY = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'city' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 52 and e.entity_name='city'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_STATUS_VAL = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'statusvalue' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 56 and e.entity_name='statusvalue'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_COMM_FOR = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'commfor' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 58 and e.entity_name='commfor'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_COMM_MEDIUM = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'commmedium' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 60 and e.entity_name='commmedium'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_TARGET_PRODUCT = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'targetproduct' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 54 and e.entity_name='targetproduct'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_Assign_to = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'assignto' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 63 and e.entity_name='assignto'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GET_ALL_PERSON = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =1 order by entity_key,attribute_id asc;";
	
	public static final String GET_ALL_RECORD = "select entity_name,entity_key,attribute_id,attr_name,value from erp_view where entity_id =? order by entity_key,attribute_id asc;";
	
	public static final String 	GET_Record_FROM_ENTITY_KEY = "SELECT attr_name, VALUE FROM erp_view WHERE entity_key = ? AND entity_name= ?;";
	
	public static final String 	GET_Entity_BLOB = "SELECT blob_value FROM master_image WHERE entity_id = ? AND entity_key = ?;";
	
	public static final String GET_ENTITY_LIST = "select entity_key, attr_name,value,link_name from erp_view where entity_name =? and entity_key = ?";
	
	public static final String 	GET_ENTITY_FROM_ENTITY_KEY = "SELECT attr_name, VALUE, entity_key FROM erp_view WHERE entity_name=? AND entity_key IN (SELECT entity_key FROM erp_view WHERE attr_name = ? AND VALUE = ? AND entity_name = ?);";

	public static final String GET_ATTR_COUNT = "SELECT COUNT(attribute_id) FROM link_entity_attribute WHERE entity_id = ?";
	
	public static final String GET_COUNT = "SELECT attr_name FROM ent_attr_view  WHERE ent_name = ?";
	
	public static final String GET_ALL_Enquiry = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =2 order by entity_key,attribute_id asc;";
	
	public static final String GET_ALL_COMM = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =3 order by entity_key,attribute_id asc;";
	
	public static final String GET_ALL_Task = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =4 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_EMP = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =5 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_DESG = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =6 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_BRANCH = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =7 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_ENQ_SOURCE = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =8 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_ENQ_FOR = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =9 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_CITY = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =15 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_STATUS_VAL = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =11 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_TARGET_PRODUCT = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =10 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_ASSIGN_TO = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =14 order by entity_key,attribute_id asc;";

	public static final String GET_ALL_COMM_MEDIUM = "select entity_name,attribute_id,attr_name,value from erp_view where entity_id =13 order by entity_key,attribute_id asc;";

	public static final String GET_ENQUIRY_BY_PROCESS_STATUS = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'enquiry' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 25 and e.entity_name='enquiry'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
			"(SELECT e.entity_name FROM erp_view e where e.value like //%?//% and e.entity_name=?)\r\n" + 
			"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like //%?//% and\r\n" + 
			"e.entity_name=?) order by entity_key asc;";
	
}
