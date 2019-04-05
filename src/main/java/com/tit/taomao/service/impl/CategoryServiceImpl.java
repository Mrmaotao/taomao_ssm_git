package com.tit.taomao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tit.taomao.entity.Category;
import com.tit.taomao.mapper.CategoryMapper;
import com.tit.taomao.service.CategoryService;
import com.tit.taomao.util.Page;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryMapper category;
	public List<Category>list(){
		return category.list();
	}
	@Override
	public List<Category> list(Page page) {
		
		return category.listPage(page);
	}
	@Override
	public int total() {
		return category.total();
	}
	@Override
	public void add(Category c) {
		category.add(c);
	}
	@Override
	public void delete(int id) {
		category.delete(id);
	}
	@Override
	public Category get(int id) {
		return category.get(id);		
	}
	@Override
	public void update(Category category1) {
		category.update(category1);
	}
	
}
