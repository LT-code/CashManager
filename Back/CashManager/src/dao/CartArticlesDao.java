package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entities.CartArticles;
import exception.InvalidNumberReslut;
import tables.CartArticlesTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartArticlesDao extends Dao {
	public CartArticlesDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
	
	public Map<String, Object> listArticles(long idCart) throws SQLException, InvalidNumberReslut {
		ArrayList<Map<String, Object>> resQuery = queryList(	"Select count(*) as quantity, a.code, a.name, a.price from CartArticles ca, Article a " +
														"where a.code = ca.codeArticle " +
														"and ca.idCart = ? " +
														"group by a.code;",
														new Object[]{idCart});
		Map<String, Object> res = new HashMap<>();
		ArrayList<Map<String, Object>> listArticle = new ArrayList<>();
		long quantity = 0;
		float totalBill = 0.0F;
		for(Map<String, Object> article : resQuery) {
			quantity = (long) article.get("quantity");
			totalBill += (float) article.get("price") * quantity; 
			article.remove("quantity");
			
			Map<String, Object> articleTransform = new HashMap<>();
			articleTransform.put("article", article);
			articleTransform.put("quantity", quantity);
			listArticle.add(articleTransform);
		}
		
		res.put("totalBill", totalBill);
		res.put("listArticle", listArticle);
		
		return res;
	}
	
	public CartArticles get(long idCart, String codeArticle) throws SQLException, InvalidNumberReslut {
		Map<String, Object> m = query("Select * from " + CartArticlesTable.getTable().getName() + " where codeArticle=?, idCart=?;", new Object[]{idCart, codeArticle});
		return new CartArticles((long) m.get("idCart"), 
								(String) m.get("codeArticle"));
	}
}
