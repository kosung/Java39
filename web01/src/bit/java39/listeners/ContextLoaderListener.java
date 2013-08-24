package bit.java39.listeners;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.reflections.Reflections;

import bit.java39.annotations.Component;

/* 
 * 학습목표:
 * 1) 클래스를 찾아서 동적으로 로딩한다.
 * 2) 로딩된 클래스 중에서 @Component 주석이 달린 것만 빈 컨테이너에서 관리한다.
 */
@WebListener
@SuppressWarnings("rawtypes")
public class ContextLoaderListener implements ServletContextListener {
	ServletContext ctx;
	Properties contextInfo;
	Hashtable<String,Object> beanContainer = new Hashtable<String,Object>();
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ctx = event.getServletContext();

		try {
			String mybatisConfig = "bit/java39/conf/mybatis-config.xml";
			/*
			String realPath = event.getServletContext().getRealPath("/WEB-INF/classes/" + mybatisConfig);
			FileInputStream inputStream = new FileInputStream(realPath);
			*/
			// classpath에서 해당 경로의 파일을 찾는다.
			InputStream inputStream = Resources.getResourceAsStream(mybatisConfig);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			beanContainer.put("sqlSessionFactory", sqlSessionFactory);
			
			System.out.println("DAO, Controller 인스턴스 중비 ----------------------------------------------------");
			loadComponent();
			
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

	private void loadComponent() throws Exception {
		Reflections reflections = new Reflections("bit.java39");
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Component.class);
		Component compAnnotation = null;
		for(Class<?> clazz : classes) {
			compAnnotation = clazz.getAnnotation(Component.class);
			beanContainer.put(compAnnotation.value(), clazz.newInstance());
		}
	}
}




























