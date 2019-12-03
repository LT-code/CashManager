package servlet.function.payment;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Payment;
import entities.Setting;
import services.CartService;
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
		Payment p = servicePayment.getActivePayment(bodyParams.getInt("idCart"));
		
		if(p != null) {
			Setting s = new CartService(db, log).getCurrentSetting(bodyParams.getInt("idCart"));
			boolean res = p.pay(bodyParams, s.getAttemptsNumber(), log);
			servicePayment.update(p);
			
			if(res)
				log.addInfo("The payment has been allowed.");
			else
				log.addError("The payment has been refused", HttpStatus.BAD_REQUEST);
			
			list.add(ResponseHandler.objectToMap(p));
		}
		else
			log.addError("You must choose a payment type before procced to the payment.", HttpStatus.BAD_REQUEST);
		
		
		return list;
	}
}
