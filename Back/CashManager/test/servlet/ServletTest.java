package servlet;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import utils.HttpService;

@SuppressWarnings("deprecation")
public abstract class ServletTest {
	private String testName;
	public static String lambdaIdMachine;
	public static String lambdaTokenMachine;
	public static String adminIdMachine;
	public static String adminTokenMachine;
	public static int lambdaIdCart;
	
	private static final String API_TEST_URL = "http://localhost:8080";
	
	@After
    public void tearDown() {
    	
    }
	
	public static JSONObject sendPost(String route, String params, JSONObject bodyParams, String token) {
		return HttpService.sendPost(API_TEST_URL + Servlet.API_ROUTE + route, params, bodyParams, token);
	}
	
	public static JSONObject sendPut(String route, String params, JSONObject bodyParams, String token) {
		return HttpService.sendPut(API_TEST_URL + Servlet.API_ROUTE + route, params, bodyParams, token);
	}
	
	public static JSONObject sendDelete(String route, String params, JSONObject bodyParams, String token) {
		return HttpService.sendDelete(API_TEST_URL + Servlet.API_ROUTE + route, params, bodyParams, token);
	}
	
	public static JSONObject sendGet(String route, String params, JSONObject bodyParams, String token) {
		return HttpService.sendGet(API_TEST_URL + Servlet.API_ROUTE + route, params, bodyParams, token);
	}
	
	@Before
	public void setUp() {
		System.out.println("\n####################################################################################" );
		System.out.println("######## " + "" + " ######## " + testName);
		System.out.println("####################################################################################" );
    }
	
	@Rule
	public TestWatchman watchman = new TestWatchman() {
		public void starting(FrameworkMethod method) {
			testName = method.getName();
		}
	};
}
