package services;

import dao.Dao;
import dao.PaymentDao;
import entities.EntityClass;
import utils.LogsHandler;
import utils.bdd.DBConnector;

public class PaymentTypeService extends Service {
	PaymentDao dao = new PaymentDao(db, this.getLogsHandler());
	
	public PaymentTypeService(DBConnector db, LogsHandler errHandler) {
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
}
