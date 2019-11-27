package dao;

import java.sql.SQLException;
import java.util.Map;

import entities.Article;
import exception.InvalidNumberReslut;
import tables.ArticleTable;
import utils.bdd.DBConnector;
import utils.LogsHandler;

public class ArticleDao extends Dao {

	public ArticleDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, ArticleTable.getTable());
	}
	
	public Article get(String code) throws SQLException, InvalidNumberReslut {
		Map<String, Object> m = query("Select * from " + ArticleTable.getTable().getName() + " where " + ArticleTable.getTable().getIDSet(), new Object[]{code});
		return new Article(	(String) m.get("code"), 
							(String) m.get("name"),
							(float) m.get("price"));
	}
}
