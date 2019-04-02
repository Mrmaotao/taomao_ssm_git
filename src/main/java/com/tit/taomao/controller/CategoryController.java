package com.tit.taomao.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.tit.taomao.entity.Category;
import com.tit.taomao.service.CategoryService;
import com.tit.taomao.util.ImageUtil;
import com.tit.taomao.util.Page;
import com.tit.taomao.util.UpLoadImage;

@Controller
@RequestMapping("")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@RequestMapping("admin_category_list")
	public String list(Model model,Page page){
		//获取当前页的分类信息
		List<Category>cs = categoryService.list(page);
		int total = categoryService.total();
		page.setTotal(total);
		System.out.println("输出"+cs);
		model.addAttribute("cs",cs);
		model.addAttribute("page",page);
		return "admin/listCategory";
	}
	@RequestMapping("admin_category_add")
	public String add(Category c,HttpSession session,UpLoadImage upLoadImage) throws Exception, IOException{
		//1.要添加的category对象
		categoryService.add(c);
        //2.上传图片的绝对地址
		File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
		System.out.println(imageFolder);
		File file = new File(imageFolder,c.getId()+".jpg");
		System.out.println(file);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		upLoadImage.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);
		ImageIO.write(img, "jpg", file);
		return "redirect:/admin_category_list";
	}
}