package servlet.function.cart;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import services.CartArticlesService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletLanbdaMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartGetArticles implements RouteFunction, ServletLanbdaMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		CartArticlesService cartArticlesService = new CartArticlesService(db, log);
		list.add(cartArticlesService.listArticles(Long.parseLong((String) urlParams.getJSONArray("idCart").get(0))));
		return list;
	}
}
