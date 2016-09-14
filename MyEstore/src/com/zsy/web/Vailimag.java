package com.zsy.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Vailimag extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-Cache");
		response.setHeader("pragma", "no-Cache");
		int height=30;
		int width=120;
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width-1, height-1);
		g.setColor(Color.RED);
		
		for(int i=0; i<5; i++){
			g.drawLine(randNumber(0, width), randNumber(0, height),randNumber(0, width),  randNumber(0, height));
		}
		
		String base="ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
		g.setFont(new Font("ËÎÌו",Font.BOLD,25));
		g.setColor(Color.BLACK);
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<4; i++){
			double c = randNumber(-45, 45)/180F*Math.PI;
			g.rotate(c, 5+width/4*i, 22);
			String s = base.charAt(randNumber(0,base.length()-1))+"";
			buffer.append(s);
			g.drawString(s, 5+width/4*i, 22);
			g.rotate(-c, 5+width/4*i, 22);
		}
		
		System.out.println(buffer.toString());
		request.getSession().setAttribute("valistr", buffer.toString());
		g.dispose();
		ImageIO.write(img, "jpg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	Random rand = new Random();
	private int randNumber(int begin, int end){
		return rand.nextInt(end-begin)+begin;
	}

}
