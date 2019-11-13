package servlet;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.HttpStatus;

public class SettingServletTest extends ServletTest {

	@Before
	public void setUp() {
		beforeTest();
    }
	
	@Test
	public void test_simpleAdd() {
		assertTrue(sendPost(HttpStatus.CREATED, "/setting/create", "", null, "{\"data\":{\"attemptsNumber\":3,\"id\":1,\"connectionDelay\":30},\"message\":\"\"}"));
	}
	
	 @After
    public void tearDown() {
    	afterTest();
    }

}
