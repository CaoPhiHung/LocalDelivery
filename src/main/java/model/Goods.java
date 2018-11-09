package main.java.model;

import java.io.Serializable;

public class Goods implements Serializable {

	private int goodsId;
	private String name;
	private double price;
	private int quantity;
	private String imgPath;
	
	public Goods(int goodsId, String name, double price, int quantity, String imgPath){
		this.goodsId = goodsId;
		this.name= name;
		this.price = price;
		this.quantity = quantity;
		this.imgPath= imgPath;
	}
	
}
