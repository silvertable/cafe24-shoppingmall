package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.OrderDAO;
import com.cafe24.shoppingmall.vo.OrderDetailVO;
import com.cafe24.shoppingmall.vo.OrderVO;
import com.cafe24.shoppingmall.vo.ProductVO;

@Service
public class OrderService {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderDAO orderDAO;

	@Transactional
	public Boolean orderAdd(OrderVO orderVO) {
		// 주문 추가
		OrderVO vo = orderDAO.orderAdd(orderVO);
		
		// 주문상세 추가
		for (OrderDetailVO detailVO : vo.getOrderDetail()) {
			detailVO.setOrder_no(vo.getNo());
			orderDAO.orderAddDetail(detailVO);
		}

		return true;
	}
	
	// junit test용
	@Transactional
	public Boolean deleteAll() {
		orderDAO.deleteAll2();
		return orderDAO.deleteAll1();
	}

	@Transactional
	public List<OrderVO> orderGet(Integer memberNo) {
		List<OrderVO> list = orderDAO.orderGet(memberNo);
		
		for(OrderVO orderVO : list) {
			for(OrderDetailVO vo : orderVO.getOrderDetail()) {
				Integer productNo = productService.getProductNo(vo.getProduct_detail_no());
				ProductVO productVO = productService.getProduct(productNo);
				vo.setOrderInfo(productVO);
			}
		}
		
		return list;
	}


}
