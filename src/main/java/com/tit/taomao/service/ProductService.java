package com.tit.taomao.service;

import java.util.List;

import com.tit.taomao.entity.Product;

public interface ProductService {
    /** 添加产品*/
	public void add(Product product);
	
	/** 删除产品*/
	public void delete(int id);
	
	/** 修改产品*/
	public void update(Product product);
	
	/** 单个查找产品*/
	public Product get(int id);
	
	/** 查找全部产品*/
	public List<Product>listProduct(int id);
}
