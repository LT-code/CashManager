package service.utils;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.setting.AdminSetting;
import service.setting.Setting;

public class TestCM_DataBase {
	protected Connection c;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		CM_Database.getDBParam();
	}

	@Before
	public void setUp() {
	}
	
	
		
	@Test
	public void test_0_db_connection() throws SQLException {
		System.out.println("\n==================test db connection");
		c = CM_Database.connect();
        assertTrue(c != null);
        CM_Database.close(c);
	}
	
	@Test
	public void test_1_creation_tables() throws SQLException {
		System.out.println("\n==================test creation tables");
		assertTrue(CM_Database.createDataBase());
	}
	
	//=============================================================
	// Setting table
	
	@Test
	public void test_2_insert_table_Setting() throws SQLException {
		System.out.println("\n==================test insert table setting");
		
		CM_Table setting = new Setting();
		assertTrue(setting.insert());
		
		AdminSetting setting_admin = new AdminSetting();;
		setting_admin.setConnectionDelay(7658);
		setting_admin.setAttemptsNumber(2434);
		assertTrue(setting_admin.insert());
	}
	
	@Test
	public void test_3_update_table_Setting() throws SQLException {
		System.out.println("\n==================test update table setting");
		
		AdminSetting setting_admin = new AdminSetting();
		assertTrue(setting_admin.insert());
		
		setting_admin.setConnectionDelay(875876);
		setting_admin.setAttemptsNumber(826383);
		assertTrue(setting_admin.update());

	}
	
	@Test
	public void test_4_delete_table_Setting() throws SQLException {
		System.out.println("\n==================test delete table setting");
		
		AdminSetting setting_admin = new AdminSetting();
		assertTrue(setting_admin.insert());
		
		assertTrue(setting_admin.delete());
	}

	@After
	public void tearDown() {
		
	}

	@AfterClass
	public static void tearDownAfterClass() {
	}
}
