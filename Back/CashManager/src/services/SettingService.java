package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import dao.SettingDao;
import entities.EntityClass;
import entities.Setting;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class SettingService extends Service {
	public SettingService(DBConnector db, LogsHandler logsHandler) {
		super(new SettingDao(db, logsHandler));
	}
	
	public ArrayList<Map<String, Object>> ListSetting() throws SQLException {
		ArrayList<Map<String, Object>> list = null;
		list =  ((SettingDao) this.getDao()).listSetting();
		this.getDao().getLogsHandler().addInfo("Succès de la recuperation des settings.");
		return list;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return 	((Setting) entityClass).getAttemptsNumber() > 1 &&
				((Setting) entityClass).getConnectionDelay() < 180 &&
				((Setting) entityClass).getConnectionDelay() > 0;
	}
}
