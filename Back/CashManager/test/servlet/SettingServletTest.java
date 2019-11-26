package servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import utils.servlet.HttpStatus;

public class SettingServletTest extends ServletTest {	
	private static int idSetting;
	
	@Test
	public void test1_simpleAdd() {		
		JSONObject res = sendPost("/setting/create", "", null, adminTokenMachine);
		assertEquals(HttpStatus.CREATED, res.get("status"));
		System.out.println(res);
		idSetting = res.getJSONObject("data").getInt("id");
	}
	
	@Test
	public void test2_simpleRemove() {		
		JSONObject res = sendDelete("/setting/remove", "idSetting=" + idSetting, null, adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test
	public void test_simpleRemove_InvalidId() {		
		JSONObject res = sendDelete("/setting/remove", "idSetting=293792487", null, adminTokenMachine);
		assertEquals(HttpStatus.INTERNAL_ERROR, res.get("status"));
	}
}
