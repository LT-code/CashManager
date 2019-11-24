package tables;

import java.sql.SQLException;

import entities.PaymentType;
import exception.FailedDBConnection;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.PaymentTypeService;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.bdd.Table;
import utils.bdd.TableFields;

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

	public static void createTable() throws ClassNotFoundException, SQLException, FailedDBConnection, ValidatorNotRecpectedException, NoResultException {
		if(table.createTable()) {
			LogsHandler logs = new LogsHandler();
			DBConnector db = new DBConnector(logs);
			PaymentTypeService service = new PaymentTypeService(db, logs);
			service.add(new PaymentType("Cheque"));
			service.add(new PaymentType("Credit card"));
			logs.displayLogs();
			db.close();
		}
	}
}
