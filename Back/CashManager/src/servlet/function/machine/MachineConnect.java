package servlet.function.machine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import entities.Cart;
import entities.Machine;
import entities.Payment;
import exception.NoResultException;
import exception.ValidatorNotRecpectedException;
import services.CartService;
import services.MachineService;
import servlet.function.RouteFunction;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.HttpStatus;
import utils.servlet.ResponseHandler;

/**
 * Create a machine
 */
public class MachineConnect implements RouteFunction {
	public static final int TOKEN_SIZE = 100;
	
	@Override
	public List<Map<String, Object>> execute(DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list, LogsHandler log) throws Exception {
		MachineService articleService = new MachineService(db, log);
		Map<String, Object> machineMap = articleService.getMachineIdCart(bodyParams.getString("idMachine"));
		System.out.println(new ObjectMapper().writeValueAsString(machineMap));
		Machine m = new Machine(machineMap);
		if (m != null) {
			if (m.pGetPassword().equals(bodyParams.getString("password"))) {
				m.setToken(this.generateToken());
				articleService.update(m);
				list.add(ResponseHandler.objectToMap(getMapRes(m, machineMap, db, log)));
			} else
				log.addError("Passwords doesn't match", HttpStatus.BAD_REQUEST);
			
		}
		log.setHttpStatus(HttpStatus.SUCCESS);
		db.close();
		return list;
	}
	
	private String generateToken() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
		return new Random().ints(TOKEN_SIZE, 0, chars.length()).mapToObj(i -> "" + chars.charAt(i))
				.collect(Collectors.joining());
	}
	
	private Map<String, Object> getMapRes(Machine m, Map<String, Object> machineMap, DBConnector db, LogsHandler log) throws ValidatorNotRecpectedException, NoResultException, SQLException {
		Map<String, Object> res = new HashMap<>();
		res.put("machine", m);
		
		if(machineMap.get("idCart") == null || (machineMap.get("status") != null ? !machineMap.get("status").equals(Payment.STATUS_NOT_DEFINED) : false))  {
			Cart c;
			CartService cartService = new CartService(db, log);
			cartService.add(c = new Cart((String) m.getId()));
			res.put("currentIdCart", c.getId());
		}
		else
			res.put("currentIdCart", machineMap.get("idCart"));
		return res;
	}
}
