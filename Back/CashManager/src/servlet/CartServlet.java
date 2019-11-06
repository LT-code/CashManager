package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import entities.Cart;
import exception.FailedDBConnection;
import services.CartService;
import utils.DBConnector;
import utils.LogsHandler;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/cart")
public class CartServlet extends Servlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cart cart = null;
		
		try {
			JSONObject params = getJsonParams(request);
			DBConnector db = new DBConnector(this.getLogsHandler());
			CartService cartService = new CartService(db, this.getLogsHandler());
			cartService.add(
					cart = new Cart(
						params.getString("idMachine")
					));
			
			db.close();
		} catch (JSONException | FailedDBConnection e) {
			System.out.println(LogsHandler.getMessageError(e));
		}

		writeResponse(response, cart);
	}
}
