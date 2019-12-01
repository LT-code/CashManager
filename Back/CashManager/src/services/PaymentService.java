package services;

import java.sql.SQLException;

import dao.Dao;
import dao.PaymentDao;
import entities.EntityClass;
import entities.Payment;
import exception.InvalidNumberReslut;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentService extends Service {
	PaymentDao dao = new PaymentDao(db, this.getLogsHandler());
	
	public PaymentService(DBConnector db, LogsHandler errHandler) {
		super(db, errHandler);
	}
	
	@Override
	public Dao getDao() {
		return this.dao;
	}

	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}

	public Payment getActivePayment(int idCart) throws SQLException, InvalidNumberReslut {
		Payment p =  dao.getActivePayment(idCart);
		logsHandler.addInfo("Success getting active payment.");
		return p;
	}
}
