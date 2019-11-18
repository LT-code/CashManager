package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import utils.Table;
import utils.TableFields;

public class PaymentTypeTable implements TableClass {
	private final static Table table = 
			new Table(	"PaymentType",
						"a payment type",
				new TableFields[]	{	
					new TableFields("idType", 	TableFields.TYPE_INT, 		11, TableFields.KEY_PRIMARY, TableFields.KEY_AUTOGEN),
					new TableFields("name",		TableFields.TYPE_VARCHAR, 	30)
			});
	
	public static Table getTable() {
		return table;
	}   

	public static void createTable() throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable();
	}
}
