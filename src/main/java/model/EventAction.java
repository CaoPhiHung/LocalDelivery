package main.java.model;

import java.io.Serializable;
import java.util.ArrayList;

public class EventAction implements Serializable {
	public int eventType;
	public User user;
	public GenericDLinkedList<Order> order_list;
	public GenericDLinkedList<Goods> goods_list;

}
