package bit.java39.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

//@WebListener
@SuppressWarnings("rawtypes")
public class ContextLoaderListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent event) {}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext ctx = event.getServletContext();

		try {
			String path = ctx.getInitParameter("contextConfigLocation");
			ApplicationContext appContext = null;
			if (path.startsWith("classpath:")) {
				appContext = 
					new ClassPathXmlApplicationContext(
							ctx.getInitParameter("contextConfigLocation"));
			} else {
				appContext = 
						new FileSystemXmlApplicationContext(
								ctx.getInitParameter("contextConfigLocation"));
			}
			
			ctx.setAttribute("appContext", appContext);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}




























