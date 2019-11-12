package servlet.function.machine;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.json.JSONObject;

import entities.Machine;
import services.MachineService;
import servlet.function.ServletFunction;
import utils.DBConnector;
import utils.HttpStatus;
import utils.LogsHandler;
import utils.ResponseHandler;

/**
 * Create a machine
 */
public class MachineConnect implements ServletFunction {
	public static final int TOKEN_SIZE = 100;
	
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) {
		MachineService articleService = new MachineService(db, log);
		Machine m = articleService.get(bodyParams.getString("idMachine"));
		if (m != null) {
			if (m.pGetPassword().equals(bodyParams.getString("password"))) {
				do
					m.setToken(this.generateToken());
				while(!articleService.update(m));				
			} else
				log.addError("Passwords doesn't match", HttpStatus.BAD_REQUEST);
			list.add(ResponseHandler.objectToMap(m));
		}
		db.close();
		return list;
	}
	
	private String generateToken() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
		return new Random().ints(TOKEN_SIZE, 0, chars.length()).mapToObj(i -> "" + chars.charAt(i))
				.collect(Collectors.joining());
	}

}
