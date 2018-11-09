package main.java.model;

import java.io.Serializable;
import java.util.ArrayList;

public class EventAction implements Serializable {
	public int eventType;
	public int requireId;
	public User user;
	public GenericDLinkedList<Order> order_list;
	public GenericDLinkedList<Goods> goods_list;
	public GenericDLinkedList<OrderDetail> order_detail_list;
}
