package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import entities.Article;
import exception.FailedDBConnection;
import services.ArticleService;
import utils.DBConnector;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/article")
public class ArticleServlet extends Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Article article = null;
		try {
			JSONObject params = getJsonParams(request);
			DBConnector db = new DBConnector(this.getLogsHandler());
			ArticleService articleService = new ArticleService(db, this.getLogsHandler());
	    	articleService.add(
	    			article = new Article(
		    			params.getString("code"),
		    			params.getString("name")
		    		));

	    	db.close();
		} catch (JSONException | FailedDBConnection e) {
			this.getLogsHandler().addError(e);
		}
		
		writeResponse(response, article);
	}
}
