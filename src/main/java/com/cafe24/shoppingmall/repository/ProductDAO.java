package com.cafe24.shoppingmall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.dto.PageInfo;
import com.cafe24.shoppingmall.vo.ProductDetailVO;
import com.cafe24.shoppingmall.vo.ProductVO;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public ProductVO insert(ProductVO vo) {
		sqlSession.insert("product.insert", vo);
		return vo;
	}

	public Boolean deleteAll() {
		return 1 == sqlSession.delete("product.deleteAll");
	}

	public Integer totalCount() {
		return sqlSession.selectOne("product.totalCount");
	}

	public List<ProductVO> getList(PageInfo pageInfo) {
		return sqlSession.selectList("product.getList", pageInfo);
	}
	
	public List<ProductVO> getMainList() {
		return sqlSession.selectList("product.getMainList");
	}

	public ProductVO getProduct(Integer no) {
		return sqlSession.selectOne("product.getProduct", no);
	}

	public Boolean update(ProductVO productVO) {
		return 1 == sqlSession.update("product.update", productVO);
	}

	public Boolean updateDel(Integer no) {
		return 1 == sqlSession.update("product.updateDel", no);
	}


}
