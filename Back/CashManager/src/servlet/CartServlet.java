package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.cart.CartAddArticle;
import servlet.function.cart.CartCreate;
import servlet.function.cart.CartGetArticles;
import servlet.function.cart.CartRemoveArticle;
import utils.servlet.Route;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/cart/*")
public class CartServlet extends Servlet {
	private static final long serialVersionUID = 1L;
	
	private static final Route[] ROUTES =
		{
			new Route(Route.POST, API_ROUTE + "/cart/create", 	new CartCreate(), "String idMachine", "", "Create a cart."),
			new Route(Route.POST, API_ROUTE + "/cart/add_article", new CartAddArticle(), "long idCart, String codeArticle", "", "Add an article in the cart."),
			new Route(Route.DELETE, API_ROUTE + "/cart/remove_article", new CartRemoveArticle(), "", "idCart=?,codeArticle=?", "Remove an article from the cart."),
			new Route(Route.GET, API_ROUTE + "/cart/get_articles", new CartGetArticles(), "", "idCart=?", "List all articles contained by the cart.")
		};
	
	@Override
	public Route[] getRoutes() {
		return ROUTES;
	}

	@Override
	public String getName() {
		return "Cart";
	}
}
