package services;

import dao.Dao;
import dao.MachineDao;
import entities.EntityClass;

public class MachineService extends Service {
	MachineDao machineDao = new MachineDao();
	
	@Override
	public Dao getDao() {
		return this.machineDao;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
