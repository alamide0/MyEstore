package com.zsy.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zsy.domain.Product;
import com.zsy.factory.BasicFactory;
import com.zsy.service.ProductService;
import com.zsy.util.IOUtils;
import com.zsy.util.PicUtils;

public class AddProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		Map<String, String> map = new HashMap<String, String>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*1024);
		factory.setRepository(new File(this.getServletContext().getRealPath("WEB-INF/temp")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		
		if(!upload.isMultipartContent(request)){
			throw new RuntimeException("表单提交方式不正确");
		}
		
		
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item: list){
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					map.put(name, value);
				}else{
					String[] name = item.getName().split("\\\\");
					String realname = name[name.length-1];
					String uuidname = UUID.randomUUID().toString()+"_"+realname;
					String hash = Integer.toHexString(uuidname.hashCode());
					String uppath = this.getServletContext().getRealPath("WEB-INF/upload");
					String imgurl = "/WEB-INF/upload";
					for(char c: hash.toCharArray()){
						uppath+="/"+c;
						imgurl+="/"+c;
					}
					imgurl+="/"+uuidname;
					map.put("imgurl", imgurl);
					File f = new File(uppath);
					if(!f.exists()){
						f.mkdirs();
					}
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(new File(uppath,uuidname));
					IOUtils.In2Out(in, out);
					IOUtils.close(in, out);
					item.delete();
					PicUtils pic = new PicUtils(this.getServletContext().getRealPath(imgurl));
					pic.resize(80, 80);
				}
			}
			Product prod = new Product();
			BeanUtils.populate(prod, map);
			ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
			//System.out.println("++++");
			System.out.println(prod.getDescription());
			service.addProduct(prod);
			
			response.getWriter().write("添加商品成功,1秒后回到主页");
			response.setHeader("Refresh", "1;url="+request.getContextPath()+"/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
