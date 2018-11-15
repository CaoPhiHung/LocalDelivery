package main.java.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Comparable<Order>,Serializable {

	public int orderId;
	public  ArrayList<Goods> good_list;
	public int userId;
	public Date date;
	public double totalPrice;
	public String destination;
	private int compareType = 0;
	
	public Order(int id, int userId, Date date, double totalPrice, String destination) {
		this.orderId = id;
		this.userId = userId;
		this.date = date;
		this.totalPrice = totalPrice;
		this.destination = destination;
	}

    @Override
    public String toString() {
	    String toReturn = "orderId: " + orderId +
                "- userId:" + userId +
                "- date:" + date.toString() +
                "- totalPrice: " + totalPrice +
                "- destination: " + destination;
        return toReturn;
    }

    public String displayOrder()
    {
        String toReturn = "oID: " + orderId + "date: " + date.toString();
        return toReturn;
    }

	@Override
	public int compareTo(Order o) {
		
		switch (compareType) {
		case 0: //compare by Date
			return this.date.compareTo(o.date);
		case 1: //compare by Id
			if(this.orderId > o.orderId){
				return 1;
			}else if(this.orderId < o.orderId){
				return -1;
			}
		default:
			break;
		}
		return 0;
	}
	
	public void setCompareType(int compareType){
		this.compareType = compareType;
	}
	
}
