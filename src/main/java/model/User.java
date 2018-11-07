package main.java.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

	public String username;
	public String name;
	public String address;
	public String email;
	public String phone_number;
	public String password;
	public ArrayList<Order> order_list;
	
}
