package com.tit.taomao.service;

import java.util.List;

import com.tit.taomao.entity.Category;
import com.tit.taomao.util.Page;

public interface CategoryService {
	List<Category>list();
	List<Category>list(Page page);
	int total();
	void add(Category category);
	void delete(int id);
	Category get(int id);
	void update(Category category);
}
