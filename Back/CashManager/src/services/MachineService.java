package services;

import java.sql.SQLException;
import java.util.Map;

import dao.Dao;
import dao.MachineDao;
import entities.EntityClass;
import entities.Machine;
import exception.InvalidNumberReslut;
import utils.bdd.DBConnector;
import utils.LogsHandler;

public class MachineService extends Service {
	MachineDao machineDao = new MachineDao(db, this.getLogsHandler());
	
	public MachineService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}

	public Machine getByToken(String token) throws SQLException, InvalidNumberReslut {
		Machine m = this.machineDao.getByToken(token);
		logsHandler.addInfo("Success getting machine token=" + token + ".");
		return m;
	}
	
	public Map<String, Object> getMachineIdCart(String code) throws SQLException, InvalidNumberReslut {
		Map<String, Object> m = machineDao.getMachineIdCart(code);
		logsHandler.addInfo("Success getting machine code=" + code + ".");
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
