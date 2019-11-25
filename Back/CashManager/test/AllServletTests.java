import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses	({ 
					servlet.SettingServletTest.class
				})

public class AllServletTests {
	@BeforeClass
	public static void setUpClass() throws InstantiationException, IllegalAccessException {

	}
}
