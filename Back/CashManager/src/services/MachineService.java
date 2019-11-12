package services;

import dao.Dao;
import dao.MachineDao;
import entities.EntityClass;
import entities.Machine;
import utils.DBConnector;
import utils.HttpStatus;
import utils.LogsHandler;

public class MachineService extends Service {
	MachineDao machineDao = new MachineDao(db, this.getLogsHandler());
	
	public MachineService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	public Machine get(String code) {
		Machine m = null;
		try {
			m =  this.machineDao.get(code);
			logsHandler.addInfo("Succès de la recuperation du la machine " + code + ".");
			return m;
		}
	    catch (Exception e) {
	    	this.getLogsHandler().addError(e, HttpStatus.BAD_REQUEST); 
	    }
	    return m;
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
