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
	
	public User(int userId, String username, String fullname, String password, String email, String phone , String address){
		this.userId = userId;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	
	public boolean checkAuthenticate(User checkUser){
		if(this.username.equals(username)){
			return true;
		}
		return false;
	}
}
