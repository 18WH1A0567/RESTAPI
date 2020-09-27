package com.ts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dao.BillDAO;
import com.dao.DriverDAO;
import com.dao.ManagerDAO;
import com.dao.RecordDAO;
import com.dao.emailSending;
import com.dto.Bill;
import com.dto.Driver;
import com.dto.Manager;
import com.dto.Record;

@Path("myresource")
public class MyResource {
   
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @Path("registerCustomer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Record registerCustomer(Record record){
    	
    	RecordDAO recordDAO = new RecordDAO();
    	recordDAO.register(record); 
    	System.out.println("Customer registered!");
    	return record;
		    	
    }
    
    @Path("registerManager")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int registerManager(Manager manager){
    	
    	System.out.println("In myresource reg manager with data " + manager );
    	ManagerDAO managerDAO = new ManagerDAO();
    	return managerDAO.register(manager);    	
    	    	
		
    }
    
   
    
   // DRIVER REGISTER
    @Path("registerDriver")
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
    public int registerDriver(Driver driver){
    	System.out.println("In reg driver");
    	
    	ManagerDAO managerDAO = new ManagerDAO();
    	List<Manager> managers = new ArrayList<Manager>();
    	Manager manager = new Manager();
    	manager = managerDAO.getManagerByBranch(driver.getDriverBranch());
    	driver.setManager(manager);
    	DriverDAO driverDAO = new DriverDAO();
    	return driverDAO.register(driver);    	
    }  
    
    @Path("deleteDriver")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int deleteDriver(Driver driver){
    	System.out.println("in delete driver");
    	DriverDAO driverDAO = new DriverDAO();
    	return driverDAO.deleteDriver(driver.getVehicleId());
    	
    }
    
    @Path("getAllCustomers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Record> getAllCustomers(){
    	List<Record> records = new ArrayList<Record>();
    	RecordDAO recordDAO = new RecordDAO();
    	records = recordDAO.getAllCustomers();
    	System.out.println(records);
    	return records;
    	
    }
    
    @Path("getAllCustomersByAdminToAllocate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Record> getAllCustomersByAdminToAllocate(){
    	List<Record> records = new ArrayList<Record>();
    	RecordDAO recordDAO = new RecordDAO();
    	records = recordDAO.getAllCustomersByAdminToAllocate();
    	System.out.println(records);
    	return records;
    	
    }
    
    @Path("deleteManager")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int deleteManager(Manager manager){
    	System.out.println("in delete manager");
    	ManagerDAO managerDAO = new ManagerDAO();
    	return managerDAO.deleteManager(manager.getLoginId());
    	
    }
    
    @Path("getAllManagers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Manager> getAllManagers(){
    	System.out.println("in getAllManagers myresource");
    	List<Manager> managers = new ArrayList<Manager>();
    	ManagerDAO managerDAO = new ManagerDAO();
    	managers = managerDAO.getAllManagers();
    	return managers;
    	
    }
    
    @Path("getAllDrivers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Driver> getAllDrivers(){
    	System.out.println("In get all drivers");
    	List<Driver> drivers = new ArrayList<Driver>();
    	DriverDAO driverDAO = new DriverDAO();
    	drivers = driverDAO.getAllDrivers();
    	return drivers;
    	
    }
    
    @Path("getAllDriversBranchwise/{branch}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Driver> getAllDriversBranchwise(@PathParam("branch") String branch){
    	System.out.println("In getalldrivers " + branch);
    	List<Driver> drivers = new ArrayList<Driver>();
    	DriverDAO driverDAO = new DriverDAO();
    	drivers = driverDAO.getAllDrivers(branch);
    	return drivers;
    	
    }
    
    @Path("getAllDriversToAllocate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Driver> getAllDriversToAllocate(){
    	System.out.println("In getalldrivers ");
    	List<Driver> drivers = new ArrayList<Driver>();
    	DriverDAO driverDAO = new DriverDAO();
    	drivers = driverDAO.getAllDriversToAllocate();
    	return drivers;
    	
    }
    
    @Path("getManagerById/{details}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    public Manager getManagerById(@PathParam("details") String details){
    	System.out.println("In get manager");
    	String loginId, password, words[];
    	words = details.split(" ");
    	loginId = words[0];
    	password = words[1];
    	Manager manager = new Manager();
    	ManagerDAO managerDAO = new ManagerDAO();
    	manager = managerDAO.getManager(loginId, password);
    	return manager;
    }
    
    @Path("getMangerByManagerId/{loginId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Manager getMangerById(@PathParam("loginId") String loginId){
    	Manager manager = new Manager();
    	ManagerDAO managerDAO = new ManagerDAO();
    	manager = managerDAO.getManager(loginId);
    	return manager;
    }
    
    @Path("getCustomer/{transactionId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Object>  getCustomer(@PathParam("transactionId") int transactionId) throws Exception{
    	System.out.println("In get customer method");
    	RecordDAO recordDAO = new RecordDAO();
    	Record record = new Record();
    	record = recordDAO.getRecord(transactionId);
    	Bill bill = new Bill();
    	BillDAO billDAO = new BillDAO();
    	List<Object> list = new ArrayList<Object>();
    	list = billDAO.billCalculation(record);
    	record.setBill(bill.getTotal());
    	recordDAO.updateRecord(record);
    	System.out.println(record.getBill());
    	return list;
    }
    
    @Path("getCustomerToAllocate/{transacationId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Record getCustomerToAllocate(@PathParam("transactionId") int transactionId){
    	System.out.println(transactionId);
    	RecordDAO recordDAO = new RecordDAO();
    	Record record = new Record();
    	record = recordDAO.getRecord(transactionId);
    	System.out.println(record);
    	return record;
    }
    
    
    
    @Path("seeAllRequests/{managerBranch}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Record> seeAllRequests(@PathParam("managerBranch") String managerBranch){
    	List<Record> records = new ArrayList<Record>();
    	RecordDAO recordDAO = new RecordDAO();
    	records = recordDAO.seeAllRequests(managerBranch);
    	System.out.println(records);
    	return records;
    	
    }
    
    @Path("generateBill/{record}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Bill generateBill(@PathParam("record") Record record) throws Exception{
    	System.out.println(record);
    	System.out.println("In bill");
    	BillDAO billDAO = new BillDAO();
    	List<Object> list = new ArrayList<Object>();
    	list =  billDAO.billCalculation(record);
    	return (Bill) list.get(1);
    } 
    
    /*@Path("allocate/{details}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)*/
    @Path("allocate")
    @POST    
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public void allocate(@FormDataParam("customer") int transactionId,
    		@FormDataParam("driver") int vehicleId){
    		//@FormDataParam("manager") Object manager ){
    	System.out.println("In allocate method!");
    	
    	
    	Driver driver = new Driver();
    	DriverDAO driverDAO = new DriverDAO();
    	driver = driverDAO.getDriver(vehicleId);
    	driver.setDriverStatus(true);
    	driverDAO.updateRecord(driver);
    	
    	Record record = new Record();
    	RecordDAO recordDAO = new RecordDAO();
    	record = recordDAO.getRecord(transactionId);
    	record.setManager(driver.getManager());
    	System.out.println(recordDAO.updateRecord(record));
    	emailSending emailSending = new emailSending();
    	emailSending.sendEmail(record, driver);
    	
    	
    }
    
    
    @Path("rejectCustomer")
    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public String rejectCustomer(@FormDataParam("customer") int transactionId,
    		@FormDataParam("message") String message){
    	
    	System.out.println("In reject method!");
    	Record record = new Record();
    	RecordDAO recordDAO = new RecordDAO();
    	record = recordDAO.getRecord(transactionId);
    	ManagerDAO managerDAO = new ManagerDAO();
    	Manager manager = new Manager();
    	manager = managerDAO.getManager("blockList");
    	record.setManager(manager);
    	recordDAO.updateRecord(record);
    	emailSending emailSending = new emailSending();
    	emailSending.rejectionMail(record, message);
    	
    	return "Driver allocated to Customer";
    }
    
    
    
    @Path("generateOTP/{transactionId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public int generateOTP(@PathParam("transactionId") int transactionId){
    	Record record = new Record();
    	RecordDAO recordDAO = new RecordDAO();
    	record = recordDAO.getRecord(transactionId);
    	emailSending emailSending = new emailSending();
    	int otp = emailSending.generateOTP(record);
    	return otp;
    	
    }
    
    @Path("confirmCustomer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int confirmCustomer(Record record){
    	RecordDAO recordDAO = new RecordDAO();
    	System.out.println(recordDAO.updateRecord(record));
    	return 0;    	
    	
    } 
    
    @Path("getAllDriversToAllocate/{branch}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Driver> getAllDriversToAllocate(@PathParam("branch") String branch){
    	List<Driver> drivers = new ArrayList<Driver>();
    	DriverDAO driverDAO = new DriverDAO();
    	drivers = driverDAO.getAllDriversToAllocate(branch);
    	return drivers;
    	
    }
    
    @Path("getAllDriversToDeallocate/{branch}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Driver> getAllDriversToDeallocate(@PathParam("branch") String branch){
    	List<Driver> drivers = new ArrayList<Driver>();
    	DriverDAO driverDAO = new DriverDAO();
    	drivers = driverDAO.getAllDriversToDeallocate(branch);
    	return drivers;
    	
    }
    
    //1 is working, 0 is free
    @Path("changeDriverStatus")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void changeDriverStatus(Driver driver){
    	
    	driver.setDriverStatus(false);
    	DriverDAO driverDAO = new DriverDAO();
    	int res = driverDAO.updateRecord(driver);
    	
    }
    /*
    
    @POST
    @Path("uploadImage")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public void uploadImage(@FormDataParam("Image") InputStream fileInputStream,
    		@FormDataParam("Image") FormDataContentDisposition formDataContentDisposition,
    		@FormDataParam("title") String title,
    		@FormDataParam("description") String description) throws IOException {
   
        
    System.out.println("Poooooo");
    int read=0;
    byte [] bytes = new byte[1024];
    String path = this.getClass().getClassLoader().getResource("").getPath();

    System.out.println(path);
    // String fileLocation = "/home/madhumitha/Downloads" + formDataContentDisposition.getFileName();  
            String pathArr[] = path.split("/WEB-INF/classes");  
            FileOutputStream out = new FileOutputStream(new File(pathArr[0] + "/image/" + formDataContentDisposition.getFileName()));
			    while((read = fileInputStream.read(bytes))!=-1) {
			    out.write(bytes,0,read);
			}
    out.flush();
    out.close();
    System.out.println("upload in");
    Advertisement advertisement = new Advertisement();
               
    advertisement.setAddTitle(title);
    advertisement.setAddDesc(description);
    advertisement.setImageurl(formDataContentDisposition.getFileName());

    System.out.println(advertisement);

    }*/
}

