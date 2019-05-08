package com.tit.taomao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tit.taomao.entity.Category;
import com.tit.taomao.entity.Property;
import com.tit.taomao.service.CategoryService;
import com.tit.taomao.service.PropertyService;
import com.tit.taomao.util.Page;

@Controller
@RequestMapping("")
public class PropertyController {
    /** 分类逻辑控制对象*/
	@Autowired
	CategoryService categoryService;
	/** 属性逻辑控制对象*/
    @Autowired
    PropertyService propertyService;
    
    /** 添加新属性*/
    @RequestMapping("admin_property_add")
    public String add(Property p,Model model){
    	propertyService.add(p);
    	return "redirect:admin_property_list?cid="+p.getCid();
    }
    
    /** 删除该条属性*/
    @RequestMapping("admin_property_delete")
    public String delete(int id){
    	Property pp = propertyService.get(id);
        propertyService.delete(id);
    	return "redirect:admin_property_list?cid="+pp.getCid();
    }
    
    /** 编辑属性信息*/
    @RequestMapping("admin_property_edit")
    public String edit(Model model,int id){
		Property p = propertyService.get(id);
		Category c = categoryService.get(p.getCid());
		p.setCategory(c);
		model.addAttribute("p",p);
    	return "admin/editProperty";
    	
    }
    
    /** 属性修改控制器*/
    @RequestMapping("admin_property_update")
    public String update(Property p){
		propertyService.update(p);
    	return "redirect:admin_property_list?cid="+p.getCid();
    	
    }
    
    /** 查询并显示出某分类的所有属性*/
    @RequestMapping("admin_property_list")
    public String list(Model model,int cid,Page page){
    	/** 获取选择的分类对象*/
    	Category c = categoryService.get(cid);
    	/** 获取首页和每一一页的数目*/
    	PageHelper.offsetPage(page.getStart(),page.getCount());
    	/** 获取该分类对象的属性集合*/
    	List<Property> list = propertyService.list(cid);
    	/** 获取后台数据库中该分类对象属性的总数*/
    	int total = (int) new PageInfo<>(list).getTotal();
    	page.setTotal(total);
    	page.setParam("&cid="+c.getId());
    	
    	model.addAttribute("list",list);
    	model.addAttribute("page",page);
    	model.addAttribute("c",c);
    	
    	return "admin/listProperty";
        
    }
}
