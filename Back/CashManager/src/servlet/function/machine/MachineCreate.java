package servlet.function.machine;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Machine;
import services.MachineService;
import servlet.function.ServletFunction;
import utils.DBConnector;
import utils.LogsHandler;
import utils.ResponseHandler;


/**
 * Create a machine
 */
public class MachineCreate implements ServletFunction {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) {
		MachineService articleService = new MachineService(db, log);
		Machine m;
		if(articleService.add(
				m = new Machine(
					bodyParams.getString("idMachine"), 
					bodyParams.getLong("idSetting"),
					bodyParams.getBoolean("isAdmin"),
					bodyParams.getString("password")
				)))
			list.add(ResponseHandler.objectToMap(m));
		db.close();
		return list;
	}
}
