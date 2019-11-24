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
public class CartAddArticle implements RouteFunction, ServletLanbdaMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		CartArticlesService cartArticleService = new CartArticlesService(db, log);
		CartArticles c;
		cartArticleService.add(
				c = new CartArticles(
					bodyParams.getLong("idCart"),
					bodyParams.getString("codeArticle")
				));
		list.add(ResponseHandler.objectToMap(c));
		db.close();
		return list;
	}
}
