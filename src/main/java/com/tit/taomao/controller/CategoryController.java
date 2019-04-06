package com.tit.taomao.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tit.taomao.entity.Category;
import com.tit.taomao.service.CategoryService;
import com.tit.taomao.util.BinaryImageUtil;
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
		/*
		 * 1.要添加的category对象
		 */
		categoryService.add(c);
        /*
         * 2.上传图片的绝对地址到服务器
         */
		File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
		
		/*
		 * 上窜图片到本地图片地址
		 * File imageFolder = new File("D:\\javadata1\\image");
		 */
		System.out.println(imageFolder);
		/*
		 * 将上传图片重新编名字，并存放到指定文件下
		 */
		File file = new File(imageFolder,c.getId()+".jpg");
		/*
		 * 将上传图片转换成二进制字符流
		 */
		byte []a = BinaryImageUtil.image2byte(upLoadImage.getImage());
		System.out.println("二进制数："+a);
		/* 二进制流转换成图片 
		 * 方法一
		 * BufferedImage img = BinaryImageUtil.byte2image(a);
		 *  ImageIO.write(img, "jpg", file);
		 *  
		 *  方法二,没有使用二进制，将上传图片之间转成bufferedImage
         * upLoadImage.getImage().transferTo(file);
         * BufferedImage img = BinaryImageUtil.change2jpg(file); 
		 * ImageIO.write(img, "jpg", file);
		 */
		
         
		/*
		 * 方法三
		 */
		BinaryImageUtil.byte2Image(a, file);
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
    /*
     * 更新的是数据库中的数据
     */
		categoryService.update(category);
    /*
     * 获取上传的图片
     */
		MultipartFile image = loadImage.getImage();
    /*
     * 判断是否上传了图片，并且上传的图片不能为空
     */
		if(null!=image&&!image.isEmpty()){
			File imgageFolder = new File(session.getServletContext().getRealPath("img/category"));
			File file = new File(imgageFolder,category.getId()+".jpg");
			image.transferTo(file);
			System.out.println(image);
			BufferedImage img = BinaryImageUtil.change2jpg(file);
			ImageIO.write(img, "jsp", file);
            			
		}
		
        		
		return "redirect:admin_category_list";
	}
}
