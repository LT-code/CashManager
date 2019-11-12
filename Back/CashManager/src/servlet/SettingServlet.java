package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import servlet.function.ServletFunction;
import servlet.function.setting.SettingCreate;
import utils.ServletTools;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/setting/*")
public class SettingServlet extends Servlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public ServletFunction post(HttpServletRequest request) {
		switch(ServletTools.getCurrentUrlPath(request)) {
			case "/cashmanager/setting/create":
				return new SettingCreate();
		}			
		return null;
	}
}
