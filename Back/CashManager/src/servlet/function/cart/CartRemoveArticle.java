package servlet.function.cart;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.CartArticles;
import services.CartArticlesService;
import servlet.function.RouteFunction;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.ResponseHandler;

/**
 * Create a machine
 */
public class CartRemoveArticle implements RouteFunction {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {		
		CartArticlesService cartArticleService = new CartArticlesService(db, log);
		CartArticles ca = (CartArticles) cartArticleService.get(urlParams.getLong("idCart"), urlParams.getString("codeArticle"));
		cartArticleService.delete(ca);
		list.add(ResponseHandler.objectToMap(ca));
		db.close();
		return list;
	}
}
