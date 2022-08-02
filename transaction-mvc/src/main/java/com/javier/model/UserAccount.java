package com.javier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
//@Table(name="user_account")
public class UserAccount {

    
   /* @GenericGenerator(name = "account_no", strategy = "com.sumit.playjava.model.AccountIdGenerator")
    @GeneratedValue(generator = "account_no") 
    @Column(columnDefinition = "varchar(22)")*/
	@Id
	   /* @GenericGenerator(name = "account_no", strategy = "com.sumit.playjava.model.AccountIdGenerator")
	    @GeneratedValue(generator = "account_no") 
	    @Column(columnDefinition = "varchar(22)")*/
		private String contactno;
		private String username;
		private String password;
	    private int accountno;
	    private int totalbal;
	    @CreationTimestamp
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdate;
		
	    
	    public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getAccountno() {
			return accountno;
		}
		public void setAccountno(int accountno) {
			this.accountno = accountno;
		}
		public String getContactno() {
			return contactno;
		}
		public void setContactno(String contactno) {
			this.contactno = contactno;
		}
		public int getTotalbal() {
			return totalbal;
		}
		public void setTotalbal(int totalbal) {
			this.totalbal = totalbal;
		}
		public Date getCreatedate() {
			return createdate;
		}
		public void setCreatedate(Date createdate) {
			this.createdate = createdate;
		}
		public UserAccount() {
			
		}
		public UserAccount(String username, String password, int accountno, String contactno, int totalbal,
				Date createdate) {
			this.username = username;
			this.password = password;
			this.accountno = accountno;
			this.contactno = contactno;
			this.totalbal = totalbal;
			this.createdate = createdate;
		}
		@Override
		public String toString() {
			return "UserAccount [username=" + username + ", password=" + password + ", accountno=" + accountno
					+ ", contactno=" + contactno + ", totalbal=" + totalbal + ", createdate=" + createdate + "]";
		}


}