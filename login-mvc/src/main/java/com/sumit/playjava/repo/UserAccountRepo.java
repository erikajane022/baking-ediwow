package com.sumit.playjava.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.playjava.model.UserAccount;

public interface UserAccountRepo extends JpaRepository<UserAccount, Integer>{

	//UserAccount findByAccountno(int accountno);


	//UserAccount findByAccountNo(int accountno);
	//UserAccount findByTotalBal(int totalBal);
	

   /* public void saveCustomer(Customer theCustomer);

    public Customer getCustomer(int theId);

    public void deleteCustomer(int theId);*/
	UserAccount findByUsernameAndPassword(String username, String password);
}