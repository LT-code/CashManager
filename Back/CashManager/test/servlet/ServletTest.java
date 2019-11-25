package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import utils.servlet.ResponseHandler;

@SuppressWarnings("deprecation")
public abstract class ServletTest {
	private String testName;
	
	private static final String API_TEST_URL = "http://localhost:8080";
	private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void afterTest() {
    }
    
    public void beforeTest() {
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

	public boolean sendGet(String route, String params, JSONObject expectedResponse) {
		HttpGet get = new HttpGet(API_TEST_URL + Servlet.API_ROUTE + route + "?" + params);
		get.addHeader(HttpHeaders.AUTHORIZATION, "");
        
		try {
			try (CloseableHttpResponse response = httpClient.execute(get)) {
		        System.out.println(response.getStatusLine().toString());
		
				return new JSONObject(EntityUtils.toString(response.getEntity())).equals(expectedResponse);
		    }
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static JSONObject sendPost(String route, String params, String bodyParams) {
		HttpPost post = new HttpPost(API_TEST_URL + Servlet.API_ROUTE + route + "?" + params);
		post.addHeader(HttpHeaders.AUTHORIZATION, "");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        
        if(bodyParams != null) {
        	Map<String, Object> json = ResponseHandler.objectToMap(new JSONObject(bodyParams));
            for(Entry<String, Object> entry : json.entrySet())
            	urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        
        try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			
			try (CloseableHttpClient httpClient = HttpClients.createDefault(); 
				CloseableHttpResponse response = httpClient.execute(post)) {
				JSONObject res = new JSONObject(EntityUtils.toString(response.getEntity()));
				
				res.put("status", response.getStatusLine().getStatusCode());
				
				return res;
	        }
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

        return null;

    }
}
