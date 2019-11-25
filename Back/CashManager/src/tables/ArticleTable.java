package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import utils.bdd.DBConnector;
import utils.bdd.Table;
import utils.bdd.TableFields;

public class ArticleTable implements TableClass {
	private final static Table table = 
			new Table(	"Article",
						"an article",
				new TableFields[]	{	
					new TableFields("code", 		TableFields.TYPE_VARCHAR, 	30, 	TableFields.KEY_PRIMARY),
					new TableFields("name",			TableFields.TYPE_VARCHAR, 	100),
					new TableFields("price",		TableFields.TYPE_FLOAT, 	6)
			});
	
	public static Table getTable() {
		return table;
	}   

	public static void createTable(DBConnector db) throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable(db);
	}
}
