import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import servlet.ServletTest;
import utils.servlet.HttpStatus;

@RunWith(Suite.class)
@SuiteClasses	({ 
					servlet.SettingServletTest.class,
					servlet.AuthorisationServletTest.class,
					servlet.ArticleServletTest.class,
					servlet.CartArticlesServletTest.class
				})
public class AllServletTests {

    @BeforeClass
	public static void setUpClass() {
		JSONObject Param = new JSONObject();
		Param.put("idMachine", "1fHUVG37hD7DIbib");
		Param.put("idSetting", 1);
		Param.put("isAdmin", true);
		Param.put("password", "125YFjkn");
		
		// machine 1
		JSONObject resmachine = ServletTest.sendPost("/machine/create", "", Param, null);
		//assertEquals(HttpStatus.CREATED, resmachine.get("status"));
		ServletTest.adminIdMachine = (String) Param.get("idMachine");

		JSONObject ParamConnect = new JSONObject();
		ParamConnect.put("idMachine", Param.get("idMachine"));
		ParamConnect.put("password", Param.get("password"));
		
		resmachine = ServletTest.sendPost("/machine/connect", "", ParamConnect, null);
		assertEquals(HttpStatus.SUCCESS, resmachine.get("status"));	
		ServletTest.adminTokenMachine = resmachine.getJSONArray("data").getJSONObject(0).getString("token");
		
		// machine 2
		Param.put("idMachine", "2fHUdz78Jhd67b");
		Param.put("isAdmin", false);
		resmachine = ServletTest.sendPost("/machine/create", "", Param, null);
		//assertEquals(HttpStatus.CREATED, resmachine.get("status"));
		ServletTest.lambdaIdMachine = (String) Param.get("idMachine");

		ParamConnect = new JSONObject();
		ParamConnect.put("idMachine", Param.get("idMachine"));
		ParamConnect.put("password", Param.get("password"));
		
		resmachine = ServletTest.sendPost("/machine/connect", "", ParamConnect, null);
		assertEquals(HttpStatus.SUCCESS, resmachine.get("status"));	
		ServletTest.lambdaTokenMachine = resmachine.getJSONArray("data").getJSONObject(0).getString("token");
	}
    
    @AfterClass
	public static void tearDownClass() {
    	JSONObject res = ServletTest.sendDelete("/machine/remove", "idMachine=" + ServletTest.lambdaIdMachine, null, ServletTest.adminTokenMachine);
    	assertEquals(HttpStatus.SUCCESS, res.get("status"));

		res = ServletTest.sendDelete("/machine/remove", "idMachine=" + ServletTest.adminIdMachine, null, ServletTest.adminTokenMachine);
		assertEquals(HttpStatus.SUCCESS, res.get("status"));
	}
}
