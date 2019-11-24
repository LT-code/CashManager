package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import entities.Article;
import entities.CartArticles;
import exception.InvalidNumberReslut;
import tables.ArticleTable;
import tables.CartArticlesTable;
import tables.CartTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartArticlesDao extends Dao {
	public CartArticlesDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
	
	public ArrayList<Map<String, Object>> listArticles(long idCart) throws SQLException, InvalidNumberReslut {
		return queryList("Select * from " + CartTable.getTable().getName() + " where " + CartTable.getTable().getIDSet(), new Object[]{idCart});
	}
	
	public CartArticles get(long idCart, String codeArticle) throws SQLException, InvalidNumberReslut {
		Map<String, Object> m = query("Select * from " + CartArticlesTable.getTable().getName() + " where codeArticle=?, idCart=?;", new Object[]{idCart, codeArticle});
		return new CartArticles((long) m.get("idCart"), 
								(String) m.get("codeArticle"));
	}
}
