package services;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Setting;
import exception.FailedDBConnection;
import fabrique.FabriqueAService;
import fabrique.ServicesTest;
import utils.DBConnector;
import utils.ErrorsHandler;

public class SettingServiceTest extends ServicesTest {
	private static SettingService settingService;
	private static Setting setting;
	private DBConnector db;
	
    @Before
    public void setUp() throws ClassNotFoundException, SQLException, FailedDBConnection {
    	ErrorsHandler errHandler = new ErrorsHandler();
    	db = new DBConnector(errHandler);
    	setting = new Setting();
    	settingService = new SettingService(db, errHandler);

        fab = new FabriqueAService(setting, settingService, new Long(0), new Long("84984165417115"));
    }
    
    @Test
    public void test1() {
    	settingService.add(setting);
    	
    	ArrayList<Map<String, Object>> toto = settingService.ListSetting();
    	if(toto != null)
	    	if(!toto.isEmpty())
		    	for(Map<String, Object> map : toto)
		    		for (Map.Entry<String, Object> entry : map.entrySet()) {
		    		    String key = entry.getKey();
		    		    Object value = entry.getValue();
		    		    System.out.println(key + ":" + value);
		    		}
    	settingService.delete(setting);
    	assertTrue(true);
    }

    @After
    public void tearDown() throws SQLException {
    	db.close();
    }

}
