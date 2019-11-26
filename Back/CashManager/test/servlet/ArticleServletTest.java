package servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import utils.servlet.HttpStatus;

public class ArticleServletTest extends ServletTest {	
	private final static String code = "nzeif3I473bib";
	private final static String name = "Acer 123";
	private final static double price = 400.0F;
	
	@Test
	public void test1_simpleAdd() {		
		JSONObject param = new JSONObject();
		param.put("code", code);
		param.put("name", name);
		param.put("price", price);
		JSONObject res = sendPost("/article/create", "", param, adminTokenMachine);
		assertEquals(HttpStatus.CREATED, res.get("status"));
	}
	
	@Test
	public void test1_simpleGet() {		
		JSONObject res = sendGet("/article/get", "code=" + code, null, adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
		assertEquals(name, res.getJSONObject("data").get("name"));
		assertEquals(price, res.getJSONObject("data").get("price"));		
	}
	
	@Test
	public void test1_simpleRemove() {	
		JSONObject res = sendDelete("/article/remove", "code=" + code, null, adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test
	public void test_simpleRemove_InvalidCode() {	
		JSONObject res = sendDelete("/article/remove", "code=8962937INDEZID9869", null, adminTokenMachine);
		assertEquals(HttpStatus.INTERNAL_ERROR, res.get("status"));
	}
}
