package services;

import java.sql.SQLException;
import java.util.Map;

import dao.MachineDao;
import entities.EntityClass;
import entities.Machine;
import exception.InvalidNumberReslut;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class MachineService extends Service {
	public MachineService(DBConnector db, LogsHandler errHandler) {
		super(new MachineDao(db, errHandler));
	}

	public Machine getByToken(String token) throws SQLException, InvalidNumberReslut {
		Machine m = ((MachineDao) this.getDao()).getByToken(token);
		this.getDao().getLogsHandler().addInfo("Success getting machine token=" + token + ".");
		return m;
	}
	
	public Map<String, Object> getMachineIdCart(String code) throws SQLException, InvalidNumberReslut {
		Map<String, Object> m = ((MachineDao) this.getDao()).getMachineIdCart(code);
		this.getDao().getLogsHandler().addInfo("Success getting machine code=" + code + ".");
		return m;
	}
	
	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
