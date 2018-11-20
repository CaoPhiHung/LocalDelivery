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

	public String displayGoods()
    {
        String returnTo = "goodId: " + goodsId + "- name: " + name +
                "- price: " + price + "- quantity: " + quantity +
                " - imgPath: " + imgPath;

        return returnTo;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
