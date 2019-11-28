package servlet;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

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
	
	@Before
	public void setUp() {
		System.out.println("\n####################################################################################" );
		System.out.println("######## " + "" + " ######## " + testName);
		System.out.println("####################################################################################" );
    }
	
	public static JSONObject sendPost(String route, String params, JSONObject bodyParams, String token) {
		return send(new HttpPost(API_TEST_URL + Servlet.API_ROUTE + route + "?" + params), bodyParams, token);
	}
	
	public static JSONObject sendPut(String route, String params, JSONObject bodyParams, String token) {
		return send(new HttpPut(API_TEST_URL + Servlet.API_ROUTE + route + "?" + params), bodyParams, token);
	}
	
	public static JSONObject sendDelete(String route, String params, JSONObject bodyParams, String token) {
		return send(new HttpDelete(API_TEST_URL + Servlet.API_ROUTE + route + "?" + params), bodyParams, token);
	}
	
	public static JSONObject sendGet(String route, String params, JSONObject bodyParams, String token) {
		return send(new HttpGet(API_TEST_URL + Servlet.API_ROUTE + route + "?" + params), bodyParams, token);
	}
	
	public static JSONObject send(HttpUriRequest request, JSONObject bodyParams, String token) {
		if(token != null)
			request.addHeader(HttpHeaders.AUTHORIZATION, token);

        try {
        	if(bodyParams != null) {
        		if(request instanceof HttpPost)
            		((HttpPost) request).setEntity(new StringEntity(bodyParams.toString()));
        		if(request instanceof HttpPut)
            		((HttpPut) request).setEntity(new StringEntity(bodyParams.toString()));
        	}
        		
			try (CloseableHttpClient httpClient = HttpClients.createDefault(); 
				CloseableHttpResponse response = httpClient.execute(request)) {
				String result = EntityUtils.toString(response.getEntity());
				System.out.println(request.getURI() + " " + response.getStatusLine().getStatusCode() + " " + result);
				JSONObject res = new JSONObject(result);
				
				res.put("status", response.getStatusLine().getStatusCode());
				
				return res;
	        }
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

        return null;
    }
	
	@Rule
	public TestWatchman watchman = new TestWatchman() {
		public void starting(FrameworkMethod method) {
			testName = method.getName();
		}
	};
}
