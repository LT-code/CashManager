package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.cart.CartAddArticle;
import servlet.function.cart.CartCreate;
import utils.servlet.Route;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/cart/*")
public class CartServlet extends Servlet {
	private static final long serialVersionUID = 1L;
	
	private static final Route[] ROUTES =
		{
			new Route(Route.POST, API_ROUTE + "/cart/create", 	new CartCreate(), "String idMachine", ""),
			new Route(Route.POST, API_ROUTE + "/cart/add_article", new CartAddArticle(), "long idCart, String codeArticle", ""),
			new Route(Route.DELETE, API_ROUTE + "/cart/remove_article", new CartCreate(), "", "idCart=?,codeArticle=?"),
			new Route(Route.GET, API_ROUTE + "/cart/get_articles", new CartCreate(), "", "idCart=?")
		};
	
	@Override
	public Route[] getRoutes() {
		return ROUTES;
	}
}
