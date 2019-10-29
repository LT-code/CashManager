package services;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import utils.DBConnector;
import utils.InitServer;

@RunWith(Suite.class)
@SuiteClasses({ MachineServiceTest.class, SettingServiceTest.class , CartServiceTest.class})
public class AllTests {
	@BeforeClass
	public static void setUpClass() throws InstantiationException, IllegalAccessException {
		DBConnector.getDBParam();
		
		InitServer.createAllTables();
	}
}
