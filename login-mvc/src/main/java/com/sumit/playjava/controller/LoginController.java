package com.sumit.playjava.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.sumit.playjava.model.Transaction;
import com.sumit.playjava.model.UserAccount;
import com.sumit.playjava.repo.TransactionRepo;
import com.sumit.playjava.repo.UserAccountRepo;
import com.sumit.playjava.service.TransactionService;
import com.sumit.playjava.service.UserAccountService;



@Controller
public class LoginController {
	
	//@Autowired
	//private UserRepo userRepo;
	
	@Autowired
	UserAccountRepo userAccountRepo;
	
	@Autowired
	private UserAccountService userAccountService;
	
	
	
	@Autowired
	private TransactionService transactionService;
	
	Transaction trans = new Transaction();
	
	UserAccount userAccount = new UserAccount();
	UserAccount u = new UserAccount();
	/*
	 * @Autowired UserAccount userAccount;
	 */
	RestTemplate restTemplate = new RestTemplate();
	
	int x;
	
	@RequestMapping("/")
	public String checkMVC() {
		return "login";
		
	}
	
	@RequestMapping("/login")
	public String loginHomePage(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model) {
		try {
			
			u = userAccountRepo.findByUsernameAndPassword(username, password);
		}catch(Exception e) {
			model.addAttribute("error", "User is not valid");
		}
		if(u!=null) {
			u.setUsername(username);
			model.addAttribute("userName", username);
			model.addAttribute("password", password);
			return "home";
		}
		return "login";
	}
	
	@RequestMapping("/register")
	public String registerService() {
		return "register";
	}
	
	/*@RequestMapping("/set-user")
	public String registerMicroservice(@RequestParam("username") String username, @RequestParam("password1") String password1, @RequestParam("password2") String password2, @RequestParam("accountno") int accountno, @RequestParam("totalbal") int totalbal,@RequestParam("contactno") String contactno, Model model) {
		System.out.println("Going to microservice from login microservice start!");
		if(password1.equals(password2) && username != null && password1 != null && accountno != null && totalbal != null) {
			restTemplate.getForObject("http://localhost:8081/register-user/"+accountno+"/"+username+"/"+password1+"/"+totalbal+"/"+contactno+"/", String.class);
			 model.addAttribute("registerSuccess", "Successfull registered. Kindly login");
		}else {
			model.addAttribute("registrationError", "Passwords are not the same!");
		}
		System.out.println("Register microservice from login microservice end!");
		return "login";
	}*/
	
	@RequestMapping("/useraccount")
	public ModelAndView home() {
		if(Objects.nonNull(u.getUsername())) {
	    List<UserAccount> listUserAccount = userAccountService.listAll();
	    ModelAndView mav = new ModelAndView("useraccount");
	    mav.addObject("listUserAccount", listUserAccount);
	    return mav;
		}else {
			
	    	ModelAndView mav = new ModelAndView("redirect:/");
			mav.addObject("user",new Transaction() );
			return mav;
		}
	}
	
	/*@RequestMapping("/new")
	public String newCustomerForm(Map<String, Object> model) {
	    //UserAccount userAccount = new UserAccount();
	    model.put("useraccount", userAccount);
	    return "new_useraccount";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("useraccount") UserAccount userAccount) {
		userAccountService.save(userAccount);
		x = userAccount.getTotalbal();
		//userAccount.setTotalbal(userAccount.getTotalbal());
	    return "redirect:/useraccount";
	}*/
	
	/*@RequestMapping("/transaction")
	public ModelAndView transaction() {
		
		if(Objects.nonNull(u.getUsername())) {
			
			List<Transaction> listTransaction = transactionService.listAll();
		    ModelAndView mav = new ModelAndView("transaction");
		    mav.addObject("listTransaction", listTransaction);
		    
		    	return mav;
		}else {
				
		    	
		    	ModelAndView mav = new ModelAndView("redirect:/");
				mav.addObject("user",new Transaction() );
				return mav;
		}
	   
	}*/
	
	
	@RequestMapping("/transaction")
	public ModelAndView transaction() {
		
		if(Objects.nonNull(u.getUsername())) {
			
			List<Transaction> listTransaction = transactionService.listAll();
		    ModelAndView mav = new ModelAndView("transaction");
		    mav.addObject("listTransaction", listTransaction);
		    
		    	return mav;
		}else {
				
		    	
		    	ModelAndView mav = new ModelAndView("redirect:/");
				mav.addObject("user",new Transaction() );
				return mav;
		}
	   
	}
	
	@RequestMapping("/new-transaction")
	public String goToNewTransaction() {
		if(Objects.nonNull(u.getUsername())) {
		return "new-transaction";
		}else {
			return "redirect:/";
		}
	}

	
	@RequestMapping("/save-transaction")
	public String goToNewTransactionMicroservice(@RequestParam("accountno") int accountno, @RequestParam("toaccountnumber") int toaccountnumber, @RequestParam("transferamount") int transferamount, Model model) {
		
		//if(accountno != 0) {
		 
		 restTemplate.getForObject("http://localhost:8083/transfer-money/"+accountno+"/"+toaccountnumber+"/"+transferamount+"", String.class);
			 model.addAttribute("transferSuccess", "Successfully Transferred!");
			
			 	userAccount.setAccountno(accountno);
			 	u.getAccountno();
				int transfer = transferamount;
				int bal = x;
				int newbal = bal - transfer;
				userAccount.setTotalbal(newbal);
				HttpHeaders header = new HttpHeaders();
				System.out.println(bal);
		        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		        
		        HttpEntity<UserAccount> entitys = new HttpEntity<UserAccount>(userAccount, header);

		         restTemplate.exchange(
		           "http://localhost:8083/update-balance", HttpMethod.PUT, entitys, String.class).getBody();
		         
		         x = newbal;
		//}else {
			//model.addAttribute("transferError", "Error! Please Try Again!");
		//}		
		return "home";
	}
	
	@RequestMapping("/home")
	public String homes() {
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	
	/*@PutMapping("/update-balance")
	public UserAccount updateUserAccount(@RequestBody UserAccount userAccount) {
		
		return transService.updateUserAccount(userAccount);
		if(userAccount.getTotalBal()  > transferAmount && accountNo != null) {
			int total = userAccount.getTotalBal() - transferAmount;
			userAccount.setTotalBal(total);		
			
		}else {
			return "Insuficient Balance";
		}
	}*/
	
	/*@GetMapping("/update")
	public String update(){
		userAccount.setAccountno(trans.getAccountno());
		int transfer = trans.getTransferamount();
		int bal = userAccount.getTotalbal();
		int newbal = bal - transfer;
		userAccount.setTotalbal(newbal);
		HttpHeaders header = new HttpHeaders();
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        HttpEntity<UserAccount> entitys = new HttpEntity<UserAccount>(userAccount, header);

         restTemplate.exchange(
           "http://localhost:8083/update-balance", HttpMethod.PUT, entitys, String.class).getBody();
			return "new-transaction";
	}*/
	
	
}

	
