package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import utils.DBConnector;
import utils.ErrorsHandler;

public class SettingDao extends Dao {
	public SettingDao(DBConnector db, ErrorsHandler errorHandler) {
		super(db, errorHandler);
	}

	public ArrayList<Map<String, Object>> listSetting() throws SQLException {
		return query("Select * from Setting;");
	}
}
