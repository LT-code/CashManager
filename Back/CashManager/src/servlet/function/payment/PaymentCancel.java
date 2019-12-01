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
import utils.servlet.ResponseHandler;


/**
 * Create a machine
 */
public class PaymentCancel implements RouteFunction, ServletLanbdaMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		PaymentService servicePayment = new PaymentService(db, log);
		Payment p = servicePayment.getActivePayment(urlParams.getJSONArray("idCart").getInt(0));
		
		if(p != null)
			servicePayment.delete(p);
		
		list.add(ResponseHandler.objectToMap(p));
		return list;
	}
}
