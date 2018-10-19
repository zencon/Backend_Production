/**
 * 
 */
package com.erpsystem.crms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public interface ICrmsSvc {

	public void orchestrateUserCreation(final PersonModel personModel) throws ClassNotFoundException, SQLException, Exception;

	public void enquiryCreation(final InquiryModel inquiryModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void communicationCreation(final CommunicationModel communicationModel) throws ClassNotFoundException, SQLException, Exception;
	
	public  void taskCreation(final TaskModel taskModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void employeeCreation(final EmployeeModel employeeModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void designationCreation(final DesignationModel designationModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void branchCreation(final BranchModel branchModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void enquirysourceCreation(EnquirySourceModel enquirySourceModel)throws ClassNotFoundException, SQLException, Exception;

	public void enquiryforCreation(EnquiryForModel enquiryForModel)throws ClassNotFoundException, SQLException, Exception;
	
	public void targetProductCreation(TargetProductModel targetProductModel)throws ClassNotFoundException, SQLException, Exception;
	
	public void statusvalueCreation(StatusValueModel statusValueModel)throws ClassNotFoundException, SQLException, Exception;
	
	public PersonModel getPersonDataByMobId(final String mobNo) throws Exception;
	
	public InquiryModel getInquiryByProcessStatus(final String processStatus) throws Exception;
	
	public List<PersonModel> searchDataSvc(final String entityName,final String searchString) throws Exception;

	public void personUpdate(final PersonModel personModel)throws ClassNotFoundException, SQLException, Exception;
	
	public List<InquiryModel> searchEnquirySvc(final String entityName,final String searchString)throws Exception;

	public void enquiryUpdate( final InquiryModel inquiryModel)throws ClassNotFoundException, SQLException, Exception;

	public List<EmployeeModel> searchEmployeeSvc(final String entityName, final String searchString) throws Exception;

	public List<CommunicationModel> searchCommunicationSvc(final String entityName, final String searchString) throws Exception;

	public List<TaskModel> searchTaskSvc(final String entityName, final String searchString) throws Exception;

	//public DesignationModel getDesigntionId(final long designationId)throws Exception;

	public PersonModel getPersonDetailsID(final long personid) throws Exception;

	public List<PersonModel> getAllPerson() throws Exception;

	public InquiryModel getEnquiryDetailsID(final long inquiryId)throws Exception;

	public List<InquiryModel> getAllEnquiry()throws Exception;

	public CommunicationModel getCommDetailsID(final long commid)throws Exception;

	public void commUpdate(final CommunicationModel communicationModel)throws ClassNotFoundException, SQLException, Exception;

	public List<CommunicationModel> getAllComm() throws Exception;

	public void taskUpdate(final TaskModel taskModel)throws ClassNotFoundException, SQLException, Exception;

	public TaskModel getTaskDetailsID(final long taskid)throws Exception ;

	public List<TaskModel> getAllTask()throws Exception;

	public void employeeUpdate(final EmployeeModel employeeModel)throws Exception;

	public EmployeeModel getEmpDetailsID(final long employeeid)throws Exception;

	public List<EmployeeModel> getAllEmp()throws Exception;

	public DesignationModel getDesigntionById(final long desgid)throws Exception;

	public List<DesignationModel> getAllDesignation()throws Exception;

	public BranchModel getBranchById(final long branchid)throws Exception;

	public List<BranchModel> getAllBranch()throws Exception;

	public EnquirySourceModel getEnqSourceById(final long id)throws Exception;

	public List<EnquirySourceModel> getAllEnqSource()throws Exception;

	public EnquiryForModel getEnqForById(final long enqforid)throws Exception;

	public List<EnquiryForModel> getAllEnqFor()throws Exception;

	public CityModel getCityById(final long cityid)throws Exception;

	public void cityCreation(final CityModel cityModel)throws Exception;

	public List<CityModel> getAllCity()throws Exception;

	public InquiryModel getEnquiryByPersonID(final long personid)throws Exception;

	public void statusValueUpdate(final StatusValueModel statusValueModel)throws Exception;

	public StatusValueModel getStatusValById(final long stsvalid)throws Exception;

	public List<StatusValueModel> getAllStatusVal()throws Exception;

	public void commForCreation(final CommForModel commForModel)throws Exception;

	public CommForModel getCommforById(final long commforid)throws Exception;

	public void commMediumCreation(final CommMediumModel commMediumModel)throws Exception;

	public CommMediumModel getCommMediumById(final long commMediumid)throws Exception;

	public List<CommMediumModel> getAllCommMedium()throws Exception;

	public CommunicationModel getCommByPesronId(final long personid)throws Exception;

	public TargetProductModel getTargetProductById(final long productid)throws Exception;

	public List<TargetProductModel> getAllTargetProduct()throws Exception;

	public void assignToCreation(final AssignToModel assignToModel)throws Exception;

	public AssignToModel getAssignToById(final long assignid)throws Exception;

	public List<AssignToModel> getAllAssignTo()throws Exception;

	public List<CommunicationModel> getCommListByPesronId()throws Exception;

	public void orchestrateUserCreation(final MasterEntityModel rec)throws Exception;

	public void addRecord(Map<String, String> hm)throws Exception;


	

}
