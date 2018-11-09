package main.java.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {

	public int orderDetailId;
	public int orderId;
	public int goodsId;
	public int quantity;
	
	public OrderDetail(int id, int orderId, int goodsId, int quantity) {
		this.orderDetailId = id;
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.quantity = quantity;
	}
	
	
}
