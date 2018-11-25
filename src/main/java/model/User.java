package main.java.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

	public int userId;
	public String username;
	public String fullname;
	public String password;
	public String address;
	public String email;
	public String phone;
	
	public ArrayList<Order> order_list;
	public User(){
		
	}
	
	/**
	 * 
	 * @param userId
	 * @param username
	 * @param fullname
	 * @param password
	 * @param email
	 * @param phone
	 * @param address
	 */
	public User(int userId, String username, String fullname, String password, String email, String phone , String address){
		this.userId = userId;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	
	/**
	 * 
	 * @param checkUser
	 * @return
	 */
	public boolean checkAuthenticate(User checkUser){
		if(checkUser.username != null && checkUser.password != null
            && this.username.equals(checkUser.username) && this.password.equals(checkUser.password)){
			return true;
		}
		return false;
	}

    @Override
    public String toString() {
        return "Data: " + userId + " - " + username + " - " + fullname + " - " + password + " - " +email;
    }
}
