package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import utils.bdd.Table;
import utils.bdd.TableFields;

public class CartTable implements TableClass {
	private final static Table table = 
			new Table(	"Cart",
						"a cart",
				new TableFields[]	{	
					new TableFields("idCart", 		TableFields.TYPE_INT, 		11, TableFields.KEY_PRIMARY, TableFields.KEY_AUTOGEN),
					new TableFields("idMachine",	TableFields.TYPE_VARCHAR, 	30, TableFields.KEY_FOREIGN, "Machine(idMachine)")
				});
	
	public static Table getTable() {
		return table;
	}

	public static void createTable() throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable();		
	}

}
