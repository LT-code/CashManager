package servlet.function.cart;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.CartArticles;
import services.CartArticlesService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletLanbdaMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.ResponseHandler;

/**
 * Create a machine
 */
public class CartRemoveArticle implements RouteFunction, ServletLanbdaMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {		
		CartArticlesService cartArticleService = new CartArticlesService(db, log);
		CartArticles ca = (CartArticles) cartArticleService.get(Integer.parseInt((String) urlParams.getJSONArray("idCart").get(0)),
																(String) urlParams.getJSONArray("codeArticle").get(0));
		cartArticleService.delete(ca);
		list.add(ResponseHandler.objectToMap(ca));
		return list;
	}
}