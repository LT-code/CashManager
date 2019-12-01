package servlet;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import utils.servlet.HttpStatus;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartArticlesServletTest extends ServletTest {		
	private static JSONObject article1;
	private static JSONObject article2;
	private static int idCart;
	
	@BeforeClass
	public static void setUp2() {
		JSONObject param = new JSONObject();
		
		param.put("code", "NKdoedIB2U98G3jigb");
		param.put("name", "Confiture de fraise");
		param.put("price", 3.60F);
		article1 = sendPost("/article/create", "", param, adminTokenMachine).getJSONArray("data").getJSONObject(0);
		//assertEquals(HttpStatus.CREATED, article1.get("status"));
		
		param.put("code", "863IGdoedIB2G3jigb");
		param.put("name", "Confiture de framboise");
		param.put("price", 4.50F);
		article2 = sendPost("/article/create", "", param, adminTokenMachine).getJSONArray("data").getJSONObject(0);
		//assertEquals(HttpStatus.CREATED, article2.get("status"));
		
		param.put("idMachine", adminIdMachine);
		JSONObject res = sendPost("/cart/create", "", param, adminTokenMachine);
		idCart = res.getJSONArray("data").getJSONObject(0).getInt("id");
		assertEquals(HttpStatus.CREATED, res.get("status"));
	}
	
	@Test 
	public void test1_simpleAdd() {		
		addArticle(idCart, article1);
	}
	
	@Test   
	public void test2_simpleGet() {		
		JSONObject res = sendGet("/cart/get_articles", "idCart=" + idCart, null, lambdaTokenMachine);
		assertEquals(1, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").getJSONObject(0).getInt("quantity"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test 
	public void test3_simpleRemove() {		
		deleteArticle(idCart, article1);
	}
	
	@Test   
	public void test4_doubleAdd() {
		addArticle(idCart, article1);
		addArticle(idCart, article2);
	}
	
	@Test
	public void test5_getDoubleArticle() {		
		JSONObject res = sendGet("/cart/get_articles", "idCart=" + idCart, null, lambdaTokenMachine);
		assertEquals(2, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").length());
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test  
	public void test6_doubleDelete() {
		deleteArticle(idCart, article1);
		deleteArticle(idCart, article2);
	}
	
	@Test   
	public void test7_tripleAdd() {
		addArticle(idCart, article1);
		addArticle(idCart, article2);
		addArticle(idCart, article2);
	}
	
	@Test
	public void test8_getTripleArticle() {		
		JSONObject res = sendGet("/cart/get_articles", "idCart=" + idCart, null, lambdaTokenMachine);
		assertEquals(2, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").length());
		assertEquals(2, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").getJSONObject(0).getInt("quantity"));
		assertEquals(1, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").getJSONObject(1).getInt("quantity"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@Test  
	public void test9_tripleDelete() {
		deleteArticle(idCart, article1);
		deleteArticle(idCart, article2);
		deleteArticle(idCart, article2);
	}
	
	@Test
	public void test_add_get_remove() {		
		addArticle(idCart, article1);
		addArticle(idCart, article2);
		addArticle(idCart, article2);
		
		deleteArticle(idCart, article2);
		
		JSONObject res = sendGet("/cart/get_articles", "idCart=" + idCart, null, lambdaTokenMachine);
		assertEquals(2, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").length());
		assertEquals(1, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").getJSONObject(0).getInt("quantity"));
		assertEquals(1, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").getJSONObject(1).getInt("quantity"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
		
		deleteArticle(idCart, article2);
		
		res = sendGet("/cart/get_articles", "idCart=" + idCart, null, lambdaTokenMachine);
		assertEquals(1, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").length());
		assertEquals(1, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").getJSONObject(0).getInt("quantity"));
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
		
		deleteArticle(idCart, article1);
		
		res = sendGet("/cart/get_articles", "idCart=" + idCart, null, lambdaTokenMachine);
		assertEquals(0, res.getJSONArray("data").getJSONObject(0).getJSONArray("listArticle").length());
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	@AfterClass
	public static void tearDown2() {
		
		JSONObject res = sendDelete("/article/remove", "code=" + article1.getString("id"), null, adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
		
		res = sendDelete("/article/remove", "code=" + article2.getString("id"), null, adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
		
		//res = sendDelete("/cart/remove", "idCart=" + article2, null, adminTokenMachine);
		//assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	private void addArticle(int idCart, JSONObject article) {
		JSONObject param = new JSONObject();
		param.put("idCart",idCart);
		param.put("codeArticle", article.getString("id"));
		JSONObject res = sendPost("/cart/add_article", "", param, lambdaTokenMachine);
		System.out.println(article.toString());
		System.out.println(res.getJSONArray("data").getJSONObject(0).toString());
		assertTrue(res.getJSONArray("data").getJSONObject(0).toString().equals(article.toString()));
		assertEquals(HttpStatus.CREATED, res.get("status"));
	}
	
	private void deleteArticle(int idCart, JSONObject article) {
		JSONObject res = sendDelete("/cart/remove_article", "codeArticle=" + article.getString("id") + "&idCart=" + idCart, null, lambdaTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
	
	
}
