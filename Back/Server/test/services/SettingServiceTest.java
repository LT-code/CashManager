package services;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fabrique.FabriqueAService;
import fabrique.ServicesTest;
import services.SettingService;
import tables.Setting;
import utils.DBConnector;

public class SettingServiceTest extends ServicesTest {
	private static SettingService settingService;
	private static Setting setting;
	private DBConnector db;
	
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
    	db = new DBConnector();
    	setting = new Setting();
    	settingService = new SettingService(db);

        fab = new FabriqueAService(setting, settingService, new Long(0), new Long("84984165417115"));
    }
    
    @Test
    public void test1() {
    	settingService.add(setting);
    	System.out.println("test 1======================================");
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
    public void tearDown() {
    	db.close();
    }

}
