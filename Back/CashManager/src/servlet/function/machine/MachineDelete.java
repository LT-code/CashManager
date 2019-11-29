package servlet.function.machine;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Machine;
import services.MachineService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletAdminMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.ResponseHandler;


/**
 * Create a machine
 */
public class MachineDelete implements RouteFunction, ServletAdminMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		MachineService machineService = new MachineService(db, log);
		Machine m = new Machine(machineService.get((String) urlParams.getJSONArray("idMachine").get(0)));
		machineService.delete(m);
		list.add(ResponseHandler.objectToMap(m));
		return list;
	}
}
