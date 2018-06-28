package com.java5200.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.java5200.entity.Order;

/**
 * 订单Dao接口
 * @author CSH
 *
 */
public interface OrderDao extends JpaRepository<Order, Integer>,JpaSpecificationExecutor<Order>{

	@Query(value="select * from t_order where order_no=?1",nativeQuery=true)
	public Order getByOrderNo(String orderNo);
}
