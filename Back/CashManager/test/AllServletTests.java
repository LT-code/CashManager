

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

import utils.bdd.DBConnector;
import utils.servlet.InitServer;


@RunWith(WildcardPatternSuite.class)
@SuiteClasses("**/*Test.class")
public class AllServletTests {
	@BeforeClass
	public static void setUpClass() throws InstantiationException, IllegalAccessException {
		DBConnector.getDBParam();
		
		//InitServer.restetDataBase();
		InitServer.createAllTables();
	}
}
