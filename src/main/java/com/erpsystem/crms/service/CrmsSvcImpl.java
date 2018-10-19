/**
 * 
 */
package com.erpsystem.crms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.erpsystem.crms.data.IMasterEntityDao;
import com.erpsystem.crms.model.AssignToModel;
import com.erpsystem.crms.model.BranchModel;
import com.erpsystem.crms.model.CityModel;
import com.erpsystem.crms.model.CommForModel;
import com.erpsystem.crms.model.CommMediumModel;
import com.erpsystem.crms.model.CommunicationModel;
import com.erpsystem.crms.model.DesignationModel;
import com.erpsystem.crms.model.EmployeeModel;
import com.erpsystem.crms.model.EnquiryForModel;
import com.erpsystem.crms.model.EnquirySourceModel;
import com.erpsystem.crms.model.EntityDetailsModel;
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
public class CrmsSvcImpl implements ICrmsSvc {
	
	@Autowired
	IMasterEntityDao masterEntityDao;
	
	public void addUserInSystem(final String entityName,final String attrName,final String attrValue,boolean isIdentity) 
			throws Exception {
		
		if(null!=masterEntityDao) {			
			EntityDetailsModel entityDetailsModel = getmasterEntityModelDtls(entityName,attrName,attrValue);
			MasterEntityModel masterEntityModel = new MasterEntityModel();
			
			if(isIdentity) {
				long eaid = Long.valueOf(entityDetailsModel.getEaid());
				long entityKey = masterEntityDao.getIdentifier(eaid);
				masterEntityModel.setEaid(eaid);
				masterEntityModel.setEntityKey(entityKey);
				masterEntityModel.setValue(Long.toString(entityKey));
				
				
			} else {
				
				masterEntityModel.setEaid(entityDetailsModel.getEaid());
				masterEntityModel.setEntityKey(entityDetailsModel.getEntityKey());
				masterEntityModel.setValue(attrValue);
				
			}			
			masterEntityDao.addDataInMasterEntity(masterEntityModel);
		}		
	}
	

	
	
	

	public void addData(final String entityName,final String attrName,final String attrValue,boolean isIdentity) 
			throws Exception {
		
		if(null!=masterEntityDao) {			
			EntityDetailsModel entityDetailsModel = getmasterEntityModelDtls(entityName,attrName,attrValue);
			MasterEntityModel masterEntityModel = new MasterEntityModel();
			
			if(isIdentity) {
				long eaid = Long.valueOf(entityDetailsModel.getEaid());
				long entityKey = masterEntityDao.getIdentifier(eaid);
				masterEntityModel.setEaid(eaid);
				masterEntityModel.setEntityKey(entityKey);
			
				masterEntityModel.setValue(Long.toString(entityKey));
				//inquiryModel.setPersonid(masterEntityModel.getEntityKey());
				//masterEntityModel.setEntityKey(inquiryModel.getPersonid());
				
			} else {
				
				masterEntityModel.setEaid(entityDetailsModel.getEaid());
				masterEntityModel.setEntityKey(entityDetailsModel.getEntityKey());
				//masterEntityModel.setEntityKey(inquiryModel.getPersonid());
				//inquiryModel.setPersonid(masterEntityModel.getEntityKey());
				masterEntityModel.setValue(attrValue);
				
			}			
			masterEntityDao.addDataInMasterEntity(masterEntityModel);
		}		
	}
	
	public  void updateUserInSystem(final String entityName,final String attrName,final String attrValue,final long id, boolean isIdentity)
			throws Exception {
if(null!=masterEntityDao) {
	
			EntityDetailsModel entityDetailsModel = getmasterEntityModelDtlsUpd(entityName,attrName,attrValue,id);
			MasterEntityModel masterEntityModel = new MasterEntityModel();
			
			if(isIdentity) {
				long eaid = Long.valueOf(entityDetailsModel.getEaid());
				long entityKey = masterEntityDao.getIdentifier(eaid);
				masterEntityModel.setEaid(eaid);
				masterEntityModel.setEntityKey(id);
				masterEntityModel.setValue(Long.toString(entityKey));
				
			} else {
				
				masterEntityModel.setEaid(entityDetailsModel.getEaid());
				masterEntityModel.setEntityKey(entityDetailsModel.getEntityKey());
				masterEntityModel.setValue(attrValue);
			}
			masterEntityDao.updateDataInMasterEntity(masterEntityModel);
		
	}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<PersonModel> searchDataSvc(final String entityName,final String searchString) throws Exception {
		
		List<PersonModel> personModel = masterEntityDao.searchData(entityName, searchString);
		return personModel;
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<InquiryModel> searchEnquirySvc(final String entityName,final String searchString) throws Exception {
		
		List<InquiryModel> inquiryModel = masterEntityDao.searchEnquiryData(entityName, searchString);
		return inquiryModel;
		
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CommunicationModel> searchCommunicationSvc(String entityName, String searchString) throws Exception {
		// TODO Auto-generated method stub
		List<CommunicationModel> communicationModel = masterEntityDao.searchCommData(entityName, searchString);
		return communicationModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TaskModel> searchTaskSvc(String entityName, String searchString) throws Exception {
		// TODO Auto-generated method stub
		List<TaskModel> taskModel = masterEntityDao.searchTaskData(entityName, searchString);
		return taskModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeModel> searchEmployeeSvc(final String entityName, final String searchString) throws Exception{
		
		List<EmployeeModel> employeeModel = masterEntityDao.searchEmployeeData(entityName, searchString);
		return employeeModel;
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void orchestrateUserCreation(final PersonModel personModel) 
			throws Exception {
		
		addUserInSystem("person", "personid", "",true);
		addUserInSystem("person", "fname", personModel.getFirstName(),false);
		addUserInSystem("person", "lname", personModel.getLastName(),false);
		addUserInSystem("person", "mobno", personModel.getMobileNo(),false);
		addUserInSystem("person", "address", personModel.getAddress(),false);
		addUserInSystem("person", "dob","" + personModel.getDob(),false);
		addUserInSystem("person", "age", "" + personModel.getAge(),false);
		addUserInSystem("person", "aadharno", personModel.getAadharNo(),false);
		addUserInSystem("person", "panno", personModel.getPanNo(),false);
		addUserInSystem("person", "mailid", personModel.getMailId(),false);
		addUserInSystem("person", "organisation", personModel.getOrganisation(),false);
		addUserInSystem("person", "orglocation", personModel.getOrgLocation(),false);
		addUserInSystem("person", "orgrole", personModel.getOrgRole(),false);
		addUserInSystem("person", "city",""+ personModel.getCity(),false);
		addUserInSystem("person", "pincode", personModel.getPinCode(),false);
		addUserInSystem("person", "occupation", personModel.getOccupation(),false);
		addUserInSystem("person", "branch","" + personModel.getBranch(),false);
		addUserInSystem("person", "reffid","" + personModel.getReffId(),false);
		
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void enquiryCreation(final InquiryModel inquiryModel) 
			throws Exception {
		
		addUserInSystem("enquiry", "inquiryid", "",true);
		addUserInSystem("enquiry", "datevalue", inquiryModel.getDatevalue(),false);
		addUserInSystem("enquiry", "branch", ""+inquiryModel.getBranch(),false);
		addUserInSystem("enquiry", "enquirysource",""+ inquiryModel.getEnquirysource(),false);
		addUserInSystem("enquiry", "enquiryfor", ""+inquiryModel.getEnquiryfor(),false);
		addUserInSystem("enquiry", "targetproduct",""+inquiryModel.getTargetProduct(),false);
		addUserInSystem("enquiry", "description", inquiryModel.getDescription(),false);
		addUserInSystem("enquiry", "processstatus", inquiryModel.getProcessStatus(),false);
		addUserInSystem("enquiry", "followupdate",inquiryModel.getFollowupdate(),false);
		addUserInSystem("enquiry", "remarkvalue", inquiryModel.getRemarkvalue(),false);
		addUserInSystem("enquiry", "statusvalue",""+ inquiryModel.getStatusvalue(),false);
		addUserInSystem("enquiry", "lastupdate", inquiryModel.getLastUpdate(),false);
		addUserInSystem("enquiry", "personid",""+ inquiryModel.getPersonid(), false);
		
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void assignToCreation(AssignToModel assignToModel) throws Exception {
		// TODO Auto-generated method stub
		addUserInSystem("assignto", "assignid", "", true);
		addUserInSystem("assignto", "assign_to", assignToModel.getAssign_to(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void personUpdate(final PersonModel personModel)
			throws Exception {
		updateUserInSystem("person", "personid",""+ personModel.getPersonId(),personModel.getPersonId(),false);
		updateUserInSystem("person", "fname", personModel.getFirstName(),personModel.getPersonId(),false);
		updateUserInSystem("person", "lname", personModel.getLastName(),personModel.getPersonId(),false);
		updateUserInSystem("person", "mobno", personModel.getMobileNo(),personModel.getPersonId(),false);
		updateUserInSystem("person", "address", personModel.getAddress(),personModel.getPersonId(),false);
		updateUserInSystem("person", "dob","" + personModel.getDob(),personModel.getPersonId(),false);
		updateUserInSystem("person", "age", "" + personModel.getAge(),personModel.getPersonId(),false);
		updateUserInSystem("person", "aadharno", personModel.getAadharNo(),personModel.getPersonId(),false);
		updateUserInSystem("person", "panno", personModel.getPanNo(),personModel.getPersonId(),false);
		updateUserInSystem("person", "mailid", personModel.getMailId(),personModel.getPersonId(),false);
		updateUserInSystem("person", "organisation", personModel.getOrganisation(),personModel.getPersonId(),false);
		updateUserInSystem("person", "orglocation", personModel.getOrgLocation(),personModel.getPersonId(),false);
		updateUserInSystem("person", "orgrole", personModel.getOrgRole(),personModel.getPersonId(),false);
		updateUserInSystem("person", "city",""+ personModel.getCity(),personModel.getPersonId(),false);
		updateUserInSystem("person", "pincode", personModel.getPinCode(),personModel.getPersonId(),false);
		updateUserInSystem("person", "occupation", personModel.getOccupation(),personModel.getPersonId(),false);
		updateUserInSystem("person", "branch","" + personModel.getBranch(),personModel.getPersonId(),false);
		updateUserInSystem("person", "reffid","" + personModel.getReffId(),personModel.getPersonId(),false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)    
	public void enquiryUpdate(InquiryModel inquiryModel) 
				throws Exception{
		updateUserInSystem("enquiry", "inquiryid",""+ inquiryModel.getInquiryId(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "datevalue", inquiryModel.getDatevalue(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "branch",""+ inquiryModel.getBranch(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "enquirysource",""+ inquiryModel.getEnquirysource(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "enquiryfor",""+ inquiryModel.getEnquiryfor(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "targetproduct", ""+inquiryModel.getTargetProduct(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "description", inquiryModel.getDescription(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "processstatus", inquiryModel.getProcessStatus(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "followupdate", inquiryModel.getFollowupdate(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "remarkvalue", inquiryModel.getRemarkvalue(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "statusvalue", ""+inquiryModel.getStatusvalue(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "lastupdate", inquiryModel.getLastUpdate(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "personid", ""+inquiryModel.getPersonid(), inquiryModel.getInquiryId(), false);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void commUpdate(CommunicationModel communicationModel)
			throws ClassNotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		updateUserInSystem("communication", "commid", ""+communicationModel.getCommid(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "commfor", ""+communicationModel.getCommfor(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "medium", ""+communicationModel.getMedium(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "datevalue",""+ communicationModel.getDatevalue(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "description", communicationModel.getDescription(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "processstatus", communicationModel.getProcessStatus(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "conclusion", communicationModel.getConclusion(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "followupdate", ""+communicationModel.getFollowupdate(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "remarkvalue", communicationModel.getRemarkvalue(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "personid", ""+communicationModel.getPersonid(), communicationModel.getCommid(), false);
		updateUserInSystem("communication", "inquiryid", ""+communicationModel.getInquiryId(), communicationModel.getCommid(), false);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void taskUpdate(TaskModel taskModel) throws ClassNotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		updateUserInSystem("task", "taskid", ""+taskModel.getTaskid(), taskModel.getTaskid(), false);
		updateUserInSystem("task", "title", ""+taskModel.getTask_title(), taskModel.getTaskid(), false);
		updateUserInSystem("task", "assignto", ""+taskModel.getAssignto(), taskModel.getTaskid(), false);
		updateUserInSystem("task", "description", ""+taskModel.getDescription(), taskModel.getTaskid(), false);
		updateUserInSystem("task", "expdate", ""+taskModel.getExpdate(), taskModel.getTaskid(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void employeeUpdate(EmployeeModel employeeModel) throws Exception {
		// TODO Auto-generated method stub
		updateUserInSystem("employee", "employeeid", "" +employeeModel.getEmployeeid(), employeeModel.getEmployeeid(), false);
		updateUserInSystem("employee", "dateofjoining",employeeModel.getDateofjoining(), employeeModel.getEmployeeid(), false);
		updateUserInSystem("employee", "designation",employeeModel.getDesignation(), employeeModel.getEmployeeid(), false);
		updateUserInSystem("employee", "branch", "" +employeeModel.getBranch(), employeeModel.getEmployeeid(), false);
		updateUserInSystem("employee", "reportingto", "" +employeeModel.getReportingTo(), employeeModel.getEmployeeid(), false);
		updateUserInSystem("employee", "personid", "" +employeeModel.getPersonid(), employeeModel.getEmployeeid(), false);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void statusValueUpdate(StatusValueModel statusValueModel) throws Exception {
		// TODO Auto-generated method stub
		updateUserInSystem("statusvalue", "stsvalid", ""+statusValueModel.getStsvalid(), statusValueModel.getStsvalid(), false);
		updateUserInSystem("statusvalue", "statusval", statusValueModel.getStatusval(), statusValueModel.getStsvalid(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void communicationCreation(final CommunicationModel communicationModel)
	          throws Exception{
		addUserInSystem("communication", "commid", "", true);
		addUserInSystem("communication", "communicationfor", ""+communicationModel.getCommfor(), false);
		addUserInSystem("communication", "medium", ""+communicationModel.getMedium(), false);
		addUserInSystem("communication", "datevalue",""+ communicationModel.getDatevalue(), false);
		addUserInSystem("communication", "description", communicationModel.getDescription(), false);
		addUserInSystem("communication", "processstatus", communicationModel.getProcessStatus(), false);
		addUserInSystem("communication", "conclusion", communicationModel.getConclusion(), false);
		addUserInSystem("communication", "followupdate", ""+communicationModel.getFollowupdate(), false);
		addUserInSystem("communication", "remarkvalue", communicationModel.getRemarkvalue(), false);
		addUserInSystem("communication", "personid", ""+communicationModel.getPersonid(), false);
		addUserInSystem("communication", "inquiryid", ""+communicationModel.getInquiryId(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void taskCreation(final TaskModel taskModel) 
			throws Exception{
		addUserInSystem("task", "taskid", "", true);
		addUserInSystem("task", "task_title", taskModel.getTask_title(), false);
		addUserInSystem("task", "assignto",""+ taskModel.getAssignto(), false);
		addUserInSystem("task", "description", taskModel.getDescription(), false);
		addUserInSystem("task", "expdate",""+ taskModel.getExpdate(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void employeeCreation(final EmployeeModel employeeModel)
			throws Exception{
		addUserInSystem("employee", "employeeid", "", true);
		addUserInSystem("employee", "dateofjoining", ""+employeeModel.getDateofjoining(), false);
		addUserInSystem("employee", "designation", employeeModel.getDesignation(), false);
		addUserInSystem("employee", "branch", ""+ employeeModel.getBranch(), false);
		addUserInSystem("employee", "reportingto", ""+ employeeModel.getReportingTo(), false);
		addUserInSystem("employee", "personid", ""+ employeeModel.getPersonid(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void designationCreation(final DesignationModel designationModel)
			throws ClassNotFoundException, SQLException, Exception {
		addUserInSystem("designation", "desgnid", "", true);
		addUserInSystem("designation", "desgName", designationModel.getDesgName(), false);
				
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void branchCreation(final BranchModel branchModel) 
			throws ClassNotFoundException, SQLException, Exception {
		addUserInSystem("branch", "branchid", "", true);
		addUserInSystem("branch", "title", branchModel.getTitle(), false);
		addUserInSystem("branch", "location", branchModel.getLocation(), false);
		addUserInSystem("branch", "contno", branchModel.getContno(), false);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void enquirysourceCreation(final EnquirySourceModel enquirySourceModel)
		throws ClassNotFoundException, SQLException, Exception {
		addUserInSystem("enquirysource", "id", "", true);
		addUserInSystem("enquirysource", "enqsource", enquirySourceModel.getEnquirysource(), false);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void enquiryforCreation(final EnquiryForModel enquiryForModel)
		throws ClassNotFoundException, SQLException, Exception {
		addUserInSystem("enquiryfor", "enqforid", "", true);
		addUserInSystem("enquiryfor", "enqfor", enquiryForModel.getEnqfor(), false);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void targetProductCreation(final TargetProductModel targetProductModel)
			throws ClassNotFoundException, SQLException, Exception {
			addUserInSystem("targetproduct", "productid", "", true);
			addUserInSystem("targetproduct", "tproductName", targetProductModel.getTproductName(), false);
		}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void statusvalueCreation(final StatusValueModel statusValueModel)
			throws ClassNotFoundException, SQLException, Exception {
			addUserInSystem("statusvalue", "stsvalid", "", true);
			addUserInSystem("statusvalue", "statusval", statusValueModel.getStatusval(), false);
			
		}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void cityCreation(final CityModel cityModel) throws Exception {
		// TODO Auto-generated method stub
		addUserInSystem("city", "cityid", "", true);
		addUserInSystem("city", "cityName", cityModel.getCityName(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void commForCreation(final CommForModel commForModel) throws Exception {
		// TODO Auto-generated method stub
		addUserInSystem("commfor", "commforid", "", true);
		addUserInSystem("commfor", "commfor", commForModel.getCommfor(), false);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void commMediumCreation(final CommMediumModel commMediumModel) throws Exception {
		// TODO Auto-generated method stub
		addUserInSystem("commmedium", "commMediumid", "", true);
		addUserInSystem("commmedium", "commMedium", commMediumModel.getCommMedium(), false);
		
	}
	
	private EntityDetailsModel getmasterEntityModelDtls(final String entityName,final String attrName,final String attrValue) 
			throws Exception {
		
		/** Step 1 : Fetch the entity id on the basis of entity name from entity data table.*/
		final long entityId = masterEntityDao.getEntityId(entityName);
		/** Step 2 : Fetch the attribute id on the basis of name from the attribute entity table.*/
		final long attributeId = masterEntityDao.getAttributeId(attrName);
		/** Step 3 : Fetch eaid on the basis of entity id and attribute id */
		final long eaid = masterEntityDao.getEAId(entityId, attributeId);
		/** Step 4 : Fetch the latest entity key from the master_entity table on the basis of eaid fetched in step 3 */
		final long entityKey = masterEntityDao.getLatestEntityKeyAndIncrement(eaid);
		
		EntityDetailsModel masterDetailsModel = new EntityDetailsModel();
		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(entityKey);
		masterDetailsModel.setEntityId(entityId);
		masterDetailsModel.setAttributeId(attributeId);
	
		return masterDetailsModel;
		
	}
	
	/*private EntityDetailsModel getmasterEntityModelDtlsNew(final String ent_name, final String attr_name) {
		// TODO Auto-generated method stub
		*//** Step 1 : Fetch the entity id on the basis of entity name from entity data table.*//*
		//final long entityId = masterEntityDao.getEntityId(entityName);
		*//** Step 2 : Fetch the attribute id on the basis of name from the attribute entity table.*//*
		//final long attributeId = masterEntityDao.getAttributeId(attrName);
		*//** Step 3 : Fetch eaid on the basis of entity id and attribute id *//*
		final long eaid = masterEntityDao.getEaId(ent_name, attr_name);
		*//** Step 4 : Fetch the latest entity key from the master_entity table on the basis of eaid fetched in step 3 *//*
		final long entityKey = masterEntityDao.getLatestEntityKeyAndIncrement(eaid);
		
		EntityDetailsModel masterDetailsModel = new EntityDetailsModel();
		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(entityKey);
		masterDetailsModel.setEntityId(entityId);
		masterDetailsModel.setAttributeId(attributeId);
	
		return masterDetailsModel;
	}*/
	
	private EntityDetailsModel getmasterEntityModelDtlsUpd(final String entityName,final String attrName,final String attrValue,long id) 
			throws Exception {
		
		/** Step 1 : Fetch the entity id on the basis of entity name from entity data table.*/
		final long entityId = masterEntityDao.getEntityId(entityName);
		/** Step 2 : Fetch the attribute id on the basis of name from the attribute entity table.*/
		final long attributeId = masterEntityDao.getAttributeId(attrName);
		/** Step 3 : Fetch eaid on the basis of entity id and attribute id */
		final long eaid = masterEntityDao.getEAId(entityId, attributeId);
		/** Step 4 : Fetch the latest entity key from the master_entity table on the basis of eaid fetched in step 3 */
		//final long entityKey = masterEntityDao.getLatestEntityKey(eaid);
		
		EntityDetailsModel masterDetailsModel = new EntityDetailsModel();
		
		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(id);
		masterDetailsModel.setEntityId(entityId);
		masterDetailsModel.setAttributeId(attributeId);
	
		return masterDetailsModel;
		
	}
	
	public PersonModel getPersonDetailsID(long personid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		PersonModel personModel = new PersonModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getPersonDetailsID(personid);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : personModel.setPersonId(Long.parseLong(userField));
			break;
			case 1 : personModel.setFirstName(userField);
			break;
			case 2 : personModel.setLastName(userField);
			break;
			case 3 : personModel.setMobileNo(userField);
			break;
			case 4 : personModel.setAddress(userField);
			break;
			case 5 : personModel.setDob(userField);
			break;
			case 6 : personModel.setAge(userField);
			break;
			case 7 : personModel.setAadharNo(userField);
			break;
			case 8 : personModel.setPanNo(userField);
			break;
			case 9 : personModel.setMailId(userField);
			break;
			case 10 : personModel.setOrganisation(userField);
			break;
			case 11 : personModel.setOrgLocation(userField);
			break;
			case 12 : personModel.setOrgRole(userField);
			break;
			case 13 : personModel.setCity(userField);
			break;
			case 14 : personModel.setPinCode(userField);
			break;
			case 15 : personModel.setOccupation(userField);
			break;
			case 16 : personModel.setBranch(userField);
			break;
			case 17 : personModel.setReffId(Long.parseLong(userField));
		    break;
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		return personModel;
	}
	/////////////
	@Transactional(propagation = Propagation.REQUIRED)
	public InquiryModel getEnquiryDetailsID(long inquiryId) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		InquiryModel inquiryModel = new InquiryModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getEnquiryDetailsID(inquiryId);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			
			switch(cnt) {
			case 0 : inquiryModel.setInquiryId(Long.parseLong(userField));
			break;
			case 1 : inquiryModel.setDatevalue(userField);
			break;
			case 2 : inquiryModel.setBranch(Long.parseLong(userField));
			break;
			case 3 : inquiryModel.setEnquirysource(Long.parseLong(userField));
			break;
			case 4 : inquiryModel.setEnquiryfor(Long.parseLong(userField));
			break;
			case 5 : inquiryModel.setTargetProduct(Long.parseLong(userField));
			break;
			case 6: inquiryModel.setDescription(userField);
			break;
			case 7: inquiryModel.setProcessStatus(userField);
			break;
			case 8 : inquiryModel.setFollowupdate(userField);
			break;
			case 9: inquiryModel.setRemarkvalue(userField);
			break;
			case 10: inquiryModel.setStatusvalue(Long.parseLong(userField));
			break;
			case 11: inquiryModel.setLastUpdate(userField);
			break;
			case 12 :inquiryModel.setPersonid(Long.parseLong(userField));
			break;
			default : System.out.println("Do nothing");
			}
			++cnt;
		}
		return inquiryModel;

	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public InquiryModel getEnquiryByPersonID(long personid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		InquiryModel inquiryModel = new InquiryModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getEnquiryByPersonID(personid);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			
			switch(cnt) {
			case 0 : inquiryModel.setInquiryId(Long.parseLong(userField));
			break;
			case 1 : inquiryModel.setDatevalue(userField);
			break;
			case 2 : inquiryModel.setBranch(Long.parseLong(userField));
			break;
			case 3 : inquiryModel.setEnquirysource(Long.parseLong(userField));
			break;
			case 4 : inquiryModel.setEnquiryfor(Long.parseLong(userField));
			break;
			case 5 : inquiryModel.setTargetProduct(Long.parseLong(userField));
			break;
			case 6: inquiryModel.setDescription(userField);
			break;
			case 7: inquiryModel.setProcessStatus(userField);
			break;
			case 8 : inquiryModel.setFollowupdate(userField);
			break;
			case 9: inquiryModel.setRemarkvalue(userField);
			break;
			case 10: inquiryModel.setStatusvalue(Long.parseLong(userField));
			break;
			case 11: inquiryModel.setLastUpdate(userField);
			break;
			case 12 :inquiryModel.setPersonid(Long.parseLong(userField));
			break;
			default : System.out.println("Do nothing");
			}
			++cnt;
		}
		return inquiryModel;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CommunicationModel getCommDetailsID(long commid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		CommunicationModel communicationModel = new CommunicationModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getCommDetailsID(commid);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			switch(cnt) {
			case 0 : communicationModel.setCommid(Long.parseLong(userField));
			break;
			case 1 : communicationModel.setCommfor(Long.parseLong(userField));
			break;
			case 2 : communicationModel.setMedium(Long.parseLong(userField));
			break;
			case 3 : communicationModel.setDatevalue(userField);
			break;
			case 4 : communicationModel.setDescription(userField);
			break;
			case 5 : communicationModel.setProcessStatus(userField);
			break;
			case 6 : communicationModel.setConclusion(userField);
			break;
			case 7 : communicationModel.setFollowupdate(userField);
			break;
			case 8 :communicationModel.setRemarkvalue(userField);
			break;
			case 9 : communicationModel.setPersonid(Long.parseLong(userField));
			break;
			case 10: communicationModel.setInquiryId(Long.parseLong(userField));
			break;
			default : System.out.println("Do nothing");
			}
			++cnt;
		}
		return communicationModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public TaskModel getTaskDetailsID(long taskid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		TaskModel taskModel = new TaskModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getTaskDetailsID(taskid);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			switch(cnt) {
			case 0 : taskModel.setTaskid(Long.parseLong(userField));
			break;
			case 1 : taskModel.setTask_title(userField);
			break;
			case 2 : taskModel.setAssignto(Long.parseLong(userField));
			break;
			case 3 : taskModel.setDescription(userField);
			break;
			case 4 : taskModel.setExpdate(userField);
			break;
			default : System.out.println("Do nothing");
			}
			++cnt;
			
			}
		return taskModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public EmployeeModel getEmpDetailsID(long employeeid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		EmployeeModel employeeModel = new EmployeeModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getEmpDetailsID(employeeid);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			switch(cnt) {
			case 0 : employeeModel.setEmployeeid(Long.parseLong(userField));
			break;
			case 1 : employeeModel.setDateofjoining(userField);
			break;
			case 2 : employeeModel.setDesignation(userField);
			break;
			case 3 : employeeModel.setBranch(Long.parseLong(userField));
			break;
			case 4 : employeeModel.setReportingTo(Long.parseLong(userField));
			break;
			case 5 : employeeModel.setPersonid(Long.parseLong(userField));
			break;
			default : System.out.println("Do nothing");
			}
			++cnt;
			
			}
		return employeeModel;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CommunicationModel> getCommListByPesronId() throws Exception {
		// TODO Auto-generated method stub
		List<CommunicationModel> communicationModel = masterEntityDao.getAllCommListByPesronId();
		return communicationModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<PersonModel> getAllPerson() throws Exception {
		
		List<PersonModel> personModel = masterEntityDao.getAllPerson();
		return personModel;
		
	}
	
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<InquiryModel> getAllEnquiry() throws Exception {
		// TODO Auto-generated method stub
		List<InquiryModel> inquiryModel = masterEntityDao.getAllEnquiry();
		
		return inquiryModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CommunicationModel> getAllComm() throws Exception {
		// TODO Auto-generated method stub
		List<CommunicationModel> communicationModel = masterEntityDao.getAllComm();
		return communicationModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TaskModel> getAllTask() throws Exception {
		// TODO Auto-generated method stub
		List<TaskModel> taskModel = masterEntityDao.getAllTask();
		return taskModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeModel> getAllEmp() throws Exception {
		// TODO Auto-generated method stub
		List <EmployeeModel> employeeModel = masterEntityDao.getAllEmp();
		return employeeModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public PersonModel getPersonDataByMobId(final String mobNo) throws Exception {
		
		String[] userDataArr = null;
		PersonModel personModel = new PersonModel();
		
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getUserDetailsByMobileNumber(mobNo);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : personModel.setPersonId(Long.parseLong(userField));
			break;
			case 1 : personModel.setFirstName(userField);
			break;
			case 2 : personModel.setLastName(userField);
			break;
			case 3 : personModel.setLastName(userField);
			break;
			case 4 : personModel.setMobileNo(userField);
			break;
			case 5 : personModel.setAddress(userField);
			break;
			case 6 : personModel.setDob(userField);
			break;
			case 7 : personModel.setDob(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		return personModel;
		
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public InquiryModel getInquiryByProcessStatus(String processStatus) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		InquiryModel inquiryModel = new InquiryModel();
		
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getInquiryByProcessStatus(processStatus);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			
			case 0 : inquiryModel.setInquiryId(Long.parseLong(userField));
			break;
			case 1 : inquiryModel.setDescription(userField);
			break;
			case 2 : inquiryModel.setRemarkvalue(userField);
			break;
			case 3 : inquiryModel.setBranch(Long.parseLong(userField));
			break;
			case 4 : inquiryModel.setEnquiryfor(Long.parseLong(userField));
			break;
			case 5 : inquiryModel.setEnquirysource(Long.parseLong(userField));
			break;
			case 6 : inquiryModel.setTargetProduct(Long.parseLong(userField)); 
			break;
			case 7 : inquiryModel.setStatusvalue(Long.parseLong(userField));
			break;
			case 8 : inquiryModel.setPersonid(Long.parseLong(userField));
			break;
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		return inquiryModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public DesignationModel getDesigntionById(long desgid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		DesignationModel designationModel = new DesignationModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getDesignationById(desgid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : designationModel.setDesgid(Long.parseLong(userField));
			break;
			case 1 : designationModel.setDesgName(userField);
			break;
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return designationModel;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<DesignationModel> getAllDesignation() throws Exception {
		// TODO Auto-generated method stub
		List <DesignationModel> designationModel = masterEntityDao.getAllDesignation();
		return designationModel;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public BranchModel getBranchById(long branchid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		BranchModel branchModel = new BranchModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getBranchById(branchid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : branchModel.setBranchid(Long.parseLong(userField));
			break;
			case 1 : branchModel.setTitle(userField);
			break;
			case 2 : branchModel.setLocation(userField);
			break;
			case 3 : branchModel.setContno(userField);
			break;
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return branchModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public EnquirySourceModel getEnqSourceById(long id) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		EnquirySourceModel enquirySourceModel = new EnquirySourceModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getEnqSourceById(id);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : enquirySourceModel.setId(Long.parseLong(userField));;
			break;
			case 1 : enquirySourceModel.setEnquirysource(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return enquirySourceModel;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public EnquiryForModel getEnqForById(long enqforid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		EnquiryForModel enquiryForModel = new EnquiryForModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getEnqForById(enqforid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : enquiryForModel.setEnqforid(Long.parseLong(userField));
			break;
			case 1 : enquiryForModel.setEnqfor(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return enquiryForModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public CityModel getCityById(long cityid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		CityModel cityModel = new CityModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getCityById(cityid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : cityModel.setCityid(Long.parseLong(userField));
			break;
			case 1 : cityModel.setCityName(userField);;
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return cityModel;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public TargetProductModel getTargetProductById(long productid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		TargetProductModel targetProductModel = new TargetProductModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getTargetProductById(productid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			
			case 0 : targetProductModel.setProductid(Long.parseLong(userField));
			break;
			case 1 : targetProductModel.setTproductName(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}	
		return targetProductModel;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public StatusValueModel getStatusValById(long stsvalid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		StatusValueModel statusValueModel = new StatusValueModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getStatusValById(stsvalid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : statusValueModel.setStsvalid(Long.parseLong(userField));
			break;
			case 1 : statusValueModel.setStatusval(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return statusValueModel;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<BranchModel> getAllBranch() throws Exception {
		// TODO Auto-generated method stub
		List <BranchModel> branchModel = masterEntityDao.getAllBranch();
		return branchModel;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<EnquirySourceModel> getAllEnqSource() throws Exception {
		// TODO Auto-generated method stub
		List<EnquirySourceModel> enquirySourceModel = masterEntityDao.getAllEnqSource();
		return enquirySourceModel;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<EnquiryForModel> getAllEnqFor() throws Exception {
		// TODO Auto-generated method stub
		List<EnquiryForModel> enquiryForModel = masterEntityDao.getAllEnqFor();
		return enquiryForModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CityModel> getAllCity() throws Exception {
		// TODO Auto-generated method stub
		List<CityModel> cityModel = masterEntityDao.getCity();
		return cityModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<StatusValueModel> getAllStatusVal() throws Exception {
		// TODO Auto-generated method stub
		List<StatusValueModel> statusValueModel = masterEntityDao.getAllStatusVal();
		return statusValueModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CommMediumModel> getAllCommMedium() throws Exception {
		// TODO Auto-generated method stub
		List<CommMediumModel> commMediumModel = masterEntityDao.getAllCommMedium();
		return commMediumModel;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TargetProductModel> getAllTargetProduct() throws Exception {
		// TODO Auto-generated method stub
		List<TargetProductModel> targetProductModel = masterEntityDao.getAllTargetProduct();
		return targetProductModel;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public CommForModel getCommforById(long commforid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		CommForModel commForModel = new CommForModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getCommforById(commforid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : commForModel.setCommforid(Long.parseLong(userField));
			break;
			case 1 : commForModel.setCommfor(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return commForModel;
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public CommMediumModel getCommMediumById(long commMediumid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		CommMediumModel commMediumModel = new CommMediumModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getCommMediumById(commMediumid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : commMediumModel.setCommMediumid(Long.parseLong(userField));
			break;
			case 1 : commMediumModel.setCommMedium(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return commMediumModel;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CommunicationModel getCommByPesronId(long personid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		CommunicationModel communicationModel = new CommunicationModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getCommByPesronId(personid);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			switch(cnt) {
			case 0 : communicationModel.setCommid(Long.parseLong(userField));
			break;
			case 1 : communicationModel.setCommfor(Long.parseLong(userField));
			break;
			case 2 : communicationModel.setMedium(Long.parseLong(userField));
			break;
			case 3 : communicationModel.setDatevalue(userField);
			break;
			case 4 : communicationModel.setDescription(userField);
			break;
			case 5 : communicationModel.setProcessStatus(userField);
			break;
			case 6 : communicationModel.setConclusion(userField);
			break;
			case 7 : communicationModel.setFollowupdate(userField);
			break;
			case 8 :communicationModel.setRemarkvalue(userField);
			break;
			case 9 : communicationModel.setPersonid(Long.parseLong(userField));
			break;
			case 10: communicationModel.setInquiryId(Long.parseLong(userField));
			break;
			default : System.out.println("Do nothing");
			}
			++cnt;
		}
		return communicationModel;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public AssignToModel getAssignToById(long assignid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		AssignToModel assignToModel = new AssignToModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getAssignToById(assignid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : assignToModel.setAssignid(Long.parseLong(userField));
			break;
			case 1 : assignToModel.setAssign_to(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return assignToModel;
	}

    
	@Transactional(propagation = Propagation.REQUIRED)
	public List<AssignToModel> getAllAssignTo() throws Exception {
		// TODO Auto-generated method stub
		List<AssignToModel> assignToModel = masterEntityDao.getAllAssignTo();
		return assignToModel;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public void orchestrateUserCreation(MasterEntityModel rec) throws Exception {
		// TODO Auto-generated method stub
		addUserInSystem("person", "id", "",true);
		addUserInSystem("person", "eaid",""+ rec.getEaid(), false);
		addUserInSystem("person", "entity_key",""+ rec.getEntityKey(), false);
		addUserInSystem("person", "value", rec.getValue(), false);
		
	}

	

	@Transactional(propagation = Propagation.REQUIRED)
	public void addRecord(Map<String, String> hm) throws Exception {
		// TODO Auto-generated method stub
            for (Map.Entry map : hm.entrySet()) 
            {            	
			System.out.println("key: " + map.getKey() + "value: " + map.getValue());
			
			String key=  map.getKey().toString();
			String value = map.getValue().toString();
			
		}
	}
	
	


	
	


	
	/*public CommForModel getCommForById(long commforid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		CommForModel commForModel = new CommForModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getCommForById(commforid);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : commForModel.setCommforid(Long.parseLong(userField));
			break;
			case 1 : commForModel.setCommfor(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return commForModel;

	}*/


	


	
	

	
	
	
	

	
	

	

	

	
	

	
	

	
	

	
	

	
	
}
		
		
		
	

	
	

	

	
	

	
	
	

	


	
	
	

	
	
	
	
	

