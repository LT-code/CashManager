package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import utils.Table;
import utils.TableFields;

public class MachineTable implements TableClass {
	private final static Table table = 
			new Table(	"Machine",
						"a machine",
				new TableFields[]	{	
					new TableFields("idMachine", 		TableFields.TYPE_VARCHAR, 	30, TableFields.KEY_PRIMARY),
					new TableFields("idSetting",		TableFields.TYPE_INT, 		11,	TableFields.KEY_FOREIGN, "Setting(idSetting)"),
					new TableFields("isAdmin", 			TableFields.TYPE_BOOLEAN,	0),
					new TableFields("password", 		TableFields.TYPE_VARCHAR,	30)
			});
	
	public static Table getTable() {
		return table;
	}   

	public static void createTable() throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable();
	}
}
