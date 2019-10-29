package services;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import service.utils.InitServer;
import utils.DBConnector;

@RunWith(Suite.class)
@SuiteClasses({ MachineServiceTest.class, SettingServiceTest.class })
public class AllTests {
	@BeforeClass
	public static void setUpClass() throws InstantiationException, IllegalAccessException {
		DBConnector.getDBParam();
		
		InitServer.createAllTables();
	}
}
