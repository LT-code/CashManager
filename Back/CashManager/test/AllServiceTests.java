import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import utils.bdd.DBConnector;
import utils.servlet.InitServer;

@RunWith(Suite.class)
@SuiteClasses	({ 
					services.MachineServiceTest.class,
					services.SettingServiceTest.class,
					services.CartServiceTest.class,
					services.ArticleServiceTest.class,
					services.CartArticlesServiceTest.class,
					services.PaymentTypeServiceTest.class, 
					services.PaymentServiceTest.class
				})

public class AllServiceTests {
	@BeforeClass
	public static void setUpClass() throws InstantiationException, IllegalAccessException {
		DBConnector.getDBParam();
		
		InitServer.restetDataBase();
		InitServer.createAllTables();
	}
}
