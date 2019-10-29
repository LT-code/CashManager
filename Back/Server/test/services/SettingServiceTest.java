package services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Setting;
import fabrique.FabriqueAService;
import fabrique.ServicesTest;
import service.utils.InitServer;
import utils.DBConnector;

public class SettingServiceTest extends ServicesTest {
	private static SettingService settingService;
	private static Setting setting;
	
	
    @Before
    public void setUp() {
    	
    	setting = new Setting();
    	settingService = new SettingService();

        fab = new FabriqueAService(setting, settingService, new Long(0), new Long("84984165417115"));
    }
    
    @Test
    public void test1() {
    	System.out.println("test 1======================================");
    	ArrayList<Map<String, Object>> toto = settingService.ListSetting();
    	if(!toto.isEmpty())
	    	for(Map<String, Object> map : toto)
	    		for (Map.Entry<String, Object> entry : map.entrySet()) {
	    		    String key = entry.getKey();
	    		    Object value = entry.getValue();
	    		    System.out.println(key + ":" + value);
	    		}

    	assertTrue(true);
    }

    @After
    public void tearDown() {
    }

}
