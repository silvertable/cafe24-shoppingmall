package com.cafe24.shoppingmall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.OrderDetailVO;
import com.cafe24.shoppingmall.vo.OrderVO;

@Repository
public class OrderDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public Boolean deleteAll1() {
		return 1 == sqlSession.delete("order.deleteAll1");
	}
	
	public Boolean deleteAll2() {
		return 1 == sqlSession.delete("order.deleteAll2");
	}

	public OrderVO orderAdd(OrderVO orderVO) {
		sqlSession.insert("order.orderAdd", orderVO);
		return orderVO;
	}

	public Boolean orderAddDetail(OrderDetailVO detailVO) {
		return 1 == sqlSession.insert("order.orderAddDetail", detailVO);
	}

	public List<OrderVO> orderGet(Integer memberNo) {
		return sqlSession.selectList("order.orderGetWithUser", memberNo);
	}

	public List<OrderVO> orderGetNoUser(OrderVO orderVO) {
		return sqlSession.selectList("order.orderGetWithNoUser", orderVO);
	}

	public Boolean update(OrderVO vo) {
		return 1 == sqlSession.update("order.update", vo);
	}

	public List<OrderVO> orderGet() {
		return sqlSession.selectList("order.orderGet");
	}


}
