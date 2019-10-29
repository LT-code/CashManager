package utils;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCM_DataBase {
	protected Connection c;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		DBConnector.getDBParam();
	}

	@Before
	public void setUp() {
	}
	
		
	@Test
	public void test_0_db_connection() throws SQLException {
		System.out.println("\n==================test db connection");
		c = DBConnector.connect();
        assertTrue(c != null);
        DBConnector.close(c);
	}

	@After
	public void tearDown() {
		
	}

	@AfterClass
	public static void tearDownAfterClass() {
	}
}
