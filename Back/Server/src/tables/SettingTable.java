package tables;

import java.sql.SQLException;

import exception.FailedDBConnection;
import utils.Table;
import utils.TableFields;

public class SettingTable {
	private final static Table table = 
			new Table(	"Setting",
						"a setting",
				new TableFields[]	{	
					new TableFields("idSetting", 		TableFields.TYPE_INT, 11, TableFields.KEY_PRIMARY, TableFields.KEY_AUTOGEN),
					new TableFields("connectionDelay",	TableFields.TYPE_INT, 3),
					new TableFields("maxAttemps", 		TableFields.TYPE_INT, 3)
			});
	
	public static Table getTable() {
		return table;
	}    

	public static void createTable() throws ClassNotFoundException, SQLException, FailedDBConnection {
		table.createTable();
	}
  
	
}
