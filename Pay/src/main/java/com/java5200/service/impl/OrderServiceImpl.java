package com.java5200.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.java5200.dao.OrderDao;
import com.java5200.entity.Order;
import com.java5200.service.OrderService;

/**
 * 订单Service实现类
 * @author CSH
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Resource
	private OrderDao orderDao;
	
	@Override
	public void save(Order order) {

		orderDao.save(order);
	}
	
	@Override
	public Order getById(Integer id) {
		return orderDao.getOne(id);
	}

	@Override
	public Order getByOrderNo(String orderNo) {
		
		return orderDao.getByOrderNo(orderNo);
	}

	@Override
	public List<Order> list(Order order, Integer page, Integer pageSize) {
		Pageable pageable=new PageRequest(page-1,pageSize,Sort.Direction.DESC,"buyTime");
		Page<Order> pageOrder = orderDao.findAll(new Specification<Order>() {
			
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				predicate.getExpressions().add(cb.equal(root.get("isPay"), 1));
				return predicate;
			}
		}, pageable);
		return pageOrder.getContent();
	}

	@Override
	public Long getCount(Order order) {
		return orderDao.count(new Specification<Order>() {

			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				predicate.getExpressions().add(cb.equal(root.get("isPay"), 1));
				return predicate;
			}
		});
	}

	
}
