package com.tit.taomao.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tit.taomao.entity.Category;
import com.tit.taomao.entity.Product;
import com.tit.taomao.service.CategoryService;
import com.tit.taomao.service.ProductService;
import com.tit.taomao.util.Page;

@Controller
@RequestMapping("")
public class ProductController {
    
	@Autowired
    ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	/** 分页查找*/
	@RequestMapping("admin_product_list")
	public String list(Model model, int cid, Page page) {
		Category category = categoryService.get(cid);
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<Product> listProduct = productService.listProduct(cid);
		
		int total = (int) new PageInfo<>(listProduct).getTotal();
		page.setTotal(total);
		page.setParam("&cid="+category.getId());
		
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("category", category);
		model.addAttribute("page", page);
		return "admin/listProduct";
		}
	/**添加商品信息*/
	@RequestMapping("admin_product_add")
	public String add(Model model,Product p) {
		p.setCreateDate(new Date());
		productService.add(p);
		System.out.println(p.getName());
		return "redirect:admin_product_list?cid="+p.getCid();
	}
}
