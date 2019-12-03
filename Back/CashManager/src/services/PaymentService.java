package services;

import java.sql.SQLException;

import dao.PaymentDao;
import entities.EntityClass;
import entities.Payment;
import exception.InvalidNumberReslut;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentService extends Service {
	public PaymentService(DBConnector db, LogsHandler errHandler) {
		super(new PaymentDao(db, errHandler));
	}

	public Payment getActivePayment(int idCart) throws SQLException, InvalidNumberReslut {
		Payment p =  ((PaymentDao) this.getDao()).getActivePayment(idCart);
		this.getDao().getLogsHandler().addInfo("Success getting active payment.");
		return p;
	}
	
	@Override
	public boolean validator(EntityClass entityClass) {
		return true;
	}
}
