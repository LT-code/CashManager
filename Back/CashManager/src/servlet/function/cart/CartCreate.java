package servlet.function.cart;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Cart;
import services.CartService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletLanbdaMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.ResponseHandler;

/**
 * Create a machine
 */
public class CartCreate implements RouteFunction, ServletLanbdaMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		CartService cartService = new CartService(db, log);
		Cart c;
		cartService.add(
				c = new Cart(
					bodyParams.getString("idMachine")
				));
		list.add(ResponseHandler.objectToMap(c));
		db.close();
		return list;
	}
}
