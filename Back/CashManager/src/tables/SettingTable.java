package tables;

import java.sql.SQLException;

import entities.Setting;
import exception.FailedDBConnection;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.SettingService;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.bdd.Table;
import utils.bdd.TableFields;

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

	public static void createTable(DBConnector db) throws ClassNotFoundException, SQLException, FailedDBConnection, ValidatorNotRecpectedException, NoResultException {
		if(table.createTable(db)) {
			LogsHandler logs = new LogsHandler();
			SettingService settingService = new SettingService(db, logs);
			settingService.add(new Setting());
			logs.displayLogs();
		}
	}
}
