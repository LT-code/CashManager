package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import tables.SettingTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class SettingDao extends Dao {
	public SettingDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, SettingTable.getTable());
	}
	
	public ArrayList<Map<String, Object>> listSetting() throws SQLException {
		return queryList("Select * from Setting;");
	}
}
