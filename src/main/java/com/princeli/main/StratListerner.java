package com.princeli.main;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StratListerner {

private static final String log4jPath = "/log4j.properties" ;
	
	public void initLog4j() throws Exception {
		Properties pro = new Properties();
		InputStream is = getClass().getResourceAsStream(log4jPath);
		pro.load(is);
		PropertyConfigurator.configure(pro); 
	}
	
	public static void start(String...args) {
		try{
			ApplicationContext applicationContext = null ;
			new StratListerner().initLog4j() ;
			if(null==args || args.length <=0){
				applicationContext = new ClassPathXmlApplicationContext(new String []{"classpath*:spring/applicationContext*.xml","classpath*:mina/applicationContext*.xml","classpath*:hibernate/applicationContext*.xml"}) ;
			}else{
				String[] base = new String []{"classpath*:spring/applicationContext*.xml","classpath*:mina/applicationContext*.xml"} ;
				String[] array = new String[base.length + args.length]  ;
				System.arraycopy(base,0,array,0,base.length);
				System.arraycopy(args,0,array,2,args.length);
				applicationContext = new ClassPathXmlApplicationContext(array) ;
			}
		}catch(Exception e){
			e.printStackTrace() ; 
			System.out.println("初始化HTTP容器失败"); 
		}
	}
}
