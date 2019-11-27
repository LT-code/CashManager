package dao;

import java.sql.SQLException;
import java.util.Map;

import entities.Machine;
import exception.InvalidNumberReslut;
import tables.MachineTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class MachineDao extends Dao {

	public MachineDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, MachineTable.getTable());
	}
	
	public Machine getByToken(String token) throws SQLException, InvalidNumberReslut {
		return new Machine(	query("Select * from " + MachineTable.getTable().getName() + " where token = ?", new Object[]{token}));
	}
	
	public Map<String, Object> getMachineIdCart(String code) throws SQLException, InvalidNumberReslut {
		return query(	"SELECT m.*, c.idCart, p.status " + 
						"FROM Machine m " + 
						"LEFT OUTER JOIN Cart c ON m.idMachine = c.idMachine " +
						"LEFT OUTER JOIN Payment p ON p.idCart = c.idCart " +
						"WHERE m.idMachine = ? " + 
						"ORDER BY c.idCart DESC " + 
						"LIMIT 0, 1",
						new Object[]{code});
	}
}
