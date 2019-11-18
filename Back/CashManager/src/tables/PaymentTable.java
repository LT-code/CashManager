package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import utils.Table;
import utils.TableFields;

public class PaymentTable implements TableClass {
	private final static Table table = 
			new Table(	"Payment",
						"a payment",
				new TableFields[]	{	
					new TableFields("idPayment", 	TableFields.TYPE_INT, 		11, TableFields.KEY_PRIMARY, TableFields.KEY_AUTOGEN),
					new TableFields("idCart",		TableFields.TYPE_INT, 		11,	TableFields.KEY_FOREIGN, CartTable.getTable().getName() + "(idCart)"),
					new TableFields("idType", 		TableFields.TYPE_BOOLEAN,	11, TableFields.KEY_FOREIGN, PaymentTypeTable.getTable().getName() + "(idType)"),
					new TableFields("status", 		TableFields.TYPE_VARCHAR,	10)
			});
	
	public static Table getTable() {
		return table;
	}   

	public static void createTable() throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable();
	}
}
