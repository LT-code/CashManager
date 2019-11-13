

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import utils.DBConnector;
import utils.InitServer;

@RunWith(Suite.class)
@SuiteClasses	({ 
					services.MachineServiceTest.class,
					services.SettingServiceTest.class,
					services.CartServiceTest.class,
					services.ArticleServiceTest.class
				})

public class AllTests {
	@BeforeClass
	public static void setUpClass() throws InstantiationException, IllegalAccessException {
		DBConnector.getDBParam();
		
		InitServer.restetDataBase();
		InitServer.createAllTables();
		
	}
}
