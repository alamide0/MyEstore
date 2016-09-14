package com.zsy.factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BasicFactory {
	private static BasicFactory factory = new BasicFactory();
	private static Properties prop;
	private BasicFactory(){
		
	}
	static{
		
		try {
			prop = new Properties();
			prop.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static BasicFactory getFactory(){
		return factory;
	}
	
	public <T> T getInstance(Class<T> clazz){
		
		try {
			String clazzName = clazz.getSimpleName();
			clazzName = prop.getProperty(clazzName);
			return (T) Class.forName(clazzName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
}
