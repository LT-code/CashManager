package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import dao.Dao;
import dao.SettingDao;
import entities.EntityClass;
import entities.Setting;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class SettingService extends Service {
	SettingDao settingDao = new SettingDao(db, this.getLogsHandler());
	
	public SettingService(DBConnector db, LogsHandler logsHandler) {
		super(db, logsHandler);
	}
	
	public ArrayList<Map<String, Object>> ListSetting() throws SQLException {
		ArrayList<Map<String, Object>> list = null;
		list =  this.settingDao.listSetting();
		logsHandler.addInfo("Succès de la recuperation des settings.");
		return list;
	}

	@Override
	public Dao getDao() {
		return this.settingDao;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return 	((Setting) entityClass).getAttemptsNumber() > 1 &&
				((Setting) entityClass).getConnectionDelay() < 180 &&
				((Setting) entityClass).getConnectionDelay() > 0;
	}
}
