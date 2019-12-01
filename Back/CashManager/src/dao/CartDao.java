package dao;

import java.sql.SQLException;

import entities.Setting;
import exception.InvalidNumberReslut;
import tables.CartTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class CartDao extends Dao {
	public CartDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, CartTable.getTable());
	}
	
	public Setting getCurrentSetting(long idCart) throws SQLException, InvalidNumberReslut {
		return new Setting(query(	"SELECT s.* from Machine m, Setting s, Cart c " +
									"WHERE m.idSetting = s.idSetting " + 
									"AND c.idMachine = m.idMachine " +
									"AND c.idCart = ?;"
									, new Object[]{idCart}));
	}
}
