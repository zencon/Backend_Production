/**
 * 
 */
package com.erpsystem.crms.data;

import java.sql.SQLException;
import java.util.List;

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
public interface IMasterEntityDao {
	
	public void addDataInMasterEntity(final MasterEntityModel masterEntityModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void updateDataInMasterEntity(final MasterEntityModel masterEntityModel) throws ClassNotFoundException, SQLException, Exception;
	
	public long getEntityId(final String entityName) throws ClassNotFoundException, SQLException, Exception;
	
	public long getAttributeId(final String attributeName) throws ClassNotFoundException, SQLException, Exception;

	public long getEAId(final long entityId,final long attributeId) throws ClassNotFoundException, SQLException, Exception;
	
	public long getLatestEntityKeyAndIncrement(final long eaId) throws ClassNotFoundException, SQLException, Exception;
	
	public long getIdentifier(final long eaId) throws ClassNotFoundException, SQLException, Exception;
	
	public String[] getUserDetailsByMobileNumber(final String mobNo) throws Exception;
	
	public String[] getInquiryByProcessStatus (final String processStatus) throws Exception;
	
	public List<PersonModel> searchData(final String entityName,final String searchString) throws Exception;

	public List<InquiryModel> searchEnquiryData(final String entityName, final String searchString)throws Exception;

	public long getLatestEntityKey(long eaid)throws ClassNotFoundException, SQLException, Exception;

	public List<EmployeeModel> searchEmployeeData(final String entityName, final String searchString) throws Exception;

	public List<CommunicationModel> searchCommData(final String entityName, final String searchString)throws Exception;

	public List<TaskModel> searchTaskData(final String entityName, final String searchString)throws Exception;

	//public String[] getDesignation(final long designationId)throws Exception;
	
	public String[] getPersonDetailsID(final long personid) throws Exception;
	
	public List<PersonModel> getAllPerson() throws Exception;

	public String[] getEnquiryDetailsID(final long inquiryId) throws Exception;

	public List<InquiryModel> getAllEnquiry() throws Exception;

	public String[] getCommDetailsID(final long commid)throws Exception;

	public List<CommunicationModel> getAllComm()throws Exception;

	public String[] getTaskDetailsID(final long taskid)throws Exception;

	public List<TaskModel> getAllTask()throws Exception;

	public String[] getEmpDetailsID(final long employeeid)throws Exception;

	public List<EmployeeModel> getAllEmp()throws Exception;

	public String[] getDesignationById(final long desgid)throws Exception;

	public List<DesignationModel> getAllDesignation()throws Exception;

	public String[] getBranchById(final long branchid)throws Exception;

	public List<BranchModel> getAllBranch()throws Exception;

	public String[] getEnqSourceById(final long id)throws Exception;

	public List<EnquirySourceModel> getAllEnqSource()throws Exception;

	public String[] getEnqForById(final long enqforid)throws Exception;

	public List<EnquiryForModel> getAllEnqFor()throws Exception;

	public String[] getCityById(final long cityid)throws Exception;

	public List<CityModel> getCity()throws Exception;

	public String[] getEnquiryByPersonID(final long personid)throws Exception;

	public String[] getStatusValById(final long stsvalid)throws Exception;

	public List<StatusValueModel> getAllStatusVal()throws Exception;

	public String[] getCommforById(final long commforid)throws Exception;

	public String[] getCommMediumById(final long commMediumid)throws Exception;

	public List<CommMediumModel> getAllCommMedium()throws Exception;

	public String[] getCommByPesronId(final long personid)throws Exception;

	public String[] getTargetProductById(final long productid)throws Exception;

	public List<TargetProductModel> getAllTargetProduct()throws Exception;

	public String[] getAssignToById(final long assignid)throws Exception;

	public List<AssignToModel> getAllAssignTo() throws Exception;

	public List<CommunicationModel> getAllCommListByPesronId()throws Exception;

	

	

	
	
	
}
