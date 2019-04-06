package com.tit.taomao.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class BinaryImageUtil {

	//图片到byte数组
	  public static byte[] image2byte(MultipartFile object){
	    byte[] data = null;
	    InputStream input = null;
	    try {
	      input = object.getInputStream();
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	      byte[] buf = new byte[1024];
	      int numBytesRead = 0;
	      while ((numBytesRead = input.read(buf)) != -1) {
	      output.write(buf, 0, numBytesRead);
	      }
	      data = output.toByteArray();
	      output.close();
	      input.close();
	    }
	    catch (FileNotFoundException ex1) {
	      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	      ex1.printStackTrace();
	    }
	    return data;
	  }
	  
	  
	  //byte数组到图片，将字节流转换成BufferedImage
	  public static BufferedImage byte2image(byte[] data) throws IOException{
	    
	    	ByteArrayInputStream in = new ByteArrayInputStream(data); 
	    	System.out.println("输出data:"+data);
	    	//将b作为输入流；
	    	BufferedImage image = ImageIO.read(in);     //将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
	    	System.out.println("Make Picture success,Please find image in "+image );
	    	in.close();
		return image;
	  }
	  //byte数组到图片 ，将字节流直接列转换成图片
	  public static void byte2Image(byte[] data,File file) throws Exception{
		 
		  InputStream in = new ByteArrayInputStream(data);
	        FileOutputStream fos=new FileOutputStream(file);
	        byte[] b = new byte[1024];
	        int nRead = 0;
	        while ((nRead = in.read(b)) != -1) {
	            fos.write(b, 0, nRead);
	        }
	        fos.flush();
	        fos.close();
	        in.close();
	  }
	  //byte数组到16进制字符串
	  public static String byte2string(byte[] data){
	    if(data==null||data.length<=1) return "0x";
	    if(data.length>200000) return "0x";
	    StringBuffer sb = new StringBuffer();
	    int buf[] = new int[data.length];
	    //byte数组转化成十进制
	    for(int k=0;k<data.length;k++){
	      buf[k] = data[k]<0?(data[k]+256):(data[k]);
	    }
	    //十进制转化成十六进制
	    for(int k=0;k<buf.length;k++){
	      if(buf[k]<16) sb.append("0"+Integer.toHexString(buf[k]));
	      else sb.append(Integer.toHexString(buf[k]));
	    }
	    return "0x"+sb.toString().toUpperCase();
	  }
	  
	  
        //确保图片的文件的二进制格式是jpg;图片格式转换，改变大小
		public static BufferedImage change2jpg(File f){
			
	        try {
	        	Image i = Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
	            PixelGrabber pg = new PixelGrabber(i, 0, 0, -1, -1, true);
				pg.grabPixels();
				int width = pg.getWidth(), height = pg.getHeight();
		        final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
		        final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
		        DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
		        WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
		        BufferedImage img = new BufferedImage(RGB_OPAQUE, raster, false, null);
		        return img;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	        
			
		}
		
		public static void resizeImage(File srcFile,int width,int height,File destFile){
			try {
	            if(!destFile.getParentFile().exists())
	                destFile.getParentFile().mkdirs();
	            Image i = ImageIO.read(srcFile);
	            i = resizeImage(i, width, height);
	            ImageIO.write((RenderedImage) i, "jpg", destFile);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		
		}
		
		 public static Image resizeImage(Image srcImage, int width, int height) {
		        try {
		  
		            BufferedImage buffImg = null;
		            buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		            buffImg.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		  
		            return buffImg;
		        } catch (Exception e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		        return null;
		    }
}
