package servlet.function.payment;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Payment;
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
public class PaymentPay implements RouteFunction, ServletLanbdaMachine {
	
	
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		PaymentService servicePayment = new PaymentService(db, log);
		Payment p = servicePayment.getActivePayment(urlParams.getJSONArray("idCart").getInt(0));
		
		if(p != null) {
			if(p.pay(bodyParams, log)) {
				servicePayment.update(p);	
				log.addInfo("The payment has been allowed.");
			}
			
			list.add(ResponseHandler.objectToMap(p));
		}
		else
			log.addError("You must choose a payment type before procced to the payment.", HttpStatus.BAD_REQUEST);
		
		
		return list;
	}
}
