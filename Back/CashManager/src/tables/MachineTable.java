package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import servlet.function.machine.MachineConnect;
import utils.Table;
import utils.TableFields;

public class MachineTable implements TableClass {
	private final static Table table = 
			new Table(	"Machine",
						"a machine",
				new TableFields[]	{	
					new TableFields("idMachine", 		TableFields.TYPE_VARCHAR, 	30, TableFields.KEY_PRIMARY),
					new TableFields("idSetting",		TableFields.TYPE_INT, 		11,	TableFields.KEY_FOREIGN, SettingTable.getTable().getName() + "(idSetting)"),
					new TableFields("isAdmin", 			TableFields.TYPE_BOOLEAN,	1),
					new TableFields("password", 		TableFields.TYPE_VARCHAR,	30),
					new TableFields("token", 			TableFields.TYPE_VARCHAR,	MachineConnect.TOKEN_SIZE, TableFields.KEY_UNIQUE)
			});
	
	public static Table getTable() {
		return table;
	}   

	public static void createTable() throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable();
	}
}
