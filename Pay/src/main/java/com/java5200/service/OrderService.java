package com.java5200.service;

import java.util.List;

import com.java5200.entity.Order;

/**
 * 订单Service接口
 * @author CSH
 *
 */
public interface OrderService {

	public void save(Order order);
	
	public Order getById(Integer id);
	
	public Order getByOrderNo(String orderNo);
	
	public List<Order> list(Order order,Integer page,Integer pageSize);
	
	public Long getCount(Order order);
}
