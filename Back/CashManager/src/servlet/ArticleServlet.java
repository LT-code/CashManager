package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.article.ArticleCreate;
import servlet.function.article.ArticleRemove;
import utils.servlet.Route;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/article/*")
public class ArticleServlet extends Servlet {
	private static final long serialVersionUID = 1L;

	private static final Route[] ROUTES =
		{
			new Route(Route.POST, API_ROUTE + "/article/create", new ArticleCreate(), "String code, String name, float price", ""),
			new Route(Route.DELETE, API_ROUTE + "/article/remove", new ArticleRemove(), "", "code=?"),
			new Route(Route.GET, API_ROUTE + "/article/get", new ArticleRemove(), "", "code=?")
		};
	
	@Override
	public Route[] getRoutes() {
		return ROUTES;
	}
}
