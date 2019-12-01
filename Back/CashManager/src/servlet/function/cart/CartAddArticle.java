package servlet.function.cart;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Article;
import entities.CartArticles;
import services.ArticleService;
import services.CartArticlesService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletLanbdaMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.HttpStatus;
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
		Article a = new Article(new ArticleService(db, log).get(c.getCodeArticle()));
		list.add(ResponseHandler.objectToMap(a));
		log.setHttpStatus(HttpStatus.CREATED);
		return list;
	}
}
