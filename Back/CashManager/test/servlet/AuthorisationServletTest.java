package servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import utils.servlet.HttpStatus;

public class AuthorisationServletTest extends ServletTest {	
	
	
	@Test
	public void test_noToken_Admin() {
		JSONObject res = sendPost("/setting/create", "", null, null);
		assertEquals(HttpStatus.UNAUTHORIZED, res.get("status"));
	}
	
	@Test
	public void test_InvalideToken_Admin() {
		JSONObject res = sendPost("/setting/create", "", null, "invalidToken");
		assertEquals(HttpStatus.FORBIDDEN, res.get("status"));
	}
	
	@Test
	public void test_noToken_Lambda() {		
		JSONObject param = new JSONObject();
		param.put("idMachine", lambdaIdMachine);	
		JSONObject res = sendPost("/cart/create", "", param, null);
		assertEquals(HttpStatus.UNAUTHORIZED, res.get("status"));
	}
	
	@Test
	public void test_InvalideToken_Lambda() {		
		JSONObject param = new JSONObject();
		param.put("idMachine", lambdaIdMachine);
		JSONObject res = sendPost("/cart/create", "", param, "invalidToken");
		assertEquals(HttpStatus.FORBIDDEN, res.get("status"));
	}
	
	@Test
	public void test_ForbiddenToken() {
		JSONObject res = sendPost("/setting/create", "", null, lambdaTokenMachine);
		assertEquals(HttpStatus.FORBIDDEN, res.get("status"));
	}
	
	@Test
	public void test_TokenAdmin() {		
		JSONObject res = sendPost("/setting/create", "", null, adminTokenMachine);
		assertEquals(HttpStatus.CREATED, res.get("status"));
	}
	
	@Test
	public void test_TokenLambda() {		
		JSONObject param = new JSONObject();
		param.put("idMachine", lambdaIdMachine);	
		JSONObject res = sendPost("/cart/create", "", param, lambdaTokenMachine);
		assertEquals(HttpStatus.CREATED, res.get("status"));
	}
	
	@Test
	public void test_TokenAdmin_for_LambdaFunction() {		
		JSONObject param = new JSONObject();
		param.put("idMachine", lambdaIdMachine);
		JSONObject res = sendPost("/cart/create", "", param, adminTokenMachine);
		assertEquals(HttpStatus.CREATED, res.get("status"));
	}
}
