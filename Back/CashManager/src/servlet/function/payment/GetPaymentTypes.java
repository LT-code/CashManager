package servlet.function.payment;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import services.PaymentTypeService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletLanbdaMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;


/**
 * Create a machine
 */
public class GetPaymentTypes implements RouteFunction, ServletLanbdaMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		PaymentTypeService service = new PaymentTypeService(db, log);
		list = service.listTypes();
		db.close();
		return list;
	}
}
