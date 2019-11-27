package dao;

import java.sql.SQLException;

import entities.Payment;
import exception.InvalidNumberReslut;
import tables.PaymentTable;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentDao extends Dao {
	public PaymentDao(DBConnector db, LogsHandler errorHandler) {
		super(db, errorHandler, PaymentTable.getTable());
	}

	public Payment getActivePayment(int idCart) throws SQLException, InvalidNumberReslut {
		return new Payment(query("select ", new Object[] {idCart}));
	}
}
