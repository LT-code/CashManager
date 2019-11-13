package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.article.ArticleCreate;
import servlet.function.article.ArticleRemove;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/article/*")
public class ArticleServlet extends Servlet {
	private static final long serialVersionUID = 1L;
	
	private static final Object[][] ROUTES_POST =
		{
			{ API_ROUTE + "/article/create", new ArticleCreate() }
		};
	
	private static final Object[][] ROUTES_GET =
		{
			{ API_ROUTE + "/article/remove", new ArticleRemove() }
		};
	
	@Override
	public Object[][] post() {
		return ROUTES_POST;
	}
	
	@Override
	public Object[][] get() {
		return ROUTES_GET;
	}
}
