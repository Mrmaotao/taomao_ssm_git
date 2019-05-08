package com.tit.taomao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tit.taomao.entity.Product;
import com.tit.taomao.entity.ProductExample;
import com.tit.taomao.mapper.ProductMapper;
import com.tit.taomao.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
	ProductMapper productmapper;
	@Override
	public void add(Product product) {
        productmapper.insert(product);
	}

	@Override
	public void delete(int id) {
        productmapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Product product) {
        productmapper.updateByPrimaryKey(product);
    }

	@Override
	public Product get(int id) {
		return productmapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Product> listProduct(int id) {
		 /** 查询对应id的所有产品*/
		 ProductExample example = new ProductExample();
		 example.createCriteria().andCidEqualTo(id);
		 /** 降序排列*/
		 example.setOrderByClause("id desc");
		return productmapper.selectByExample(example);
	}

}
