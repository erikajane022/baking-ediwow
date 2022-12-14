package com.javier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javier.model.Transaction;
import com.javier.model.TransactionRepo;
import com.javier.model.*;

@RestController
public class TransactionController {

	@Autowired
	private TransactionRepo transactionRepo;
	@Autowired
	private UserAccountService userAccountService;
	
	
	
	@RequestMapping("/check-transfer")
	public String check() {
		return "checked transfer!";
	}
	
	@PutMapping("/update-balance")
	public UserAccount updateUserAccount(@RequestBody UserAccount useraccount) {
		
		return userAccountService.updateUserAccount(useraccount);
		
	}
	
	@RequestMapping("/transfer-money/{accountno}/{toaccountnumber}/{transferamount}")
	public String transferMoney(@PathVariable("accountno") int accountno,
			@PathVariable("toaccountnumber") int toaccountnumber, @PathVariable("transferamount") int transferamount) {
		System.out.println("Register microservice from login microservice start!");
				
		Transaction u= new Transaction();
		u.getTransactionId();
		u.setAccountno(accountno);
		u.setToaccountnumber(toaccountnumber);
		u.setTransferamount(transferamount);

		transactionRepo.save(u);
		System.out.println("Transaction microservice from login microservice end!");
		
		return "Successfully Transferred!";
	}
}
