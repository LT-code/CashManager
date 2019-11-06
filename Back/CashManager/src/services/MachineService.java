package services;

import dao.Dao;
import dao.MachineDao;
import entities.EntityClass;
import utils.DBConnector;
import utils.LogsHandler;

public class MachineService extends Service {
	MachineDao machineDao = new MachineDao(db, this.getErrorsHandler());
	
	public MachineService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	@Override
	public Dao getDao() {
		return this.machineDao;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
