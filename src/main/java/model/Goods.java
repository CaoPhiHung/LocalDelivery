package main.java.model;

public class Goods {

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
