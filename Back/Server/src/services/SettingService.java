package services;

import java.util.ArrayList;
import java.util.Map;

import dao.Dao;
import dao.SettingDao;
import entities.EntityClass;
import entities.Setting;
import utils.DBConnector;
import utils.ErrorsHandler;

public class SettingService extends Service {
	SettingDao settingDao = new SettingDao(db, this.getErrorsHandler());
	
	public SettingService(DBConnector db, ErrorsHandler errHandler) {
		super(db, errHandler);
	}
	
	public ArrayList<Map<String, Object>> ListSetting() {
		try 
		{
			ArrayList<Map<String, Object>> list =  this.settingDao.listSetting();
			errHandler.setInfo("Succès de la recuperation des settings.", list.toString());
	
	      return list;
	    }
	    catch (Exception e) {
	      errHandler.setError("Echec lors de la recuperation des settings", ErrorsHandler.getMessageError(e));
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
