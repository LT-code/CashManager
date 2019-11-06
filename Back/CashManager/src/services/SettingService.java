package services;

import java.util.ArrayList;
import java.util.Map;

import dao.Dao;
import dao.SettingDao;
import entities.EntityClass;
import entities.Setting;
import utils.DBConnector;
import utils.LogsHandler;

public class SettingService extends Service {
	SettingDao settingDao = new SettingDao(db, this.getErrorsHandler());
	
	public SettingService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	public ArrayList<Map<String, Object>> ListSetting() {
		try 
		{
			ArrayList<Map<String, Object>> list =  this.settingDao.listSetting();
			errHandler.addInfo("Succès de la recuperation des settings.");
	
	      return list;
	    }
	    catch (Exception e) {
	      errHandler.addError(e);
	    }
	    return null;
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
