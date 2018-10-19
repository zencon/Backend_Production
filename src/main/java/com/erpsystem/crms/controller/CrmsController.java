/**
 * @author Amruta Lalit Kulkarni
 * Created date  : 07-Jul-2018
 * Modified date : 14-Jul-2018
 * @version 1.0
 * 
 */
package com.erpsystem.crms.controller;

import java.awt.color.CMMException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erpsystem.crms.model.AssignToModel;
import com.erpsystem.crms.model.BranchModel;
import com.erpsystem.crms.model.CityModel;
import com.erpsystem.crms.model.CommForModel;
import com.erpsystem.crms.model.CommMediumModel;
import com.erpsystem.crms.model.CommunicationModel;
import com.erpsystem.crms.model.DataSearchModel;
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
import com.erpsystem.crms.model.UserDetailRequest;
import com.erpsystem.crms.service.ICrmsSvc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 * This class is the controller and acts as the entry point to the web service.
 */
@RestController
@RequestMapping(value = "/crms")
@Api(tags = { "" })
public class CrmsController extends AbstractRestHandler {

	@Autowired
	ICrmsSvc crmsSvc;

	////////////////ADD////////////////////
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void createUser(@RequestBody JSONObject data , HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {
		MasterEntityModel  rec = new MasterEntityModel();
		
		MasterEntityModel masterEntityModel = new MasterEntityModel();
		
		

		rec.setEaid(masterEntityModel.getEaid());
		rec.setEntityKey(masterEntityModel.getEntityKey());
		rec.setValue(masterEntityModel.getValue());
		
		System.out.println("Hello");
		try {
			crmsSvc.orchestrateUserCreation(rec);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addEnquiry", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void createEnquiry(@RequestBody InquiryModel inquiryModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.enquiryCreation(inquiryModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addCommunication", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void createCommunication(@RequestBody CommunicationModel communicationModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.communicationCreation(communicationModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addTask", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void createTask(@RequestBody TaskModel taskModel, HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.taskCreation(taskModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void createEmployee(@RequestBody EmployeeModel employeeModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.employeeCreation(employeeModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/AddDesignation", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void createDesignation(@RequestBody DesignationModel designationModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.designationCreation(designationModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/AddBranch", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void createBranch(@RequestBody BranchModel branchModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.branchCreation(branchModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addEnquirySource", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void enquirysourceCreation(@RequestBody EnquirySourceModel enquirySourceModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.enquirysourceCreation(enquirySourceModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addEnquiryFor", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void enquiryforCreation(@RequestBody EnquiryForModel enquiryForModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.enquiryforCreation(enquiryForModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addTargetProduct", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void tagetProductCreation(@RequestBody TargetProductModel targetProductModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.targetProductCreation(targetProductModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addStatusValue", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void statusValueCreation(@RequestBody StatusValueModel statusValueModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.statusvalueCreation(statusValueModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addCommFor", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void commForCreation(@RequestBody CommForModel commForModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.commForCreation(commForModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addCommMedium", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void commMediumCreation(@RequestBody CommMediumModel commMediumModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.commMediumCreation(commMediumModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addCity", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void cityCreation(@RequestBody CityModel cityModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.cityCreation(cityModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addAssignTo", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void assignToCreation(@RequestBody AssignToModel assignToModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			crmsSvc.assignToCreation(assignToModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}
	
	////////////////UPDATE////////////////////
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updatePerson", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void updatePerson(@RequestBody PersonModel personModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			System.out.println(personModel.getPersonId());
			crmsSvc.personUpdate(personModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateEnquiry", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void updateEnquiry(@RequestBody InquiryModel inquiryModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			System.out.println(inquiryModel.getInquiryId());
			crmsSvc.enquiryUpdate(inquiryModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println("Done");

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateCommunication", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void updateCommunicartion(@RequestBody CommunicationModel communicationModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			System.out.println(communicationModel.getCommid());
			crmsSvc.commUpdate(communicationModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println("Done");

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateTask", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void updateTask(@RequestBody TaskModel taskModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			System.out.println(taskModel.getTaskid());
			crmsSvc.taskUpdate(taskModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println("Done");

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void updateEmployee(@RequestBody EmployeeModel employeeModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			System.out.println(employeeModel.getEmployeeid());
			crmsSvc.employeeUpdate(employeeModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println("Done");

	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateStatusValue", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void updateStatusValue(@RequestBody StatusValueModel statusValueModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		try {
			
			crmsSvc.statusValueUpdate(statusValueModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

	}
	
	///////////////GETBYID//////////////////
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getPersonById/{personid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public PersonModel getUserDetailsByID(@PathVariable("personid") long personid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		PersonModel personModel = new PersonModel();
		try {
			if (personid != 0) {
				personModel = crmsSvc.getPersonDetailsID(personid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return personModel;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getTargetProductById/{productid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public TargetProductModel getTargetProductById(@PathVariable("productid") long productid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		TargetProductModel targetProductModel = new TargetProductModel();
		try {
			if (productid != 0) {
				targetProductModel = crmsSvc.getTargetProductById(productid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return targetProductModel;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEnquiryById/{inquiryId}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public InquiryModel getEnquiryDetailsByID(@PathVariable("inquiryId") long inquiryId, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		InquiryModel inquiryModel = new InquiryModel();
		try {
			if (inquiryId != 0) {
				inquiryModel = crmsSvc.getEnquiryDetailsID(inquiryId);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return inquiryModel;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEnquiryByPersonId/{personid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public InquiryModel getEnquiryByPersonId(@PathVariable("personid") long personid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		InquiryModel inquiryModel = new InquiryModel();
		try {
			if (personid != 0) {
				inquiryModel = crmsSvc.getEnquiryByPersonID(personid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return inquiryModel;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getCommById/{commid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public CommunicationModel getCommDetailsByID(@PathVariable("commid") long commid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		CommunicationModel communicationModel = new CommunicationModel();
		try {
			if (commid != 0) {
				communicationModel = crmsSvc.getCommDetailsID(commid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return communicationModel;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getCommByPesronId/{personid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public CommunicationModel getCommByPesronId(@PathVariable("personid") long personid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		CommunicationModel communicationModel = new CommunicationModel();
		try {
			if (personid != 0) {
				communicationModel = crmsSvc.getCommByPesronId(personid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return communicationModel;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getTaskById/{taskid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public TaskModel getTaskDetailsByID(@PathVariable("taskid") long taskid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		TaskModel taskModel = new TaskModel();
		try {
			if (taskid != 0) {
				taskModel = crmsSvc.getTaskDetailsID(taskid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return taskModel;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEmployeeById/{employeeid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public EmployeeModel getEmployeeDetailsByID(@PathVariable("employeeid") long employeeid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		EmployeeModel employeeModel = new EmployeeModel();
		try {
			if (employeeid != 0) {
				employeeModel = crmsSvc.getEmpDetailsID(employeeid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return employeeModel;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getDesignationById/{desgid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public DesignationModel getDesignationById(@PathVariable("desgid") long desgid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		DesignationModel designationModel = new DesignationModel();
		try {
			if ( desgid != 0) {
				designationModel = crmsSvc.getDesigntionById(desgid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return designationModel;

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getBranchById/{branchid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public BranchModel getBranchById(@PathVariable("branchid") long branchid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		BranchModel branchModel = new BranchModel();
		try {
			if ( branchid != 0) {
				branchModel = crmsSvc.getBranchById(branchid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return branchModel;

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEnqSourceById/{id}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public EnquirySourceModel getEnqSourceById(@PathVariable("id") long id, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		EnquirySourceModel enquirySourceModel = new EnquirySourceModel();
		try {
			if ( id != 0) {
				enquirySourceModel  = crmsSvc.getEnqSourceById(id);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return enquirySourceModel;

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEnqForById/{enqforid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public EnquiryForModel getEnqForById(@PathVariable("enqforid") long enqforid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		EnquiryForModel enquiryForModel = new EnquiryForModel();
		try {
			if ( enqforid != 0) {
				enquiryForModel  = crmsSvc.getEnqForById(enqforid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return enquiryForModel;

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getStatusValById/{stsvalid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public StatusValueModel getStatusValById(@PathVariable("stsvalid") long stsvalid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		StatusValueModel statusValueModel = new StatusValueModel();
		try {
			if ( stsvalid != 0) {
				statusValueModel = crmsSvc.getStatusValById(stsvalid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return statusValueModel;

	}
	
	
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getCommforById/{commforid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public CommForModel getCommforById(@PathVariable("commforid") long commforid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		CommForModel commForModel = new CommForModel();
		try {
			if (commforid != 0) {
				commForModel = crmsSvc.getCommforById(commforid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return commForModel;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getCommMediumById/{commMediumid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public CommMediumModel getCommMediumById(@PathVariable("commMediumid") long commMediumid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		CommMediumModel commMediumModel = new CommMediumModel();
		try {
			if (commMediumid != 0) {
				commMediumModel = crmsSvc.getCommMediumById(commMediumid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return commMediumModel;
	}
	

	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getCityById/{cityid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public CityModel getCityById(@PathVariable("cityid") long cityid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		CityModel cityModel = new CityModel();
		try {
			if ( cityid != 0) {
				cityModel  = crmsSvc.getCityById(cityid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return cityModel;

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAssignToById/{assignid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public AssignToModel getAssignToById(@PathVariable("assignid") long assignid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		AssignToModel assignToModel = new AssignToModel();
		try {
			if ( assignid != 0) {
				assignToModel  = crmsSvc.getAssignToById(assignid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return assignToModel;

	}
	
	
	///////////GETALL/////////
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllPerson", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<PersonModel> getAllPerson(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<PersonModel> personModelList = new ArrayList<>();

		try {

			personModelList = crmsSvc.getAllPerson();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return personModelList;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getCommListByPesronId/{personid}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<CommunicationModel>  getCommListByPesronId(@PathVariable("personid") long personid, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<CommunicationModel> commByPersonList = new ArrayList<>();
		try {

			commByPersonList = crmsSvc.getCommListByPesronId();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return commByPersonList;

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllEnquiry", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<InquiryModel> getAllEnquiry(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<InquiryModel> enquiryModelList = new ArrayList<>();

		try {

			enquiryModelList = crmsSvc.getAllEnquiry();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return enquiryModelList;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllComm", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<CommunicationModel> getAllComm(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<CommunicationModel> commModelList = new ArrayList<>();

		try {

			commModelList = crmsSvc.getAllComm();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return commModelList;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllTask", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<TaskModel> getAllTask(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<TaskModel> taskModelList = new ArrayList<>();

		try {

			taskModelList = crmsSvc.getAllTask();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return taskModelList;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<EmployeeModel> getAllEmp(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<EmployeeModel> empModelList = new ArrayList<>();

		try {

			empModelList = crmsSvc.getAllEmp();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return empModelList;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllDesignation", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<DesignationModel> getAllDesignation(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<DesignationModel> desigModelList = new ArrayList<>();

		try {

			desigModelList = crmsSvc.getAllDesignation();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return desigModelList;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllBranch", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<BranchModel> getAllBranch(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<BranchModel> branchModelList = new ArrayList<>();

		try {

			branchModelList = crmsSvc.getAllBranch();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return branchModelList;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllCommMedium", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<CommMediumModel> getAllCommMedium(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<CommMediumModel> commMediumModelList = new ArrayList<>();

		try {

			commMediumModelList = crmsSvc.getAllCommMedium();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return commMediumModelList;
	}

	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllEnqFor", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<EnquiryForModel> getAllEnqFor(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<EnquiryForModel> enqForModelList = new ArrayList<>();

		try {

			enqForModelList = crmsSvc.getAllEnqFor();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return enqForModelList;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllEnqSource", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<EnquirySourceModel> getAllEnqSource(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<EnquirySourceModel> enqSourceModelList = new ArrayList<>();

		try {

			enqSourceModelList = crmsSvc.getAllEnqSource();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return enqSourceModelList;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllCity", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<CityModel> getAllCity(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<CityModel> cityModelList = new ArrayList<>();

		try {

			cityModelList = crmsSvc.getAllCity();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return cityModelList;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllTargetProduct", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<TargetProductModel> getAllTargetProduct(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<TargetProductModel> tagetProductList = new ArrayList<>();

		try {

			tagetProductList = crmsSvc.getAllTargetProduct();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return tagetProductList;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllAssignTo", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<AssignToModel> getAllAssignTo(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<AssignToModel> assignToList = new ArrayList<>();

		try {

			assignToList = crmsSvc.getAllAssignTo();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return assignToList;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllStatusVal", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<StatusValueModel> getAllStatusVal(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<StatusValueModel> statusvalModelList = new ArrayList<>();

		try {

			statusvalModelList = crmsSvc.getAllStatusVal();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return statusvalModelList;
	}
	
	/////////
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getUserDetails", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public PersonModel getUserDetails(@RequestBody UserDetailRequest userDetailRequest, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		PersonModel personModel = new PersonModel();
		try {
			if (null != userDetailRequest && null != userDetailRequest.getMobileNo()) {
				personModel = crmsSvc.getPersonDataByMobId(userDetailRequest.getMobileNo());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return personModel;

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEnquiryDetails", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public InquiryModel getEnquiryDetails(@RequestBody UserDetailRequest userDetailRequest, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		InquiryModel inquiryModel = new InquiryModel();
		try {
			if (null != userDetailRequest && null != userDetailRequest.getProcessStatus()) {

				inquiryModel = crmsSvc.getInquiryByProcessStatus(userDetailRequest.getProcessStatus());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");
		return inquiryModel;

	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/searchPersonDetails", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<PersonModel> searchPersonDetails(@RequestBody DataSearchModel dataSearchModel,
			HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<PersonModel> personModel = new ArrayList<>();

		try {

			if (null != dataSearchModel && null != dataSearchModel.getSearchKey()) {

				personModel = crmsSvc.searchDataSvc("person", dataSearchModel.getSearchKey());

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

		return personModel;

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/searchEnquiryDetails", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<InquiryModel> searchEnquiryDetails(@RequestBody DataSearchModel dataSearchModel,
			HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<InquiryModel> inquiryModel = new ArrayList<>();

		try {

			if (null != dataSearchModel && null != dataSearchModel.getSearchKey()) {

				inquiryModel = crmsSvc.searchEnquirySvc("enquiry", dataSearchModel.getSearchKey());

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

		return inquiryModel;

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/searchCommDetails", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<CommunicationModel> searchCommDetails(@RequestBody DataSearchModel dataSearchModel,
			HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<CommunicationModel> communicationModel = new ArrayList<>();

		try {

			if (null != dataSearchModel && null != dataSearchModel.getSearchKey()) {

				communicationModel = crmsSvc.searchCommunicationSvc("communication", dataSearchModel.getSearchKey());

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

		return communicationModel;

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/searchTaskDetails", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<TaskModel> searchTaskDetails(@RequestBody DataSearchModel dataSearchModel, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<TaskModel> taskModel = new ArrayList<>();

		try {

			if (null != dataSearchModel && null != dataSearchModel.getSearchKey()) {

				taskModel = crmsSvc.searchTaskSvc("task", dataSearchModel.getSearchKey());

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

		return taskModel;

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/searchEmployeeDetails", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public List<EmployeeModel> searchEmployeeDetails(@RequestBody DataSearchModel dataSearchModel,
			HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
		List<EmployeeModel> employeeModel = new ArrayList<>();

		try {

			if (null != dataSearchModel && null != dataSearchModel.getSearchKey()) {

				employeeModel = crmsSvc.searchEmployeeSvc("employee", dataSearchModel.getSearchKey());

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

		return employeeModel;

	}

	
	/**
	
	New Approach code
	
	*/
	
	/*@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, consumes = { "application/json","application/xml" }, 
	produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void addPerson(@RequestBody JSONObject data, HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		
		
		

	}
	*/
	
	
}
