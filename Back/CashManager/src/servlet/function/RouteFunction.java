package servlet.function;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import utils.LogsHandler;
import utils.bdd.DBConnector;

public interface RouteFunction {
	public abstract List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception;
}
