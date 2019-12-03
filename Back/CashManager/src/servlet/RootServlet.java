package servlet;

import java.util.Set;

import javax.servlet.annotation.WebServlet;

import org.reflections.Reflections;

import utils.servlet.ServletTools;

@WebServlet("/*")
public class RootServlet extends Servlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getServletRoutes() {
		Reflections reflections = new Reflections("servlet");
		String res = "";
		Set<Class<? extends Servlet>> allClasses =  reflections.getSubTypesOf(Servlet.class);
		 
		for(Class<? extends Servlet> servletClass : allClasses) 
			try { res += ServletTools.getServletRoutes(servletClass.newInstance()); } catch(Exception e) {}
			
		return res;
	}

	@Override
	public String getName() {
		return "/";
	}
}