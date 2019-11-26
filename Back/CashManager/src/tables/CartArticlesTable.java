package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import utils.bdd.DBConnector;
import utils.bdd.Table;
import utils.bdd.TableFields;

public class CartArticlesTable implements TableClass {
	private final static Table table = 
			new Table(	"CartArticles",
						"a cart article",
				new TableFields[]	{	
					new TableFields("idCartArticles", 	TableFields.TYPE_INT, 		11, 	TableFields.KEY_PRIMARY, TableFields.KEY_AUTOGEN),
					new TableFields("idCart",			TableFields.TYPE_INT, 		11, TableFields.KEY_FOREIGN_ON_DELETE_CASCADE, "Cart(idCart)"),
					new TableFields("codeArticle",		TableFields.TYPE_VARCHAR,	30, TableFields.KEY_FOREIGN, "Article(code)")
			});
	
	public static Table getTable() {
		return table;
	}   

	public static void createTable(DBConnector db) throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable(db);
	}
}
