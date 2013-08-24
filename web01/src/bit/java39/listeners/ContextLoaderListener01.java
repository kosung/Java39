package bit.java39.listeners;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FilenameFilter;
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
 * 1) 클래스를 찾아서 동적으로 로딩한다.
 * 2) 로딩된 클래스 중에서 @Component 주석이 달린 것만 빈 컨테이너에서 관리한다.
 */
@SuppressWarnings("rawtypes")
public class ContextLoaderListener01 implements ServletContextListener {
	ServletContext ctx;
	Properties contextInfo;
	Hashtable<String,Object> beanContainer = new Hashtable<String,Object>();
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ctx = event.getServletContext();

		try {
			DBConnectionPool pool = new DBConnectionPool(
				"oracle.jdbc.driver.OracleDriver", 
				"jdbc:oracle:thin:@192.168.0.253:1521:xe", 
				"scott", "tiger");
			
			beanContainer.put("dbPool", pool);
			
			System.out.println("DAO, Controller 클래스 로딩----------------------------------------------------");
			loadComponent();
			
			System.out.println("인스턴스 생성----------------------------------------------------");
			//prepareInstances();
			
			System.out.println("의존객체 주입----------------------------------------------------");
			//injectDependancy();
			
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

	private void loadComponent() throws Exception {
		// 1. dao와 controller 패키지가 있는 시스템 폴더의 경로를 알아낸다.
		String rootPath = ctx.getRealPath("/WEB-INF/classes/bit/java39");
		System.out.println(rootPath);
		
		// 2. dao 패키지 경로에 있는 클래스 파일의 이름을 알아낸다.
		printAllFileName(new File(rootPath), 1);
		
		/*
		System.out.println("dao 패키지의 클래스들...");
		File file = new File(rootPath + "/dao");
		String[] filenames = file.list();
		for(String filename : filenames) {
			System.out.println(filename);
		}
		
		System.out.println("controls 패키지의 클래스들...");
		file = new File(rootPath + "/controls");
		filenames = file.list();
		for(String filename : filenames) {
			System.out.println(filename);
		}
		*/
	}
	
	private void printAllFileName(File file, int indent) throws Exception {
		/*/
		class MyFileFilter implements FileFilter {
			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) { 
					return true;
				} else if (file.getName().endsWith(".class")
						&& !file.getName().contains("$")) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		File[] childFiles = file.listFiles(new MyFileFilter());
		*/
		File[] childFiles = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) { 
					return true;
				} else if (file.getName().endsWith(".class")
						&& !file.getName().contains("$")) {
					return true;
				} else {
					return false;
				}
			}
		});
		
		for(File fileItem : childFiles) {
			
			printIndent(indent);
			
			if (fileItem.isDirectory()) {
				System.out.println("+" + fileItem.getName());
				printAllFileName(fileItem, indent + 1);
			} else {
				String className = fileItem.getName().substring(0, fileItem.getName().indexOf("."));
				System.out.println(className);
				Class clazz = Class.forName(/*패키지 이름은? */ className);
			}
		}
		
	}

	private void printIndent(int indent) {
		for(int i = 0; i < indent; i++) 
			System.out.print("   ");
	}

}




























