package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entities.CartArticles;
import exception.InvalidNumberReslut;
import exception.NoResultException;
import tables.CartArticlesTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartArticlesDao extends Dao {
	public CartArticlesDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, CartArticlesTable.getTable());
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
	
	public CartArticles get(long idCart, String codeArticle) throws SQLException, InvalidNumberReslut, NoResultException {
		ArrayList<Map<String, Object>> m = queryList("Select * from " + CartArticlesTable.getTable().getName() + " where idCart=? and codeArticle=?;", new Object[]{idCart, codeArticle});
		if(m == null)
			throw new NoResultException();
		return new CartArticles((int) m.get(0).get("idCartArticles"),
								(int) m.get(0).get("idCart"), 
								(String) m.get(0).get("codeArticle"));
	}
}
