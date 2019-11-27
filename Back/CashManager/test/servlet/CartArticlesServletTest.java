package servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.servlet.HttpStatus;

public class CartArticlesServletTest extends ServletTest {		
	private final static String code1 = "NKdoedIB2U98G3jigb";
	private final static String code2 = "863IGdoedIB2G3jigb";
	private static int idCart;
	
	@BeforeClass
	public static void setUp2() {
		JSONObject param = new JSONObject();
		
		param.put("code", code1);
		param.put("name", "Confiture de fraise");
		param.put("price", 3.60F);
		JSONObject res = sendPost("/article/create", "", param, adminTokenMachine);
		//assertEquals(HttpStatus.CREATED, res.get("status"));
		
		param.put("code", code2);
		param.put("name", "Confiture de framboise");
		param.put("price", 4.50F);
		res = sendPost("/article/create", "", param, adminTokenMachine);
		//assertEquals(HttpStatus.CREATED, res.get("status"));
		
		param.put("idMachine", adminIdMachine);
		idCart = sendPost("/cart/create", "", param, adminTokenMachine).getJSONArray("data").getJSONObject(0).getInt("id");
		assertEquals(HttpStatus.CREATED, res.get("status"));
	}
	
	@Test
	public void test1_simpleAdd() {		
		createArticle(idCart, code1);
	}
	
	@Test
	public void test1_simpleGet() {		
		JSONObject res = sendGet("/cart/get_articles", "idCart=" + idCart, null, lambdaTokenMachine);
		assertEquals(1, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").getJSONObject(0).getInt("quantity"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test
	public void test1_simpleRemove() {		
		JSONObject res = sendDelete("/cart/remove_article", "codeArticle=" + code1 + "&idCart=" + idCart, null, lambdaTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@AfterClass
	public static void tearDown2() {
		
		JSONObject res = sendDelete("/article/remove", "code=" + code1, null, adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
		
		res = sendDelete("/article/remove", "code=" + code2, null, adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
		
		//res = sendDelete("/cart/remove", "idCart=" + code2, null, adminTokenMachine);
		//assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	private void createArticle(int idCart, String code) {
		JSONObject param = new JSONObject();
		param.put("idCart",idCart);
		param.put("codeArticle", code);
		JSONObject res = sendPost("/cart/add_article", "", param, lambdaTokenMachine);
		assertEquals(HttpStatus.CREATED, res.get("status"));
	}
}
