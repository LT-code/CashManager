package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import utils.bdd.DBConnector;
import utils.bdd.Table;
import utils.bdd.TableFields;

public class CartTable implements TableClass {
	private final static Table table = 
			new Table(	"Cart",
						"a cart",
				new TableFields[]	{	
					new TableFields("idCart", 		TableFields.TYPE_INT, 		11, TableFields.KEY_PRIMARY, TableFields.KEY_AUTOGEN),
					new TableFields("idMachine",	TableFields.TYPE_VARCHAR, 	30, TableFields.KEY_FOREIGN_ON_DELETE_CASCADE, "Machine(idMachine)")
				});
	
	public static Table getTable() {
		return table;
	}

	public static void createTable(DBConnector db) throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable(db);		
	}

}
