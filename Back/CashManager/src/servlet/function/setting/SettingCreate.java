package servlet.function.setting;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import entities.Setting;
import services.SettingService;
import servlet.function.ServletFunction;
import utils.DBConnector;
import utils.LogsHandler;
import utils.ResponseHandler;

public class SettingCreate implements ServletFunction {
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		SettingService service = new SettingService(db, log);
		Setting s = new Setting();
		service.add(s);
		db.close();
		list.add(ResponseHandler.objectToMap(s));
		return list;
	}

}
