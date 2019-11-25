package servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.servlet.HttpStatus;

public class SettingServletTest extends ServletTest {

	@Before
	public void setUp() {
		beforeTest();
    }
	
	@Test
	public void test_simpleAdd_noToken() {
		JSONObject res = sendPost("/setting/create", "", null);
		assertEquals(HttpStatus.FORBIDDEN, res.get("status"));
	}
	
	@Test
	public void test_simpleAdd_noToken() {
		JSONObject res = sendPost("/setting/create", "", null);
		assertEquals(HttpStatus.FORBIDDEN, res.get("status"));
	}
	
	 @After
    public void tearDown() {
    	afterTest();
    }
}
