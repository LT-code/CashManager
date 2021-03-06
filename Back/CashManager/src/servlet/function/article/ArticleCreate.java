package servlet.function.article;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Article;
import services.ArticleService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletAdminMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.ResponseHandler;

/**
 * Create a machine
 */
public class ArticleCreate implements RouteFunction, ServletAdminMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		ArticleService articleService = new ArticleService(db, log);
		Article a;
		articleService.add(
				a = new Article(
					bodyParams.getString("code"), 
					bodyParams.getString("name"),
					bodyParams.getFloat("price")
				));
		list.add(ResponseHandler.objectToMap(a));
		return list;
	}
}
