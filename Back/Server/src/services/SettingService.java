package services;

import java.util.ArrayList;
import java.util.Map;

import dao.Dao;
import dao.SettingDao;
import entities.EntityClass;
import entities.Setting;

public class SettingService extends Service {
	SettingDao settingDao = new SettingDao();
	
	public ArrayList<Map<String, Object>> ListSetting() {
		return this.settingDao.listSetting();
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
