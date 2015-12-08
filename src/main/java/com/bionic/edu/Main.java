package com.bionic.edu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.edu.service.AccountantService;
import com.bionic.edu.service.ColdStoreManagerService;
import com.bionic.edu.service.CustomerService;
import com.bionic.edu.service.GeneralManagerService;
import com.bionic.edu.service.SecurityOfficerService;
import com.bionic.edu.service.UserService;
import com.bionic.edu.entity.AdminService;
import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.ReportByDate;
import com.bionic.edu.entity.ReportByFish;
import com.bionic.edu.entity.SaleParcel;
import com.bionic.edu.entity.SaleParcelItem;
import com.bionic.edu.entity.TotalReport;
import com.bionic.edu.entity.User;
import com.bionic.edu.security.PasswordHash;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	
	private static AccountantService accountantService;
	private static AdminService adminService;
	private static ColdStoreManagerService coldStoreManagerService;
	private static CustomerService customerService;
	private static GeneralManagerService generalManagerService;
	private static SecurityOfficerService securityOfficerService;
	private static UserService userService;
	
	public static void main(String[] args){
		//Preparation
		DBCreator.executeDBScript();
		obtainServices();
	    
		//Check user stories
		//checkUserStory1();
		//checkUserStory2();  
		//checkUserStory4();  
		//checkUserStory5(); 
		//checkUserStory6();
		//checkUserStory8();
		//checkUserStory10();
		checkUserStory11();
		//checkUserStory12();
		//checkUserStory13();
		//checkUserStory14();
		//checkUserStory15();
		//checkUserStory16();
		//checkUserStory19();
		//checkUserStory20();
		//checkUserStory21();
	}
	
	public static void obtainServices(){
		accountantService = context.getBean(AccountantService.class);
		adminService = context.getBean(AdminService.class);
		coldStoreManagerService = context.getBean(ColdStoreManagerService.class);
		customerService = context.getBean(CustomerService.class);
		generalManagerService = context.getBean(GeneralManagerService.class);
		securityOfficerService = context.getBean(SecurityOfficerService.class);
		userService = context.getBean(UserService.class);
	}
	
	public static void checkUserStory1(){
		for(FishItem fishItem: customerService.getFish4Sale()){
	    	System.out.println(fishItem);
	    }
	}
	
	public static void checkUserStory2(){
		SaleParcel saleParcel = new SaleParcel();
	    saleParcel.setUser(adminService.findUById(2));
	    
	    List<SaleParcelItem> saleParcelItems = new ArrayList<>();
	    
	    SaleParcelItem saleParcelItem1 = new SaleParcelItem();
	    saleParcelItem1.setFishItem(adminService.findFIById(3));
	    saleParcelItem1.setPrice(504000);
	    saleParcelItem1.setSaleParcel(saleParcel);
	    saleParcelItem1.setWeight(20);
	    
	    SaleParcelItem saleParcelItem2 = new SaleParcelItem();
	    saleParcelItem2.setFishItem(adminService.findFIById(4));
	    saleParcelItem2.setPrice(456000);
	    saleParcelItem2.setSaleParcel(saleParcel);
	    saleParcelItem2.setWeight(160);
	    
	    saleParcelItems.add(saleParcelItem1);
	    saleParcelItems.add(saleParcelItem2);
	    
	    saleParcel.setSaleParcelItems(saleParcelItems);
	    saleParcel.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
	    saleParcel.setStatus("S");
	    
	    System.out.println("Saved sale parcel id: " + 
	    		customerService.sumbitSaleParcel(saleParcel).getId());
	}
	
	public static void checkUserStory4(){
		User user = new User();
	    user.setName("Jimmy Hendrix");
	    user.setLogin("j.hendrix");
	    user.setEmail("j.hendrix@gmail.com");
	    try{
	    	user.setPassword(PasswordHash.createHash("j.hendrix"));
	    } catch (Exception e) { }
	    user.setAddress("Blues St. 5, NY, USA");
	    user.setPrepaymentPercent(25);
	    user.setTIN("436723453442");
	    
	    System.out.println("New customer id: " + userService.register(user).getId());
	}
	
	public static void checkUserStory5(){
		PurchaseParcel purchaseParcel = new PurchaseParcel();
	    purchaseParcel.setCreated(Timestamp.valueOf(LocalDateTime.now()));
	    purchaseParcel.setShip("cort");
	    
	    FishItem fishItem1 = new FishItem();
	    fishItem1.setPurchaseParcel(purchaseParcel);
	    fishItem1.setFishType(adminService.findFTById(1));
	    fishItem1.setPrice(450000);
	    fishItem1.setPurchasePrice(80000);
	    fishItem1.setPurchaseWeight(7633);
	    fishItem1.setWeight(7633);
	    
	    FishItem fishItem2 = new FishItem();
	    fishItem2.setPurchaseParcel(purchaseParcel);
	    fishItem2.setFishType(adminService.findFTById(2));
	    fishItem2.setPrice(300000);
	    fishItem2.setPurchasePrice(50000);
	    fishItem2.setPurchaseWeight(7233);
	    fishItem2.setWeight(7233);
	    
	    purchaseParcel.setFishItems(Arrays.asList(fishItem1,fishItem2));
	    
	    System.out.println("New parcel id: " + generalManagerService
	    				   .savePurchaseParcel(purchaseParcel).getId() +
	    				   ", item1 id: " + fishItem1.getId() +
	    				   ", item2 id: " + fishItem2.getId());
	}
	
	public static void checkUserStory6(){
		PurchaseParcel purchaseParcel = adminService.findPPById(2);
		List<FishItem> fishItems = (List<FishItem>)purchaseParcel.getFishItems();
		
		for(FishItem fi: fishItems)
			System.out.println(" item" + fi.getId() + " old weight: " + fi.getWeight());
		
		purchaseParcel.setShip("Trimbita UA");
		fishItems.get(0).setWeight(1000);
		fishItems.get(1).setWeight(2000);
		purchaseParcel.setFishItems(fishItems);
		generalManagerService.savePurchaseParcel(purchaseParcel);
	    
		purchaseParcel = adminService.findPPById(2);
		fishItems = (List<FishItem>)purchaseParcel.getFishItems();
		
		for(FishItem fi: fishItems)
			System.out.println(" item" + fi.getId() + " new weight: " + fi.getWeight());
	}
	
	public static void checkUserStory8(){
		for(FishItem fi: generalManagerService.getItemsOnSale())
			System.out.println("id: " + fi.getId() + " price: " + fi.getPrice());
		
		FishItem fishItem;
		System.out.println("Old price: " + (fishItem = adminService.findFIById(4)).getPrice());
		
		fishItem.setPrice(444444);
		generalManagerService.updateFishItem(fishItem);
		
		System.out.println("New price: " + (fishItem = adminService.findFIById(4)).getPrice());
	}
	
	public static void checkUserStory10(){
		User cust = adminService.findUById(2);
		System.out.println("Old prepayment percent: " + cust.getPrepaymentPercent());
		cust.setPrepaymentPercent(55);
		generalManagerService.updateCustomer(cust);
		cust = adminService.findUById(2);
		System.out.println("New prepayment percent: " + cust.getPrepaymentPercent());
	}
	
	public static void checkUserStory11(){
		TotalReport tReport = generalManagerService.
				generateTotalReport(LocalDateTime.of(2015,10,5,13,00), LocalDateTime.of(2015,10,8,15,00));
		System.out.println("Total sum: " + tReport.getSum() + 
						   "Total weight: " + tReport.getWeight() + 
						   "Total income: " + tReport.getIncome());
	}
	
	public static void checkUserStory12(){
		for(ReportByFish reportByFish: generalManagerService.
				generateReportByFish(LocalDateTime.of(2015,10,5,13,00), LocalDateTime.of(2015,10,8,15,00))){
			
			System.out.println("Fish: " + reportByFish.getName() +
							   " Sum: " + reportByFish.getSum() + 
					   		   " Weight: " + reportByFish.getWeight() + 
					   		   " Income: " + reportByFish.getIncome());
		}
		
		for(ReportByDate reportByDate: generalManagerService.
				generateReportByDate(LocalDateTime.of(2015,10,5,13,00), LocalDateTime.of(2015,10,8,15,00))){
			
			System.out.println("From " + reportByDate.getBeginDTime() +
							   " to  " + reportByDate.getEndDTime() +
							   " Sum: " + reportByDate.getSum() + 
					   		   " Weight: " + reportByDate.getWeight() + 
					   		   " Income: " + reportByDate.getIncome());
		}
	}
	
	public static void checkUserStory13(){
		PurchaseParcel pp = coldStoreManagerService.getParcelsToArrive().get(0);
		pp.setArrived(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		List<FishItem> fishItems = new ArrayList<>();
		FishItem fi1 = new FishItem();
		fi1.setFishType(adminService.findFTById(1));
		fi1.setPurchaseParcel(pp);
		fi1.setPurchaseWeight(6000);
		fi1.setPurchasePrice(34000);
		
		FishItem fi2 = new FishItem();
		fi2.setFishType(adminService.findFTById(4));
		fi2.setPurchaseParcel(pp);
		fi2.setPurchaseWeight(5000);
		fi2.setPurchasePrice(58000);
		fishItems.add(fi1);
		fishItems.add(fi2);
		fishItems.add(((List<FishItem>)pp.getFishItems()).get(0));
		pp.setFishItems(fishItems);
		coldStoreManagerService.registerParcelArrival(pp);
	    for(FishItem fi: fishItems)
	    	System.out.println("FishItem id: " + fi.getId());
	}
	
	public static void checkUserStory14(){
		for(SaleParcel sp: coldStoreManagerService.getParcelsToShip()){
			System.out.println("SP.id = " + sp.getId() + " Old status: " + sp.getStatus());
			sp = coldStoreManagerService.registerShipment(sp);
			System.out.println("SP.id = " + sp.getId() + " New status: " + sp.getStatus());
		}
	}
	
	public static void checkUserStory15(){
		for(FishItem fi: coldStoreManagerService.getCurrentFishItems()){
			System.out.println("FI.id = " + fi.getId());
			fi.setWrittenOff(java.sql.Timestamp.valueOf(LocalDateTime.now()));
			fi = coldStoreManagerService.updateFishItem(fi);
			System.out.println("FI.id = " + fi.getId() + " Written-Off: " + fi.getWrittenOff());
		}
	}
	
	public static void checkUserStory16(){
		//Total sum for payment: 24,000,000.00
		Payment pmnt = new Payment();
		pmnt.setSaleParcel(adminService.findSPById(2));
		pmnt.setTstamp(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		pmnt.setAmount(7000000); 
	    System.out.println("Residue: " + accountantService.registerPayment(pmnt) + 
	    				   ", parcel status: " + adminService.findSPById(2).getStatus());
	    
	    pmnt = new Payment();
		pmnt.setSaleParcel(adminService.findSPById(2));
		pmnt.setTstamp(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		pmnt.setAmount(16000000); 
		System.out.println("Residue: " + accountantService.registerPayment(pmnt) + 
				   ", parcel status: " + adminService.findSPById(2).getStatus());
	    
	    pmnt = new Payment();
		pmnt.setSaleParcel(adminService.findSPById(2));
		pmnt.setTstamp(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		pmnt.setAmount(2000000); 
		System.out.println("Residue: " + accountantService.registerPayment(pmnt) + 
				   ", parcel status: " + adminService.findSPById(2).getStatus());
	}
	
	public static void checkUserStory19(){
		User user = new User();
		user.setName("Jimmy Hendrix");
		user.setEmail("j.hendrix@gmail.com");
		user.setLogin("j.hendrix");
		try{user.setPassword(PasswordHash.createHash("j.hendrix"));}
		catch(Exception e) { }
		user.setRoleId("Accountant");
		System.out.println("New user id: " + securityOfficerService.saveUser(user).getId());
	}
	
	public static void checkUserStory20(){
		User user = adminService.findUById(7);
		securityOfficerService.blockUser(user);
	}
	
	public static void checkUserStory21(){
		User user = new User();
		user.setLogin("j.hendrix");
		user.setPassword("j.hendrix");

		user = userService.login(user);
		 
	    if (user.getId() != 0)
	    	System.out.println("Hello " + user.getName() + "!");
	    else 
	    	System.out.println("Sorry, logon failed :(");
	}
}
