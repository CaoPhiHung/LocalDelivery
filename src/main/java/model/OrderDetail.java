package main.java.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {

	public int orderDetailId;
	public int orderId;
	public int goodsId;
	public int quantity;
	
	/**
	 * 
	 * @param id
	 * @param orderId
	 * @param goodsId
	 * @param quantity
	 */
	public OrderDetail(int id, int orderId, int goodsId, int quantity) {
		this.orderDetailId = id;
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.quantity = quantity;
	}

	/**
	 * 
	 * @param orderId
	 * @param goodsId
	 * @param quantity
	 */
    public OrderDetail(int orderId, int goodsId, int quantity) {
        this(0,orderId,goodsId,quantity);
    }

    /**
     * 
     * @return
     */
    public String displayOrderDetail()
    {
        String toReturn = "odID: " + orderDetailId + "- quantity: " + quantity + "- goodsId: " + goodsId;
        return toReturn;
    }
	
	
}
