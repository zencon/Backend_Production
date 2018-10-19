/**
 * 
 */
package com.erpsystem.crms.data;

import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GENERIC_DATA_SEARCH;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ATTRIBUTE_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_CURRENT_ENTITY_KEY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_EA_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENQUIRY_BY_PROCESS_STATUS;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_IDENTIFIER;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_USER_DTLS_BY_MOB;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.INSERT_MASTER_ENTITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.UPDATE_MASTER_ENTITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_KEY_UPD;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_Designation;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_USER_DTLS_BY_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_PERSON;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENQUIRY_DTLS_BY_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_Enquiry;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_COMM_DTLS_BY_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_COMM;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_TASK_DTLS_BY_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_Task;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_EMP_DTLS_BY_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_EMP;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_DESG;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_BRANCH;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_BRANCH;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENQ_SOURCE;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_ENQ_SOURCE;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENQ_FOR;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_ENQ_FOR;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_CITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_CITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_STATUS_VAL;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_STATUS_VAL;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_COMM_FOR;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_COMM_MEDIUM;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENQUIRY__BY_PERSONID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_COMM_BY_PERSON_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_TARGET_PRODUCT;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_TARGET_PRODUCT;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_Assign_to;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_ASSIGN_TO;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_COMM_MEDIUM;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_EAID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.erpsystem.crms.model.AssignToModel;
import com.erpsystem.crms.model.BranchModel;
import com.erpsystem.crms.model.CityModel;
import com.erpsystem.crms.model.CommMediumModel;
import com.erpsystem.crms.model.CommunicationModel;
import com.erpsystem.crms.model.DesignationModel;
import com.erpsystem.crms.model.EmployeeModel;
import com.erpsystem.crms.model.EnquiryForModel;
import com.erpsystem.crms.model.EnquirySourceModel;
import com.erpsystem.crms.model.InquiryModel;
import com.erpsystem.crms.model.MasterEntityModel;
import com.erpsystem.crms.model.PersonModel;
import com.erpsystem.crms.model.StatusValueModel;
import com.erpsystem.crms.model.TargetProductModel;
import com.erpsystem.crms.model.TaskModel;

/**
 * @author AMRUTA
 *
 */

@Service
public class MasterEntityDaoImpl extends AbstractDatabaseConfig implements IMasterEntityDao {
	
	private static PreparedStatement psmt = null;
	
	private static Connection conn = null;
	
	private int result =  0;
	
	public void addDataInMasterEntity(final MasterEntityModel masterEntityModel) throws Exception {
		
		try {
			
			if(null!=masterEntityModel) {
			
			conn = getDbConn();
			psmt = conn.prepareStatement(INSERT_MASTER_ENTITY);
			psmt.setLong(1, masterEntityModel.getEaid());
			psmt.setLong(2, masterEntityModel.getEntityKey());
			psmt.setString(3, masterEntityModel.getValue());
			result = psmt.executeUpdate();
			//conn.commit();
			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
	public List<PersonModel> searchData(final String entityName,final String searchString) throws Exception {
		
		ResultSet rs = null;
		int counter = 0;
		int eaid = 0;
		
		
		PersonModel personModel = new PersonModel();
		List<PersonModel> personDetailList = new ArrayList<>();
		String srchStr = "'%".concat(searchString).concat("%'");
		
		try {
			
			if(null!=entityName && null!=searchString) {
			
				String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
						"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
						"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
						"e.entity_name='"+entityName+"') order by entity_key asc;";
				
				conn = getDbConn();
				psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
				//psmt.setString(1, entityName);
				//psmt.setString(2, entityName);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					
					counter++;
					eaid = rs.getInt(2);
					
					switch(eaid) {
					
					case 1 : personModel.setPersonId(rs.getLong(3));
						break;
						
					case 2 : personModel.setFirstName(rs.getString(3));
						break;
						
					case 3 : personModel.setLastName(rs.getString(3));
						break;
						
					case 4 : personModel.setMobileNo(rs.getString(3));
						break;
						
					case 5 : personModel.setAddress(rs.getString(3));
						break;
						
					case 6 : personModel.setDob(rs.getString(3));
						break;
						
					case 7 : personModel.setAge(rs.getString(3));
						break;
						
					case 8 : personModel.setAadharNo(rs.getString(3));
						break;
						
					case 9 : personModel.setPanNo(rs.getString(3));
						break;
						
					case 10 : personModel.setMailId(rs.getString(3));
						break;
						
					case 11 : personModel.setOrganisation(rs.getString(3));
						break;
						
					//case 12 : personModel.setOrganisation(organisation);(rs.getString(3));
					case 12 : personModel.setOrgLocation(rs.getString(3));	
						break;
						
					case 13 : personModel.setOrgRole(rs.getString(3));
						break;
						
					case 14 : personModel.setCity(rs.getString(3));
						break;
						
					case 15 : personModel.setPinCode(rs.getString(3));
						break;
						
					case 16 : personModel.setOccupation(rs.getString(3));
						break;	
						
					case 17 : personModel.setBranch(rs.getString(3));
						break;	
						
					case 18 : personModel.setReffId(rs.getLong(3));
						break;	
						
					//default : System.out.println("In default");	
					
					}
					
					if(counter==18) {
						personDetailList.add(personModel);
						personModel = new PersonModel();
						counter = 0;
					}					
				}	
								
				}
			
			return personDetailList;
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
	//////////////////////
	public List<PersonModel> getAllPerson() throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int counter = 0;
		int attrid = 0;
		
		PersonModel personModel = new PersonModel();
		List<PersonModel> personDetailList = new ArrayList<>();
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ALL_PERSON);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				attrid = rs.getInt(2);
				
				switch(attrid) {
				
				case 1 : personModel.setPersonId(rs.getLong(4));
					break;
				case 2 : personModel.setFirstName(rs.getString(4));
				    break;
				
			    case 3 : personModel.setLastName(rs.getString(4));
				    break;
				
			    case 4 : personModel.setMobileNo(rs.getString(4));
				    break;
				
			    case 5 : personModel.setAddress(rs.getString(4));
				    break;
				
			    case 6 : personModel.setDob(rs.getString(4));
				    break;
				
			    case 7 : personModel.setAge(rs.getString(4));
				    break;
				
			    case 8 : personModel.setAadharNo(rs.getString(4));
				    break;
				
			    case 9 : personModel.setPanNo(rs.getString(4));
				    break;
				
			    case 10 : personModel.setMailId(rs.getString(4));
				    break;
				
			    case 11 : personModel.setOrganisation(rs.getString(4));
				    break;
				
			    case 12 : personModel.setOrgLocation(rs.getString(4));	
				    break;
				
			    case 13 : personModel.setOrgRole(rs.getString(4));
				    break;
				
			    case 14 : personModel.setCity(rs.getString(4));
				    break;
				
			    case 15 : personModel.setPinCode(rs.getString(4));
				   break;
				
			    case 16 : personModel.setOccupation(rs.getString(4));
				   break;	
				
			    case 17 : personModel.setBranch(rs.getString(4));
				   break;	
				
			    case 18 : personModel.setReffId(rs.getLong(4));
				   break;		
		}
				if(counter==18) {
					personDetailList.add(personModel);
					personModel = new PersonModel();
					counter = 0;
				}					
			}	
							
		
		
		return personDetailList;
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}

	}
	
	
	public List<InquiryModel> getAllEnquiry() throws Exception {
		ResultSet rs = null;
		int counter = 0;
		int attrid = 0;
		
		InquiryModel inquiryModel = new InquiryModel();
		List<InquiryModel> enquiryDetailsList = new ArrayList<>();
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ALL_Enquiry);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				attrid = rs.getInt(2);
				
				switch(attrid) {
		
				case 19 : inquiryModel.setInquiryId(rs.getLong(4));
				break;
			
			case 20 : inquiryModel.setDatevalue(rs.getString(4));
				break;
			
			case 17 : inquiryModel.setBranch(rs.getLong(4));
				break;
			
			case 21 : inquiryModel.setEnquirysource(rs.getLong(4));
				break;
			
			case 22 : inquiryModel.setEnquiryfor(rs.getLong(4));
				break;
			
			case 23 : inquiryModel.setTargetProduct(rs.getLong(4));
				break;
			
			case 24 : inquiryModel.setDescription(rs.getString(4));
				break;
			
			case 25 : inquiryModel.setProcessStatus(rs.getString(4));
				break;
			
			case 26 : inquiryModel.setFollowupdate(rs.getString(4));
				break;
			
			case 27 : inquiryModel.setRemarkvalue(rs.getString(4));
				break;
			
			case 28 : inquiryModel.setStatusvalue(rs.getLong(4));
				break;
			
			case 29 : inquiryModel.setLastUpdate(rs.getString(4));
				break;
				
			case 1 : inquiryModel.setPersonid(rs.getLong(4));	
				break;
				}
				if(counter==13) {
					enquiryDetailsList.add(inquiryModel);
					inquiryModel = new InquiryModel();
					counter = 0;
				}					
			}	
							
		
		
		return enquiryDetailsList;
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
	}
	
	public List<CommunicationModel> getAllComm() throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int counter = 0;
		int attrid = 0;
		
		CommunicationModel communicationModel = new CommunicationModel();
		List<CommunicationModel> commDetailsList = new ArrayList<>();
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ALL_COMM);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				attrid = rs.getInt(2);
				
				switch(attrid) {
				case 30 : communicationModel.setCommid(rs.getLong(4));
				 break;
				 case 31 : communicationModel.setCommfor(rs.getLong(4));  
				 break;
				 case 32 : communicationModel.setMedium(rs.getLong(4));
				 break;
				 case 20 : communicationModel.setDatevalue(rs.getString(4));
				 break;
				 case 24 : communicationModel.setDescription(rs.getString(4));
				 break;
				 case 25 : communicationModel.setProcessStatus(rs.getString(4));
				 break;
				case 33 : communicationModel.setConclusion(rs.getString(4));
				 break;
				case 26 : communicationModel.setFollowupdate(rs.getString(4));
				break;
				case 27 : communicationModel.setRemarkvalue(rs.getString(4));
				break;
				case 1 :communicationModel.setPersonid(rs.getLong(4));
				break;
				case 19 : communicationModel.setInquiryId(rs.getLong(4));
				break;
				}
				if(counter==12) {
					commDetailsList.add(communicationModel);
					communicationModel = new CommunicationModel();
					counter = 0;
				}					
			}	
							
		
		
		return commDetailsList;
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
	}

	public List<TaskModel> getAllTask() throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int counter = 0;
		int attrid = 0;
		
		TaskModel taskModel = new TaskModel();
		List<TaskModel> taskDetailsList = new ArrayList<>();
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ALL_Task);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				attrid = rs.getInt(2);
				
				switch(attrid) {
				
			
				case 34 : taskModel.setTaskid(rs.getLong(4));
				break;
				case 35 : taskModel.setTask_title(rs.getString(4));; 
				break;
				case 36 : taskModel.setAssignto(rs.getLong(4));
				break;
				case 24 : taskModel.setDescription(rs.getString(4));
				break;
				case 37 : taskModel.setExpdate(rs.getString(4));
				break;
				}
				if(counter==5) {
					taskDetailsList.add(taskModel);
					taskModel = new TaskModel();
					counter = 0;
				}					
			}	
							
		
		
		return taskDetailsList;
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
	}
	
	public List<EmployeeModel> getAllEmp() throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		int counter = 0;
		int attrid = 0;
		
		EmployeeModel employeeModel = new EmployeeModel();
		List<EmployeeModel> empDetailsList = new ArrayList<>();
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ALL_EMP);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				attrid = rs.getInt(2);
				
				switch(attrid) {
				case 38 : employeeModel.setEmployeeid(rs.getLong(4));
				break;
				case 39 : employeeModel.setDateofjoining(rs.getString(4));
				break;
				case 40 : employeeModel.setDesignation(rs.getString(4));
				break;
				case 17 : employeeModel.setBranch((rs.getLong(4)));
				break;
				case 41 : employeeModel.setReportingTo(rs.getLong(4));
				break;
				case 1 : employeeModel.setPersonid(rs.getLong(4));
				break;
				}
				if(counter==5) {
					empDetailsList.add(employeeModel);
					employeeModel = new EmployeeModel();
					counter = 0;
				}					
			}	
							
		
		
		return empDetailsList;
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
	}
	
public List<InquiryModel> searchEnquiryData(final String entityName,final String searchString) throws Exception {
		
		ResultSet rs = null;
		int counter = 0;
		int eaid = 0;
		
		
		InquiryModel inquiryModel = new InquiryModel();
 		List<InquiryModel> enquiryDetailList = new ArrayList<>();
		String srchStr = "'%".concat(searchString).concat("%'");
		
		try {
			
			if(null!=entityName && null!=searchString) {
			
				String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
						"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
						"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
						"e.entity_name='"+entityName+"') order by entity_key asc;";
				
				conn = getDbConn();
				psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
				//psmt.setString(1, entityName);
				//psmt.setString(2, entityName);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					
					counter++;
					eaid = rs.getInt(2);
					
					switch(eaid) {
					
					case 19 : inquiryModel.setInquiryId(rs.getLong(3));
						break;
					
					case 20 : inquiryModel.setDatevalue(rs.getString(3));
						break;
					
					case 21 : inquiryModel.setBranch(rs.getLong(3));
						break;
					
					case 22 : inquiryModel.setEnquirysource(rs.getLong(3));
						break;
					
					case 23 : inquiryModel.setEnquiryfor(rs.getLong(3));
						break;
					
					case 24 : inquiryModel.setTargetProduct(rs.getLong(3));
						break;
					
					case 25 : inquiryModel.setDescription(rs.getString(3));
						break;
					
					case 26 : inquiryModel.setProcessStatus(rs.getString(3));
						break;
					
					case 27 : inquiryModel.setFollowupdate(rs.getString(3));
						break;
					
					case 28 : inquiryModel.setRemarkvalue(rs.getString(3));
						break;
					
					case 29 : inquiryModel.setStatusvalue(rs.getLong(3));
						break;
					
					case 30 : inquiryModel.setLastUpdate(rs.getString(3));
						break;
					case 1 :inquiryModel.setPersonid(rs.getLong(3));
					   break;
					//default : System.out.println("In default");	
					
					}
					
					if(counter==12) {
						enquiryDetailList.add(inquiryModel);
						inquiryModel = new InquiryModel();
						counter = 0;
					}
					
				}
				
				
				
				}
			
			return enquiryDetailList;
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
public List<CommunicationModel> searchCommData(String entityName, String searchString) throws Exception {
	// TODO Auto-generated method stub
	
	ResultSet rs = null;
	int counter = 0;
	int eaid = 0;
	
	
	CommunicationModel communicationModel = new CommunicationModel();
		List<CommunicationModel> commDetailList = new ArrayList<>();
	String srchStr = "'%".concat(searchString).concat("%'");
	
try {
		
		if(null!=entityName && null!=searchString) {
		
			String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
					"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
					"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
					"e.entity_name='"+entityName+"') order by entity_key asc;";
			
			conn = getDbConn();
			psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
			//psmt.setString(1, entityName);
			//psmt.setString(2, entityName);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				eaid = rs.getInt(2);
				
				switch(eaid) {
				
			
				case 31 : communicationModel.setCommid(rs.getLong(3));
				   break;
				 case 32 : communicationModel.setCommfor(rs.getLong(3));  
				 break;
				 case 33 : communicationModel.setMedium(rs.getLong(3));
				 break;
				 case 34 : communicationModel.setDatevalue(rs.getString(3));
				 break;
				 case 35 : communicationModel.setDescription(rs.getString(3));
				 break;
				 case 36 : communicationModel.setProcessStatus(rs.getString(3));
				 break;
				case 37 : communicationModel.setConclusion(rs.getString(3));
				 break;
				case 38 : communicationModel.setFollowupdate(rs.getString(3));
				break;
				case 39 : communicationModel.setRemarkvalue(rs.getString(3));
				break;
				case 40 :communicationModel.setPersonid(rs.getLong(3));
				break;
				case 41 : communicationModel.setInquiryId(rs.getLong(3));
				break;
				//default : System.out.println("In default");	
				
				}
				
				if(counter==11) {
					commDetailList.add(communicationModel);
					communicationModel = new CommunicationModel();
					counter = 0;
				}
				
			}
			
			
			
			}
		
		return commDetailList;
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
}

public List<TaskModel> searchTaskData(String entityName, String searchString) throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int eaid = 0;
	
	
	TaskModel taskModel = new TaskModel();
		List<TaskModel> taskDetailList = new ArrayList<>();
	String srchStr = "'%".concat(searchString).concat("%'");
	
try {
		
		if(null!=entityName && null!=searchString) {
		
			String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
					"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
					"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
					"e.entity_name='"+entityName+"') order by entity_key asc;";
			
			conn = getDbConn();
			psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
			//psmt.setString(1, entityName);
			//psmt.setString(2, entityName);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				eaid = rs.getInt(2);
				
				switch(eaid) {
				
				case 42 : taskModel.setTaskid(rs.getLong(3));
				break;
				case 43 : taskModel.setTask_title(rs.getString(3));
				break;
				case 44 : taskModel.setAssignto(rs.getLong(3));
				break;
				case 45 : taskModel.setDescription(rs.getString(3));
				break;
				case 46 : taskModel.setExpdate(rs.getString(3));
				break;
				
				//default : System.out.println("In default");	
				
				}
				
				if(counter==5) {
					taskDetailList.add(taskModel);
					taskModel = new TaskModel();
					counter = 0;
				}
				
			}
		}
		return taskDetailList;
	
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
}

public List<EmployeeModel> searchEmployeeData(final String entityName,final String searchString) throws Exception {
	
	ResultSet rs = null;
	int counter = 0;
	int eaid = 0;
	
	
	EmployeeModel employeeModel = new EmployeeModel();
		List<EmployeeModel> employeeDetailList = new ArrayList<>();
	String srchStr = "'%".concat(searchString).concat("%'");
	
	try {
		
		if(null!=entityName && null!=searchString) {
		
			String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
					"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
					"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
					"e.entity_name='"+entityName+"') order by entity_key asc;";
			
			conn = getDbConn();
			psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
			//psmt.setString(1, entityName);
			//psmt.setString(2, entityName);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				eaid = rs.getInt(2);
				
				switch(eaid) {
				case 47 : employeeModel.setEmployeeid(rs.getLong(3));;
				   break;
				  
				case 48 : employeeModel.setDateofjoining(rs.getString(3));
				   break;
				   
				case 49 : employeeModel.setDesignation(rs.getString(3));
				   break;
				case 50 : employeeModel.setBranch(rs.getLong(3));   
				   break;
				case 51 : employeeModel.setReportingTo(rs.getLong(3));  
				   break;
				case 52 :employeeModel.setPersonid(rs.getLong(3));
				break;
				//default : System.out.println("In default");	
				
				}
				
				if(counter==6) {
					employeeDetailList.add(employeeModel);
					employeeModel = new EmployeeModel();
					counter = 0;
				}
				
			}
			
			
			
			}
		
		return employeeDetailList;
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
	}
	public void updateDataInMasterEntity(final MasterEntityModel masterEntityModel) throws Exception {
	
		try {
			
			if (null != masterEntityModel) {

				conn = getDbConn();
				psmt = conn.prepareStatement(UPDATE_MASTER_ENTITY);
							
				psmt.setString(1, masterEntityModel.getValue());
				psmt.setLong(2, masterEntityModel.getEaid());
				psmt.setLong(3, masterEntityModel.getEntityKey());
				result = psmt.executeUpdate();

			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
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
			
			while(rs.next()) {
				entityidRs = rs.getString(1);
			}
			
			entityId = Long.parseLong(entityidRs);
			
		} catch(final Exception exception) {
				throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return entityId;
		
		}
	
	public long getAttributeId(final String attributeName) throws Exception {
		
		long attributeId = 0;
		
		String attributeidRs = null;
		ResultSet rs = null;
		
		try {
			
			if (null != attributeName) {

				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ATTRIBUTE_ID);
				psmt.setString(1, attributeName);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					attributeidRs = rs.getString(1);
				}
				
				attributeId = Long.parseLong(attributeidRs);

			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return attributeId;
		
		}
	
	public long getEAId(final long entityId,final long attributeId) throws Exception {
		
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_EA_ID);
				psmt.setLong(2, entityId);
				psmt.setLong(1, attributeId);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					eaidRs = rs.getString(1);
				}
				
				if(null!=eaidRs) {
					eaid = Long.parseLong(eaidRs);
				}else {
					eaid = 0;
				}
				
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return eaid;
		
		}
	

	public long getLatestEntityKeyAndIncrement(final long eaId) throws Exception {
		
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_CURRENT_ENTITY_KEY);
				psmt.setLong(1, eaId);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					eaid = rs.getLong(1);
				}
				
				eaid++;
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return eaid;
		
		}
	
public long getLatestEntityKey(final long eaId) throws Exception {
		
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ENTITY_KEY_UPD);
				psmt.setLong(1, eaId);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					eaid = rs.getLong(1);
				}
				
				
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return eaid;
		
		}
	
	public long getIdentifier(final long eaId) throws Exception {
		
		String entityKeyRs = null;
		long entityKey = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_IDENTIFIER);
				psmt.setLong(1, eaId);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityKey = rs.getLong(1);
				}
		
				//entityKey = Long.parseLong(entityKeyRs);
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return entityKey+1;
		
		}
	

public String[] getPersonDetailsID(final long personid) throws Exception {
		
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_USER_DTLS_BY_ID);
				psmt.setLong(1, personid);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityId = rs.getString(1);
					userDetailsStr = rs.getString(2);
				}
		
				if(null!=userDetailsStr) {
					userData = userDetailsStr.split(",");
				}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return userData;
		
		}


	public String[] getEnquiryDetailsID(long inquiryId) throws Exception {
		// TODO Auto-generated method stub
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ENQUIRY_DTLS_BY_ID);
			psmt.setLong(1, inquiryId);
			rs = psmt.executeQuery();
					
			while(rs.next()) {
				entityId = rs.getString(1);
				userDetailsStr = rs.getString(2);
			}
	
			if(null!=userDetailsStr) {
				userData = userDetailsStr.split(",");
			}
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		}finally {
			closeResources(conn,rs,psmt,null);
		}
	
	return userData;
	
	}
	
	
	public String[] getEnquiryByPersonID(long personid) throws Exception {
		// TODO Auto-generated method stub
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ENQUIRY__BY_PERSONID);
			psmt.setLong(1, personid);
			rs = psmt.executeQuery();
					
			while(rs.next()) {
				entityId = rs.getString(1);
				userDetailsStr = rs.getString(2);
			}
	
			if(null!=userDetailsStr) {
				userData = userDetailsStr.split(",");
			}
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		}finally {
			closeResources(conn,rs,psmt,null);
		}
	
	return userData;
	}
	
	
	public String[] getCommDetailsID(long commid) throws Exception {
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_COMM_DTLS_BY_ID);
			psmt.setLong(1, commid);
			rs = psmt.executeQuery();
					
			while(rs.next()) {
				entityId = rs.getString(1);
				userDetailsStr = rs.getString(2);
			}
	
			if(null!=userDetailsStr) {
				userData = userDetailsStr.split(",");
			}
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		}finally {
			closeResources(conn,rs,psmt,null);
		}
	
	return userData;
	}
	
	public String[] getTaskDetailsID(long taskid) throws Exception {
		// TODO Auto-generated method stub
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_TASK_DTLS_BY_ID);
			psmt.setLong(1, taskid);
			rs = psmt.executeQuery();
					
			while(rs.next()) {
				entityId = rs.getString(1);
				userDetailsStr = rs.getString(2);
			}
	
			if(null!=userDetailsStr) {
				userData = userDetailsStr.split(",");
			}
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		}finally {
			closeResources(conn,rs,psmt,null);
		}
	
	return userData;
	}
	
	
	public String[] getEmpDetailsID(long employeeid) throws Exception {
		// TODO Auto-generated method stub
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_EMP_DTLS_BY_ID);
			psmt.setLong(1, employeeid);
			rs = psmt.executeQuery();
					
			while(rs.next()) {
				entityId = rs.getString(1);
				userDetailsStr = rs.getString(2);
			}
	
			if(null!=userDetailsStr) {
				userData = userDetailsStr.split(",");
			}
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		}finally {
			closeResources(conn,rs,psmt,null);
		}
	
	return userData;
	}
	
	
	public String[] getUserDetailsByMobileNumber(final String mobNo) throws Exception {
		
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_USER_DTLS_BY_MOB);
				psmt.setString(1, mobNo);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityId = rs.getString(1);
					userDetailsStr = rs.getString(2);
				}
		
				if(null!=userDetailsStr) {
					userData = userDetailsStr.split(",");
				}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return userData;
		
		}

public String[] getInquiryByProcessStatus(final String processStatus) throws Exception {
		
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ENQUIRY_BY_PROCESS_STATUS);
				psmt.setString(1, processStatus);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityId = rs.getString(1);
					userDetailsStr = rs.getString(2);
				}
		
				if(null!=userDetailsStr) {
					userData = userDetailsStr.split(",");
				}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return userData;		
		}





public String[] getDesignationById(long desgid) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_Designation);
		psmt.setLong(1, desgid);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}

public String[] getBranchById(long branchid) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_BRANCH);
		psmt.setLong(1, branchid);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}

public String[] getEnqSourceById(long id) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ENQ_SOURCE);
		psmt.setLong(1, id);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}


public String[] getEnqForById(long enqforid) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ENQ_FOR);
		psmt.setLong(1, enqforid);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}


public String[] getCityById(long cityid) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_CITY);
		psmt.setLong(1, cityid);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}

public String[] getCommforById(long commforid) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_COMM_FOR);
		psmt.setLong(1, commforid);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}

	public String[] getStatusValById(long stsvalid) throws Exception {
		// TODO Auto-generated method stub
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;

		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_STATUS_VAL);
			psmt.setLong(1, stsvalid);
			rs = psmt.executeQuery();

			while (rs.next()) {
				entityId = rs.getString(1);
				userDetailsStr = rs.getString(2);
			}

			if (null != userDetailsStr) {
				userData = userDetailsStr.split(",");
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return userData;
	}

	public String[] getCommMediumById(long commMediumid) throws Exception {
		// TODO Auto-generated method stub
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;

		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_COMM_MEDIUM);
			psmt.setLong(1, commMediumid);
			rs = psmt.executeQuery();

			while (rs.next()) {
				entityId = rs.getString(1);
				userDetailsStr = rs.getString(2);
			}

			if (null != userDetailsStr) {
				userData = userDetailsStr.split(",");
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return userData;
	}

public List<DesignationModel> getAllDesignation() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	DesignationModel designationModel = new DesignationModel();
	List<DesignationModel> desigModelList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ALL_DESG);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {

			
			case 42 : designationModel.setDesgid(rs.getLong(4));
			break;
			case 43 : designationModel.setDesgName(rs.getString(4));
			break;
			default : System.out.println("Do nothing");
			
			}
		
			if(counter==2) {
				desigModelList.add(designationModel);
				designationModel = new DesignationModel();
				counter = 0;
			}					
		}	
						
	
	
	return desigModelList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}



}


public List<BranchModel> getAllBranch() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	BranchModel branchModel = new BranchModel();
	List<BranchModel> branchModelList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ALL_BRANCH);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {

			
			case 44 : branchModel.setBranchid(rs.getLong(4));
			break;
			case 45 : branchModel.setTitle(rs.getString(4));
			break;
			case 46 : branchModel.setLocation(rs.getString(4));
			break;
			case 47 : branchModel.setContno(rs.getString(4));
			break;
			default : System.out.println("Do nothing");
			
			}
		
			if(counter==4) {
				branchModelList.add(branchModel);
				branchModel = new BranchModel();
				counter = 0;
			}					
		}	
						
	
	
	return branchModelList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}

}


public List<EnquirySourceModel> getAllEnqSource() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	EnquirySourceModel enquirySourceModel = new EnquirySourceModel();
	List<EnquirySourceModel> enqSourceModelList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ALL_ENQ_SOURCE);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {

			case 48 : enquirySourceModel.setId(rs.getLong(4));;
			break;
			case 49 : enquirySourceModel.setEnquirysource(rs.getString(4));
			break;
			
			default : System.out.println("Do nothing");
			
			}
		
			if(counter==2) {
				enqSourceModelList.add(enquirySourceModel);
				enquirySourceModel = new EnquirySourceModel();
				counter = 0;
			}					
		}	
						
	
	
	return enqSourceModelList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}

}


public List<EnquiryForModel> getAllEnqFor() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	EnquiryForModel enquiryForModel = new EnquiryForModel();
	List<EnquiryForModel> enqForModelList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ALL_ENQ_FOR);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {

			case 50 : enquiryForModel.setEnqforid(rs.getLong(4));;
			break;
			case 51 : enquiryForModel.setEnqfor(rs.getString(4));
			break;
			
			default : System.out.println("Do nothing");
			
			}
		
			if(counter==2) {
				enqForModelList.add(enquiryForModel);
				enquiryForModel = new EnquiryForModel();
				counter = 0;
			}					
		}	
						
	
	
	return enqForModelList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}

}


public List<CityModel> getCity() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	CityModel cityModel = new CityModel();
	List<CityModel> cityModelList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ALL_CITY);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {

			case 52 : cityModel.setCityid(rs.getLong(4));;
			break;
			case 53 : cityModel.setCityName(rs.getString(4));
			break;
			
			default : System.out.println("Do nothing");
			
			}
		
			if(counter==2) {
				cityModelList.add(cityModel);
				cityModel = new CityModel();
				counter = 0;
			}					
		}				
	return cityModelList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}

}


public List<StatusValueModel> getAllStatusVal() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	StatusValueModel statusValueModel = new StatusValueModel();
	List<StatusValueModel> statusvalModelList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ALL_STATUS_VAL);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {

			case 56 : statusValueModel.setStsvalid(rs.getLong(4));
			break;
			case 57 : statusValueModel.setStatusval(rs.getString(4));
			break;
			
			default : System.out.println("Do nothing");
			
			}
		
			if(counter==2) {
				statusvalModelList.add(statusValueModel);
				statusValueModel = new StatusValueModel();
				counter = 0;
			}					
		}				
	return statusvalModelList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
}


public List<CommMediumModel> getAllCommMedium() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	CommMediumModel commMediumModel = new CommMediumModel();
	List<CommMediumModel> commMediumModelList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ALL_COMM_MEDIUM);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {

			case 60 : commMediumModel.setCommMediumid(rs.getLong(4));
			break;
			case 61 : commMediumModel.setCommMedium(rs.getString(4));
			break;
			
			default : System.out.println("Do nothing");
			
			}
		
			if(counter==2) {
				commMediumModelList.add(commMediumModel);
				commMediumModel = new CommMediumModel();
				counter = 0;
			}					
		}	
						
	
	
	return commMediumModelList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}

}

public List<TargetProductModel> getAllTargetProduct() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	TargetProductModel targetProductModel = new TargetProductModel();
	List<TargetProductModel> tagetProductList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ALL_TARGET_PRODUCT);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {

			case 54 : targetProductModel.setProductid(rs.getLong(4));
			break;
			case 55 : targetProductModel.setTproductName(rs.getString(4));
			break;
			
			default : System.out.println("Do nothing");
			
			}
		
			if(counter==2) {
				tagetProductList.add(targetProductModel);
				targetProductModel = new TargetProductModel();
				counter = 0;
			}					
		}	
						
	
	
	return tagetProductList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
}

public String[] getCommByPesronId(long personid) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_COMM_BY_PERSON_ID);
		psmt.setLong(1, personid);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}


public String[] getTargetProductById(long productid) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_TARGET_PRODUCT);
		psmt.setLong(1, productid);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}


public String[] getCommForById(long commforid) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_TARGET_PRODUCT);
		psmt.setLong(1, commforid);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}



public String[] getAssignToById(long assignid) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_Assign_to);
		psmt.setLong(1, assignid);
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
	
} catch(final Exception exception) {
	throw new Exception(exception);
	}finally {
		closeResources(conn,rs,psmt,null);
	}

return userData;
}



public List<AssignToModel> getAllAssignTo() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	AssignToModel assignToModel = new AssignToModel();
	List<AssignToModel> assignToList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_ALL_ASSIGN_TO);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {

			case 63 : assignToModel.setAssignid(rs.getLong(4));
			break;
			case 64 : assignToModel.setAssign_to(rs.getString(4));
			break;
			
			default : System.out.println("Do nothing");
			
			}
		
			if(counter==2) {
				assignToList.add(assignToModel);
				assignToModel = new AssignToModel();
				counter = 0;
			}					
		}	
						
	
	
	return assignToList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
}


public List<CommunicationModel> getAllCommListByPesronId() throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int attrid = 0;
	
	CommunicationModel communicationModel = new CommunicationModel();
	List<CommunicationModel> commDetailsList = new ArrayList<>();
	try {
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_COMM_BY_PERSON_ID);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			
			counter++;
			attrid = rs.getInt(2);
			
			switch(attrid) {
			case 30 : communicationModel.setCommid(rs.getLong(4));
			 break;
			 case 31 : communicationModel.setCommfor(rs.getLong(4));  
			 break;
			 case 32 : communicationModel.setMedium(rs.getLong(4));
			 break;
			 case 20 : communicationModel.setDatevalue(rs.getString(4));
			 break;
			 case 24 : communicationModel.setDescription(rs.getString(4));
			 break;
			 case 25 : communicationModel.setProcessStatus(rs.getString(4));
			 break;
			case 33 : communicationModel.setConclusion(rs.getString(4));
			 break;
			case 26 : communicationModel.setFollowupdate(rs.getString(4));
			break;
			case 27 : communicationModel.setRemarkvalue(rs.getString(4));
			break;
			case 1 :communicationModel.setPersonid(rs.getLong(4));
			break;
			case 19 : communicationModel.setInquiryId(rs.getLong(4));
			break;
			}
			if(counter==12) {
				commDetailsList.add(communicationModel);
				communicationModel = new CommunicationModel();
				counter = 0;
			}					
		}	
						
	
	
	return commDetailsList;
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
}


































	



/*public String[] getAllPerson(PersonModel personModel) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {
		
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ALL_PERSON);
			//psmt.setLong(1, personid);
			//psmt.setString(1,);
			rs = psmt.executeQuery();
					
			while(rs.next()) {
				entityId = rs.getString(1);
				userDetailsStr = rs.getString(2);
			}
	
			if(null!=userDetailsStr) {
				userData = userDetailsStr.split(",");
			}
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		}finally {
			closeResources(conn,rs,psmt,null);
		}
	
	return userData;
	
}*/














	
}

