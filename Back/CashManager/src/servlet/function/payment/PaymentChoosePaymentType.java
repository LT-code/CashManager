package servlet.function.payment;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Payment;
import exception.InvalidNumberReslut;
import services.PaymentService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletLanbdaMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.HttpStatus;
import utils.servlet.ResponseHandler;


/**
 * Create a machine
 */
public class PaymentChoosePaymentType implements RouteFunction, ServletLanbdaMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		int idCart = bodyParams.getInt("idCart");
		int idType = bodyParams.getInt("idType");
		
		PaymentService servicePayment = new PaymentService(db, log);
		Payment p = null;
		
		// check existance of a current payment
		try {
			p = servicePayment.getActivePayment(idCart);
		}
		catch(Exception e) {
			if(!(e instanceof InvalidNumberReslut)) throw e;
		}
		
		// add or update payment
		if(p == null) {
			p = new Payment(idCart, idType);
			servicePayment.add(p);
		}	
		else {
			p.setIdType(idType);
			servicePayment.update(p);
		}
		
		list.add(ResponseHandler.objectToMap(p));
		log.setHttpStatus(HttpStatus.SUCCESS);
		db.close();
		return list;
	}
}
