package service.tables;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import service.utils.CM_Database;
import service.utils.CM_Table;

abstract class TestCM_Table {
	protected Connection c;
	protected CM_Table table;
	
	public abstract CM_Table createTable();
	
	@BeforeClass
	static void setUpBeforeClass() throws Exception {
		CM_Database.getDBParam();
	}
	
	@Test
	public void test_2_insert_table() throws SQLException {
		System.out.println("\n==================test insert table " + table.getTableName());
		
		assertTrue(CM_Database.insert(table));
	}
}
