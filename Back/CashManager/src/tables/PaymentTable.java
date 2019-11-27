package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import utils.bdd.DBConnector;
import utils.bdd.Table;
import utils.bdd.TableFields;

public class PaymentTable implements TableClass {
	private final static Table table = 
			new Table(	"Payment",
						"a payment",
				new TableFields[]	{	
					new TableFields("idPayment", 	TableFields.TYPE_INT, 		11, TableFields.KEY_PRIMARY, TableFields.KEY_AUTOGEN),
					new TableFields("idCart",		TableFields.TYPE_INT, 		11,	TableFields.KEY_FOREIGN_ON_DELETE_CASCADE, CartTable.getTable().getName() + "(idCart)"),
					new TableFields("idType", 		TableFields.TYPE_INT,		11, TableFields.KEY_FOREIGN, PaymentTypeTable.getTable().getName() + "(idType)"),
					new TableFields("status", 		TableFields.TYPE_VARCHAR,	15)
			});
	
	public static Table getTable() {
		return table;
	}   

	public static void createTable(DBConnector db) throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable(db);
	}
}
