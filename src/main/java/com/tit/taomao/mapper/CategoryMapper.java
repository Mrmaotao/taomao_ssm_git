package com.tit.taomao.mapper;

import java.util.List;

import com.tit.taomao.entity.Category;
import com.tit.taomao.util.Page;

public interface CategoryMapper {
	public List<Category>list();
	public List<Category>listPage(Page page);
	public int total();
	public void add(Category category);
}
