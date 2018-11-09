package main.java.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {

	public int orderId;
	public  ArrayList<Goods> good_list;
	public int userId;
	public Date date;
	public double totalPrice;
	public String destination;
	
	public Order(int id, int userId, Date date, double totalPrice, String destination) {
		this.orderId = id;
		this.userId = userId;
		this.date = date;
		this.totalPrice = totalPrice;
		this.destination = destination;
	}
	
}
