package servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import utils.servlet.HttpStatus;

public class ArticleServletTest extends ServletTest {	
	private final static String articleCode = "nzeif3I473bib";
	
	@Test
	public void test1_simpleAdd() {		
		JSONObject param = new JSONObject();
		param.put("code", articleCode);
		param.put("name", "Acer 123");
		param.put("price", 400.0F);
		JSONObject res = sendPost("/article/create", "", param, adminTokenMachine);
		assertEquals(HttpStatus.CREATED, res.get("status"));
		
	}
	
	@Test
	public void test1_simpleGet() {		
		JSONObject res = sendGet("/article/get", "code=" + articleCode, null, adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
		
	}
	
	@Test
	public void test1_simpleRemove() {	
		JSONObject res = sendDelete("/article/remove", "code=" + articleCode, null, adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
		
	}
}
