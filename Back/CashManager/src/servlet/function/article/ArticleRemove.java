package servlet.function.article;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Article;
import services.ArticleService;
import servlet.function.ServletFunction;
import utils.DBConnector;
import utils.LogsHandler;
import utils.ResponseHandler;


/**
 * Create a machine
 */
public class ArticleRemove implements ServletFunction {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) {
		ArticleService articleService = new ArticleService(db, log);
		System.out.println(urlParams);
		Article a = articleService.get(urlParams.getString("code"));
		if(articleService.delete(a))
			list.add(ResponseHandler.objectToMap(a));
		db.close();
		return list;
	}
}
