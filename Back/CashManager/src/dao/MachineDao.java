package dao;

import java.sql.SQLException;
import java.util.Map;

import entities.Machine;
import exception.InvalidNumberReslut;
import tables.MachineTable;
import utils.DBConnector;
import utils.LogsHandler;

public class MachineDao extends Dao {

	public MachineDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
	
	public Machine get(String code) throws SQLException, InvalidNumberReslut {
		Map<String, Object> m = query("Select * from " + MachineTable.getTable().getName() + " where " + MachineTable.getTable().getIDSet(), new Object[]{code});
		return new Machine(	(String) m.get("idMachine"), 
							(int) m.get("idSetting"),
							(boolean) m.get("isAdmin"),
							(String) m.get("password"));
	}
	
	public Machine getByToken(String token) throws SQLException, InvalidNumberReslut {
		Map<String, Object> m = query("Select * from " + MachineTable.getTable().getName() + " where token = ?", new Object[]{token});
		return new Machine(	(String) m.get("idMachine"), 
							(int) m.get("idSetting"),
							(boolean) m.get("isAdmin"),
							(String) m.get("password"));
	}
}
