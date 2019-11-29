package servlet.function.setting;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Setting;
import services.SettingService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletAdminMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.ResponseHandler;

public class SettingCreate implements RouteFunction, ServletAdminMachine {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		SettingService service = new SettingService(db, log);
		Setting s = new Setting();
		service.add(s);
		list.add(ResponseHandler.objectToMap(s));
		return list;
	}
}
