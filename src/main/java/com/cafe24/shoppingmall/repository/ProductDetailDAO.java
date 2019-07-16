package com.cafe24.shoppingmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.ProductDetailVO;

@Repository
public class ProductDetailDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(ProductDetailVO vo) {
		
		return 1 == sqlSession.insert("productDetail.insert", vo);
	}
}