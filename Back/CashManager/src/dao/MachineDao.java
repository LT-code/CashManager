package dao;

import java.sql.SQLException;

import entities.Machine;
import exception.InvalidNumberReslut;
import tables.MachineTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class MachineDao extends Dao {

	public MachineDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler);
	}
	
	public Machine get(String code) throws SQLException, InvalidNumberReslut {
		return new Machine(query("Select * from " + MachineTable.getTable().getName() + " where " + MachineTable.getTable().getIDSet(), new Object[]{code}));
	}
	
	public Machine getByToken(String token) throws SQLException, InvalidNumberReslut {
		return new Machine(	query("Select * from " + MachineTable.getTable().getName() + " where token = ?", new Object[]{token}));
	}
}
