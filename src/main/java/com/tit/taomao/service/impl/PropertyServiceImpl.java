package com.tit.taomao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tit.taomao.entity.Property;
import com.tit.taomao.entity.PropertyExample;
import com.tit.taomao.mapper.PropertyMapper;
import com.tit.taomao.service.PropertyService;
@Service
public class PropertyServiceImpl implements PropertyService{
	/**自动注入对Property操作的对象*/
	@Autowired
	PropertyMapper propertyMapper;
	/**添加*/
	public void add(Property p) {
        propertyMapper.insert(p);
	}
    /**删除*/
	@Override
	public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);		
	}
    /**修改*/
	@Override
	public void update(Property p) {
        propertyMapper.updateByPrimaryKeySelective(p);		
	}
    /**多个查询*/
	@Override
	public List list(int id) {
        PropertyExample example = new PropertyExample();
        /*
         * 查询id对应值这个字段的所有数据
         */
        example.createCriteria().andCidEqualTo(id);
        /*
         * 降序排列
         */
        example.setOrderByClause("id desc");
		return propertyMapper.selectByExample(example);
	}
    /**单个查询*/
	@Override
	public Property get(int id) {
        
		return propertyMapper.selectByPrimaryKey(id);
	}

}
