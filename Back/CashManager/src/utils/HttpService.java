package utils;

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

public class HttpService {
	public static JSONObject sendPost(String route, String params, JSONObject bodyParams, String token) {
		return send(new HttpPost(route + "?" + params), bodyParams, token);
	}
	
	public static JSONObject sendPost(String route, String params, JSONObject bodyParams, String token, String headerKey, String headerValue) {
		HttpUriRequest request = new HttpPost(route + "?" + params);
		request.addHeader(headerKey, headerValue);
		return send(request, bodyParams, token);
	}
	
	public static JSONObject sendPut(String route, String params, JSONObject bodyParams, String token) {
		return send(new HttpPut(route + "?" + params), bodyParams, token);
	}
	
	public static JSONObject sendDelete(String route, String params, JSONObject bodyParams, String token) {
		return send(new HttpDelete(route + "?" + params), bodyParams, token);
	}
	
	public static JSONObject sendGet(String route, String params, JSONObject bodyParams, String token) {
		return send(new HttpGet(route + "?" + params), bodyParams, token);
	}
	
	private static JSONObject send(HttpUriRequest request, JSONObject bodyParams, String token) {
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
}
