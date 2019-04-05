package com.tit.taomao.mapper;

import java.util.List;

import com.tit.taomao.entity.Category;
import com.tit.taomao.util.Page;

public interface CategoryMapper {
	 List<Category>list();
//	 List<Category>listPage(Page page);
//	 int total();
	 void add(Category category);
	 void delete(int id);
	 Category get(int id);
	 void update(Category category);
}
