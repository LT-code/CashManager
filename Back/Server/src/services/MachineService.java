package services;

import dao.Dao;
import dao.MachineDao;
import tables.TableClass;
import utils.DBConnector;

public class MachineService extends Service {
	MachineDao machineDao = new MachineDao(db, this.getErrorsHandler());
	
	public MachineService(DBConnector db) {
		super(db);
	}
	
	@Override
	public Dao getDao() {
		return this.machineDao;
	}

	@Override
	public boolean validator(TableClass entityClass) {
		return true;
	}
}
