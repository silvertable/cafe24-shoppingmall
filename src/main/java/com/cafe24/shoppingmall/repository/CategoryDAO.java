package com.cafe24.shoppingmall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.CategoryVO;

@Repository
public class CategoryDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public CategoryVO insert(CategoryVO vo) {
		sqlSession.insert("category.insert", vo);
		return vo;
	}

	public Boolean deleteAll() {
		sqlSession.delete("category.deleteAllProduct");
		return 1 == sqlSession.delete("category.deleteAll");
	}
	
	public Boolean deleteAllCategoryProduct() {
		return 1 == sqlSession.delete("category.deleteAllProduct");
	}

	public List<CategoryVO> getList() {
		return sqlSession.selectList("category.getList");
	}

	public Boolean update(CategoryVO vo) {
		return 1 == sqlSession.update("category.update", vo);
	}

	public Boolean checkCategoryNo(Integer category_high_no) {
		Integer number = sqlSession.selectOne("category.checkCategoryNo", category_high_no);
		return 0 == number;
	}

	public Boolean delete(Integer category_no) {
		sqlSession.delete("category.deleteCategoryProduct", category_no);
		return 1 == sqlSession.delete("category.delete", category_no);
	}

	public Boolean insertCategoryProduct(CategoryVO categVO) {
		return 1 == sqlSession.insert("category.insertCategoryProduct", categVO);
	}

	public Boolean deleteCategProductByProductNo(Integer no) {
		return 1 == sqlSession.delete("category.deleteCategProductByProductNo", no);
	}

	public List<CategoryVO> getCategoryProduct(Integer no) {
		return sqlSession.selectList("category.getCategoryProduct", no);
	}
	
	public CategoryVO getCategory(Integer no) {
		return sqlSession.selectOne("category.getCategory", no);
	}

	public Boolean updateOrd(CategoryVO categoryVO) {
		return 1 == sqlSession.update("category.updateOrd", categoryVO);
	}

	public List<CategoryVO> getLowList(CategoryVO vo) {
		return sqlSession.selectList("category.getLowList", vo);
	}

	public Boolean updateParent(CategoryVO categoryVO) {
		return 1 == sqlSession.update("category.updateParent", categoryVO);
	}

}
