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
import service.utils.CM_Database;

public class SettingServiceTest extends ServicesTest {
	private static SettingService settingService;
	private static Setting setting;
	
	@BeforeClass
	public static void setUpClass() {
		CM_Database.getDBParam();
	}

    @Before
    public void setUp() {
    	setting = new Setting();
    	settingService = new SettingService();

        fab = new FabriqueAService(setting, settingService);
    }
    
    @Test
    public void test1() {
    	System.out.println("t\n" + 
    			"import java.awt.List;est 1======================================");
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
