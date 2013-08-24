package bit.java39.listeners;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Log4jConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		String 웹애플리케이션의루트경로를찾을때사용할키 = event.getServletContext().getInitParameter("webAppRootKey");
		// webAppRootKey = "webapp.root";
		
		Properties prop = System.getProperties();
		prop.put(웹애플리케이션의루트경로를찾을때사용할키, event.getServletContext().getRealPath("/"));
		
		// webapp.root -> /Users/jinyoungeom/Documents/JavaWorkspaces/Java39_bak/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web03
		

	}

}
