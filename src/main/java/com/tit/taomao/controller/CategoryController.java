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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
		//获取分页的起始页和一页的数据数
		PageHelper.offsetPage(page.getStart(), page.getCount());
		//获取当前页的分类信息
		List<Category>cs = categoryService.list();
		//获取从后台查出数据的总数
		int total = (int) new PageInfo<>(cs).getTotal();
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
        //2.上传图片的绝对地址到服务器
		File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
		//File imageFolder = new File("D:\\javadata1\\image");
		
		System.out.println(imageFolder);
		File file = new File(imageFolder,c.getId()+".jpg");
		//System.out.println(file);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		upLoadImage.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);
		ImageIO.write(img, "jpg", file);
		return "redirect:/admin_category_list";
	}
	@RequestMapping("admin_category_delete")
	public String delete(int id,HttpSession session){
		categoryService.delete(id);
		File imgageFolder = new File(session.getServletContext().getRealPath("img/category"));
		File file = new File(imgageFolder,id+".jsp");
		file.delete();
		System.out.println(file);
		return "redirect:admin_category_list";
	}
	
	@RequestMapping("admin_category_edit")
	public String get(int id ,Model model) throws IOException{
		Category category = categoryService.get(id);
		//System.out.println(category);
		model.addAttribute("c", category);
		return "admin/editCategory";
	}
	
	@RequestMapping("admin_category_update")
	public String update(Category category,HttpSession session,UpLoadImage loadImage) throws Exception, IOException{
//		更新的是数据库中的数据
		categoryService.update(category);
//		获取上传的图片
		MultipartFile image = loadImage.getImage();
//		判断是否上传了图片，并且上传的图片不能为空
		if(null!=image&&!image.isEmpty()){
			File imgageFolder = new File(session.getServletContext().getRealPath("img/category"));
			File file = new File(imgageFolder,category.getId()+".jpg");
			image.transferTo(file);
			System.out.println(image);
			BufferedImage img = ImageUtil.change2jpg(file);
			ImageIO.write(img, "jsp", file);
			
		}
		
		
		return "redirect:admin_category_list";
	}
}
