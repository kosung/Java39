package bit.java39.listeners;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import bit.java39.utils.DBConnectionPool;

/* 
 * 학습목표:
 * 1) properties 파일을 로딩하는 방법.
 * 		- <context-param> 정의하고, 사용하는 방법
 * 		- context-param 값을 꺼내는 방법
 * 2) 동적으로 클래스를 로딩하여 인스턴스를 만드는 방법
 * 3) 해당 객체의 메서드를 조회하는 방법
 * 4) 메서드의 파라미터 정보를 조회하는 방법
 * 5) 조회된 메서드를 호출하는 방법
 */
@SuppressWarnings("rawtypes")
public class ContextLoaderListener implements ServletContextListener {
	ServletContext ctx;
	Properties contextInfo;
	Hashtable<String,Object> beanContainer = new Hashtable<String,Object>();
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// 웹 애플리케이션이 종료되기 직전에 호출된다.
		// 마무리 작업. DB연결을 close....
		System.out.println("contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ctx = event.getServletContext();

		try {
			DBConnectionPool pool = new DBConnectionPool(
				"oracle.jdbc.driver.OracleDriver", 
				"jdbc:oracle:thin:@192.168.0.253:1521:xe", 
				"scott", "tiger");
			
			beanContainer.put("dbPool", pool);
			
			System.out.println("context.properties 파일 로딩----------------------------------------------------");
			loadContextProperties();
			
			System.out.println("인스턴스 생성----------------------------------------------------");
			prepareInstances();
			
			System.out.println("의존객체 주입----------------------------------------------------");
			injectDependancy();
			
			ctx.setAttribute("beanContainer", beanContainer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void injectDependancy() throws Exception {
		Class clazz = null;
		Class[] paramClasses = null;
		
		for(Object bean : beanContainer.values()) {
			clazz = bean.getClass();
			System.out.println("   [" + clazz.getName() + "]의 의존객체를 주입 중입니다.");
			for(Method method : clazz.getMethods()) {
				if (method.getName().startsWith("set")) {
					paramClasses = method.getParameterTypes();
					if (paramClasses.length == 1) {
						Object dependancy = findObject(paramClasses[0]);
						if (dependancy != null) {
							method.invoke(bean, dependancy);
							System.out.println("      " + method.getName() + "의 의존 객체를 찾아서 주입했습니다.");
						}
					}
				}
			}
		}
	}
	
	private Object findObject( Class clazz) {
		for(Object bean : beanContainer.values()) {
			if (clazz.isInstance(bean)) {
				return bean;
			}
		}
		
		return null;
	}

	private void prepareInstances() throws Exception {
		// 빈의 이름과 클래스 명을 추출하여, 인스턴스를 생성한 후 보관한다.
		Enumeration<Object> keyList = contextInfo.keys();
		String beanName = null;
		String className = null;
		while(keyList.hasMoreElements()) {
			beanName = (String)keyList.nextElement();
			className = contextInfo.getProperty(beanName);
			beanContainer.put(beanName, Class.forName(className).newInstance());
			System.out.println(className + " 인스턴스를 생성하였습니다!");
		}
	}

	private void loadContextProperties() throws Exception {
		// 1. properties 파일의 경로를 알아낸다.
		FileReader propReader = new FileReader(	ctx.getRealPath( 
						ctx.getInitParameter("contextConfigLocation")));
		
		// 2. 파일을 찾아서 읽어들인다.
		this.contextInfo = new Properties();
		this.contextInfo.load(propReader);
		propReader.close();
		
		//1) 값 1개 꺼내기
		/*
		System.out.println(contextInfo.getProperty("employeeDao"));
		*/
		
		//2) 모든 값 꺼내기
		/*
		for (Object item : contextInfo.values()) {
			System.out.println((String)item);
		}*/
		
		//3) 모든 키 꺼내기
		/*
		Enumeration<Object> keyList = contextInfo.keys();
		while(keyList.hasMoreElements()) {
			System.out.println((String)keyList.nextElement());
		}*/
		
	
	}

}




























