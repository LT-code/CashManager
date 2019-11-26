package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import entities.Setting;
import exception.InvalidNumberReslut;
import tables.SettingTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class SettingDao extends Dao {
	public SettingDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
	public Setting get(long idSetting) throws SQLException, InvalidNumberReslut {
		return new Setting(query("Select * from " + SettingTable.getTable().getName() + " where " + SettingTable.getTable().getIDSet(), new Object[]{idSetting}));
	}
	
	public ArrayList<Map<String, Object>> listSetting() throws SQLException {
		return queryList("Select * from Setting;");
	}
}
